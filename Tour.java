public class Tour extends Piece
{
    public Tour(String couleur)
    {
        super(couleur);
    }

    public boolean deplacement(Case destination)
    {
        int colonneDepart = Echiquier.indiceEtiquette(this.getCase().getEtiquette());
        int colonneArrivee = Echiquier.indiceEtiquette(destination.getEtiquette());
        int ligneDepart = Echiquier.indiceNumero(this.getCase().getNumero());
        int ligneArrivee = Echiquier.indiceNumero(destination.getNumero());
        return (ligneDepart == ligneArrivee || colonneDepart == colonneArrivee);
    }

    public String toString()
    {
        if(this.getCouleur().equals("blanc"))
            return " ♜ ";
        return " ♖ ";
    }
}
