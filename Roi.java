public class Roi extends Piece
{
    public Roi(String couleur)
    {
        super(couleur);
    }

    public Case deplacement(Case destination)
    {
        return null;
    }

    public String toString()
    {
        if(this.getCouleur().equals("blanc"))
            return " ♚ ";
        return " ♔ ";
    }
}
