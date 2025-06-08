/**
 *  gestion d'un Cavalier
 *  @author Clarence EDOH-DAGNON
 */
public class Cavalier extends Piece
{

    /**
     * constructeur champ a champ
     * @param couleur
     */
    public Cavalier(String couleur)
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

        if(destination != null){
            int colonneDepart = Echiquier.indiceEtiquette(this.getCase().getEtiquette());
            int colonneArrivee = Echiquier.indiceEtiquette(destination.getEtiquette());
            int ligneDepart = Echiquier.indiceNumero(this.getCase().getNumero());
            int ligneArrivee = Echiquier.indiceNumero(destination.getNumero());
        
            //le deplacement doit etre en "L", donc de deux cases dans une direction et de 1 case dans l'autre
            return  ((Math.abs(ligneDepart - ligneArrivee) == 1) && (Math.abs(colonneDepart - colonneArrivee) == 2)) || 
                    ((Math.abs(ligneDepart - ligneArrivee) == 2) && (Math.abs(colonneDepart - colonneArrivee) == 1));
        }
        
        return false;
    }
    // fin methode deplacement

    /**
     * @return la chaine de caracteres representant le cavalier selon sa couleur
     */
    public String toString()
    {
        if(this.getCouleur().equals("blanc"))
            return " c ";
        return " C ";
    }
    // fin methode toString

}
// fin classe Cavalier