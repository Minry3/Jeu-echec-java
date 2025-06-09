/**
 *  gestion d'une Horloge
 *  @author Clarence EDOH-DAGNON
 */

public class Horloge {

    private long tempsTotal;       // en millisecondes
    private long debutTour;        // horodatage du début du tour, en millisecondes
    private boolean enCours;       // est-ce que le joueur possede le trait ?
    




    /**
     * constructeur par défaut
     * initialise le temps total à 0
     */
    public Horloge() {

        this.tempsTotal = 0;
        this.enCours = false;
    } // fin constructeur


    /**
     * Démarre le chronomètre au début du tour
     */
    public void demarrerTour() {

        this.debutTour = System.currentTimeMillis();
        this.enCours = true;
    } // fin méthode demarrerTour



    /**
     * Arrête le chronomètre à la fin du tour
     */    
    public void finirTour() {

        // si un tour avait déja commencé
        if (this.enCours) { 
            long tempsTour = getTempsEnCours();
            this.tempsTotal += tempsTour;
            this.enCours = false;
        }
    } // fin méthode finirTour



    /**
     * @return le temps total de reflexion du joueur
     */
    public long getTempsTotal() {

        return tempsTotal;
    } // fin méthode getTempsTotal


    /**
     * @return le temps de reflexion du joueur pendant son tour
     */
    public long getTempsEnCours() {

        return System.currentTimeMillis() - this.debutTour;
    } // fin méthode getTempsEnCours
 
    /**
     * affiche la chaine de caractères representant le temps du tour passé
     */
    public void afficherTempsEnCours() {

        long secondes = this.getTempsEnCours() / 1000;
        long minutes = secondes / 60;
        secondes = secondes % 60;

        System.out.printf("%02d:%02d\n", minutes, secondes);
    } 
    // fin méthode afficherTempsEnCours 
    
    /**
     * @return la chaine de caractères representant l'étatde l'horloge
     * Temps total de reflexion du joueur durant la partie
     */
    public String toString() {

        long secondes = this.getTempsTotal() / 1000;
        long minutes = secondes / 60;
        secondes = secondes % 60;

        return String.format("%02d:%02d", minutes, secondes);
    }

}