package se.miun.g3.android_app_2;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import java.util.List;


import se.miun.g3.android_app_2.dishes.Dish;
import se.miun.g3.android_app_2.dishes.DishesRepository;
import se.miun.g3.android_app_2.orders.Order;
import se.miun.g3.android_app_2.orders.OrdersRepository;

public class MainActivity extends AppCompatActivity {
    DishesRepository dishesRepo = new DishesRepository();
    OrdersRepository ordersRepo = new OrdersRepository();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView orderRecyclerView = findViewById(R.id.orderRecyclerView);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        asyncLoadDishes();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private ShowOrders orderToShowOrders(Order backendOrder) {
        List<String> starters = new ArrayList<>();
        List<String> mains = new ArrayList<>();
        List<String> desserts = new ArrayList<>();

        int tableNum = 0;
        for (Dish d : backendOrder.dishes) {
            if (d.category == null) {
                continue;
            }

            switch (d.category.id) {
                case 1:
                    starters.add(d.name);
                    break;
                case 2:
                    mains.add(d.name);
                    break;
                case 3:
                    desserts.add(d.name);
                    break;
                default:
                    Log.w("ORDER", "Okänd kategori: " + d.category.id);
            }
        }
        tableNum = backendOrder.sitting.restaurantTable.tableNum;
        return new ShowOrders(tableNum, starters, mains, desserts);
    }


    /**
     * Hämtar alla rätter (dishes) från backend och skickar resultatet till UI.
     * <p>
     * Vad den gör:
     * - Startar ett nätverksanrop via DishesRepository.
     * - När datan är klar: onSuccess() körs och skickar listan vidare till populateDishesUI(dishes).
     * - Om något går fel: onError() körs och felet loggas.
     * <p>
     * Viktigt:
     * - Den här metoden ger INTE tillbaka en lista direkt.
     * - Anropet tar tid (pga nätverk), så listan kommer först i onSuccess(...).
     */
    private void asyncLoadDishes() {
        dishesRepo.getDishes(new DishesRepository.GetCallback() {
            @Override
            public void onSuccess(List<Dish> dishes) {
                Log.d("DISH", "Size: " + dishes.size());
                asyncLoadOrders();
            }
            @Override
            public void onError(String message) {
                Log.e("DISHES", "Fel: " + message);
            }
        });
    }
    private void asyncLoadOrders() {
        ordersRepo.getOrders(new OrdersRepository.GetCallback() {
            @Override
            public void onSuccess(List<Order> backendOrders) {
                Log.d("ORDERS", "API gav " + backendOrders.size() + " orders");
                List<ShowOrders> uiOrders = new ArrayList<>();
                for (Order o : backendOrders) {
                    uiOrders.add(orderToShowOrders(o));
                }
                RecyclerView orderRecyclerView = findViewById(R.id.orderRecyclerView);
                orderRecyclerView.setAdapter(new ShowOrdersAdapter(uiOrders));
            }

            @Override
            public void onError(String message) {
                Log.e("ORDERS", "Fel: " + message);
            }
        });
    }


    /**
     * Tar emot listan med rätter och uppdaterar UI så köket kan se dem.
     */
    public void populateDishesUI(List<Dish> dishes) {
        // Your UI code
        Log.d("DISH", "Size: " +dishes.size());
    }


}