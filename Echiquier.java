import java.util.*;

/**
 * gestion d'un Echiquier
 */
public class Echiquier
{
    Case[][] lesCases;

    // constantes de classe
    public static final String[] ETIQUETTES = {"A", "B", "C", "D", "E", "F", "G", "H"};
    public static final String[] COULEURS = {"blanc", "noir"};
    public static final int[] NUMEROS = {8, 7, 6, 5, 4, 3, 2, 1};

    /**
     * constructeur vide
     * initialise les cases de l'echiquier
     */
    public Echiquier()
    {
        this.lesCases = new Case[8][8];

        int longueur = this.lesCases.length;
        for(int i=0; i<longueur; i++)
        {
            int numero = Echiquier.NUMEROS[i];
            String couleur = Echiquier.COULEURS[0];
            if(i%2 != 0)
                couleur = Echiquier.COULEURS[1];
            for(int j=0; j<longueur; j++)
            {
                String etiquette = Echiquier.ETIQUETTES[j];
                this.lesCases[i][j] = new Case(etiquette, numero, couleur);
                if(couleur.equals(Echiquier.COULEURS[0]))
                    couleur = Echiquier.COULEURS[1];
                else
                    couleur = Echiquier.COULEURS[0];
            }
        }
    }
    // fin constructeur vide



    /**
     * Getter sur une case du tableau Case[][]
     * @param etiquette l'etiquette de la case
     * @param numero le numero de la case
     * @return la case correspondante
     */
    public Case getCase(String etiquette, int numero)
    {
        int e = Echiquier.indiceEtiquette(etiquette);
        int n = Echiquier.indiceNumero(numero);
        Case laCase = this.lesCases[n][e];
        return laCase;
    }
    // fin methode getCase


    public ArrayList<Piece> getPiecesCouleur(String couleur) {
        ArrayList<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < lesCases.length; i++) {
            for (int j = 0; j < lesCases[i].length; j++) {
                Piece p = lesCases[i][j].getPiece();
                if (p != null && p.getCouleur().equals(couleur)) {
                    pieces.add(p);
                }
            }
        }

