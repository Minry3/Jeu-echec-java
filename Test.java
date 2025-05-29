public class Test{
    public static void main(String[] args)
    {
        Echiquier echiquier = new Echiquier();
        echiquier.init();
        System.out.println(echiquier.toString());

        echiquier.getCase("A", 1).supprimerPiece();
        System.out.println(echiquier.toString());
    }
}