package com.example.william.loginsqlite_tab_rest.tap;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.william.loginsqlite_tab_rest.R;
import com.example.william.loginsqlite_tab_rest.model.Persona;
import com.example.william.loginsqlite_tab_rest.rest.HttpClient;
import com.example.william.loginsqlite_tab_rest.rest.OnHttpRequestComplete;
import com.example.william.loginsqlite_tab_rest.rest.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Tab02Favotites extends Fragment {
    LinearLayout stackContent;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){
        View view = inflater.inflate(R.layout.tab02_favorites,container,false);


        stackContent = (LinearLayout) view.findViewById(R.id.stackContent);

        HttpClient client = new HttpClient(new OnHttpRequestComplete() {
            @Override
            public void onComplete(Response status) {
                if(status.isSuccess()){
                    Gson gson = new GsonBuilder().create();
                    try {
                        JSONObject jsono = new JSONObject(status.getResult());
                        JSONArray jsonarray = jsono.getJSONArray("records");
                        ArrayList<Persona> personas = new ArrayList<Persona>();
                        for(int i = 0; i < jsonarray.length(); i++) {
                            String person = jsonarray.getString(i);
                            System.out.println(person);
                            Persona p = gson.fromJson(person,Persona.class);
                            personas.add(p);
                            System.err.println(p.getNombres());
                            TextView t = new TextView(getContext());
                            t.setText(p.getNombres());
                            stackContent.addView(t);
                        }
                    }catch (Exception e){
                        System.out.println("Fallo!");
                        e.printStackTrace();
                    }
                    Context context = getContext().getApplicationContext();
                    Toast.makeText(context, status.getResult(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        client.excecute("http://localhost:8000/api/planilla/empleados/");

        return view;
    }

}
