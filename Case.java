public class Case 
{
    private String etiquette;
    private int numero;
    private String couleur;
    private Piece piece;

    public Case(String uneEtiquette, int unNumero, String uneCouleur)
    {
        this.etiquette = uneEtiquette;
        this.numero = unNumero;
        this.couleur = uneCouleur;
    }

    public String getEtiquette()
    {
        return this.etiquette;
    }

    public int getNumero()
    {
        return this.numero;
    }

    public String getCouleur()
    {
        return this.couleur;
    }
    
    public Piece getPiece()
    {
        return this.piece;
    }

    public void setPiece(Piece nouvellePiece)
    {
        this.piece = nouvellePiece;
    }

    public void supprimerPiece()
    {
        this.piece.supprimerCase();
        this.piece = null;
    }

    public String toString()
    {
        if(this.piece == null)
            return " . ";
        return this.piece.toString();
    }
    
}
