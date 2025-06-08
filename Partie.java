import java.util.*;

public class Partie {

    private ArrayList<String[]> coupsJoues;
    private ArrayList<Piece> piecesMangees;
    private Joueur joueur_1;
    private Joueur joueur_2;
    private int trait;
    private Echiquier echiquier;

    public Partie(Joueur leJoueur1, Joueur leJoueur2){

        this.coupsJoues = new ArrayList<String[]>();
        this.piecesMangees = new ArrayList<Piece>();
        this.echiquier = new Echiquier();
        this.echiquier.init();
        this.joueur_1 = leJoueur1;
        this.joueur_2 = leJoueur2;
        this.trait = 1;
    }

    public ArrayList<String[]> getCoupsJoues(){
        return this.coupsJoues;
    }
    
    public ArrayList<Piece> getPiecesMangees(){
        return this.piecesMangees;
    }

    public Joueur getJoueur1(){
        return this.joueur_1;
    }


    public Joueur getJoueur2(){
        return this.joueur_2;
    }

    public String[] saisirCoup(){
        String[] t = new String[2];
        Scanner sc = new Scanner(System.in);

        System.out.println("Veuillez saisir la case de votre pièce : ");

        t[0] = sc.nextLine();
        System.out.println();

        System.out.println("Veuillez proposer un coup : ");
        System.out.println();

        t[1] = sc.nextLine();
        
        return t;

    }

    public boolean verifChemin(Case depart, Case destination){
        // On récupère la pièce à déplacer
        Piece laPiece = depart.getPiece();  

        // Si c’est un roi ou un cavalier, pas besoin de vérifier le chemin
        if (laPiece instanceof Roi || laPiece instanceof Cavalier)
            return true;

        // On récupère les indices de colonnes 
        int colDep = Echiquier.indiceEtiquette(depart.getEtiquette());
        int colArr = Echiquier.indiceEtiquette(destination.getEtiquette());

        // On récupère les indices de lignes
        int ligDep = Echiquier.indiceNumero(depart.getNumero());
        int ligArr = Echiquier.indiceNumero(destination.getNumero());

        // On détermine la direction du mouvement :

        // dCol = -1, 0 ou 1 → déplacement vers la gauche, aucun, ou la droite
        // dLig = -1, 0 ou 1 → déplacement vers le haut, aucun, ou le bas
        int dCol = Integer.compare(colArr, colDep);
        int dLig = Integer.compare(ligArr, ligDep);

        // On commence à la case juste après la case de départ
        int col = colDep + dCol;
        int lig = ligDep + dLig;

        // On avance jusqu’à la case juste avant la destination
        while (col != colArr || lig != ligArr) {
            // On récupère la case intermédiaire
            Case inter = this.echiquier.getCase(Echiquier.ETIQUETTES[col], Echiquier.NUMEROS[lig]);

            // S’il y a une pièce sur cette case, le chemin est bloqué → retour faux
            if (inter.getPiece() != null)
                return false;

            // On continue dans la même direction
            col += dCol;
            lig += dLig;
        }

        // Si toutes les cases intermédiaires sont vides, alors chemin libre
        return true;
    }

    public boolean verifCoup(String[] leCoup)
    {
        String etiquetteD = leCoup[0].substring(0, 1).toUpperCase();
        String etiquetteA = leCoup[1].substring(0, 1).toUpperCase();
        int numeroD = Character.getNumericValue(leCoup[0].charAt(1));
        int numeroA = Character.getNumericValue(leCoup[1].charAt(1));

        Case caseDepart = this.echiquier.getCase(etiquetteD, numeroD);
        Case caseArrivee = this.echiquier.getCase(etiquetteA, numeroA);

        if (caseDepart == null || caseArrivee == null)
            return false;

        if (caseDepart.getPiece() == null)
            return false;

        String couleurJoueurActuel = (this.trait == 1) ? this.joueur_1.getCouleur() : this.joueur_2.getCouleur();
        if (!caseDepart.getPiece().getCouleur().equals(couleurJoueurActuel))
            return false;

        Piece laPiece = caseDepart.getPiece();

        // Interdit d'aller sur une case avec une pièce de la même couleur
        if (caseArrivee.getPiece() != null && caseArrivee.getPiece().getCouleur().equals(laPiece.getCouleur()))
            return false;

        if (!laPiece.deplacement(caseArrivee))
            return false;

        if (!this.verifChemin(caseDepart, caseArrivee))
            return false;

        // à ce stade, le coup est potentiellement valide
        return true;
    }

    public void ajouterCoupJoue(String[] leCoup){

        this.coupsJoues.add(leCoup);
    }

    public void ajouterPieceMangee(Piece laPiece){

        this.piecesMangees.add(laPiece);
    }


    public void jouerCoup(String[] leCoup){
        String etiquetteD = leCoup[0].substring(0, 1).toUpperCase();
        String etiquetteA = leCoup[1].substring(0, 1).toUpperCase();
        int numeroD = Character.getNumericValue(leCoup[0].charAt(1));
        int numeroA = Character.getNumericValue(leCoup[1].charAt(1));

        Case caseDepart = this.echiquier.getCase(etiquetteD, numeroD);
        Case caseArrivee = this.echiquier.getCase(etiquetteA, numeroA);

        if(caseArrivee.getPiece() != null)
        {
            this.ajouterPieceMangee(caseArrivee.getPiece());
            caseArrivee.supprimerPiece();
        }
        Piece piece = caseDepart.getPiece();
        caseDepart.supprimerPiece();
        caseArrivee.setPiece(piece); 
        piece.setCase(caseArrivee);
        this.ajouterCoupJoue(leCoup);
        if(this.trait == 1)
            this.trait = 2;
        else
            this.trait = 1;
    }

  

    public int finDePartie()
    {   
        Joueur joueur;
        int numAdversaire;

        if(this.trait == 1){
            joueur = this.getJoueur1();
            numAdversaire = 2;            
        }
        else {
            joueur = this.getJoueur2();
            numAdversaire = 1;            
        }

        if(this.echiquier.pat(joueur.getCouleur()))
            return 0;
        else if(this.echiquier.mat(joueur.getCouleur()))
            return numAdversaire;
        else 
            return -1;
    }

    public String toString()
    {
        String chaine = "-----------------------------\n\n";

        chaine +=   "Joueur 1 (blanc) : "       +   this.joueur_1.getNom()  ;
        if(this.trait == 1)
            chaine += " (a le trait)";
        
        chaine +=   "\nJoueur 2 (noir)  : "     +   this.joueur_2.getNom()  ;
        if(this.trait == 2)
            chaine += " (a le trait)";

        chaine +=   "\n\nEtat de la partie:\n\n" + this.echiquier.toString() +
                    "\n\nListe des coups  : ["   ;

        for(int i=0; i<this.coupsJoues.size(); i++)
        {
            chaine += "(" + coupsJoues.get(i)[0] + ", " + coupsJoues.get(i)[1] + ")";
        }
        
        chaine += "]\nPièces mangées   : [";
                    
        for(int i=0; i<this.piecesMangees.size(); i++)
        {
            chaine += piecesMangees.get(i).toString();
        }
        chaine += "]\n\n-----------------------------";

        return chaine;
    }
}