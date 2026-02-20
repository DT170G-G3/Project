package com.example.myapplication;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

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

        if(!order.isStartersDone()) {
            addFood(holder, "Förrätter", order.getStarters(), true, () -> {
                order.setStartersDone(true);
                notifyItemChanged(position);
            });
        }

        if(!order.isMainCoursesDone()) {
            addFood(holder, "Varmrätter", order.getMainCourses(), order.isStartersDone(), () -> {
                order.setMainCoursesDone(true);
                notifyItemChanged(position);
            });
        }

        if(!order.isDessertsDone()) {
            addFood(holder, "Efterrätter", order.getDesserts(), order.isStartersDone() && order.isMainCoursesDone(), () -> {
                order.setDessertsDone(true);
                notifyItemChanged(position);
            });
        }

        if (order.isStartersDone() && order.isMainCoursesDone() && order.isDessertsDone()) {
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }
    }

    private void addFood (OrderViewHolder holder, String title, List< String > orderList, boolean showButton, Runnable onClick){
        Context context = holder.itemView.getContext();

        LinearLayout headerAndButtonContainer = new LinearLayout(context);
        headerAndButtonContainer.setOrientation(LinearLayout.HORIZONTAL);
        headerAndButtonContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        headerAndButtonContainer.setGravity((Gravity.CENTER_VERTICAL));

        //Rubrik för kategir av mat
        TextView header = new TextView(context);
        header.setText(title);
        header.setTextSize(22);
        header.setTypeface(null, Typeface.BOLD);
        header.setPadding(0, 24, 0, 12);
        header.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        headerAndButtonContainer.addView(header);

        if(showButton) {
            TextView doneButton = new TextView(context);
            doneButton.setText("KLAR");
            doneButton.setTextSize(14);
            doneButton.setPadding(20,10,20,10);
            doneButton.setBackground(context.getDrawable(R.drawable.done_button));
            doneButton.setTextColor(context.getColor(R.color.black));
            doneButton.setOnClickListener(v -> onClick.run());
            doneButton.setElevation(4f);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(24,0,0,0);
            doneButton.setLayoutParams(params);
            headerAndButtonContainer.addView(doneButton);
        }
        holder.listOfDishesLinearLayout.addView(headerAndButtonContainer);

        //Avdelare i form av streck för struktur
        View divider = new View(context);
        divider.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2));
        divider.setBackgroundColor(context.getColor(R.color.headerColor));
        holder.listOfDishesLinearLayout.addView(divider);

        for (String order : orderList) {
            TextView textView = new TextView( holder.itemView.getContext());
            textView.setText("• " + order);
            textView.setTextSize(20);
            textView.setPadding(0, 12, 0, 12);
            holder.listOfDishesLinearLayout.addView(textView);
        }
        View space = new View(context);
        space.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 24));
        holder.listOfDishesLinearLayout.addView(space);
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
