package com.harf.elearning;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.harf.elearning.model.DetailsCoursMatiere;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdapterCours extends RecyclerView.Adapter<Adapter.MyVieWHolder> implements Filterable {

    String data1[], data2[],data3[];
    int images[];
    Context context;
    String cours[];
    List<String> courslist;
    List<String> courslistvaovao;
    List<String> descriptionliste;
    List<String> coursliste;

    public AdapterCours(Context ct, String s1[]){
        context = ct;
        data1 =s1;
        data2 =s1;
        data3 =s1;
        cours = s1;
        courslist=new ArrayList<>();
        courslistvaovao=new ArrayList<>();
        descriptionliste=new ArrayList<>();
        coursliste=new ArrayList<>();
        for(int i=0;i<s1.length;i++){
            courslist.add(s1[i]);
            courslistvaovao.add(s1[i]);
        }
        for(int i=0;i<s1.length;i++){
            descriptionliste.add(null);
        }
        for(int i=0;i<s1.length;i++){
            coursliste.add(null);
        }
        images = new int[s1.length];
        for(int i=0;i<s1.length;i++){
            images[i] = R.drawable.logo;
        }
    }
    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<String> filterdList = new ArrayList<>();
            List<String> temp=new ArrayList<>();
            for(int i=0;i<cours.length;i++){
                temp.add(cours[i]);
            }
            if(charSequence.toString().isEmpty()){
                filterdList.addAll(courslistvaovao);
            }else{
                for(String ville: temp){
                    if(ville.toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filterdList.add(ville);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterdList;
            return filterResults;

        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            data1=null;
            data1 = new String[cours.length];
            data2=null;
            data2 = new String[cours.length];
            data3=null;
            data3 = new String[cours.length];
            images = null;
            images = new int[cours.length];
            courslist.clear();
            courslist.addAll((Collection<? extends String>) filterResults.values);
            for(int i=0;i<courslist.size();i++){
                data1[i]=courslist.get(i);
                for(int a=0; a<descriptionliste.size(); a++){
                    if (descriptionliste.get(a).contains(data1[i])){
                        data2[i]=descriptionliste.get(a);
                        data3[i]=coursliste.get(a);
                        System.out.println(data1[i]+">>>>>>>>>>>>"+a+">>>>>>>>>>><"+data2[i]);
                    }
                }
                String anarana=  data1[i].toLowerCase();
                int temps= getFilter(anarana);
                images[i]=temps;
            }
            notifyDataSetChanged();
        }
    };

    public int getFilter(String anarana){
        int valiny=0;
//        if(anarana.equals("antananarivo")){
        valiny = R.drawable.logo;
//        }if(anarana.equals("mahajanga")){
//            valiny = R.drawable.mahajanga;
//        }if(anarana.equals("toamasina")){
//            valiny = R.drawable.toamasina;
//        }if(anarana.equals("toliara")){
//            valiny = R.drawable.toliara;
//        }if(anarana.equals("antsiranana")){
//            valiny = R.drawable.antsiranana;
//        }if(anarana.equals("fianarantsoa")){
//            valiny = R.drawable.fianarantsoa;
//        }
        return valiny;
    }
    @NonNull
    @Override
    public Adapter.MyVieWHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new Adapter.MyVieWHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyVieWHolder myVieWHolder, final int position) {
        myVieWHolder.myText1.setText(data1[position]);
        myVieWHolder.myText2.setText(data3[position]);
        myVieWHolder.myImages.setImageResource(images[position]);

        DetailsCoursMatiere detailsCoursMatiere = new DetailsCoursMatiere();
        final String description = detailsCoursMatiere.getListeDetails().get(data1[position]);
        myVieWHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Fiche.class);
                intent.putExtra("data1", data1[position]);
                intent.putExtra("data2", description);
                intent.putExtra("data3", "Details Cours");
                intent.putExtra("myImage", images[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

}
