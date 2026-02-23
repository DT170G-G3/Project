package com.DT170G.G3.android_app_1;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.DT170G.G3.android_app_1.classes.PagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

//Temporära imports
import com.DT170G.G3.android_app_1.drinks.DrinksRepository;
import com.DT170G.G3.android_app_1.orders.OrdersRepository;
import com.DT170G.G3.android_app_1.tables.TablesRepository;
import com.DT170G.G3.android_app_1.dishes.DishesRepository;
import com.DT170G.G3.android_app_1.dishes.Dish;
import com.DT170G.G3.android_app_1.tables.Table;
import com.DT170G.G3.android_app_1.drinks.Drink;
import com.DT170G.G3.android_app_1.orders.Order;
import java.util.List;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    // Temporärt test
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


        //------GET--------
        asyncLoadTables();
        //asyncLoadDrinks();
        asyncLoadDishes();
        //------/GET--------
    }

    public void sendOrderButtonListener(){
        //ViewPager2 viewPager = findViewById(R.id.viewPager);
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