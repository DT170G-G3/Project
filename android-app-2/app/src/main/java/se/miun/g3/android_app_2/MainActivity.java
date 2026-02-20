package se.miun.g3.android_app_2;
/*
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
    }
}
*/

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.miun.g3.android_app_2.orders.*;

public class MainActivity extends AppCompatActivity {

    private TextView txtOrderId, txtTableId, txtNote, txtItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtOrderId = findViewById(R.id.txtOrderId);
        txtTableId = findViewById(R.id.txtTableId);
        txtNote = findViewById(R.id.txtNote);
        txtItems = findViewById(R.id.txtItems);

        Button btnRefresh = findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(v -> loadOneOrder());

        loadOneOrder();
    }



    
    private void loadOneOrder() {
        ApiClient.ordersApi().getOrders().enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    txtOrderId.setText("Error HTTP: " + response.code());
                    txtTableId.setText("");
                    txtNote.setText("");
                    txtItems.setText("");
                    return;
                }

                List<Order> orders = response.body();
                if (orders.isEmpty()) {
                    txtOrderId.setText("No orders found");
                    txtTableId.setText("");
                    txtNote.setText("");
                    txtItems.setText("");
                    return;
                }

                Order o = orders.get(0); // proof-of-concept: första ordern
                txtOrderId.setText("Order ID: 0");
                txtTableId.setText("Table ID: " + o.tableId);
                txtNote.setText("Note: " + (o.note == null ? "" : o.note));

                StringBuilder sb = new StringBuilder();
                if (o.orderedItems != null) {
                    for (Order.OrderItem it : o.orderedItems) {
                        sb.append("dishId=").append(it.dishId)
                                .append(", category=").append(it.category)
                                .append("\n");
                    }
                }
                txtItems.setText("Items:\n" + sb);
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                String msg = (t.getMessage() != null) ? t.getMessage() : t.toString();
                txtOrderId.setText("Network error: " + msg);
                txtTableId.setText("");
                txtNote.setText("");
                txtItems.setText("");
            }
        });
    }
}
