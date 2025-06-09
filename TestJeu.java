import java.util.*;

/**
 * gestion de TestJeu
 * classe permettant de simuler et de tester une partie de jeu d'echec
 * @author Noemie CHHUN
 */
public class TestJeu{
    public static void main(String[] args)
    {
        boolean continuer = true;   //etat du programme
        Scanner sc = new Scanner(System.in);

        //on continue le programme tant que l'user veut continuer de jouer
        while(continuer)
        {
            int choix = 0;

            //demande de choix a l'utilisateur
            while(choix == 0)
            {

                System.out.println("\nQue souhaitez-vous faire ?\n");
                System.out.println("1.  Commencer une nouvelle partie");
                System.out.println("2.  Quitter\n");
                try
                {
                    //saisie du choix par l'user
                    System.out.print("Saisissez votre choix : ");
                    int leChoix = sc.nextInt();
                    sc.nextLine();
                    System.out.println();
                    
                    
                    //si le choix n'est pas le bon, on retourne une exception
                    if (leChoix != 1 && leChoix != 2) 
                    {
                        throw (new MauvaisChoixException("\nErreur : entrez 1 ou 2 uniquement.\n"));
                    }

                    choix = leChoix;
                }
                catch(InputMismatchException | MauvaisChoixException e) //recupere les exceptions en cas de saisie d'un autre type qu'un int, ou d'une valeur differente de 1 ou 2
                {
                    System.out.println("\nAttention !\nEntrez la valeur 1 ou 2 selon votre choix.\n");
                    sc.nextLine(); //vide l'entree du scanner pour eviter une boucle infinie
                }
                
            }

            //si quitter
            if(choix == 2)
            {
                System.out.println("Au revoir !\n");
                continuer = false;
            }

            //si commencer une nouvelle partie
            else
            {
                System.out.println("Nouvelle partie lancée !");

                String nom1 = "";
                String nom2 = "";

                //verification de la saisie du nom du joueur 1
                boolean nom1Correcte = false;
                while(!nom1Correcte)
                {
                    try
                    {
                        //saisie des noms des joueurs par l'user
                        System.out.println("Veuillez saisir le nom du Joueur 1 (blanc) : ");
                        nom1 = sc.nextLine();
                        if(nom1.trim().isEmpty())
                            throw (new ChaineVideException("La chaine est vide"));
                        nom1Correcte = true;
                    }
                    catch(ChaineVideException e)
                    {
                        System.out.println("Attention !\nEntrez au moins un caractère.\n");
                    }
                }
                
                //verification de la saisie du nom du joueur 2
                boolean nom2Correcte = false;
                while(!nom2Correcte)
                {   
                    try
                    {
                        System.out.println("Veuillez saisir le nom du Joueur 2 (noir) : ");
                        nom2 = sc.nextLine();
                        if(nom2.trim().isEmpty())
                            throw (new ChaineVideException("La chaine est vide"));
                        nom2Correcte = true;
                    }
                    catch(ChaineVideException e)
                    {
                        System.out.println("Attention !\nEntrez au moins un caractère.\n");
                    }
                }

                //creation des joueurs
                Joueur joueur_1 = new Joueur(nom1, "blanc");
                Joueur joueur_2 = new Joueur(nom2, "noir");

                //creation de la partie
                Partie partie = new Partie(joueur_1, joueur_2);
                System.out.println("!! Commençons !!");
                //affichage de l'etat de la partie (echiquier, coups joues...)
                System.out.println(partie.toString());

                while(partie.finDePartie() == -1)
                {
                    //saisie du coup
                    String[] leCoup = partie.saisirCoup();
                    System.out.println();

                    //tant que le coup n'est pas faisable
                    while(!partie.verifCoup(leCoup))
                    {
                        System.out.println("Le coup saisi n'est pas valide.");
                        System.out.println();
                        leCoup = partie.saisirCoup();
                    }

                    partie.jouerCoup(leCoup);
                    System.out.println("Coup saisi !\n");

                    //affichage de l'etat de la partie (echiquier, coups joues...)
                    System.out.println(partie.toString());
                }

                //affichage du resultat de la partie
                int finPartie = partie.finDePartie();
                if(finPartie == 1)
                    System.out.println(joueur_1.getNom() + " a gagné la partie !");
                else if(finPartie == 2)
                    System.out.println(joueur_2.getNom() + " a gagné la partie !");
                else
                    System.out.println("Partie nulle");
            }
        }
        sc.close(); //fermeture du scanner
    }
}
// fin classe TestJeu