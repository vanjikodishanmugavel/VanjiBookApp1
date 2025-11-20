package com.example.vanjibookapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText authorEditText;
    private EditText yearEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        titleEditText = findViewById(R.id.titleEditText);
        authorEditText = findViewById(R.id.authorEditText);
        yearEditText = findViewById(R.id.yearEditText);
        submitButton = findViewById(R.id.submitButton);

        // Restore saved state if exists
        if (savedInstanceState != null) {
            titleEditText.setText(savedInstanceState.getString("title"));
            authorEditText.setText(savedInstanceState.getString("author"));
            yearEditText.setText(savedInstanceState.getString("year"));
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString().trim();
                String author = authorEditText.getText().toString().trim();
                String year = yearEditText.getText().toString().trim();

                // Validate input
                if (title.isEmpty() || author.isEmpty() || year.isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.error_empty_fields, Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create Book object
                Book book = new Book(title, author, year);

                // Pass Book object to Second Activity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("book", book);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save state when device rotates
        outState.putString("title", titleEditText.getText().toString());
        outState.putString("author", authorEditText.getText().toString());
        outState.putString("year", yearEditText.getText().toString());
    }
}
