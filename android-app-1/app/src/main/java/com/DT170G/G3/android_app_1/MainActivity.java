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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
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


}