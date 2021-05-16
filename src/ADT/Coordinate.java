package ADT;

import java.util.Objects;

public class Coordinate
{
    private int x, y;

    public Coordinate(int xIn, int yIn)
    {
        x = xIn;
        y = yIn;
    }

    public Coordinate(char xIn, int yIn)
    {
        xIn = Character.toUpperCase(xIn);
        x = xIn - 'A';
        y = yIn - 1;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj)
    {
        // Reference
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Coordinate that = (Coordinate) obj;

        return x == that.x && y == that.y;
    }
}
