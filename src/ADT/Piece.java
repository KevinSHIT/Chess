package ADT;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece
{
    int colour;

    public int getColour()
    {
        return colour;
    }

    public abstract List<Coordinate> getValidCoordinates();

    public boolean isValidMove(Coordinate target)
    {
        List<Coordinate> list = getValidCoordinates();
        return list.contains(target);
    }

    public boolean validSquare(Coordinate coordinate)
    {
        if (!cs.isInside(coordinate))
            return false;
        Piece p = cs.getPiece(coordinate);
        return p == null || p.getColour() != getColour();
    }

    public abstract char toChar();

    boolean isMoved = false;

    public boolean getIsMoved()
    {
        return isMoved;
    }

    public void setIsMoved()
    {
        isMoved = true;
    }

    @Override
    public String toString()
    {
        return " ";
    }

    public abstract PieceType getPieceType();

    private ChessSet cs;
    private Coordinate currCo;

    public Piece(ChessSet csIn, Coordinate co, int colourIn)
    {
        cs = csIn;
        currCo = co;
        colour = colourIn;
    }

    public ChessSet getChessSet()
    {
        return cs;
    }

    public Coordinate getCurrentCoordinate()
    {
        return currCo;
    }

    public boolean move(int x, int y)
    {
        return move(new Coordinate(x, y));
    }

    public boolean move(Coordinate co)
    {
        if (!isValidMove(co))
        {
            return false;
        }

        setIsMoved();
        unsafeMove(co);
        return true;
    }

    protected void unsafeMove(Coordinate co)
    {
        cs.setPiece(co, this);
        cs.setPiece(currCo, null);
        this.currCo = co;
    }

    public void unsafeMove(int x, int y)
    {
        unsafeMove(new Coordinate(x, y));
    }

    public void setCurrentCoordinate(Coordinate co)
    {
        currCo = co;
    }

    public List<Coordinate> getValidCoordinatesByDirections(List<IFunc<Coordinate, Coordinate>> directions)
    {
        List<Coordinate> valid = new ArrayList<>();
        for (IFunc<Coordinate, Coordinate> direction : directions)
        {
            Coordinate co = getCurrentCoordinate();
            while (true)
            {
                co = direction.invoke(co);
                if (!cs.isInside(co))
                    break;

                Piece p = cs.getPiece(co);
                if (p == null)
                {
                    valid.add(co);
                    continue;
                }

                if (p.getColour() != getColour())
                    valid.add(co);
                break;
            }
        }
        return valid;
    }
}
