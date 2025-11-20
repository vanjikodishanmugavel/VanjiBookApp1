package com.example.vanjibookapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private TextView titleTextView;
    private TextView authorTextView;
    private TextView yearTextView;
    private ImageView bookImageView;
    private Button takePhotoButton;
    private Button nextButton;

    private Book book;
    private Bitmap photoBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialize views
        titleTextView = findViewById(R.id.titleTextView);
        authorTextView = findViewById(R.id.authorTextView);
        yearTextView = findViewById(R.id.yearTextView);
        bookImageView = findViewById(R.id.bookImageView);
        takePhotoButton = findViewById(R.id.takePhotoButton);
        nextButton = findViewById(R.id.nextButton);

        // Get Book object from intent
        Intent intent = getIntent();
        book = intent.getParcelableExtra("book");

        if (book != null) {
            titleTextView.setText(getString(R.string.display_title, book.getTitle()));
            authorTextView.setText(getString(R.string.display_author, book.getAuthor()));
            yearTextView.setText(getString(R.string.display_year, book.getYear()));
        }

        // Restore photo if exists
        if (savedInstanceState != null) {
            photoBitmap = savedInstanceState.getParcelable("photo");
            if (photoBitmap != null) {
                bookImageView.setImageBitmap(photoBitmap);
                bookImageView.setVisibility(View.VISIBLE);
            }
        }

        // Take Photo Button
        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                } else {
                    Toast.makeText(SecondActivity.this, R.string.error_no_camera, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Next Button - go to Third Activity
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            photoBitmap = (Bitmap) extras.get("data");
            bookImageView.setImageBitmap(photoBitmap);
            bookImageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save photo when device rotates
        if (photoBitmap != null) {
            outState.putParcelable("photo", photoBitmap);
        }
    }
}
