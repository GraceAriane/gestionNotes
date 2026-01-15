package com.projet.gnotes.models;


public class Matiere {
    private int id;
    private String code;
    private String intitule;
    private int credits;

    public Matiere() {}

    public Matiere(String code, String intitule, int credits) {
        this.code = code;
        this.intitule = intitule;
        this.credits = credits;
    }

    public Matiere(int id, String code, String intitule, int credits) {
        this.id = id;
        this.code = code;
        this.intitule = intitule;
        this.credits = credits;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getIntitule() { return intitule; }
    public void setIntitule(String intitule) { this.intitule = intitule; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
}
