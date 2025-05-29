public abstract class Piece 
{
    private String couleur;
    private Case uneCase;

    public Piece(String uneCouleur)
    {
        this.couleur = uneCouleur;
    }

    public abstract Case deplacement(Case destination);

    public String getCouleur()
    {
        return this.couleur;
    }

    public void setCase(Case nouvelleCase)
    {
        this.uneCase = nouvelleCase;
    }

    public abstract String symbole();

    public String toString()
    {
        return null;
    }

}
