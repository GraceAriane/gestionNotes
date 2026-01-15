package com.projet.gnotes.models;

public class Utilisateur {
    private int idU;
    private String matricule;
    private String nom;
    private String prenom;
    private String role; // "administrateur", "etudiant", "enseignant"
    private String specialite;
    private String discipline;
    private String grade;

    // Constructeurs
    public Utilisateur() {}

    //constructeur pour les enseignants
    public Utilisateur(int idU, String matricule, String nom, String prenom, String role,
                         String discipline, String grade) {
        this.idU = idU;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.discipline = discipline;
        this.grade = grade;
    }
    //constructeur pour l'etudiant
    public Utilisateur(int idU, String matricule, String nom, String prenom, String role, String specialite) {
        this.idU = idU;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.specialite = specialite;
    }

    // Getters
    public int getId() {
        return idU;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getRole() {
        return role;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getGrade() {
        return grade;
    }

    // Setters
    public void setId(int idU) {
        this.idU = idU;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
