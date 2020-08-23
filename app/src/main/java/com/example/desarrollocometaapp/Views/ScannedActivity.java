package com.example.desarrollocometaapp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.desarrollocometaapp.R;
import com.example.desarrollocometaapp.Classes.RequestClass;

public class ScannedActivity extends AppCompatActivity implements View.OnClickListener {
    private Button plusButton, minusButton, saveButton;
    private TextView quantityText,productText;
    private int quantity = 0;
    RequestClass requester;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanned);

        productText = (TextView) findViewById(R.id.productTextViewScanned);
        quantityText = (TextView) findViewById(R.id.quantityTextViewScanned);
        quantityText.setText("" + quantity);
        productText = (TextView) findViewById(R.id.productTextViewScanned);
        plusButton = (Button) findViewById(R.id.plusButtonScanned);
        plusButton.setOnClickListener(this);
        minusButton = (Button) findViewById(R.id.minusButtonScanned);

        minusButton.setOnClickListener(this);
        saveButton = (Button) findViewById(R.id.saveButtonScanned);
        saveButton.setOnClickListener(this);
        requester = new RequestClass();
        requester.postScannedProductRequest(getApplicationContext(), "https://cometa.app/cafeteria/stock/getproduct",getProductId());
    }

    public void onClick(View view) {
        switch (view.getId()){

            case R.id.plusButtonManual:
                quantity += 1;
                quantityText.setText("" + quantity);
                break;
            case R.id.minusButtonManual:
                if(quantity >= 1){
                    quantity -= 1;
                    quantityText.setText("" + quantity);
                }
                break;
        case R.id.saveButtonScanned:
            requester.postSaveProductRequest(getApplicationContext(), "https://cometa.app/cafeteria/stock/newsale", getUserId(), getProductId(), quantityText.getText().toString());
            quantity = 0;
            quantityText.setText("" + quantity);
            break;

        default:
        break;
        }
    }

    public String getProductId(){
        String id = getIntent().getStringExtra("JsonObjectId");
        return id;
    }
    public String getUserId(){
        String id = getIntent().getStringExtra("userId");
        return id;
    }

}