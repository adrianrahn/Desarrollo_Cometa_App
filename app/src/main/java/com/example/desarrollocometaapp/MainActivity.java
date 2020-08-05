package com.example.desarrollocometaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button plusButton, minusButton, scanButton, saveButton;
    private TextView quantityText, priceText,productText;
    private int quantity = 0;
    private RequestQueue myQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quantityText = (TextView) findViewById(R.id.quantityTextView);
        quantityText.setText("" + quantity);
        priceText = (TextView) findViewById(R.id.priceTextView);
        productText = (TextView) findViewById(R.id.productTextView);
        plusButton = (Button) findViewById(R.id.plusButton);
        plusButton.setOnClickListener(this);
        minusButton = (Button) findViewById(R.id.minusButton);
        minusButton.setOnClickListener(this);
        scanButton = (Button) findViewById(R.id.scanButton);
        scanButton.setOnClickListener(this);
        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        myQueue = Volley.newRequestQueue(getApplicationContext());

    }

    public void onClick(View view) {
        switch (view.getId()){

            case R.id.plusButton:
                    quantity += 1;
                    quantityText.setText("" + quantity);
                break;
            case R.id.minusButton:
                if(quantity >= 1){
                    quantity -= 1;
                    quantityText.setText("" + quantity);
                    startActivity(new Intent(this, Login.class));
                }
                break;
            case R.id.scanButton:
                ScanCode();
                break;
            case R.id.saveButton:
               // startActivity(new Intent(this, Login.class));
                 JsonResponse();
                break;
            default:
                break;
        }
    }
//todo: Get
    private void JsonResponse() {
        String url = "https://cometa.app/cafeteria/home/pruebaget";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){};
        myQueue.add(request);
    }

    private void ScanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("scanning code");
        integrator.initiateScan();
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode, data);
        if (result != null){
            if (result.getContents() != null){

                String resultado = result.getContents();
                try {
                    JSONObject jsonjObject = new JSONObject(resultado);

                    //{ "producto":"Manzana" , "precio":"1" }
                    String productString = jsonjObject.getString("producto");
                    String priceString = jsonjObject.getString("precio");
                    productText.setText(productString);
                    priceText.setText(priceString + " euro");
                    quantity = 1;
                    quantityText.setText("" + quantity);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {
                Toast.makeText(this, "No Results", Toast.LENGTH_SHORT).show();
            }
        } else {super.onActivityResult( requestCode, resultCode, data);}
    }


}
