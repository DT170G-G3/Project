package com.DT170G.G3.android_app_1;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.DT170G.G3.android_app_1.dishes.*;
import com.DT170G.G3.android_app_1.drinks.*;
import com.DT170G.G3.android_app_1.orders.*;
import com.DT170G.G3.android_app_1.tables.*;
import com.DT170G.G3.android_app_1.waiters.*;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final DishesRepository dishesRepo = new DishesRepository();
    private final OrdersRepository ordersRepo = new OrdersRepository();
    private final TablesRepository tablesRepo = new TablesRepository();
    private final DrinksRepository drinksRepo = new DrinksRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        //------GET--------
        asyncLoadTables();
        asyncLoadDrinks();
        asyncLoadDishes();
        //------/GET--------


        //------POST ORDER--------
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);

        btn1.setOnClickListener(v -> createOrder());
        btn2.setOnClickListener(v -> createOrder2());
        btn3.setOnClickListener(v -> createOrder3());
        //-----/POST ORDER--------


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void asyncLoadDishes() {
        dishesRepo.getDishes(new DishesRepository.GetCallback() {
            @Override
            public void onSuccess(List<Dish> dishes) {
                //DishesCache.setCache(dishes);   //om vi ska använda cache
                populateDishesUI(dishes);
            }
            @Override
            public void onError(String message) {
                Log.e("DISHES", "Fel: " + message);
            }
        });
    }
    private void asyncLoadTables() {
        tablesRepo.getTables(new TablesRepository.GetCallback() {
            @Override
            public void onSuccess(List<Table> tables) {
                populateTablesUI(tables);
            }
            @Override
            public void onError(String message) {
                Log.e("TABLES", "Fel: " + message);
            }
        });
    }
    private void asyncLoadDrinks() {
        drinksRepo.getDrinks(new DrinksRepository.GetCallback() {
            @Override
            public void onSuccess(List<Drink> drinks) {
                populateDrinksUI(drinks);
            }
            @Override
            public void onError(String message) {
                Log.e("DRINKS", "Fel: " + message);
            }
        });
    }

    private void populateTablesUI(List<Table> tables) {
        // Your code here
        Log.d("TABLE", "Size: " +tables.size());
    }
    private void populateDrinksUI(List<Drink> drinks) {
        // Your code here
        Log.d("DRINK", "Size: " +drinks.size());
    }
    public void populateDishesUI(List<Dish> dishes) {
        // Your UI code
        Log.d("DISH", "Size: " +dishes.size());
    }



    // Methods used for testing below
    private void createOrder() {
        Order first = new Order();
        first.note = "Extra salt";
        first.tableId = 3;

        // First order item and its category
        Order.OrderItem item = new Order.OrderItem();
        item.dishId = 1;    // id of dish ordered?
        item.category = 2;  // appetizer, main course, dessert?


        // Second order item and its category
        Order.OrderItem item2 = new Order.OrderItem();
        item2.dishId = 1;
        item2.category = 2;

        //Third order item and its category
        Order.OrderItem item3 = new Order.OrderItem();
        item3.dishId = 2;
        item3.category = 2;

        //Creates a list of the items ordered
        first.orderedItems = java.util.Arrays.asList(item, item2, item3);

        //asynchronous post the order to the json-server ( later database)
        ordersRepo.postOrder(first, new OrdersRepository.PostCallback() {
            @Override
            public void onSuccess(Order postOrder) {
                Log.d("ORDER", "SUCCESSFULLY POSTED ORDER");
            }
            @Override
            public void onError(String message) {
                Log.e("ORDER", "FAILED TO POST: " + message);
            }
        });
    }
    private void createOrder2() {
        Order first = new Order();
        first.note = "Ingen lök";
        first.tableId = 5;

        // First order item and its category
        Order.OrderItem item = new Order.OrderItem();
        item.dishId = 3;    // id of dish ordered?
        item.category = 1;  // appetizer, main course, dessert?

        // Second order item and its category
        Order.OrderItem item2 = new Order.OrderItem();
        item2.dishId = 5;
        item2.category = 2;

        //Third order item and its category
        Order.OrderItem item3 = new Order.OrderItem();
        item3.dishId = 4;
        item3.category = 2;
        first.orderedItems = java.util.Arrays.asList(item, item2, item3);

        //asynchronous post the order to the json-server ( later database)
        ordersRepo.postOrder(first, new OrdersRepository.PostCallback() {
            @Override
            public void onSuccess(Order postOrder) {
                Log.d("ORDER", "SUCCESSFULLY POSTED ORDER");
            }
            @Override
            public void onError(String message) {
                Log.e("ORDER", "FAILED TO POST: " + message);
            }
        });
    }
    private void createOrder3() {
        Order first = new Order();
        first.note = "Extra allt";
        first.tableId = 2;

        // First order item and its category
        Order.OrderItem item = new Order.OrderItem();
        item.dishId = 2;    // id of dish ordered?
        item.category = 2;  // appetizer, main course, dessert?

        // Second order item and its category
        Order.OrderItem item2 = new Order.OrderItem();
        item2.dishId = 1;
        item2.category = 2;

        //Third order item and its category
        Order.OrderItem item3 = new Order.OrderItem();
        item3.dishId = 3;
        item3.category = 3;
        first.orderedItems = java.util.Arrays.asList(item, item2, item3);

        //asynchronous post the order to the json-server ( later database)
        ordersRepo.postOrder(first, new OrdersRepository.PostCallback() {
            @Override
            public void onSuccess(Order postOrder) {
                Log.d("ORDER", "SUCCESSFULLY POSTED ORDER");
            }
            @Override
            public void onError(String message) {
                Log.e("ORDER", "FAILED TO POST: " + message);
            }
        });
    }
}