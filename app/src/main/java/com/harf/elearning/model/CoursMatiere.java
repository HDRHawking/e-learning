package com.harf.elearning.model;

import java.util.HashMap;

public class CoursMatiere {

    HashMap<String,String[]> listeCours=new HashMap<String,String[]>();

    public CoursMatiere(){
        assignListeCours();
    }

    public HashMap<String, String[]> getListeCours() {
        return listeCours;
    }

    public void assignListeCours(){
        this.listeCours.put("MAT0001",new String[]{"Exercice","Calcul","Table"});
        this.listeCours.put("MAT0002",new String[]{"Grammaire","Orthographe"});
    }
}
