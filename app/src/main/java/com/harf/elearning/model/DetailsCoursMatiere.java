package com.harf.elearning.model;

import org.w3c.dom.Text;

import java.util.HashMap;

public class DetailsCoursMatiere {

    public HashMap<String, String> listeDetails = new HashMap<>();

    public HashMap<String, String> getListeDetails() {
        return listeDetails;
    }

    public DetailsCoursMatiere(){
        assignDetails();
    }

    public void assignDetails(){
        this.listeDetails.put("Exercice","Calculer le résultat des additions posées suivantes :\n" +
                "9 + 10 \n" +
                "10 +  10 ");
        this.listeDetails.put("Calcul","Addition :\n" +
                "12 + 12 = 24 \n" +
                "14 + 2 = 16");
        this.listeDetails.put("Table","Multiplication de 2 \n" +
                "2 * 2 = 4 \n" +
                "2 * 3 = 6 \n" +
                "2 * 4 = 8 \n" +
                "2 * 5 = 10 \n" +
                "2 * 6 = 12 \n" +
                "2 * 7 = 14 \n" +
                "2 * 8 = 16 \n" +
                "2 * 9 = 18 \n" +
                "2 * 10 = 20");
        this.listeDetails.put("Grammaire","Le complément du nom sans article est au singulier quand il s'agit :\n" +
                "\n" +
                "-D'une matière, d'une quantité qu'on ne peut compter, diviser\n" +
                "un kilo de beurre, une botte de foin \n" +
                "\n" +
                "-  D'un nom abstrait\n" +
                "une poussée de fièvre, un accès de colère\n" +
                "\n" +
                "-  D'une caractéristique ou d'une destination unique\n" +
                "    un fruit à noyau, une chaîne de montre");
        this.listeDetails.put("Orthographe","Pas encore disponible");
    }
}
