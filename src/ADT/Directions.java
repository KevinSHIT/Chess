package ADT;


public class Directions
{
    // Horizontal
    public static class Left implements IFunc<Coordinate, Coordinate>
    {
        @Override
        public Coordinate invoke(Coordinate co)
        {
            return new Coordinate(co.getX() - 1, co.getY());
        }
    }

    public static class Right implements IFunc<Coordinate, Coordinate>
    {
        @Override
        public Coordinate invoke(Coordinate co)
        {
            return new Coordinate(co.getX() + 1, co.getY());
        }
    }

    // Vertical
    public static class Up implements IFunc<Coordinate, Coordinate>
    {
        @Override
        public Coordinate invoke(Coordinate co)
        {
            return new Coordinate(co.getX(), co.getY() + 1);
        }
    }

    public static class Down implements IFunc<Coordinate, Coordinate>
    {
        @Override
        public Coordinate invoke(Coordinate co)
        {
            return new Coordinate(co.getX(), co.getY() - 1);
        }
    }

    public static class CrossLeftUp implements IFunc<Coordinate, Coordinate>
    {
        @Override
        public Coordinate invoke(Coordinate co)
        {
            return new Coordinate(co.getX() - 1, co.getY() + 1);
        }
    }

    public static class CrossLeftDown implements IFunc<Coordinate, Coordinate>
    {
        @Override
        public Coordinate invoke(Coordinate co)
        {
            return new Coordinate(co.getX() - 1, co.getY() - 1);
        }
    }

    public static class CrossRightDown implements IFunc<Coordinate, Coordinate>
    {
        @Override
        public Coordinate invoke(Coordinate co)
        {
            return new Coordinate(co.getX() + 1, co.getY() - 1);
        }
    }

    public static class CrossRightUp implements IFunc<Coordinate, Coordinate>
    {
        @Override
        public Coordinate invoke(Coordinate co)
        {
            return new Coordinate(co.getX() + 1, co.getY() + 1);
        }
    }
}

