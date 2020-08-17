package com.example.desarrollocometaapp.Views;

import androidx.appcompat.app.AppCompatActivity;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.desarrollocometaapp.Classes.CaptureAct;
import com.example.desarrollocometaapp.Classes.Producto;
import com.example.desarrollocometaapp.Classes.RequestClass;
import com.example.desarrollocometaapp.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button scanButton, saveButton;
    RequestClass requestClass;
    ArrayList<String> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanButton = (Button) findViewById(R.id.scanButton);
        scanButton.setOnClickListener(this);
        saveButton = (Button) findViewById(R.id.manualButton);
        saveButton.setOnClickListener(this);
        requestClass = new RequestClass();
        arrayList = new ArrayList<String>();

        requestClass.getArrayRequest(getApplicationContext(), arrayList);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.scanButton:

                ScanCode();

                break;

            case R.id.manualButton:
                Intent intent = new Intent(this, ManualActivity.class);
                intent.putStringArrayListExtra("array", arrayList);
                startActivity(intent);
                break;

            default:
                break;
        }
    }



    //Todo: Scanner

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

                    //{ "token":"Manzana" , "id":"1" }
                    String productString = jsonjObject.getString("token");
                    String priceString = jsonjObject.getString("id");
                    Intent intent = new Intent(this, ScannedActivity.class);
                    intent.putExtra("JsonObjectId", productString);
                    intent.putExtra("JsonObjectPrice", priceString);
                    startActivity(intent);

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
