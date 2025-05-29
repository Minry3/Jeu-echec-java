public class Cavalier extends Piece
{
    public Cavalier(String couleur)
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
            return " ♞ ";
        return " ♘ ";
    }

}
