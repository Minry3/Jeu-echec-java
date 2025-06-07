/**
 * gestion d'une Tour
 * @author Noemie CHHUN
 */
public class Tour extends Piece
{
    /**
     * constructeur champ a champ
     * @param couleur
     */
    public Tour(String couleur)
    {
        super(couleur);
    }

    /**
     * Permet de savoir si le deplacement vers la case de destination est possible
     * @param destination la case de destination
     * @return true si le deplacement est possible, false sinon
     */
    public boolean deplacement(Case destination)
    {
        int colonneDepart = Echiquier.indiceEtiquette(this.getCase().getEtiquette());
        int colonneArrivee = Echiquier.indiceEtiquette(destination.getEtiquette());
        int ligneDepart = Echiquier.indiceNumero(this.getCase().getNumero());
        int ligneArrivee = Echiquier.indiceNumero(destination.getNumero());
        
        // le deplacement doit etre horizontal ou vertical
        return (ligneDepart == ligneArrivee || colonneDepart == colonneArrivee);
    }
    // fin methode deplacement

    /**
     * @return la chaine de caracteres representant la tour selon sa couleur
     */
    public String toString()
    {
        if(this.getCouleur().equals("blanc"))
            return " t ";
        return " T ";
    }
    // fin methode toString
}
// fin classe Tour
