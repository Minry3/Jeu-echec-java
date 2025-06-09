/**
 * gestion d'une chaine de mauvaise longueur
 * cette exception est levee lorsque la chaine n'est pas de la longueur attendue
 * @author Noemie CHHUN
 */
public class MauvaiseLongueurException extends Exception
{
    /**
     * constructeur champ a champ
     * @param message
     */
    public MauvaiseLongueurException(String message)
    {
        super(message);
    }
}
// fin classe MauvaiseLongueurException
