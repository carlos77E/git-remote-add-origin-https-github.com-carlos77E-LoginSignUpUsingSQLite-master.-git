package com.example.william.loginsqlite_tab_rest.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;


import com.example.william.loginsqlite_tab_rest.R;
import com.example.william.loginsqlite_tab_rest.adapters.UsersRecyclerAdapter;
import com.example.william.loginsqlite_tab_rest.model.User;
import com.example.william.loginsqlite_tab_rest.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.william.loginsqlite_tab_rest.tap.Tab01User.listUser;


public class UsersListActivity extends AppCompatActivity {

    public int userId=0;
    Button elimina;
    private AppCompatActivity activity = UsersListActivity.this;
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewUsers;
    private List<User> listUsers;
    private UsersRecyclerAdapter usersRecyclerAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        initViews();
        initObjects();
        eliminar();

    }


    /**
     * Este método consiste en inicializar vistas(views)
     */
    private void initViews() {
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerViewUsers);
    }

    /**
     * Este método consiste en inicializar los objetos que se van a utilizar
     */
    public void initObjects() {
        listUsers = new ArrayList<>();
        usersRecyclerAdapter = new UsersRecyclerAdapter(listUsers);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setAdapter(usersRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);

        String emailFromIntent = getIntent().getStringExtra("NAME");
        textViewName.setText(emailFromIntent);

        getDataFromSQLite();
    }

    /**
     * Este método es buscar todos los registros de usuario de SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask se utiliza que la operación SQLite no bloquea el UI Thread(subproceso de UI).
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listUsers.clear();
                listUsers.addAll(databaseHelper.getAllUser());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                usersRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    public void eliminar(){

    }
}
