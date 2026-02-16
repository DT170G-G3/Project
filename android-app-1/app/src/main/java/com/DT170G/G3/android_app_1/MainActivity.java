package com.DT170G.G3.android_app_1;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final DishesRepository dishesRepo = new DishesRepository();
    private final WaitersRepository  waitersRepo = new WaitersRepository();
    private final OrdersRepository ordersRepo = new OrdersRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        //----Parsing--------

        //Creates the text fields to display API fetch
        TextView tvId = findViewById(R.id.tvDishId);
        TextView tvName = findViewById(R.id.tvDishName);
        TextView tvPrice = findViewById(R.id.tvDishPrice);


        dishesRepo.getDishes(new DishesRepository.DishesCallback() {
            @Override
            public void onSuccess(List<Dish> dishes) {
                Dish d = dishes.get(0);
                tvId.setText("id: " + d.id);
                tvName.setText("Beställd rätt: " + d.name);
                tvPrice.setText("Totalt pris: " + d.price);
            }
            @Override
            public void onError(String message) {
                Log.e("DISHES", "Fel: " + message);
            }
        });

        //-------------POST-----------------------
        OrdersRepository repo = new OrdersRepository();

        Order o = new Order();
        o.note = "Testorder";
        o.tableId = 3;

        Order.OrderItem item1 = new Order.OrderItem();
        item1.dishId = 1;
        item1.category = 2; // main

        Order.OrderItem item2 = new Order.OrderItem();
        item2.dishId = 2;
        item2.category = 1; // appetizer

        o.orderedItems = java.util.Arrays.asList(item1, item2);

        repo.postOrder(o, new OrdersRepository.OrderCallback() {
            @Override
            public void onSuccess(Order postedOrder) {
                Log.d("ORDER", "POST OK!");
                // Om du har id i response-klassen kan du logga den här.
            }

            @Override
            public void onError(String message) {
                Log.e("ORDER", "POST FAIL: " + message);
            }
        });
        //-------------/POST----------------------






        /*
        waitersRepo.getWaiters(new WaitersRepository.WaitersCallback() {
            @Override
            public void onSuccess(List<Waiter> waiters) {
                Waiter w = waiters.get(0);
                tvId.setText("id: " + w.id);
                tvName.setText("Servitörens namn: " + w.name);
                tvPrice.setText("Schema: " + w.schema);
            }
            @Override
            public void onError(String message) {
                Log.e("WAITERS", "Fel: " + message);
            }
        });
        */



        //-------------------







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}