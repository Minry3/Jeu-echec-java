/**
 * gestion d'un Roi
 * @author Noemie CHHUN
 */
public class Roi extends Piece
{
    /**
     * constructeur champ a champ
     * @param couleur
     */
    public Roi(String couleur)
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
        
        // le deplacement doit etre de seulement une case, quelque soit la direction
        return (Math.abs(ligneDepart - ligneArrivee) <= 1) && (Math.abs(colonneDepart - colonneArrivee) <= 1);            
    }
    // fin methode deplacement

    /**
     * @return la chaine de caracteres representant le roi selon sa couleur
     */
    public String toString()
    {
        if(this.getCouleur().equals("blanc"))
            return " r ";
        return " R ";
    }
    // fin methode toString
}
// fin classe Roi
