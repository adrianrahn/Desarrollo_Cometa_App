package com.example.desarrollocometaapp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.desarrollocometaapp.Classes.SharedPreferenceConfig;
import com.example.desarrollocometaapp.R;

public class RealizandoCompra extends AppCompatActivity {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    SharedPreferenceConfig sharedPreferenceConfig;

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizando_compra);

        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {


                    if(sharedPreferenceConfig.compraReadStatus() == false){
                        Intent mainIntent = new Intent(RealizandoCompra.this, ErrorEnCompra.class);
                        RealizandoCompra.this.startActivity(mainIntent);
                        RealizandoCompra.this.finish();
                    }else{
                        Intent mainIntent = new Intent(RealizandoCompra.this, CompraRealizada.class);
                        RealizandoCompra.this.startActivity(mainIntent);
                        RealizandoCompra.this.finish();
                    }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }


}