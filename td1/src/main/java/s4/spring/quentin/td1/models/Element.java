package s4.spring.quentin.td1.models;

public class Element {

    private String nom;
    private int evaluation = 0;

    public Element(){

    }

    public Element(String s){
        nom = s;
    }

    public boolean equals(Element e){
        if(e.getNom().equals(this.nom)){
            return true;
        }else{
            return false;
        }
    }

    public String getNom(){
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEvaluation(int e) {
        this.evaluation = e;
    }

    public int getEvaluation() {
        return evaluation;
    }
}
