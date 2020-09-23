package com.example.desarrollocometaapp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.desarrollocometaapp.R;

public class ErrorEnCompra extends AppCompatActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 4500;

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_en_compra);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(ErrorEnCompra.this, MainActivity.class);
                ErrorEnCompra.this.startActivity(mainIntent);
                ErrorEnCompra.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}