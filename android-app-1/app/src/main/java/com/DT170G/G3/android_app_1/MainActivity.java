package com.DT170G.G3.android_app_1;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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
import com.DT170G.G3.android_app_1.fragments.TableFragment;
import com.DT170G.G3.android_app_1.orders.Order;
import com.DT170G.G3.android_app_1.tables.Table;
import com.DT170G.G3.android_app_1.orders.OrdersRepository;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    OrdersRepository ordersRepo = new OrdersRepository();
    DrinkFragment drinkFragment = new DrinkFragment();
    StarterFragment starterFragment = new StarterFragment();
    MainCourseFragment mainCourseFragment = new MainCourseFragment();
    DessertFragment dessertFragment = new DessertFragment();
    TableFragment tableFragment = new TableFragment();


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
        notesSwitchListener();
    }

    /**
     * Resets all selected items
     *
     */
    public void resetOrderButtonListener(){
        //Resest form - ändra så att den skickar till databasen oxå
        TextView resetButton = findViewById(R.id.resetOrderButton);


        resetButton.setOnClickListener(buttonClicked -> {
            resetItemCountersAndAllOrderedItems();

            Snackbar.make(findViewById(R.id.main), "Beställningen rensad", Snackbar.LENGTH_SHORT).setAnchorView(resetButton).show();

            viewPager2.setCurrentItem(0);
        });

    }


    /**
     * Function that on clicks to Skicka beställning sends inputed items to kitchen
     *
     */
    public void sendOrderButtonListener(){
        //Resest form - ändra så att den skickar till databasen oxå
        TextView sendButton = findViewById(R.id.sendOrderButton);

        sendButton.setOnClickListener(buttonClicked -> {
            createOrder();
            viewPager2.setCurrentItem(0);
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

        Switch noteSwitch = findViewById(R.id.notesSwitch);
        noteSwitch.setVisibility(INVISIBLE);

        EditText notes = findViewById(R.id.orderNotes);
        notes.setVisibility(INVISIBLE);
        notes.setText("");

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

        Switch noteSwitch = findViewById(R.id.notesSwitch);
        noteSwitch.setVisibility(VISIBLE);

        if(noteSwitch.isChecked()){
            noteSwitch.setChecked(false);
        }

    }

    /**
     * Funktion som resettar alla counters för drink, förrätt, varmrätt och efterrätt
     * @return void
     */
    private void resetItemCountersAndAllOrderedItems(){
        drinkFragment = pagerAdapter.getDrinkFragment();
        drinkFragment.resetDrinkCounter();
        drinkFragment.clearAllOrderedDrinks();

        starterFragment = pagerAdapter.getStarterFragment();
        starterFragment.resetStarterCounter();
        starterFragment.clearAllOrderedStarters();

        mainCourseFragment = pagerAdapter.getMainCourseFragment();
        mainCourseFragment.resetMainCounter();
        mainCourseFragment.clearAllOrderedMainCourses();

        dessertFragment = pagerAdapter.getDessertFragment();
        dessertFragment.resetDessertCounter();
        dessertFragment.clearAllOrderedDesserts();

        EditText notes = findViewById(R.id.orderNotes);
        notes.setText("");
    }


    /**
     * Funktion som skapar en order och lägger till den i databasen
     * Enbart id krävs för bord, drink, förrätter, varmrätter och desserter
     *
     *
     * @return void
     */
    public void createOrder() {
        //Kollar om Table är valt annars be att välja bord
        tableFragment = pagerAdapter.getTableFragment();
        int tableNumber = tableFragment.getSelectedTable();
        TextView sendButton = findViewById(R.id.sendOrderButton);
        if (tableNumber == 0) {
            Snackbar snackbar = Snackbar.make(findViewById(R.id.main), "Vänligen välj ett bord", Snackbar.LENGTH_SHORT);
            snackbar.setAnchorView(sendButton);
            snackbar.getView().setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.redSnackbar)));
            snackbar.show();
            return;
        }


        Order order = new Order();
        order.dishes = new ArrayList<>();
        order.drinks = new ArrayList<>();

        //DRINKS
        drinkFragment = pagerAdapter.getDrinkFragment();
        List<Integer> allOrderedDrinks = drinkFragment.getAllOrderedDrinks();
        if (!allOrderedDrinks.isEmpty()) {
            for (int drinkId : allOrderedDrinks) {
                Drink orderedDrink = new Drink();
                orderedDrink.id = drinkId;
                order.drinks.add(orderedDrink);
            }
        }

        //STARTERS
        starterFragment = pagerAdapter.getStarterFragment();
        List<Integer> allOrderedStarters = starterFragment.getAllOrderedStarters();
        if (!allOrderedStarters.isEmpty()) {
            for (int starterId : allOrderedStarters) {
                Dish orderedStarter = new Dish();
                orderedStarter.id = starterId;
                order.dishes.add(orderedStarter);
            }
        }

        //MAIN COURSES
        mainCourseFragment = pagerAdapter.getMainCourseFragment();
        List<Integer> allOrderedMainCourses = mainCourseFragment.getAllOrderedMainCourses();
        if (!allOrderedMainCourses.isEmpty()) {
            for (int mainCourseId : allOrderedMainCourses) {
                Dish orderedMainCourse = new Dish();
                orderedMainCourse.id = mainCourseId;
                order.dishes.add(orderedMainCourse);
            }
        }

        //DESSERTS
        dessertFragment = pagerAdapter.getDessertFragment();
        List<Integer> allOrderedDesserts = dessertFragment.getAllOrderedDesserts();
        if (!allOrderedDesserts.isEmpty()) {
            for (int dessertId : allOrderedDesserts) {
                Dish orderedDessert = new Dish();
                orderedDessert.id = dessertId;
                order.dishes.add(orderedDessert);
            }
        }

        if (allOrderedDrinks.isEmpty() && allOrderedStarters.isEmpty() && allOrderedMainCourses.isEmpty() && allOrderedDesserts.isEmpty()){
            Snackbar.make(findViewById(R.id.main), "Tom order", Snackbar.LENGTH_SHORT).setAnchorView(sendButton).show();
            return;
        }

        //TABLE
        Table table = new Table();
        table.tableNum = tableNumber;
        table.id = tableNumber;
        order.table = table;

        //NOTES
        String orderNote = "";

        EditText notes = findViewById(R.id.orderNotes);
        String notesString = notes.getText().toString();
        if(!notesString.matches("")){
            orderNote = orderNote + notesString;
        }

        order.note = orderNote;

        Log.d("NOTES",orderNote);
        resetItemCountersAndAllOrderedItems();
        asyncCreateOrder(order);
        Snackbar.make(findViewById(R.id.main), "Beställningen är skickad", Snackbar.LENGTH_SHORT).setAnchorView(sendButton).show();
    }

    private void notesSwitchListener(){
        Switch orderSwitch = findViewById(R.id.notesSwitch);
        EditText notes = findViewById(R.id.orderNotes);

        orderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    notes.setVisibility(VISIBLE);
                } else{
                    notes.setVisibility(GONE);
                }
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
}