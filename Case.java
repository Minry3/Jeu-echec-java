/**
 *  gestion d'une Case
 *  @author Noemie CHHUN
 */
public class Case 
{
    private String etiquette;
    private int numero;
    private String couleur;
    private Piece piece;

    /**
     * Constructeur par copie
     * @param uneEtiquette
     * @param unNumero
     * @param uneCouleur
     */
    public Case(String uneEtiquette, int unNumero, String uneCouleur)
    {
        this.etiquette = uneEtiquette;
        this.numero = unNumero;
        this.couleur = uneCouleur;
    }


    /**
     * Getter sur etiquette
     * @return l'etiquette de la case
     */
    public String getEtiquette()
    {
        return this.etiquette;
    }
    // fin methode getEtiquette

    /**
     * Getter sur numero
     * @return le numero de la case
     */
    public int getNumero()
    {
        return this.numero;
    }
    // fin methode getNumero

    /**
     * Getter sur couleur
     * @return la couleur de la case
     */
    public String getCouleur()
    {
        return this.couleur;
    }
    // fin methode getCouleur
    
    /**
     * Getter sur piece
     * @return la piece de la case ou null s'il n'y en a pas
     */
    public Piece getPiece()
    {
        return this.piece;
    }
    // fin methode getPiece

    /**
     * Setter sur piece
     * @param nouvellePiece
     */
    public void setPiece(Piece nouvellePiece)
    {
        this.piece = nouvellePiece;
    }
    // fin methode setPiece

    /**
     * Supprime la piece sur la case
     */
    public void supprimerPiece()
    {
        this.piece.supprimerCase();
        this.piece = null;
    }
    // fin methode supprimerPiece

    /**
     * @return la chaine de caracteres representant la case courante
     */
    public String toString()
    {
        // si la case n'a pas de piece
        if(this.piece == null)
            return " . ";
        return this.piece.toString();
    }
    // fin methode toString
}
