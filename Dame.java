/**
 *  gestion d'une Dame
 *  @author Clarence EDOH-DAGNON
 */
public class Dame extends Piece {

    /**
     * constructeur champ a champ
     * @param couleur
     */
    public Dame(String couleur)
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

        //verification si le deplacement est vertical ou en diagonale
        boolean deplacementHorizontalOuVertical =  colonneDepart == colonneArrivee || ligneDepart == ligneArrivee;
        boolean deplacementDiagonale = Math.abs(colonneArrivee - colonneDepart) == Math.abs(ligneArrivee - ligneDepart);

        //le deplacement doit etre soit vertical, soit en diagonale
        return deplacementHorizontalOuVertical || deplacementDiagonale;
    }
    // fin methode deplacement

    /**
     * @return la chaine de caracteres representant la dame selon sa couleur
     */
    public String toString(){

        if(this.getCouleur().equals("blanc"))
            return " d " ;
        return " D ";
    }
    // fin methode toString

}
// fin classe Dame
