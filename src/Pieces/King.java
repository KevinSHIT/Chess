package Pieces;

import ADT.*;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece
{
    public King(ChessSet csIn, Coordinate co, int colourIn)
    {
        super(csIn, co, colourIn);
    }

    @Override
    public PieceType getPieceType()
    {
        return PieceType.King;
    }

    @Override
    public List<Coordinate> getValidCoordinates()
    {
        List<Coordinate> l = new ArrayList<>();
        List<Coordinate> valid = new ArrayList<>();
        l.add(new Coordinate(getCurrentCoordinate().getX() + 1, getCurrentCoordinate().getY() + 1));
        l.add(new Coordinate(getCurrentCoordinate().getX() + 1, getCurrentCoordinate().getY()));
        l.add(new Coordinate(getCurrentCoordinate().getX() + 1, getCurrentCoordinate().getY() - 1));

        l.add(new Coordinate(getCurrentCoordinate().getX(), getCurrentCoordinate().getY() + 1));

        l.add(new Coordinate(getCurrentCoordinate().getX(), getCurrentCoordinate().getY() - 1));

        l.add(new Coordinate(getCurrentCoordinate().getX() - 1, getCurrentCoordinate().getY() + 1));
        l.add(new Coordinate(getCurrentCoordinate().getX() - 1, getCurrentCoordinate().getY()));
        l.add(new Coordinate(getCurrentCoordinate().getX() - 1, getCurrentCoordinate().getY() - 1));

        for (Coordinate c : l)
        {
            if (!validSquare(c) || willBeChecked(c))
            {
                continue;
            }
            valid.add(c);
        }

        l = null; // GC l

        boolean isLeftValid = isValidCastling(getCurrentCoordinate(), new Coordinate(0, getCurrentCoordinate().getY()));
        boolean isRightValid = isValidCastling(getCurrentCoordinate(), new Coordinate(7, getCurrentCoordinate().getY()));

        if (isLeftValid)
        {
            Coordinate left = new Coordinate(getCurrentCoordinate().getX() - 2, getCurrentCoordinate().getY());
            if (!willBeChecked(left))
                valid.add(left);
        }
        if (isRightValid)
        {
            Coordinate right = new Coordinate(getCurrentCoordinate().getX() + 2, getCurrentCoordinate().getY());
            if (!willBeChecked(right))
                valid.add(right);
        }

        return valid;
    }


    private boolean isValidCastling(Coordinate kingCo, Coordinate castleCo)
    {
        try
        {
            ChessSet cs = getChessSet();
            if (kingCo.getY() != castleCo.getY())
            {
                return false;
            }

            Piece k = cs.getPiece(kingCo);
            if (k.getPieceType() != PieceType.King)
            {
                return false;
            }
            King king = (King) k;

            Piece c = cs.getPiece(castleCo);
            if (c.getPieceType() != PieceType.Castle)
            {
                return false;
            }
            Castle castle = (Castle) c;

            if (castle.getIsMoved() || king.getIsMoved())
                return false;

            int step = 1;
            if (kingCo.getX() > castleCo.getX())
            {
                step = -1;
            }

            for (int start = kingCo.getX() + step; start != castleCo.getX(); start += step)
            {
                if (cs.getPiece(start, kingCo.getY()) != null)
                {
                    return false;
                }
            }
            return true;

        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean willBeChecked(Coordinate coordinate)
    {
        // TODO
        return false;
    }

    public boolean underChecked()
    {
        // TODO:
        return false;
    }

    @Override
    public boolean move(Coordinate co)
    {
        if (!isValidMove(co))
            return false;

        if (!underChecked() && Math.abs(co.getX() - getCurrentCoordinate().getX()) > 1)
        {
            // left - 2
            // right + 2
            int shift = 1;
            int castleX = 0;

            if (co.getX() > getCurrentCoordinate().getX())
            {
                shift = -shift;
                castleX = 7;
            }

            Castle castle = (Castle) getChessSet().getPiece(castleX, co.getY());

            castle.unsafeMove(co.getX() + shift, co.getY());
            getChessSet().setPiece(co, this);
            getChessSet().setPiece(getCurrentCoordinate(), null);
            setCurrentCoordinate(co);

            setIsMoved();
            return true;
        }
        getChessSet().setPiece(co, this);
        getChessSet().setPiece(getCurrentCoordinate(), null);
        setCurrentCoordinate(co);

        setIsMoved();
        return true;
    }


    @Override
    public char toChar()
    {
        return 'K';
    }

    @Override
    public String toString()
    {
        if (getColour() == Player.WHITE)
            return "♔";
        return "♚";
    }
}
