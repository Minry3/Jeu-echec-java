public class Pion extends Piece
{
    public Pion(String couleur)
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
            return " p ";
        return " P ";
    }
}
