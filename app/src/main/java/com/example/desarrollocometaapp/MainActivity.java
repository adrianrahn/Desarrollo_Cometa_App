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

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button plusButton, minusButton, scanButton, saveButton;
    TextView quantityText, priceText,productText;
    int quantity = 0;

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

    }

    public void onClick(View view) {
        switch (view.getId()){

            case R.id.plusButton:
                    quantity += 1;
                    quantityText.setText("" + quantity);
                    Toast.makeText(this, "+ pressed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.minusButton:
                if(quantity >= 1){
                    quantity -= 1;
                    quantityText.setText("" + quantity);
                    Toast.makeText(this, "- pressed", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.scanButton:
                Toast.makeText(this, "scanButton pressed", Toast.LENGTH_SHORT).show();
                ScanCode();
                break;
            case R.id.saveButton:
                Toast.makeText(this, "saveButton pressed", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
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



               /* AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Scanning Result");
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ScanCode();
                    }
                }).setNegativeButton("Finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();*/
            }
            else {
                Toast.makeText(this, "No Results", Toast.LENGTH_SHORT).show();
            }
        } else {super.onActivityResult( requestCode, resultCode, data);}
    }
}
