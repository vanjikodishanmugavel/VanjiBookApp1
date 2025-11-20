package com.example.vanjibookapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    private EditText phoneNumberEditText;
    private EditText locationEditText;
    private Button dialButton;
    private Button mapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Initialize views
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        locationEditText = findViewById(R.id.locationEditText);
        dialButton = findViewById(R.id.dialButton);
        mapButton = findViewById(R.id.mapButton);

        // Phone Dialer Intent
        dialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneNumberEditText.getText().toString().trim();

                if (phoneNumber.isEmpty()) {
                    Toast.makeText(ThirdActivity.this, R.string.error_empty_phone, Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(dialIntent);
            }
        });

        // Map Location Intent
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = locationEditText.getText().toString().trim();

                if (location.isEmpty()) {
                    Toast.makeText(ThirdActivity.this, R.string.error_empty_location, Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create a URI for the location (can be address or coordinates)
                Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                mapIntent.setData(Uri.parse("geo:0,0?q=" + Uri.encode(location)));

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    Toast.makeText(ThirdActivity.this, R.string.error_no_map, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state when device rotates
        outState.putString("phoneNumber", phoneNumberEditText.getText().toString());
        outState.putString("location", locationEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore the state when device rotates
        if (savedInstanceState != null) {
            phoneNumberEditText.setText(savedInstanceState.getString("phoneNumber"));
            locationEditText.setText(savedInstanceState.getString("location"));
        }
    }
}
