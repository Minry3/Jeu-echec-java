public class Cavalier extends Piece
{
    public Cavalier(String couleur)
    {
        super(couleur);
    }

    public boolean deplacement(Case destination)
    {
        return false;
    }

    public String toString()
    {
        if(this.getCouleur().equals("blanc"))
            return " ♞ ";
        return " ♘ ";
    }

}
