package com.example.desarrollocometaapp.Classes;

import android.content.Context;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.desarrollocometaapp.Classes.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RequestClass {

    public void getStringRequest(final Context context, String Url){
        RequestQueue myQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        myQueue.add(request);
    }

    public void postStringLoginRequest(final Context context, String Url, final String email, final String password){
        RequestQueue myQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                switch (response){
                    case "true":
                        Toast.makeText(context, "Bienvenido", Toast.LENGTH_LONG).show();
                        break;
                    case "ERROR 10001":
                        Toast.makeText(context, "Correo incorrecto", Toast.LENGTH_LONG).show();
                        break;
                    case "ERROR 10002True":
                        Toast.makeText(context, "Contraseña incorrecta", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(context, response, Toast.LENGTH_LONG).show();

                        break;
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context , error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("password", password);
                params.put("email", email);
                return params;
            }
        };
        myQueue.add(stringRequest);
    }

    public void getArrayRequests(final Context context, final ArrayList<String> list, final ArrayList<Producto> arrayList){
        final RequestQueue myQueue = Volley.newRequestQueue(context);
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, "https://cometa.app/cafeteria/stock/getproducts", null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONArray jsonArray = response;

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("id");
                        String name = object.getString("name");
                        String price = object.getString("price");
                        list.add(name);

                        Producto producto = new Producto(id, name, price);
                        arrayList.add(producto);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context , error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        myQueue.add(request);
    }
}
