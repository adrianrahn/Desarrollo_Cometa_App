package com.example.desarrollocometaapp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desarrollocometaapp.Classes.Producto;
import com.example.desarrollocometaapp.R;
import com.example.desarrollocometaapp.Classes.RequestClass;

import java.util.ArrayList;

public class ManualActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button plusButton, minusButton, saveButton;
    private TextView quantityText;
    private Spinner productsSpinner;
    private int quantity = 0;
    RequestClass requester;
    ArrayList<String> arrayList= new ArrayList<String>();
    ArrayList<Producto> productoArrayList= new ArrayList<Producto>();
    String productId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        quantityText = (TextView) findViewById(R.id.quantityTextViewManual);
        quantityText.setText("" + quantity);
        plusButton = (Button) findViewById(R.id.plusButtonManual);
        plusButton.setOnClickListener(this);
        minusButton = (Button) findViewById(R.id.minusButtonManual);
        minusButton.setOnClickListener(this);
        saveButton = (Button) findViewById(R.id.saveButtonManual);
        saveButton.setOnClickListener(this);
        productsSpinner = (Spinner) findViewById(R.id.productsSpinnerManual);
        requester = new RequestClass();
        arrayList = getIntentArray();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, arrayList);
        productsSpinner.setAdapter(adapter);
        productsSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

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

            case R.id.saveButtonManual:
                requester.postSaveProductRequest(getApplicationContext(), "https://cometa.app/cafeteria/stock/newsale", getUserId(), "1", quantityText.getText().toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        try {
            String text = productsSpinner.getSelectedItem().toString();
            Toast.makeText(this, "id:" + text , Toast.LENGTH_SHORT).show();

        }catch (Error error){
            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public ArrayList<String> getIntentArray(){
        ArrayList<String> list = getIntent().getStringArrayListExtra("array");
        return list;
    }

    public String getUserId(){
        String id = getIntent().getStringExtra("userId");
        return id;
    }
}