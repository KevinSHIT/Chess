package ADT;

public class Player
{
    public static final int BLACK = -1;
    public static final int WHITE = 1;
    public static String toString(int value)
    {
        if (value > 0)
            return "White";
        return "Black";
    }
}
