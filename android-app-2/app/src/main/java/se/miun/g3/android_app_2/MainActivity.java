package se.miun.g3.android_app_2;

import android.os.Bundle;
import android.provider.ContactsContract;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import se.miun.g3.android_app_2.dishes.DishesRepository;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView orderRecyclerView = findViewById(R.id.orderRecyclerView);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        //För test, byt till databaskoppling sen
        /*List<Orders> starterOrders = Arrays.asList(
                new Orders(1, Arrays.asList("Carpaccio", "Caprese"), new ArrayList<>(), new ArrayList<>()),
                new Orders(2, Arrays.asList("Bruschetta", "oliver", "chark"), new ArrayList<>(), new ArrayList<>()),
                new Orders(3, Arrays.asList("Bruschetta", "oliver", "chark"), new ArrayList<>(), new ArrayList<>()),
                new Orders(4, Arrays.asList("Bruschetta", "oliver", "chark"), new ArrayList<>(), new ArrayList<>()),
                new Orders(5, Arrays.asList("Bruschetta", "oliver", "chark"), new ArrayList<>(), new ArrayList<>()),
                new Orders(6, Arrays.asList("Bruschetta", "oliver", "chark"), new ArrayList<>(), new ArrayList<>()),
                new Orders(7, Arrays.asList("Bruschetta", "oliver", "chark"), new ArrayList<>(), new ArrayList<>()),
                new Orders(8, Arrays.asList("Bruschetta", "oliver", "chark"), new ArrayList<>(), new ArrayList<>()),
                new Orders(9, Arrays.asList("Bruschetta", "oliver", "chark"), new ArrayList<>(), new ArrayList<>())

        );

        List<Orders> mainCourserOrders = Arrays.asList(
                new Orders(1, new ArrayList<>(), Arrays.asList("Kött", "Fisk"), new ArrayList<>()),
                new Orders(2, new ArrayList<>(), Arrays.asList("Pasta", "kött"),  new ArrayList<>()),
                new Orders(3, new ArrayList<>(), Arrays.asList("Pasta", "kött"),  new ArrayList<>()),
                new Orders(4, new ArrayList<>(), Arrays.asList("Pasta", "kött"),  new ArrayList<>()),
                new Orders(5, new ArrayList<>(), Arrays.asList("Pasta", "kött"),  new ArrayList<>()),
                new Orders(6, new ArrayList<>(), Arrays.asList("Pasta", "kött"),  new ArrayList<>()),
                new Orders(7, new ArrayList<>(), Arrays.asList("Pasta", "kött"),  new ArrayList<>()),
                new Orders(8, new ArrayList<>(), Arrays.asList("Pasta", "kött"),  new ArrayList<>()),
                new Orders(9, new ArrayList<>(), Arrays.asList("Pasta", "kött"),  new ArrayList<>())

        );

        List<Orders> dessertOrders = Arrays.asList(
                new Orders(1, new ArrayList<>(), new ArrayList<>(), Arrays.asList("Pannacotta", "Tiramisu")),
                new Orders(2, new ArrayList<>(), new ArrayList<>(), Arrays.asList("Pannacotta", "Chokladpralin")),
                new Orders(3, new ArrayList<>(), new ArrayList<>(), Arrays.asList("Pannacotta", "Chokladpralin")),
                new Orders(4, new ArrayList<>(), new ArrayList<>(), Arrays.asList("Pannacotta", "Chokladpralin")),
                new Orders(5, new ArrayList<>(), new ArrayList<>(), Arrays.asList("Pannacotta", "Chokladpralin")),
                new Orders(6, new ArrayList<>(), new ArrayList<>(), Arrays.asList("Pannacotta", "Chokladpralin")),
                new Orders(7, new ArrayList<>(), new ArrayList<>(), Arrays.asList("Pannacotta", "Chokladpralin")),
                new Orders(8, new ArrayList<>(), new ArrayList<>(), Arrays.asList("Pannacotta", "Chokladpralin")),
                new Orders(9, new ArrayList<>(), new ArrayList<>(), Arrays.asList("Pannacotta", "Chokladpralin"))
        );*/

        List<Orders> starterOrders = Collections.emptyList();
        List<Orders> mainCourserOrders = Collections.emptyList();
        List<Orders> dessertOrders = Collections.emptyList();

        List<Orders> finalList = setOrderList(starterOrders, mainCourserOrders, dessertOrders);
        orderRecyclerView.setAdapter(new OrdersAdapter(finalList));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private List<Orders> setOrderList(List<Orders> starters, List<Orders> mainCourse, List<Orders>desserts) {
        Map<Integer, List<String>> starterMap = new HashMap<>();
        Map<Integer, List<String>> mainCourseMap = new HashMap<>();
        Map<Integer, List<String>> dessertMap = new HashMap<>();

        for(Orders o : starters) {
            starterMap.putIfAbsent(o.getTableNumber(), new ArrayList<>());
            starterMap.get(o.getTableNumber()).addAll(o.getStarters());
        }

        for(Orders o : mainCourse) {
            mainCourseMap.putIfAbsent(o.getTableNumber(), new ArrayList<>());
            mainCourseMap.get(o.getTableNumber()).addAll(o.getMainCourses());
        }

        for(Orders o : desserts) {
            dessertMap.putIfAbsent(o.getTableNumber(), new ArrayList<>());
            dessertMap.get(o.getTableNumber()).addAll(o.getDesserts());
        }

        Set<Integer> allTables = new HashSet<>();
        allTables.addAll(starterMap.keySet());
        allTables.addAll(mainCourseMap.keySet());
        allTables.addAll(dessertMap.keySet());

        List<Orders> completeOrder = new ArrayList<>();
        for(Integer table : allTables) {
            completeOrder.add(new Orders(table, starterMap.getOrDefault(table, new ArrayList<>()),
                    mainCourseMap.getOrDefault(table, new ArrayList<>()),
                    dessertMap.getOrDefault(table, new ArrayList<>())
            ));
        }
        return completeOrder;
    }
}