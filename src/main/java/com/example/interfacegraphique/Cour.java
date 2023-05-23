package com.example.interfacegraphique;

public class Cour {
    public int id;
    public String matiere, enseignant, classe, anneeScolaire;

    public Cour() {
        this.id = id;
        this.matiere = matiere;
        this.enseignant = enseignant;
        this.classe = classe;
        this.anneeScolaire = anneeScolaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(String enseignant) {
        this.enseignant = enseignant;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }
}
