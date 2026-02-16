package com.DT170G.G3.android_app_1;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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


        BottomNavigationView bottomNavigationMenu = findViewById(R.id.bottomNavigationMenu);

        Fragment starterFragment = new StarterFragment();
        Fragment mainFragment = new MainFragment();
        Fragment drinkFragment = new DrinkFragment();
        Fragment dessertFragment = new DessertFragment();
        Fragment todaysFragment = new TodaysFragment();

        setCurrentFragment(drinkFragment);

        bottomNavigationMenu.setOnItemSelectedListener(item-> {
            int id = item.getItemId();
            if(id == R.id.todaysTab) {
                setCurrentFragment(todaysFragment);
            }
            else if (id == R.id.starterTab) {
                setCurrentFragment(starterFragment);
            }
            else if (id == R.id.mainTab) {
                setCurrentFragment(mainFragment);
            }
            else if (id == R.id.dessertTab) {
                setCurrentFragment(dessertFragment);
            } else {
                setCurrentFragment(drinkFragment);
            }
            return true;
        });

    }

    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}