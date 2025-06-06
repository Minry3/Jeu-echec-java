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
        this.joueur_1 = leJoueur1;
        this.joueur_2 = leJoueur2;
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

        System.out.println("Veuillez saisir votre case actuelle");
        System.out.println();

        t[0] = sc.nextLine();
        System.out.println();

        System.out.println("Veuillez proposer un coup");
        System.out.println();

        t[1] = sc.nextLine();
        sc.close();
        
        return t;

    }

    public boolean verifChemin(Case depart, Case destination)
    {
        Piece laPiece = depart.getPiece();
        if(laPiece instanceof Roi || laPiece instanceof Cavalier)
            return true;
        return false;
    }

    public boolean verifCoup(String[] leCoup)
    {
        String etiquetteD = leCoup[0].substring(0, 1).toUpperCase();
        String etiquetteA = leCoup[1].substring(0, 1).toUpperCase();
        int numeroD = (int)leCoup[0].charAt(1);
        int numeroA = (int)leCoup[1].charAt(1);

        Case caseDepart = this.echiquier.getCase(etiquetteD, numeroD);
        Case caseArrivee = this.echiquier.getCase(etiquetteA, numeroA);

        if(caseDepart == null || caseArrivee == null)
            return false;
        if(caseDepart.getPiece() == null)
            return false;
        if(this.trait == 1 && !caseDepart.getPiece().getCouleur().equals(this.joueur_1.getCouleur()))
                return false;
        if(!caseDepart.getPiece().getCouleur().equals(joueur_2.getCouleur()))
            return false;
        
        Piece laPiece = caseDepart.getPiece();

        if(caseArrivee.getPiece() == null || !caseArrivee.getPiece().getCouleur().equals(laPiece.getCouleur()))
            return false;

        return true;
        
        
    }

    public void ajouterCoupJoue(String[] leCoup){

        this.coupsJoues.add(leCoup);
    }

    public void ajouterPieceMangee(Piece laPiece){

        this.piecesMangees.add(laPiece);
    }

    public void afficheCoupJoues(){
        System.out.println(this.getCoupsJoues().toString());
    }

    public void jouerCoup(String[] leCoup){
        this.echiquier.getCase(leCoup[1].charAt(0), Integer.parseInt(leCoup[1].charAt(0)));


    }





}