/**
 * gestion d'une ChaineVideException
 * cette exception est levee lorsqu'une chaine est vide
 * @author Noemie CHHUN
 */
public class ChaineVideException extends Exception
{
    /**
     * constructeur champ a champ
     * @param message
     */
    public ChaineVideException(String message) 
    {
        super(message);
    }
}
// fin classe ChaineVideException
