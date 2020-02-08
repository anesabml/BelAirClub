package modele;


import java.util.ArrayList;

public class Activite {
    private int code;
    private String designation;
    private double prix;
    private ArrayList<Creneaux> creneaux;

    public Activite() {
        creneaux = new ArrayList<Creneaux>();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
       this.code = code;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public ArrayList<Creneaux> getCreneaux() {
        return creneaux;
    }

    public void setCreneaux(ArrayList<Creneaux> creneaux) {
        this.creneaux = creneaux;
    }
}
