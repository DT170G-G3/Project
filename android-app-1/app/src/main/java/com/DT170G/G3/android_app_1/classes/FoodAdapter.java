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

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<Order> orderList;

    public FoodAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }
    @NonNull
    @Override
    public FoodAdapter.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_layout, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.FoodViewHolder holder, int position) {
        Order order = orderList.get(position);

        holder.tableName.setText(order.getTableName());
        holder.notes.setText(order.getNotes());

        List<String> items = order.getItems();


        holder.items.setText(String.join("\n", items));

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
    static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView tableName, items, notes;
        CheckBox doneCheckBox;
        public FoodViewHolder(@NonNull View view) {
            super(view);

            tableName = view.findViewById(R.id.tableNameTextView);
            items = view.findViewById(R.id.orderItemsTextView);
            notes = view.findViewById(R.id.orderNotesTextView);
            doneCheckBox = view.findViewById(R.id.doneCheckBox);
        }
    }
}
