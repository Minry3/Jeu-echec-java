/**
 *  gestion d'un Fou
 *  @author Noemie CHHUN
 */
public class Fou extends Piece
{
    /**
     * constructeur champ a champ
     * @param couleur
     */
    public Fou(String couleur)
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
        
        //le deplacement doit etre en diagonale
        return (Math.abs(ligneDepart - ligneArrivee) == Math.abs(colonneDepart - colonneArrivee));
    }
    // fin methode deplacement

    /**
     * @return la chaine de caracteres representant le fou selon sa couleur
     */
    public String toString()
    {
        if(this.getCouleur().equals("blanc"))
            return " f ";
        return " F ";
    }
}
// fin classe Fou
