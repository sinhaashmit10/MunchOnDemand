package com.example.fooddelivery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> implements Filterable {

    private static List<MenuItem> itemsList;
    private List<MenuItem> itemsListFull;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(MenuItem item);
    }

    public MenuAdapter(List<MenuItem> itemsList) {
        this.itemsList = itemsList;
        this.itemsListFull = new ArrayList<>(itemsList);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new MenuViewHolder(itemView, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem currentItem = itemsList.get(position);
        holder.itemImage.setImageResource(currentItem.getImageResource());
        holder.itemName.setText(currentItem.getName());
        holder.itemPrice.setText(String.valueOf(currentItem.getPrice()));
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    @Override
    public Filter getFilter() {
        return itemsFilter;
    }

    private Filter itemsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<MenuItem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(itemsListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (MenuItem item : itemsListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            itemsList.clear();
            itemsList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemImage;
        public TextView itemName;
        public TextView itemPrice;

        public MenuViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.menuImage);
            itemName = itemView.findViewById(R.id.menuFoodName);
            itemPrice = itemView.findViewById(R.id.menuPrice);

            // Set click listener for the entire item view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemsList.get(position));
                        }
                    }
                }
            });

            // Set click listener for the item image
            itemImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemsList.get(position));
                        }
                    }
                }
            });
        }
    }
}
