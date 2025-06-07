/**
 * gestion d'une Piece
 * @author Noemie CHHUN
 */
public abstract class Piece 
{
    private String couleur;
    private Case uneCase;

    /**
     * constructeur champ a champ
     * @param uneCouleur
     */
    public Piece(String uneCouleur)
    {
        this.couleur = uneCouleur;
    }

    /**
     * Permet de savoir si le deplacement vers la case de destination est possible
     * @param destination
     * @return true si le deplacement est possible, fase sinon
     */
    public abstract boolean deplacement(Case destination);

    /**
     * Getter sur couleur
     * @return la couleur de la piece
     */
    public String getCouleur()
    {
        return this.couleur;
    }
    // fin methode getCouleur

    /**
     * Getter sur uneCase
     * @return la case sur laquelle est la piece
     */
    public Case getCase()
    {
        return this.uneCase;
    }
    // fin methode getCase

    /**
     * Setter sur uneCase
     * @param nouvelleCase
     */
    public void setCase(Case nouvelleCase)
    {
        this.uneCase = nouvelleCase;
    }
    // fin methode setCase

    /**
     * Supprime la case associee a la piece
     */
    public void supprimerCase()
    {
        this.uneCase = null;
    }
    // fin methode supprimerCase

    /**
     * @return la chaine de caracteres representant la piece selon sa couleur
     */
    public abstract String toString();

}
// fin classe Piece
