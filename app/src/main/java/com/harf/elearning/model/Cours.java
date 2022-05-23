package com.harf.elearning.model;

public class Cours {

    public static final String _id = "id";
    public static final String _libelle = "libelle";
    public static final String _description = "description";
    public static final String _image = "image";

    public String ListeDescription[] = new String[6];
    public String ListeLibelle[] = new String[6];
    public String ListeId[] = new String[6];

    private String id,libelle, description,image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Cours(String id, String libelle, String description) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String [] getListeId(){ return ListeId; }
    public String [] getListeLibelle(){ return ListeLibelle; }
    public String [] getListeDescription(){ return ListeDescription; }

    public void setListeMatiere(){
        ListeId[0] = "MAT0001";
        ListeId[1] = "MAT0002";
        ListeId[2] = "MAT0003";
        ListeId[3] = "MAT0004";
        ListeId[4] = "MAT0005";
        ListeId[5] = "MAT0006";
        ListeLibelle[0] = "MATHEMATIQUES";
        ListeLibelle[1] = "FRANCAIS";
        ListeLibelle[2] = "MALAGASY";
        ListeLibelle[3] = "LOGIQUE";
        ListeLibelle[4] = "PHYSIQUES";
        ListeLibelle[5] = "HISTOIRE";
        ListeDescription[0] = "Mathematiques de bases";
        ListeDescription[1] = "Francais ecrit et Grammaire";
        ListeDescription[2] = "Literature Malagasy ";
        ListeDescription[3] = "Un peu de logique";
        ListeDescription[4] = "Cours de physique simple";
        ListeDescription[5] = "Ce cours est specialise pour les connaitres notre monde entier";
    }

    public Cours(){
        setListeMatiere();
    }
}
