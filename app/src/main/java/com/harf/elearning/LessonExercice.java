package com.harf.elearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.harf.elearning.model.Cours;
import com.harf.elearning.model.CoursMatiere;

import java.util.Arrays;
import java.util.HashMap;

public class LessonExercice extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchView mysearchView;
    int images[] ={R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo};
    static String key_words = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_exercice);
        recyclerView =findViewById(R.id.recyclerView);
        mysearchView = findViewById(R.id.searchview);

        CoursMatiere cours= new CoursMatiere();

//        String listedescription[] = cours.getListeDescription();
        if(key_words==null){
            key_words = getIntent().getStringExtra("data2");
        }
        String listeLibelle[] = cours.getListeCours().get(key_words);
//        String listeid[] = cours.getListeId();
//
        final AdapterCours mumAdapter = new AdapterCours( this,listeLibelle);
        recyclerView.setAdapter(mumAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mysearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mumAdapter.getFilter().filter(s);
                return false;
            }
        });
//
//
//        //Initialize And Assin
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_naviation);
        bottomNavigationView.setSelectedItemId(R.id.liste);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.liste:
                        return true;
                    case R.id.actualite:
                        startActivity(new Intent(getApplicationContext(),Actualite.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }


}