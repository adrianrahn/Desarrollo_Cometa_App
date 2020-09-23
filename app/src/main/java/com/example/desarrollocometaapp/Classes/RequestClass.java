package com.example.desarrollocometaapp.Classes;

import android.content.Context;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RequestClass {

    SharedPreferenceConfig sharedPreferenceConfig;

    //Todo: Products Array Request
    public void getArrayRequest(final Context context, final ArrayList<String> nameList, final ArrayList<String> idList, final ArrayList<String> priceList) {
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
                        idList.add(id);
                        nameList.add(name);
                        priceList.add(price);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        myQueue.add(request);
    }

    //Todo: Save Product Request
    public void postSaveProductRequest(final Context context, final String userId, final String productId, final String quantity) {

        sharedPreferenceConfig = new SharedPreferenceConfig(context);
        String Url = "https://cometa.app/cafeteria/stock/newsale";
        RequestQueue myQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                sharedPreferenceConfig.compraStatus(true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                sharedPreferenceConfig.compraStatus(false);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("productid", productId);
                params.put("userid", userId);
                params.put("qty", quantity);
                params.put("token", sharedPreferenceConfig.getToken());
                return params;
            }
        };
        myQueue.add(stringRequest);
    }

    //Todo: Login Request
    public void postStringLoginRequests(final Context context,final String email, final String password) {
        String url = "https://cometa.app/cafeteria/login/check";
        RequestQueue myQueue = Volley.newRequestQueue(context);
        sharedPreferenceConfig = new SharedPreferenceConfig(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String id = response.getString("userId");
                    String token = response.getString("token");
                    sharedPreferenceConfig.saveIdAndToken(id, token);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
                public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
            }
        }){@Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<String, String>();
            params.put("email", email);
            params.put("password", password);
            params.put("token", sharedPreferenceConfig.getToken());
            return params;
        }
        };
        myQueue.add(jsonObjectRequest);
    }

    /*

    public String postStringLoginRequest(final Context context, String Url, final String email, final String password) {
        final Map<String, String> respuesta = new HashMap<String, String>();
        RequestQueue myQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                switch (response) {
                    case "1":
                        respuesta.put("response", response);
                        break;
                    case "\"ERROR 10001\"1":
                        Toast.makeText(context, "Correo incorrecto", Toast.LENGTH_SHORT).show();
                        break;
                    case "\"ERROR 10002\"1":
                        Toast.makeText(context, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(context, "Correo y contraseña incorrecto", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("password", password);
                params.put("email", email);
                return params;
            }
        };
        myQueue.add(stringRequest);
        return respuesta.get("response");
    }


      //Todo: request simple string

    public void getStringRequest(final Context context, String Url) {
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



*/
}