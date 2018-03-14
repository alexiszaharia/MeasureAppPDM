package com.example.stud.measureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    AdaptorLista adaptorLista;
    ArrayList<Masuratoare> listaMasuratori = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdaugareMasuratoareActivity.class);
                int requestCode = 0;
                startActivityForResult(intent, requestCode);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Punct punct1 = new Punct(40, 45);
        Punct punct2 = new Punct(50, 60);
        Punct punct3 = new Punct(60, 75);
        ArrayList<Punct> listaPuncte = new ArrayList<>();
        listaPuncte.add(punct1);
        listaPuncte.add(punct2);
        listaPuncte.add(punct3);
        Masuratoare masuratoare = new Masuratoare("mas1", listaPuncte, new Date(), 1);
        listaMasuratori.add(masuratoare);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        adaptorLista = new AdaptorLista(listaMasuratori);
        recyclerView.setAdapter(adaptorLista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_lista) {
            // Handle the camera action
        } else if (id == R.id.nav_adaugare) {
            Intent intent = new Intent(this, AdaugareMasuratoareActivity.class);
            int requestCode = 0;
            startActivityForResult(intent, requestCode);
        } else if (id == R.id.nav_setari) {

        } else if (id == R.id.nav_despre) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            Masuratoare nouaMasuratoare = (Masuratoare)data.getSerializableExtra("masuratoare");
            listaMasuratori.add(nouaMasuratoare);
            adaptorLista.notifyDataSetChanged();
        }
    }
}
