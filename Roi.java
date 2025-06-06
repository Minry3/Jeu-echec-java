

public class Roi extends Piece
{
    public Roi(String couleur)
    {
        super(couleur);
    }

    public boolean deplacement(Case destination)
    {
        int colonneDepart = Echiquier.indiceEtiquette(this.getCase().getEtiquette());
        int colonneArrivee = Echiquier.indiceEtiquette(destination.getEtiquette());
        int ligneDepart = Echiquier.indiceNumero(this.getCase().getNumero());
        int ligneArrivee = Echiquier.indiceNumero(destination.getNumero());
        return (Math.abs(ligneDepart - ligneArrivee) <= 1) && (Math.abs(colonneDepart - colonneArrivee) <= 1);
    }

    public String toString()
    {
        if(this.getCouleur().equals("blanc"))
            return " r ";
        return " R ";
    }
}
