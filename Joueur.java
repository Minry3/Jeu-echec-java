/**
 * gestion d'un Joueur
 * @author Clarence EDOH-DAGNON
 */
public class Joueur 
{
    private String nom;
    private String couleur;

    /**
     * constructeur champ a champ
     * @param unNom
     * @param uneCouleur
     */
    public Joueur(String unNom, String uneCouleur){
        this.nom = unNom;
        this.couleur = uneCouleur;
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
     * Setter sur nom
     * @param unNom
     */
    public void setNom(String unNom){
        this.nom = unNom;
    }
    // fin methode setNom
    
}
// fin classe Joueur