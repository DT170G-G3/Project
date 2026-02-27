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

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

        holder.itemView.setOnClickListener(v -> {
            if(!order.getStarters().isEmpty() && !order.isStartersDone()) {
                order.setStartersDone(true);
                order.setStarterDoneTime(LocalTime.now());
                notifyItemChanged(position);
                return;
            }
            if(!order.getMainCourses().isEmpty() && !order.isMainCoursesDone()) {
                order.setMainCoursesDone(true);
                order.setMainCourseDoneTime(LocalTime.now());
                notifyItemChanged(position);
                return;
            }
            if(!order.getDesserts().isEmpty() && !order.isDessertsDone()) {
                order.setDessertsDone(true);
                order.setDessertDoneTime(LocalTime.now());
                notifyItemChanged(position);
            }
        });

        if(order.getStarters().isEmpty() && order.getMainCourses().isEmpty() && order.getDesserts().isEmpty()) {
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0,0));
            return;
        }
        //String time = order.getCreatedAt().substring(11,16);
        //holder.tableTextView.setText("Bord " + order.getTableNumber() + "   " + time);
        holder.tableTextView.setText("Bord " + order.getTableNumber());

        if(order.getNotes() != null && !order.getNotes().isEmpty()) {
            holder.notesTextView.setVisibility(View.VISIBLE);
            holder.notesTextView.setText("Notering: " + order.getNotes());
        } else {
            holder.notesTextView.setVisibility(View.GONE);
        }

        holder.listOfDishesLinearLayout.removeAllViews();


        if(!order.getStarters().isEmpty()){
            addCategoryHeader(holder, "Förrätter", order.isStartersDone(), order.getStarterDoneTime());
            if(!order.isStartersDone()) {
                addFood(holder, order.getStarters());
            }
        }

        if(!order.getMainCourses().isEmpty()){
            addCategoryHeader(holder, "Varmrätter", order.isMainCoursesDone(), order.getMainCourseDoneTime());
            if(!order.isMainCoursesDone()) {
                addFood(holder, order.getMainCourses());
            }
        }

        if(!order.getDesserts().isEmpty()){
            addCategoryHeader(holder, "Efterrätter", order.isDessertsDone(), order.getDessertDoneTime());
            if(!order.isDessertsDone()) {
                addFood(holder, order.getDesserts());
            }
        }


        boolean starterDone = order.getStarters().isEmpty() || order.isStartersDone();
        boolean mainDone = order.getMainCourses().isEmpty() || order.isMainCoursesDone();
        boolean dessertDone = order.getDesserts().isEmpty() || order.isDessertsDone();

        if (starterDone && mainDone && dessertDone) {
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

    private void addCategoryHeader(OrderViewHolder holder, String title, boolean isDone, LocalTime doneTime) {
        Context context = holder.itemView.getContext();

        TextView header = new TextView(context);
        //Rubrik för kategir av mat
        header.setText(title);
        header.setTextSize(22);
        header.setTypeface(null, Typeface.BOLD);
        header.setPadding(0, 24, 0, 12);

        if(isDone) {
            header.setTextColor(context.getColor(R.color.doneColor));
            DateTimeFormatter timeDateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            String timeString = doneTime.format(timeDateTimeFormatter);
            header.setText(title + "   " + timeString);
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
        LinearLayout listOfDishesLinearLayout;
        TextView notesTextView;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tableTextView = itemView.findViewById(R.id.tableTextView);
            listOfDishesLinearLayout = itemView.findViewById(R.id.listOfDishesLinearLayout);
            notesTextView = itemView.findViewById(R.id.noteTextView);
        }
    }
}
