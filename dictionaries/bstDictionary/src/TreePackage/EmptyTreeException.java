package TreePackage;

public class EmptyTreeException extends RuntimeException
{
    public EmptyTreeException()
    {
        super("The current tree is empty.");
    }

    public EmptyTreeException (String message)
    {
        super(message);
    }
}
