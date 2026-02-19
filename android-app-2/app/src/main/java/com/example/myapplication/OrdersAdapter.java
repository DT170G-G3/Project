package com.example.myapplication;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {
    private List<Orders> orderList;

    public OrdersAdapter(List<Orders> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Orders order = orderList.get(position);
        holder.tableTextView.setText("Bord " + order.getTableNumber());

        holder.listOfDishesLinearLayout.removeAllViews();

        if (!order.isStartersDone()) {
            addFood(holder, "Förrätt:", order.getStarters());
            positionOfButton(holder, () -> {
                order.setStartersDone(true);
                notifyItemChanged(position);
            });
            if(!order.isMainCoursesDone()) {
                addFood(holder, "Varmrätt", order.getMainCourses());
            }
            if(!order.isDessertsDone()) {
                addFood(holder, "Efterrätt", order.getDesserts());
            }
            return;
        }

        if (!order.isMainCoursesDone()) {
            addFood(holder, "Varmrätt:", order.getMainCourses());
            positionOfButton(holder, () -> {
                order.setMainCoursesDone(true);
                notifyItemChanged(position);
            });
            if(!order.isDessertsDone()) {
                addFood(holder, "Efterrätt", order.getDesserts());
            }
            return;
        }

        if (!order.isDessertsDone()) {
            addFood(holder, "Efterrätt:", order.getDesserts());
            positionOfButton(holder, () -> {
                order.setDessertsDone(true);
                notifyItemChanged(position);
            });
        }
        if(order.isStartersDone() && order.isMainCoursesDone() && order.isDessertsDone()) {
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0,0));
            return;
        }
    }

    private void positionOfButton(OrderViewHolder holder, Runnable onClick) {
        Button button = new Button(holder.itemView.getContext());
        button.setText("KLAR");
        button.setTextSize(20);
        button.setPadding(12,12,12,12);
        button.setBackgroundTintList(holder.itemView.getContext().getColorStateList(R.color.green));
        button.setTextColor(holder.itemView.getContext().getColor(R.color.white));
        button.setBackground(holder.itemView.getContext().getDrawable(R.drawable.round_button));
        button.setOnClickListener(v -> onClick.run());
        holder.listOfDishesLinearLayout.addView(button);
    }

    private void addFood (OrderViewHolder holder, String title, List < String > orderList){
        TextView header = new TextView(holder.itemView.getContext());
        header.setText(title);
        header.setTextSize(18);
        header.setTypeface(null, Typeface.BOLD);
        header.setPadding(0, 16, 0, 8);
        holder.listOfDishesLinearLayout.addView(header);

        for (String order : orderList) {
            TextView textView = new TextView( holder.itemView.getContext());
            textView.setText("• " + order);
            textView.setTextSize(20);
            textView.setPadding(0, 8, 0, 8);
            holder.listOfDishesLinearLayout.addView(textView);
        }

    }

    @Override
    public int getItemCount () {
        return orderList.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tableTextView;
        LinearLayout listOfDishesLinearLayout;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tableTextView = itemView.findViewById(R.id.tableTextView);
            listOfDishesLinearLayout = itemView.findViewById(R.id.listOfDishesLinearLayout);
        }
    }
}
