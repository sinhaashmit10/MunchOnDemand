package com.example.fooddelivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<CartItem> cartItems;
    private DatabaseReference cartRef;

    public CartAdapter(Context context, List<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;

        // Initialize Firebase Database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        cartRef = firebaseDatabase.getReference("cartItems");
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        holder.cartFoodImage.setImageResource(cartItem.getImageResId());
        holder.cartFoodName.setText(cartItem.getName());
        holder.cartItemPrice.setText(String.format("Rs %d", cartItem.getPrice()));
        holder.quantity.setText(String.valueOf(cartItem.getQuantity()));

        holder.minusButton.setOnClickListener(v -> {
            int updatedQuantity = cartItem.getQuantity() - 1;
            if (updatedQuantity >= 1) {
                cartItem.setQuantity(updatedQuantity);
                holder.quantity.setText(String.valueOf(updatedQuantity));
                updateCartItemQuantity(cartItem, updatedQuantity);
            }
        });

        holder.plusButton.setOnClickListener(v -> {
            int updatedQuantity = cartItem.getQuantity() + 1;
            cartItem.setQuantity(updatedQuantity);
            holder.quantity.setText(String.valueOf(updatedQuantity));
            updateCartItemQuantity(cartItem, updatedQuantity);
        });

        holder.deleteButton.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                cartItems.remove(adapterPosition);
                notifyItemRemoved(adapterPosition);
                deleteCartItem(cartItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView cartFoodImage;
        TextView cartFoodName;
        TextView cartItemPrice;
        ImageButton minusButton;
        TextView quantity;
        ImageButton plusButton;
        ImageButton deleteButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartFoodImage = itemView.findViewById(R.id.cartImage);
            cartFoodName = itemView.findViewById(R.id.cartFoodName);
            cartItemPrice = itemView.findViewById(R.id.cartItemPrice);
            minusButton = itemView.findViewById(R.id.minusButton);
            quantity = itemView.findViewById(R.id.cartItemQuantity);
            plusButton = itemView.findViewById(R.id.plusButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    private void updateCartItemQuantity(CartItem cartItem, int quantity) {
        cartRef.child(cartItem.getDatabaseKey()).child("quantity").setValue(quantity);
    }

    private void deleteCartItem(CartItem cartItem) {
        cartRef.child(cartItem.getDatabaseKey()).removeValue();
    }
}
