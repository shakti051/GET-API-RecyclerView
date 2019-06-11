package com.example.getsync;

import android.app.LauncherActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity implements PersonAdapter.ItemClicked {
    String myURL = "https://apex.oracle.com/pls/apex/neha_semwal/showinsert/alldatashow/";
    TextView userName;
    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Person> people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.buttonApi);
    //  userName = findViewById(R.id.userName);

        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myURL, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Log.i("JSON ", "Your JSON " + jsonObject);
                                try {

                                    JSONObject jsonobj;
                                    jsonobj = new JSONObject(jsonObject.toString());
                                    JSONArray jsonArray = jsonobj.getJSONArray("items");
                                    people = new ArrayList<Person>();
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject object = jsonArray.getJSONObject(i);
                                        String name = object.getString("name");
                                        String age= object.getString("age");
                                        String imei = object.getString("imei_no");
                                        people.add(new Person(name,age,imei));
                                        Log.i("Name ", "name is " + name+" Age: "+age);
                                    }

                                    myAdapter = new PersonAdapter(MainActivity.this,people);
                                    recyclerView.setAdapter(myAdapter);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.i("Error ", "Sth went wrong " + volleyError);
                    }
                }
                );
                MySingleton.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);
            }
        });
    }

    @Override
    public void onItemClicked(int index) {

    }
}
