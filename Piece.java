public abstract class Piece 
{
    private String couleur;
    private Case uneCase;

    public Piece(String uneCouleur)
    {
        this.couleur = uneCouleur;
    }

    public abstract boolean deplacement(Case destination);

    public String getCouleur()
    {
        return this.couleur;
    }

    public Case getCase()
    {
        return this.uneCase;
    }

    public void setCase(Case nouvelleCase)
    {
        this.uneCase = nouvelleCase;
    }

    public void supprimerCase()
    {
        this.uneCase = null;
    }

    public abstract String toString();

}
