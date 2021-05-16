package Pieces;

import ADT.*;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece
{
    public Pawn(ChessSet csIn, Coordinate co, int colourIn)
    {
        super(csIn, co, colourIn);
    }


    @Override
    public PieceType getPieceType()
    {
        return PieceType.Pawn;
    }

    @Override
    public List<Coordinate> getValidCoordinates()
    {
        ChessSet cs = getChessSet();
        List<Coordinate> l = new ArrayList<>();
        Coordinate target = new Coordinate(getCurrentCoordinate().getX(), getCurrentCoordinate().getY() + getColour());
        if (cs.isInside(target) && isNoPiece(target))
        {
            l.add(target);
            if (!getIsMoved())
            {
                Coordinate dob = new Coordinate(getCurrentCoordinate().getX(), getCurrentCoordinate().getY() + 2 * getColour());
                if (isNoPiece(dob))
                {
                    l.add(dob);
                }
            }
        }

        Coordinate attackLeft = new Coordinate(getCurrentCoordinate().getX() + 1, getCurrentCoordinate().getY() + getColour());
        Coordinate attackRight = new Coordinate(getCurrentCoordinate().getX() - 1, getCurrentCoordinate().getY() + getColour());

        if (getChessSet().isInside(attackLeft) && getChessSet().getPiece(attackLeft) != null && getChessSet().getPiece(attackLeft).getColour() != getColour())
        {
            l.add(attackLeft);
        }

        if (getChessSet().isInside(attackRight) && getChessSet().getPiece(attackRight) != null && getChessSet().getPiece(attackRight).getColour() != getColour())
        {
            l.add(attackRight);
        }

        // TODO: 过路兵
        return l;
    }


    private boolean isNoPiece(Coordinate co)
    {
        ChessSet cs = getChessSet();
        return cs.getPiece(co) == null;
    }


    @Override
    public char toChar()
    {
        return ' ';
    }

    @Override
    public String toString()
    {
        if (getColour() == Player.WHITE)
            return "♙";
        return "♟";
    }
}
