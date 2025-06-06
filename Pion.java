public class Pion extends Piece
{
    public Pion(String couleur)
    {
        super(couleur);
    }

    public boolean deplacement(Case destination)
    {
        int colonneDepart = Echiquier.indiceEtiquette(this.getCase().getEtiquette());
        int colonneArrivee = Echiquier.indiceEtiquette(destination.getEtiquette());
        int ligneDepart = Echiquier.indiceNumero(this.getCase().getNumero());
        int ligneArrivee = Echiquier.indiceNumero(destination.getNumero());
        boolean deplacementSansCapture = false;
        boolean deplacementCapture = false;
        if(this.getCouleur().equals("blanc")){
            deplacementSansCapture = (colonneDepart == colonneArrivee) && ((ligneArrivee - ligneDepart ) == 1);
            deplacementCapture = ((ligneArrivee - ligneDepart) == 1) && (Math.abs(colonneDepart - colonneArrivee) == 1);
        }
        else{
            deplacementSansCapture = (colonneDepart == colonneArrivee) && ((ligneDepart - ligneArrivee ) == 1);
            deplacementCapture = ((ligneDepart - ligneArrivee) == 1) && (Math.abs(colonneDepart - colonneArrivee) == 1);
        }
        
        return deplacementCapture || deplacementSansCapture;
    }

    public String toString()
    {
        if(this.getCouleur().equals("blanc"))
            return " ♟ ";
        return " ♙ ";
    }
}
