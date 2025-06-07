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
}
// fin classe Echiquier