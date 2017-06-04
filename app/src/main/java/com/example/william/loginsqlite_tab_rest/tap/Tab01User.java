package com.example.william.loginsqlite_tab_rest.tap;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.example.william.loginsqlite_tab_rest.R;

import com.example.william.loginsqlite_tab_rest.model.User;


import java.util.ArrayList;
import java.util.List;



public class Tab01User extends Fragment {

    public static List<User> listUser = new ArrayList<User>();
    public int userId = 0;
    ListView listView;
    public int ID = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.tab01_user, container, false);

        listView = (ListView) view.findViewById(R.id.listView);


        int layout = android.R.layout.simple_list_item_1;
        final ArrayAdapter<User> arrayAdapter = new ArrayAdapter<User>(getActivity(), layout, listUser);
        listView.setAdapter(arrayAdapter);


        return view;

    }
}

