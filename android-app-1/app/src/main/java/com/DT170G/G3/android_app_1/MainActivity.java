package com.DT170G.G3.android_app_1;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.DT170G.G3.android_app_1.classes.PagerAdapter;

import com.DT170G.G3.android_app_1.dishes.Dish;
import com.DT170G.G3.android_app_1.drinks.Drink;
import com.DT170G.G3.android_app_1.fragments.DessertFragment;
import com.DT170G.G3.android_app_1.fragments.DrinkFragment;
import com.DT170G.G3.android_app_1.fragments.MainCourseFragment;
import com.DT170G.G3.android_app_1.fragments.StarterFragment;
import com.DT170G.G3.android_app_1.orders.Order;
import com.DT170G.G3.android_app_1.tables.Table;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

//TODO Temporära imports
import com.DT170G.G3.android_app_1.orders.OrdersRepository;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    // TODO Temporärt test
    OrdersRepository ordersRepo = new OrdersRepository();
    DrinkFragment drinkFragment = new DrinkFragment();
    StarterFragment starterFragment = new StarterFragment();
    MainCourseFragment mainCourseFragment = new MainCourseFragment();
    DessertFragment dessertFragment = new DessertFragment();


    private PagerAdapter pagerAdapter;
    private ViewPager2 viewPager2;

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

        viewPager2 = findViewById(R.id.viewPager);
        pagerAdapter = new PagerAdapter(this);
        viewPager2.setAdapter(pagerAdapter);

        changeTabListener();
        sendOrderButtonListener();
        resetOrderButtonListener();


        //------POST--------
        exampleCreateOrder();

        //------GET---------
        //exampleGetDishes();

    }

    /**
     * Resets all selected items
     *
     */
    public void resetOrderButtonListener(){
        //Resest form - ändra så att den skickar till databasen oxå
        TextView resetButton = findViewById(R.id.resetOrderButton);


        resetButton.setOnClickListener(buttonClicked -> {
            resetItemCounters();

            Snackbar.make(findViewById(R.id.main), "Beställningen rensad", Snackbar.LENGTH_SHORT).setAnchorView(resetButton).show();

            //Ändrar vy till val av bord
            viewPager2.setCurrentItem(0);
        });

    }


    /**
     * Function that on clicks to Skicka beställning sends inputed items to kitchen
     *
     */
    public void sendOrderButtonListener(){
        BottomNavigationView bottomNavigationMenu = findViewById(R.id.bottomNavigationMenu);

        //Resest form - ändra så att den skickar till databasen oxå
        TextView sendButton = findViewById(R.id.sendOrderButton);

        sendButton.setOnClickListener(buttonClicked -> {
            exampleCreateOrder();

            Snackbar.make(findViewById(R.id.main), "Beställningen skickas", Snackbar.LENGTH_SHORT).setAnchorView(sendButton).show();

        });

    }

    /**
     * Function that listens to swipes or selection in navbar
     * and changes page
     */
    public void changeTabListener() {
        BottomNavigationView bottomNavigationMenu = findViewById(R.id.bottomNavigationMenu);
        bottomNavigationMenu.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            TextView sendOrderButton = findViewById(R.id.sendOrderButton);

            //Döljer eller visar Skicka beställningsknappen beroende på sida som visas
            if (id == R.id.tableTab && sendOrderButton.getVisibility() == VISIBLE) {
                hideManagebles();

            } else if (id != R.id.tableTab && sendOrderButton.getVisibility() == INVISIBLE) {
                showManagebles();
            }

            if (id == R.id.tableTab) {
                viewPager2.setCurrentItem(0);
            } else if (id == R.id.drinkTab) {
                viewPager2.setCurrentItem(1);
            } else if (id == R.id.starterTab) {
                viewPager2.setCurrentItem(2);
            } else if (id == R.id.mainCourseTab) {
                viewPager2.setCurrentItem(3);
            } else if (id == R.id.dessertTab) {
                viewPager2.setCurrentItem(4);
            }
            return true;
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                                                   @Override
                                                   public void onPageSelected(int position) {
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

    private void hideManagebles(){
        TextView sendOrderButton = findViewById(R.id.sendOrderButton);
        TextView resetOrderButton = findViewById(R.id.resetOrderButton);
        sendOrderButton.setVisibility(INVISIBLE);
        resetOrderButton.setVisibility(INVISIBLE);

        TextView tv1 = findViewById(R.id.tableBorderTop);
        TextView tv2 = findViewById(R.id.selectedTable);
        TextView tv3 = findViewById(R.id.tableBorderBottom);
        tv1.setVisibility(INVISIBLE);
        tv2.setVisibility(INVISIBLE);
        tv3.setVisibility(INVISIBLE);
    }

    private void showManagebles(){
        TextView sendOrderButton = findViewById(R.id.sendOrderButton);
        TextView resetOrderButton = findViewById(R.id.resetOrderButton);
        sendOrderButton.setVisibility(VISIBLE);
        resetOrderButton.setVisibility(VISIBLE);

        TextView tv1 = findViewById(R.id.tableBorderTop);
        TextView tv2 = findViewById(R.id.selectedTable);
        TextView tv3 = findViewById(R.id.tableBorderBottom);
        tv1.setVisibility(VISIBLE);
        tv2.setVisibility(VISIBLE);
        tv3.setVisibility(VISIBLE);
    }

    private void resetItemCounters(){
        drinkFragment = pagerAdapter.getDrinkFragment();
        drinkFragment.resetDrinkCounter();

        starterFragment = pagerAdapter.getStarterFragment();
        starterFragment.resetStarterCounter();

        mainCourseFragment = pagerAdapter.getMainCourseFragment();
        mainCourseFragment.resetMainCounter();

        dessertFragment = pagerAdapter.getDessertFragment();
        dessertFragment.resetDessertCounter();
    }

    public void exampleCreateOrder() {
        // EXAMPLE how to POST an Order
        // Only ID is required for drinks, sitting and dishes

        //Create new order, initialize new Lists
        Order order = new Order();
        order.dishes = new ArrayList<>();
        order.drinks = new ArrayList<>();

        Dish dish = new Dish();
        dish.id = 2;

        Drink drink = new Drink();
        drink.id = 2;

        Table table = new Table();
        table.tableNum = 1;
        table.id = 1;

        order.dishes.add(dish);
        order.drinks.add(drink);
        order.table = table;

        asyncCreateOrder(order);
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
}