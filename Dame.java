public class Dame extends Piece
{
    public Dame(String couleur)
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
            return " ♛ " ;
        return " ♕ ";
    }

}
