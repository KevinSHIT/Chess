package Pieces;

import ADT.ChessSet;
import ADT.Coordinate;
import ADT.Piece;
import ADT.PieceType;
import Foundamental.Player;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece
{
    public Knight(ChessSet csIn, Coordinate co, int colourIn)
    {
        super(csIn, co, colourIn);
    }

    @Override
    public List<Coordinate> getValidCoordinates()
    {
        List<Coordinate> l = new ArrayList<>();
        l.add(new Coordinate(getCurrentCoordinate().getX() + 2, getCurrentCoordinate().getY() + 1));
        l.add(new Coordinate(getCurrentCoordinate().getX() + 2, getCurrentCoordinate().getY() - 1));
        l.add(new Coordinate(getCurrentCoordinate().getX() - 2, getCurrentCoordinate().getY() + 1));
        l.add(new Coordinate(getCurrentCoordinate().getX() - 2, getCurrentCoordinate().getY() - 1));
        l.add(new Coordinate(getCurrentCoordinate().getX() + 1, getCurrentCoordinate().getY() + 2));
        l.add(new Coordinate(getCurrentCoordinate().getX() + 1, getCurrentCoordinate().getY() - 2));
        l.add(new Coordinate(getCurrentCoordinate().getX() - 1, getCurrentCoordinate().getY() + 2));
        l.add(new Coordinate(getCurrentCoordinate().getX() - 1, getCurrentCoordinate().getY() - 2));

        for (Coordinate c : l)
        {
            if (!validSquare(c))
                l.remove(c);
        }
        return l;
    }

    @Override
    public char toChar()
    {
        return 'N';
    }


    @Override
    public String toString()
    {
        if (getColour() == Player.WHITE)
            return "♘";
        return "♞";
    }

    @Override
    public PieceType getPieceType()
    {
        return PieceType.Knight;
    }
}
