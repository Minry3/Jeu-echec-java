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

    public boolean estEnEchec(String couleur) {
        // Etape 1 : trouver le roi de la couleur donnée
        Case caseRoi = null;

        int i = 0;
        boolean trouve = false;
        while(i<this.echiquier.getLesCases().length && !trouve)
        {
            int j = 0;
            while(j<this.echiquier.getLesCases()[i].length && !trouve)
            {
                Piece piece = this.echiquier.getLesCases()[i][j].getPiece();
                if(piece != null && piece instanceof Roi && piece.getCouleur().equals(couleur))
                {
                    caseRoi = this.echiquier.getLesCases()[i][j];
                    trouve = true;
                }
                j++;
            }
            i++;
        }
        
        // s'il n'y a pas de case roi trouvee
        if(!trouve)
            return false;

        // Etape 2 : vérifier si une pièce adverse peut attaquer cette case
        boolean enEchec = false;
        i = 0;

        while (i < this.echiquier.getLesCases().length && !enEchec) {
            int j = 0;
            while (j < this.echiquier.getLesCases()[i].length && !enEchec) {
                Piece attaquant = this.echiquier.getLesCases()[i][j].getPiece();
                // s'il y a une piece sur la case et que la couleur est celle de l'adversaire
                if (attaquant != null && !attaquant.getCouleur().equals(couleur)) {
                    // si le deplacement est faisable
                    if (attaquant.deplacement(caseRoi) && verifChemin(this.echiquier.getLesCases()[i][j], caseRoi)) {
                        enEchec = true; // Le roi est attaqué
                    }
                }
                j++;
            }
            i++;
        }
        return enEchec;
    } 
    // fin de la methode estEnEchec

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

        // si les cases ne sont pas sur l'echiquier
        if (caseDepart == null || caseArrivee == null)
            return false;

        // si la case de depart ne contient pas de piece
        if (caseDepart.getPiece() == null)
            return false;

        // si la piece de la case de depart n'est pas de la meme couleur que celle du joueur ayant le trait
        if(this.trait == 1 && !caseDepart.getPiece().getCouleur().equals(this.joueur_1.getCouleur()))
            return false;
        else if(this.trait == 2 && !caseDepart.getPiece().getCouleur().equals(this.joueur_2.getCouleur()))
            return false;

        Piece laPiece = caseDepart.getPiece();

        // Interdit d'aller sur une case avec une pièce de la même couleur
        if (caseArrivee.getPiece() != null && caseArrivee.getPiece().getCouleur().equals(laPiece.getCouleur()))
            return false;

        // si le deplacement de la piece n'est pas possible
        if (!laPiece.deplacement(caseArrivee))
            return false;

        // si il y a une piece situee entre la case de depart et la case d'arrivee
        if (!this.verifChemin(caseDepart, caseArrivee))
            return false;

        // Simulation du coup
        Piece pieceMangee = caseArrivee.getPiece();
        if(pieceMangee != null)
            caseArrivee.supprimerPiece();
        caseArrivee.setPiece(laPiece);
        caseDepart.supprimerPiece();
        laPiece.setCase(caseArrivee);

        // Variable qui serivra a verifier si après la simulation, le roi est en echec
        boolean roiEnEchec = this.estEnEchec(laPiece.getCouleur());

        // Annuler la simulation
        caseDepart.setPiece(laPiece);
        laPiece.setCase(caseDepart);
        if (pieceMangee != null) {
            pieceMangee.setCase(caseArrivee);
        }
        caseArrivee.setPiece(pieceMangee);
        
        // si le roi est en echec return false, sinon le coup est valide donc true
        return !roiEnEchec;
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

   public boolean mat(String couleur){

        if (!estEnEchec(couleur)) {
            return false;
        }

        Case[][] lesCases = this.echiquier.getLesCases();

        for (int i = 0; i < lesCases.length; i++) {
            for (int j = 0; j < lesCases[i].length; j++) {
                Piece piece = lesCases[i][j].getPiece();
                if (piece != null && piece.getCouleur().equals(couleur)) {
                    Case caseDepart = lesCases[i][j];
                    for (int x = 0; x < lesCases.length; x++) {
                        for (int y = 0; y < lesCases[x].length; y++) {
                            Case caseArrivee = lesCases[x][y];

                            String[] leCoup = {caseDepart.getEtiquette() + caseDepart.getNumero(), caseArrivee.getEtiquette() + caseArrivee.getNumero()};

                            // Si le joueur peut faire un coup "legal" avec la pièce en question vers la case en cours de verification
                            if (this.verifCoup(leCoup)) {
                                // Simuler le coup
                                // Simulation du coup
                                Piece pieceMangee = caseArrivee.getPiece();
                                caseArrivee.supprimerPiece();
                                caseArrivee.setPiece(piece);
                                caseDepart.supprimerPiece();
                                piece.setCase(caseArrivee);
                                
                                // Variable qui serivra a verifier si après la simulation, le roi est toujours en echec
                                boolean encoreEnEchec = estEnEchec(couleur);
                                
                                //On retourne à l'état avant la simulation
                                caseDepart.setPiece(piece);
                                piece.setCase(caseDepart);
                                if (pieceMangee != null) {
                                    pieceMangee.setCase(caseArrivee);
                                }
                                caseArrivee.setPiece(pieceMangee);

                                // Si le roi n'est plus en echec
                                if (!encoreEnEchec) {
                                    return false; // au moins un coup sauve du mat
                                }
                            }
                        }
                    }
                }
            }
        }
        return true; // aucun coup possible pour éviter donc échec et mat

    }// fin de la methode mat

    public boolean pat(String couleur) {

    // Si le roi est en echec, ce n'est pas un pat 
        if (estEnEchec(couleur)) {
            return false;
        }

        Case[][] lesCases = this.echiquier.getLesCases();

        // On parcourt toutes les cases
        for (int i = 0; i < lesCases.length; i++) {
            for (int j = 0; j < lesCases[i].length; j++) {
                Case caseDepart = lesCases[i][j];
                Piece piece = caseDepart.getPiece();

                // Si la case contient une pièce de la bonne couleur
                if (piece != null && piece.getCouleur().equals(couleur)) {

                    // On parcourt toutes les cases possibles comme destinations
                    for (int x = 0; x < lesCases.length; x++) {
                        for (int y = 0; y < lesCases[x].length; y++) {
                            Case caseDestination = lesCases[x][y];

                            if (piece.deplacement(caseDestination)) {
                                // Sauvegarde de l’état initial
                                Case ancienneCase = piece.getCase();
                                Piece pieceCapturee = caseDestination.getPiece();

                                // Simulation du coup
                                ancienneCase.supprimerPiece();
                                caseDestination.setPiece(piece);
                                piece.setCase(caseDestination);

                                boolean encoreEnEchec = estEnEchec(couleur);

                                // Annuler le coup simulé
                                caseDestination.supprimerPiece();
                                ancienneCase.setPiece(piece);
                                piece.setCase(ancienneCase);

                                if (pieceCapturee != null) {
                                    caseDestination.setPiece(pieceCapturee);
                                    pieceCapturee.setCase(caseDestination);
                                }

                                // Si le coup sort d’un blocage, alors pas pat
                                if (!encoreEnEchec) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }

        // Aucun coup légal trouvé et pas en échec,donc égalité 
        return true;
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

        if(this.pat(joueur.getCouleur()))
            return 0;
        else if(this.mat(joueur.getCouleur()))
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