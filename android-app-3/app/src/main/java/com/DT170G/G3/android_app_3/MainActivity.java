package com.DT170G.G3.android_app_3;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.DT170G.G3.android_app_3.classes.PagerAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DrinksRepository drinksRepo = new DrinksRepository();


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

        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        PagerAdapter adapter = new PagerAdapter(this);
        viewPager2.setAdapter(adapter);

   }


    private void asyncLoadShifts() {
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

    public void populateShiftsUI(List<Shift> dishes) {
        // Your UI code
        Log.d("SHIFT", "Size: " +dishes.size());
    }

}