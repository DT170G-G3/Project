package se.miun.g3.android_app_2;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShowOrdersAdapter extends RecyclerView.Adapter<ShowOrdersAdapter.OrderViewHolder> {
    private List<ShowOrders> orderList;

    public ShowOrdersAdapter(List<ShowOrders> orderList) {
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
        ShowOrders order = orderList.get(position);
        //String time = order.getCreatedAt().substring(11,16);
        holder.tableTextView.setText("Bord " + order.getTableNumber());
        //holder.timeTextView.setText(time);
        holder.listOfDishesLinearLayout.removeAllViews();

        holder.itemView.setOnClickListener(v -> {
            if(!order.isStartersDone()) {
                order.setStartersDone(true);
            } else if (!order.isMainCoursesDone()) {
                order.setMainCoursesDone(true);
            } else if (!order.isDessertsDone()) {
                order.setDessertsDone(true);
            }
            notifyItemChanged(position);
        });

        addCategoryHeader(holder, "Förrätter", order.isStartersDone());
        if(!order.isStartersDone()) {
            addFood(holder, order.getMainCourses());
        }

        addCategoryHeader(holder, "Varmrätter", order.isMainCoursesDone());
        if(!order.isMainCoursesDone()) {
            addFood(holder, order.getDesserts());
        }

        addCategoryHeader(holder, "Efterrätter", order.isDessertsDone());
        if(!order.isDessertsDone()) {
            addFood(holder, order.getDesserts());
        }

        if (order.isStartersDone() && order.isMainCoursesDone() && order.isDessertsDone()) {
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }
    }

    private void addFood(OrderViewHolder holder, List<String> items) {
        Context context = holder.itemView.getContext();

        for(String item : items) {
            TextView textView = new TextView(context);
            textView.setText("• " + item);
            textView.setTextSize(20);
            textView.setPadding(0,12,0,12);
            holder.listOfDishesLinearLayout.addView(textView);
        }
        View space = new View(context);
        space.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 24));
        holder.listOfDishesLinearLayout.addView(space);
    }

    private void addCategoryHeader(OrderViewHolder holder, String title, boolean isDone) {
        Context context = holder.itemView.getContext();

        TextView header = new TextView(context);
        //Rubrik för kategir av mat
        TextView category = new TextView(context);
        header.setText(title);
        header.setTextSize(22);
        header.setTypeface(null, Typeface.BOLD);
        header.setPadding(0, 24, 0, 12);

        if(isDone) {
            header.setTextColor(context.getColor(R.color.buttonColor));
        }

        holder.listOfDishesLinearLayout.addView(header);

        //Avdelare i form av streck för struktur
        View divider = new View(context);
        divider.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2));
        divider.setBackgroundColor(context.getColor(R.color.headerColor));
        holder.listOfDishesLinearLayout.addView(divider);
    }


    @Override
    public int getItemCount () {
        return orderList.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView tableTextView;
        TextView timeTextView;
        LinearLayout listOfDishesLinearLayout;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tableTextView = itemView.findViewById(R.id.tableTextView);
            //timeTextView = itemView.findViewById(R.id.timeTextView);
            listOfDishesLinearLayout = itemView.findViewById(R.id.listOfDishesLinearLayout);
        }
    }
}
