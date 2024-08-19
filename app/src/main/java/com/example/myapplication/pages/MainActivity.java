package com.example.myapplication.pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private static final String MANAGER_PASSWORD = "123456"; // Replace with your saved password

    private Button clientButton, managerButton, submitButton;
    private EditText nameInput, passwordInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initViews();
    }

    private void findViews() {
        clientButton = findViewById(R.id.client_button);
        managerButton = findViewById(R.id.manager_button);
        submitButton = findViewById(R.id.submit_button);
        nameInput = findViewById(R.id.name_input);
        passwordInput = findViewById(R.id.password_input);
    }

    private void initViews() {
        clientButton.setOnClickListener(v -> {
            nameInput.setVisibility(View.VISIBLE);
            passwordInput.setVisibility(View.GONE);
            submitButton.setVisibility(View.VISIBLE);
        });

        managerButton.setOnClickListener(v -> {
            passwordInput.setVisibility(View.VISIBLE);
            nameInput.setVisibility(View.GONE);
            submitButton.setVisibility(View.VISIBLE);
        });

        submitButton.setOnClickListener(v -> {
            if (nameInput.getVisibility() == View.VISIBLE) {
                String name = nameInput.getText().toString().trim();
                if (!name.isEmpty()) {
                    // Proceed as client
                    proceedAsClient(name);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                }
            } else if (passwordInput.getVisibility() == View.VISIBLE) {
                String password = passwordInput.getText().toString().trim();
                if (password.equals(MANAGER_PASSWORD)) {
                    // Proceed as manager
                    proceedAsManager();
                } else {
                    Toast.makeText(MainActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void proceedAsClient(String name) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.KEY_NAME, name);
        startActivity(intent);
        finish();
    }

    private void proceedAsManager() {
        // Handle manager logic here
        Toast.makeText(this, "Welcome, Manager", Toast.LENGTH_SHORT).show();
        // You can navigate to the manager-specific page
    }
}