public class Test{
    public static void main(String[] args)
    {
        Echiquier echiquier = new Echiquier();
        echiquier.init();
        System.out.println(echiquier.toString());
        System.out.println();

        echiquier.getCase("A", 1).supprimerPiece();
        System.out.println(echiquier.toString());

        Case nouvelCase = new Case("E", 2, "blanc");
        System.out.println(echiquier.getCase("E", 1).getPiece().deplacement(nouvelCase));
    }
}