package com.example.interfacegraphique;

public class Tab {
    String enseignant,matiere,heure;
    public Tab(String enseignant,String matiere,String heure) {
        this.enseignant=enseignant;
        this.matiere=matiere;
        this.heure=heure;
    }
    public String getEnseignant(){
        return enseignant;
    }

    public void setEnseignant(String enseignant) {
        this.enseignant = enseignant;
    }

    public String getMatiere(){
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getHeure(){
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
}
