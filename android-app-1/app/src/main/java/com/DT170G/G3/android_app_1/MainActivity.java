package com.DT170G.G3.android_app_1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.DT170G.G3.android_app_1.classes.PagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);


            /*
             *    Below is simply a test to see if the database can be reached!
             *   THis should not be in the MainActivity? And might not even look
             *   like this later. But we should use Retrofit. //J
             */

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080/TrialForProject-1.0-SNAPSHOT/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            MessageApi api = retrofit.create(MessageApi.class);

            api.getMessages().enqueue(new Callback<List<Message>>() {
                @Override
                public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                    if(response.isSuccessful()){
                        List<Message> messages = response.body();
                        for (Message m: messages){
                            Log.d("API", m.id + ": " + m.text);
                        }
                    }
                    else {
                        Log.e("API", "Response error: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<Message>> call, Throwable t) {
                    Log.e("API", "Network error", t);
                }
            });
            System.out.println("Just a checkstatement.");

            return insets;
        });


        ViewPager2 viewPager = findViewById(R.id.viewPager);
        BottomNavigationView bottomNavigationMenu = findViewById(R.id.bottomNavigationMenu);

        viewPager.setAdapter(new PagerAdapter(this));

        bottomNavigationMenu.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.drinkTab) {
                viewPager.setCurrentItem(0);
            }
            else if (id == R.id.starterTab){
                viewPager.setCurrentItem(1);
            }
            else if (id == R.id.mainTab){
                viewPager.setCurrentItem(2);
            }
            else if (id == R.id.dessertTab){
                viewPager.setCurrentItem(3);
            }
            else if (id == R.id.todaysTab){
                viewPager.setCurrentItem(4);
            }
            return true;
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position){
                        switch (position) {
                            case 0:
                                bottomNavigationMenu.setSelectedItemId(R.id.drinkTab);
                                break;
                            case 1:
                                bottomNavigationMenu.setSelectedItemId(R.id.starterTab);
                                break;
                            case 2:
                                bottomNavigationMenu.setSelectedItemId(R.id.mainTab);
                                break;
                            case 3:
                                bottomNavigationMenu.setSelectedItemId(R.id.dessertTab);
                                break;
                            case 4:
                                bottomNavigationMenu.setSelectedItemId(R.id.todaysTab);
                                break;
                        }
                    }
                }
        );


        // https://developer.android.com/develop/ui/views/components/spinner
        //Bordsval
        Spinner tableSpinner = this.findViewById(R.id.tableSpinner);

        String[] tables = getResources().getStringArray(R.array.tableList);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.tableList,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tableSpinner.setAdapter(adapter);




        //Resest form ändra så att den skickar till databasen oxå
        Button sendButton = findViewById(R.id.sendOrderButton);
        int selectedMenuId = bottomNavigationMenu.getSelectedItemId();

        sendButton.setOnClickListener(buttonClicked -> {
            if(selectedMenuId == R.id.drinkTab) {
                TextView counter1Text = findViewById(R.id.drinkCounter1);
                TextView counter2Text = findViewById(R.id.drinkCounter2);
                TextView counter3Text = findViewById(R.id.drinkCounter3);
                TextView counter4Text = findViewById(R.id.drinkCounter4);
                TextView counter5Text = findViewById(R.id.drinkCounter5);
                TextView counter6Text = findViewById(R.id.drinkCounter6);
                TextView counter7Text = findViewById(R.id.drinkCounter7);

                counter1Text.setText("0");
                counter2Text.setText("0");
                counter3Text.setText("0");
                counter4Text.setText("0");
                counter5Text.setText("0");
                counter6Text.setText("0");
                counter7Text.setText("0");


                Snackbar.make(findViewById(R.id.viewPager), "Drinkbeställningen skickas till baren", Snackbar.LENGTH_SHORT).setAnchorView(sendButton).show();
            }

        });

    }


}