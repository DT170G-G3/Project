package com.DT170G.G3.android_app_1.classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.DT170G.G3.android_app_1.R;

import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder> {

    private List<Order> drinkList;

    public DrinkAdapter(List<Order> drinkList) {
        this.drinkList = drinkList;
    }
    @NonNull
    @Override
    public DrinkAdapter.DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_layout, parent, false);
        return new DrinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkAdapter.DrinkViewHolder holder, int position) {
        Order order = drinkList.get(position);

        holder.tableName.setText(order.getTableName());
        holder.notes.setText(order.getNotes());

        List<String> items = order.getItems();


        holder.items.setText(String.join("\n", items));

    }

    @Override
    public int getItemCount() {
        return drinkList.size();
    }
    static class DrinkViewHolder extends RecyclerView.ViewHolder {
        TextView tableName, items, notes;
        CheckBox doneCheckBox;
        public DrinkViewHolder(@NonNull View view) {
            super(view);

            tableName = view.findViewById(R.id.tableNameTextView);
            items = view.findViewById(R.id.orderItemsTextView);
            notes = view.findViewById(R.id.orderNotesTextView);
            doneCheckBox = view.findViewById(R.id.doneCheckBox);
        }
    }
}
