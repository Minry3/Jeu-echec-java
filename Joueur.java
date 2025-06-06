
public class Joueur {
    private String nom;
    private String couleur;


    public Joueur(String unNom, String uneCouleur){
        this.nom = unNom;
        this.couleur = uneCouleur;
    }

    public String getNom(){
        return this.nom;
    }

    public String getCouleur(){
        return this.couleur;
    }
    
    public void setNom(String unNom){
        this.nom = unNom;
    }
    
}