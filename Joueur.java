/**
 * gestion d'un Joueur
 * @author Clarence EDOH-DAGNON
 */
public class Joueur 
{
    private String nom;
    private String couleur;
    private Horloge horloge;

    /**
     * constructeur champ a champ
     * @param unNom
     * @param uneCouleur
     */
    public Joueur(String unNom, String uneCouleur){
        this.nom = unNom;
        this.couleur = uneCouleur;
        this.horloge = new Horloge();
    }

    /**
     * Getter sur nom
     * @return le nom du joueur
     */
    public String getNom(){
        return this.nom;
    }
    // fin methode getNom

    /**
     * Getter sur couleur
     * @return la couleur associee au joueur
     */
    public String getCouleur(){
        return this.couleur;
    }
    // fin methode getCouleur

    /**
     * Getter sur horloge
     * @return l'horloge du joueur
     */
    public Horloge getHorloge()
    {
        return this.horloge;
    }
    // fin methode getHorloge
    
    /**
     * Setter sur nom
     * @param unNom
     */
    public void setNom(String unNom){
        this.nom = unNom;
    }
    // fin methode setNom
    
}
// fin classe Joueur