package com.harf.elearning;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Actualite extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualite);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Actualité");

        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList2.add("Nouvelle année, nouvelles perspectives.L’objectif est de faire connaître la destination Madagascar dans toute sa diversité. Quelques agences de voyage et la compagnie aérienne nationale Air Madagascar, accompagnée de sa filiale Tsaradia, vont réunir les professionnels de voyage en France pour leur présenter, au cours d’un cocktail ou d’un petit déjeuner, les offres touristiques de la Grande île.Avec l’Allemagne, la France figure parmi les principales cibles de l’ONTM. Outre le tourisme affinitaire, les opérateurs touristiques de la Grande île misent également sur plusieurs offres nautiques, entre autres, la plongée, la pêche sportive et le birdwatchning. Dans la même foulée, une douzaine de journalistes et blogueurs internationaux seront invités à Madagascar pour promouvoir, à leur tour, la destination Île aux trésors. ");

        prepareViewPager(viewPager,arrayList,arrayList2);

        tabLayout.setupWithViewPager(viewPager);



        //Initialize And Assin
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_naviation);
        bottomNavigationView.setSelectedItemId(R.id.actualite);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.liste:
                        startActivity(new Intent(getApplicationContext(),Liste.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.actualite:
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

    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList,ArrayList<String> zavatra) {
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        Ongletfragment ongletfragment = new Ongletfragment();
        for(int i=0; i<arrayList.size();i++){
            Bundle bundle = new Bundle();
            bundle.putString("title",zavatra.get(i));
            ongletfragment.setArguments(bundle);
            adapter.addFragment(ongletfragment,arrayList.get(i));
            ongletfragment = new Ongletfragment();
        }
        viewPager.setAdapter(adapter);
    }

    private class MainAdapter extends FragmentPagerAdapter {
        ArrayList<String> arrayList= new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();

        public void  addFragment(Fragment fragment, String title){
            arrayList.add(title);
            fragmentList.add(fragment);
        }

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            return fragmentList.get(position);
        }

        @Override
        public int getCount() {

            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return  arrayList.get(position);
        }
    }
}