        return pieces;
    }


    /**
     * permet de retrouver l'indice correspondant a l'etiquette voulue dans le tableau ETIQUETTES
     * @param etiquette
     * @return l'indice de l'etiquette
     */
    public static int indiceEtiquette(String etiquette)
    {
		int i = 0 ;
		while (!etiquette.equals(Echiquier.ETIQUETTES[i]))
		       i++ ;		    
		return i ;
    }
    // fin methode indiceEtiquette

    /**
     * permet de retrouver l'indice correspondant au numero voulu dans le tableau NUMEROS
     * @param numero
     * @return l'indice du numero
     */
    public static int indiceNumero(int numero)
    {
		int i = 0 ;
		while (numero != Echiquier.NUMEROS[i])
		       i++ ;		    
		return i ;
    }
    // fin methode indiceNumero

    /**
     * @return la chaine de caracteres representant l'echiquier
     * il s'agit d'une representation textuelle de l'etat de l'echiquier
     */
    public String toString()
    {
        int longueur = this.lesCases.length;
        String chaine = "";

        // parcourt le tableau de cases ligne par ligne
        for(int i=0; i<longueur; i++)
        {
            //ajoute l'etiquette de la ligne
            chaine += Echiquier.NUMEROS[i] + " | ";

            //parcourt les cases de la ligne et ajoute leur representation a la chaine
            for(int j=0; j<longueur; j++)
            {
                chaine += this.lesCases[i][j].toString();
            }

            chaine += "\n";
        }

        chaine += "   ";
        for (int i=0; i<longueur; i++)
        {
            chaine += "---";
        }
        chaine += "\n    ";

        // ajout des numeros de chaque colonnes
        for (int i=0; i<longueur; i++)
        {
            chaine += " " + Echiquier.ETIQUETTES[i] + " ";
        }

        return chaine;
    }
    // fin methode toString

    /**
     * place les pieces sur l'echiquier a leur place de depart
     */
    public void init()
    {
        int longueur = this.lesCases.length;
        // parcourt de la longueur d'une ligne
        for(int i=0; i<longueur; i++)
        {
            // creation d'un pion noir et placement du pion sur sa ligne de depart
            Piece lePionNoir = new Pion(Echiquier.COULEURS[1]);
            Case laCase1 = this.getCase(Echiquier.ETIQUETTES[i], 7);
            laCase1.setPiece(lePionNoir);
            lePionNoir.setCase(laCase1);

            // creation d'un pion blanc et placement du pion sur sa ligne de depart
            Piece lePionBlanc = new Pion(Echiquier.COULEURS[0]);
            Case laCase2 = this.getCase(Echiquier.ETIQUETTES[i], 2);
            laCase2.setPiece(lePionBlanc);
            lePionBlanc.setCase(laCase2);
        }

        // parcourt de la longueur d'une ligne
        for(int i=0; i<longueur; i++)
        {
            String etiquette = Echiquier.ETIQUETTES[i];
            int indice = Echiquier.indiceEtiquette(etiquette);

            Piece pieceBlanche;
            Piece pieceNoire;

            // placement des pieces selon leur type et leur position
            if(indice == 0 || indice == 7)
            {
                pieceBlanche = new Tour(Echiquier.COULEURS[0]);
                pieceNoire = new Tour(Echiquier.COULEURS[1]);
            }
            else if(indice == 1 || indice == 6)
            {
                pieceBlanche = new Cavalier(Echiquier.COULEURS[0]);
                pieceNoire = new Cavalier(Echiquier.COULEURS[1]); 
            }
            else if(indice == 2 || indice == 5)
            {
                pieceBlanche = new Fou(Echiquier.COULEURS[0]);
                pieceNoire = new Fou(Echiquier.COULEURS[1]); 
            }
            else if(indice == 3)
            {
                pieceBlanche = new Dame(Echiquier.COULEURS[0]);
                pieceNoire = new Dame(Echiquier.COULEURS[1]); 
            }
            else 
            {
                pieceBlanche = new Roi(Echiquier.COULEURS[0]);
                pieceNoire = new Roi(Echiquier.COULEURS[1]);
            }

            Case case1 = this.getCase(etiquette, 8);
            case1.setPiece(pieceNoire);
            pieceNoire.setCase(case1);

            Case case2 = this.getCase(etiquette, 1);
            case2.setPiece(pieceBlanche);
            pieceBlanche.setCase(case2);
        }
    }
    // fin methode init

    public boolean estEnEchec(String couleur) {
        // Etape 1 : trouver le roi de la couleur donnée
        Case caseRoi = null;

        for (int i = 0; i < lesCases.length; i++) {
            for (int j = 0; j < lesCases[i].length; j++) {
                Piece piece = lesCases[i][j].getPiece();
                if (piece != null && piece instanceof Roi && piece.getCouleur().equals(couleur)) {
                    caseRoi = lesCases[i][j];
                    break;
                }
            }
        }

        // Etape 2 : vérifier si une pièce adverse peut attaquer cette case
        for (int i = 0; i < lesCases.length; i++) {
            for (int j = 0; j < lesCases[i].length; j++) {
                Piece attaquant = lesCases[i][j].getPiece();
                if (attaquant != null && !attaquant.getCouleur().equals(couleur)) {
                    if (attaquant.deplacement(caseRoi)) {
                        return true; // Le roi est attaqué
                    }
                }
            }
        }

        return false; // Aucune menace
    } // fin de la methode estEnEchec

    public boolean mat(String couleur){

        if (!estEnEchec(couleur)) {
            return false;
        }

        for (int i = 0; i < lesCases.length; i++) {
            for (int j = 0; j < lesCases[i].length; j++) {
                Piece piece = lesCases[i][j].getPiece();
                if (piece != null && piece.getCouleur().equals(couleur)) {
                    Case caseDepart = lesCases[i][j];
                    for (int x = 0; x < lesCases.length; x++) {
                        for (int y = 0; y < lesCases[x].length; y++) {
                            Case caseArrivee = lesCases[x][y];

                            // Si le joueur peut faire un coup "legal" avec la pièce en question vers la case en cours de verification
                            if (piece.deplacement(caseArrivee)) {
                                // Simuler le coup
                                Piece sauvegarde = caseArrivee.getPiece();
                                caseArrivee.setPiece(piece);
                                caseDepart.supprimerPiece();
                                piece.setCase(caseArrivee);

                                // Variable qui serivra a verifier si après la simulation, le roi est toujours en echec
                                boolean encoreEnEchec = estEnEchec(couleur);
                                
                                //On retourne à l'état avant la simulation
                                caseDepart.setPiece(piece);
                                caseArrivee.setPiece(sauvegarde);
                                piece.setCase(caseDepart);

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




}
// fin classe Echiquier