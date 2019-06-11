package com.example.getsync;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mconstext;

    public MySingleton(Context context) {
        mconstext = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(mconstext.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MySingleton getInstance(Context context){
        if (mInstance == null){
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    public void addToRequestQue(Request request){requestQueue.add(request);}
}
