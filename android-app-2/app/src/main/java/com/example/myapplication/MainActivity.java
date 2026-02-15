package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView starterRecyclerView = findViewById(R.id.starterRecyclerView);
        RecyclerView mainRecyclerView = findViewById(R.id.mainCourseRecyclerView);
        RecyclerView dessertRecyclerView = findViewById(R.id.dessertRecyclerView);

        starterRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dessertRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        //bara för test
        List<Order> starterOrder = Arrays.asList(
                new Order(1, Arrays.asList("Carpaccio")),
                        new Order(5, Arrays.asList("Caprese"))
        );

        List<Order> mainOrder = Arrays.asList(
                new Order(2, Arrays.asList("kött", "Fish and chips")),
                new Order(6, Arrays.asList("Bolognese"))
        );

        List<Order> dessertOrder = Arrays.asList(
                new Order(3, Arrays.asList("kladdkaka", "glass")),
                new Order(1, Arrays.asList("tiramisu"))
        );

        starterRecyclerView.setAdapter(new OrdersAdapter(starterOrder));
        mainRecyclerView.setAdapter(new OrdersAdapter(mainOrder));
        dessertRecyclerView.setAdapter(new OrdersAdapter(dessertOrder));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}