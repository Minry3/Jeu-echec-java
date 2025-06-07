/**
 * gestion de MauvaisChoixException
 * cette exception est levee lorsque l'user ne choisi pas un des choix propose
 * @author Noemie CHHUN
 */
public class MauvaisChoixException extends Exception
{
    /**
     * constructeur champ a champ
     */
    public MauvaisChoixException(String message) 
    {
        super(message);
    }
}
// fin classe MauvaisChoixException