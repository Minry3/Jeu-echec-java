/**
 *  gestion d'un Pion
 *  @author Clarence EDOH-DAGNON
 */
public class Pion extends Piece
{
    /**
     * constructeur champ a champ
     * @param couleur
     */
    public Pion(String couleur)
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

            //etat du type de deplacement
            boolean deplacementSansCapture = false;
            boolean deplacementCapture = false;

            // pour les pions blancs
            if(this.getCouleur().equals("blanc"))
            {
                // pour un deplacement sans capture
                if(this.getCase().getNumero() == 2) // si le pion est sur sa case de depart
                    deplacementSansCapture = (colonneDepart == colonneArrivee) && ((ligneArrivee - ligneDepart ) <=2); // peut faire un deplacement jusqu'a 2 cases
                else
                    deplacementSansCapture = (colonneDepart == colonneArrivee) && ((ligneArrivee - ligneDepart ) == 1);
                // pour un deplacement avec capture
                deplacementCapture = ((ligneArrivee - ligneDepart) == 1) && (Math.abs(colonneDepart - colonneArrivee) == 1);
            }
            // pour les pions noirs
            else
            {
                // pour un deplacement sans capture
                if(this.getCase().getNumero() == 7) // si le pion est sur sa case de depart
                    deplacementSansCapture = (colonneDepart == colonneArrivee) && ((ligneDepart - ligneArrivee ) <= 2);
                else
                    deplacementSansCapture = (colonneDepart == colonneArrivee) && ((ligneDepart - ligneArrivee ) == 1);
                // pour un deplacement avec capture
                deplacementCapture = ((ligneDepart - ligneArrivee) == 1) && (Math.abs(colonneDepart - colonneArrivee) == 1);
            }
            
            return deplacementCapture || deplacementSansCapture;
        }
        return false;
    }
    // fin methode deplacement

    /**
     * @return la chaine de caracteres representant le pion selon sa couleur
     */
    public String toString()
    {
        if(this.getCouleur().equals("blanc"))
            return " p ";
        return " P ";
    }
    // fin methode toString
}
// fin classe Pion
