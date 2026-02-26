package com.DT170G.G3.android_app_1;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static androidx.core.content.ContentProviderCompat.requireContext;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.DT170G.G3.android_app_1.classes.PagerAdapter;
import com.DT170G.G3.android_app_1.dishes.Category;
import com.DT170G.G3.android_app_1.dishes.Dish;
import com.DT170G.G3.android_app_1.dishes.DishesRepository;
import com.DT170G.G3.android_app_1.drinks.Drink;
import com.DT170G.G3.android_app_1.drinks.DrinksRepository;
import com.DT170G.G3.android_app_1.orders.Order;
import com.DT170G.G3.android_app_1.orders.OrdersRepository;
import com.DT170G.G3.android_app_1.orders.Sitting;
import com.DT170G.G3.android_app_1.tables.Table;
import com.DT170G.G3.android_app_1.tables.TablesRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DishesRepository dishesRepo = new DishesRepository();
    DrinksRepository drinksRepo = new DrinksRepository();
    TablesRepository tablesRepo = new TablesRepository();
    OrdersRepository ordersRepo = new OrdersRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        changeTabListener();
        sendOrderButtonListener();


        //------POST--------
        exampleCreateOrder();

        //-----/POST--------

        //-------GET--------
        //asyncLoadTables();
        asyncLoadOrders();
        //asyncLoadDishes();
        //asyncLoadDrinks();
        //------/GET--------
    }

    public void sendOrderButtonListener(){
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        BottomNavigationView bottomNavigationMenu = findViewById(R.id.bottomNavigationMenu);

        //Resest form - ändra så att den skickar till databasen oxå
        Button sendButton = findViewById(R.id.sendOrderButton);


        sendButton.setOnClickListener(buttonClicked -> {
            int selectedMenuId = bottomNavigationMenu.getSelectedItemId();


            //TODO ändra så att beställningen skickas till databasen den man står på plus de föregående sidorna (om något är ifyllt).

            if (selectedMenuId == R.id.drinkTab){
                Snackbar.make(findViewById(R.id.main), "Beställningen skickas DRINK", Snackbar.LENGTH_SHORT).setAnchorView(sendButton).show();
            }
            else if (selectedMenuId == R.id.starterTab){
                Snackbar.make(findViewById(R.id.main), "Beställningen skickas FÖRRÄTT", Snackbar.LENGTH_SHORT).setAnchorView(sendButton).show();
            }
            else if (selectedMenuId == R.id.mainCourseTab){
                Snackbar.make(findViewById(R.id.main), "Beställningen skickas VARMRÄTT", Snackbar.LENGTH_SHORT).setAnchorView(sendButton).show();
            }
            else{
                Snackbar.make(findViewById(R.id.main), "Beställningen skickas DESSERT", Snackbar.LENGTH_SHORT).setAnchorView(sendButton).show();
            }
        });

    }

    /**
     * Function that listens to swipes or selection in navbar
     * and changes page
     */
    public void changeTabListener(){
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        BottomNavigationView bottomNavigationMenu = findViewById(R.id.bottomNavigationMenu);
        viewPager.setAdapter(new PagerAdapter(this));

        bottomNavigationMenu.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            Button sendOrderButton = findViewById(R.id.sendOrderButton);

            //Döljer eller visar Skicka beställningsknappen beroende på sida som visas
            if(id == R.id.tableTab && sendOrderButton.getVisibility() == VISIBLE){
                sendOrderButton.setVisibility(INVISIBLE);
                TextView tv1 = findViewById(R.id.tableBorderTop);
                TextView tv2 = findViewById(R.id.tableHeader);
                TextView tv3 = findViewById(R.id.tableBorderBottom);
                tv1.setVisibility(INVISIBLE);
                tv2.setVisibility(INVISIBLE);
                tv3.setVisibility(INVISIBLE);

            } else if(id != R.id.tableTab && sendOrderButton.getVisibility() == INVISIBLE){
                sendOrderButton.setVisibility(VISIBLE);

                TextView tv1 = findViewById(R.id.tableBorderTop);
                TextView tv2 = findViewById(R.id.tableHeader);
                TextView tv3 = findViewById(R.id.tableBorderBottom);
                tv1.setVisibility(VISIBLE);
                tv2.setVisibility(VISIBLE);
                tv3.setVisibility(VISIBLE);
            }

            if (id == R.id.tableTab) {
                viewPager.setCurrentItem(0);
            }
            else if (id == R.id.drinkTab){
                viewPager.setCurrentItem(1);
            }
            else if (id == R.id.starterTab){
                viewPager.setCurrentItem(2);
            }
            else if (id == R.id.mainCourseTab){
                viewPager.setCurrentItem(3);
            }
            else if (id == R.id.dessertTab){
                viewPager.setCurrentItem(4);
            }
            return true;
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                                                   @Override
                                                   public void onPageSelected(int position){
                                                       switch (position) {
                                                           case 0:
                                                               bottomNavigationMenu.setSelectedItemId(R.id.tableTab);
                                                               break;
                                                           case 1:
                                                               bottomNavigationMenu.setSelectedItemId(R.id.drinkTab);
                                                               break;
                                                           case 2:
                                                               bottomNavigationMenu.setSelectedItemId(R.id.starterTab);
                                                               break;
                                                           case 3:
                                                               bottomNavigationMenu.setSelectedItemId(R.id.mainCourseTab);
                                                               break;
                                                           case 4:
                                                               bottomNavigationMenu.setSelectedItemId(R.id.dessertTab);
                                                               break;
                                                       }
                                                   }
                                               }
        );

    }



    public void exampleCreateOrder() {
        // EXAMPLE how to POST an Order
        // Only ID is required for drinks, sitting and dishes

        //Create new order, initialize new Lists
        Order order = new Order();
        order.dishes = new ArrayList<>();
        order.drinks = new ArrayList<>();

        Dish dish = new Dish();
        dish.id = 3;

        Drink drink = new Drink();
        drink.id = 1;

        Sitting sit = new Sitting();
        sit.id = 1;

        order.dishes.add(dish);
        order.drinks.add(drink);
        order.sitting = sit;

        asyncCreateOrder(order);
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
    private void asyncLoadOrders() {
        ordersRepo.getOrders(new OrdersRepository.GetCallback() {
            @Override
            public void onSuccess(List<Order> orders) {
                populateOrdersUI(orders);
            }
            @Override
            public void onError(String message) {
                Log.e("ORDERS", "Fel: " + message);
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
                Log.e("ORDERS", "Fel: " + message);
            }
        });

    }
    private void asyncCreateOrder(Order order) {
        //asynchronous post the order to the database
        ordersRepo.postOrder(order, new OrdersRepository.PostCallback() {
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

    private void populateTablesUI(List<Table> tables) {
        // Your code here
        Log.d("TABLE", "Size: " +tables.size());
    }
    private void populateOrdersUI(List<Order> orders) {
        // EXAMPLE how to get Order object
        Log.d("ORDER", "TOTAL ORDERS: " +orders.size());
        for (int i = 68; i < 69; i++) {
            Order order = orders.get(i);
            Sitting sitting = order.sitting;
            Table table = sitting.restaurantTable;
            Log.d("ORDER ID", "" + order.id);
            Log.d("ORDER: TABLE NUM", "" + table.tableNum);

            for (Dish dish : order.dishes) {
                Log.d("DISH", "" + dish.name);
                Log.d("DISH CATEGORY", "" + dish.category.name);
            }
            for (Drink drink : order.drinks) {
                Log.d("DRINK", "" + drink.name);
            }
        }

    }
    public void populateDishesUI(List<Dish> dishes) {
        // Your UI code
        Log.d("DISH", "Size: " +dishes.size());
    }
    public void populateDrinksUI(List<Drink> drinks) {
        // YOur UI code
        Log.d("DRINK", "Size: " + drinks.size());
    }
}

