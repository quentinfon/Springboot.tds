package s4.spring.quentin.td1.models;

import java.util.ArrayList;

public class Categorie {

    private String libelle;

    private ArrayList<Element> listeElement;

    public Categorie(){
        libelle = "";
        listeElement = new ArrayList<Element>();
    }

    public Categorie(String l){
        libelle = l;
    }

    public String getLibelle() {
        return libelle;
    }

    public ArrayList<Element> getListeElement() {
        return listeElement;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void ajoutElement(Element e){
        listeElement.add(e);
    }

    public void supprimerElement(Element e){
        if (listeElement.contains(e)){
            listeElement.remove(e);
        }
    }
    public void supprimerElement(String s){
        ArrayList<Element> aSupp = new ArrayList<Element>();

        for (Element e: listeElement){
            if (e.getNom().equals(s)){
                aSupp.add(e);
            }
        }

        for (Element e : aSupp){
            listeElement.remove(e);
        }

    }

}
