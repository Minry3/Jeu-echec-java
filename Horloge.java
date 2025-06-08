/**
 *  gestion d'une Horloge
 *  @author Clarence EDOH-DAGNON
 */

public class Horloge {

    private Joueur joueur;         //Joueur associe a l'horloge

    private long tempsTotal;       // en millisecondes
    private long debutTour;        // timestamp quand le tour commence
    private boolean enCours;       // est-ce que le joueur possede le trait ?*
    
    
    public Horloge() {
        this.tempsTotal = 0;
        this.enCours = false;
    }

    public void demarrerTour() {
        this.debutTour = System.currentTimeMillis();
        this.enCours = true;
    }

    public void finirTour() {
        if (enCours) {
            long tempsTour = System.currentTimeMillis() - debutTour;
            this.tempsTotal += tempsTour;
            this.enCours = false;
        }
    }

    public long getTempsTotal() {
        if (enCours) {
            return tempsTotal + (System.currentTimeMillis() - debutTour);
        }
        return tempsTotal;
    }

    public long getTempsEnCours() {
        if (!enCours) return 0;
        return System.currentTimeMillis() - debutTour;
    }

}