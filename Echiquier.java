public class Echiquier
{
    Case[][] lesCases;
    public static final String[] ETIQUETTES = {"A", "B", "C", "D", "E", "F", "G", "H"};
    public static final String[] COULEURS = {"blanc", "noir"};
    public static final int[] NUMEROS = {8, 7, 6, 5, 4, 3, 2, 1};
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

    public Case getCase(String etiquette, int numero)
    {
        int e = Echiquier.indiceEtiquette(etiquette);
        int n = Echiquier.indiceNumero(numero);
        Case laCase = this.lesCases[n][e];
        return laCase;
    }

    public static int indiceEtiquette(String etiquette)
    {
		int i = 0 ;
		while (!etiquette.equals(Echiquier.ETIQUETTES[i]))
		       i++ ;		    
		return i ;
    }

    public static int indiceNumero(int numero)
    {
		int i = 0 ;
		while (numero != Echiquier.NUMEROS[i])
		       i++ ;		    
		return i ;
    }

    public String toString()
    {
        int longueur = this.lesCases.length;
        String chaine = "";
        for(int i=0; i<longueur; i++)
        {
            chaine += Echiquier.NUMEROS[i] + " | ";
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
        for (int i=0; i<longueur; i++)
        {
            chaine += " " + Echiquier.ETIQUETTES[i] + " ";
        }
        return chaine;
    }

    public void init()
    {
        int longueur = this.lesCases.length;
        for(int i=0; i<longueur; i++)
        {
            Piece lePionNoir = new Pion(Echiquier.COULEURS[1]);
            Case laCase1 = this.getCase(Echiquier.ETIQUETTES[i], 7);
            laCase1.setPiece(lePionNoir);
            lePionNoir.setCase(laCase1);

            Piece lePionBlanc = new Pion(Echiquier.COULEURS[0]);
            Case laCase2 = this.getCase(Echiquier.ETIQUETTES[i], 2);
            laCase2.setPiece(lePionBlanc);
            lePionBlanc.setCase(laCase2);
        }


        for(int i=0; i<longueur; i++)
        {
            String etiquette = Echiquier.ETIQUETTES[i];
            int indice = Echiquier.indiceEtiquette(etiquette);

            Piece pieceBlanche;
            Piece pieceNoire;

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
}