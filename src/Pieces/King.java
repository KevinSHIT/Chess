package Pieces;

import ADT.*;

import java.util.ArrayList;
import java.util.Arrays;
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

        if (underChecked())
            return valid;

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
        return getChessSet().assume(getCurrentCoordinate(), coordinate, new CheckKingState(coordinate));
    }

    private class CheckKingState implements IFunc<Boolean, ChessSet>
    {
        Coordinate target;

        public CheckKingState(Coordinate targetIn)
        {
            target = targetIn;
        }

        @Override
        public Boolean invoke(ChessSet chessSet)
        {
            return ((King) chessSet.getPiece(target)).underChecked();
        }
    }

    public boolean isOppositePiece(Coordinate co, PieceType pt)
    {
        ChessSet cs = getChessSet();
        if (!cs.isInside(co))
            return false;
        Piece p = cs.getPiece(co);
        if (p == null || p.getColour() == getColour() || p.getPieceType() != pt)
            return false;
        return true;
    }

    public boolean underChecked()
    {
        System.out.println("***** CHECK CHECK! -> " + getColour());
        Coordinate c1 = new Coordinate(getCurrentCoordinate().getX() + 1, getCurrentCoordinate().getY() + getColour());
        Coordinate c2 = new Coordinate(getCurrentCoordinate().getX() - 1, getCurrentCoordinate().getY() + getColour());
        if (isOppositePiece(c1, PieceType.Pawn) || isOppositePiece(c2, PieceType.Pawn))
            return true;

        List<IFunc<Coordinate, Coordinate>> direct = new ArrayList<>();
        direct.add(new Directions.Up());
        direct.add(new Directions.Down());
        direct.add(new Directions.Left());
        direct.add(new Directions.Right());
        if (underChecked(direct, PieceType.Castle, PieceType.Queen))
            return true;

        direct.clear();
        direct = new ArrayList<>();
        direct.add(new Directions.CrossLeftDown());
        direct.add(new Directions.CrossLeftUp());
        direct.add(new Directions.CrossRightDown());
        direct.add(new Directions.CrossRightUp());
        if (underChecked(direct, PieceType.Bishop, PieceType.Queen))
            return true;

        List<Coordinate> l = new ArrayList<>();
        l.add(new Coordinate(getCurrentCoordinate().getX() + 2, getCurrentCoordinate().getY() + 1));
        l.add(new Coordinate(getCurrentCoordinate().getX() + 2, getCurrentCoordinate().getY() - 1));
        l.add(new Coordinate(getCurrentCoordinate().getX() - 2, getCurrentCoordinate().getY() + 1));
        l.add(new Coordinate(getCurrentCoordinate().getX() - 2, getCurrentCoordinate().getY() - 1));
        l.add(new Coordinate(getCurrentCoordinate().getX() + 1, getCurrentCoordinate().getY() + 2));
        l.add(new Coordinate(getCurrentCoordinate().getX() + 1, getCurrentCoordinate().getY() - 2));
        l.add(new Coordinate(getCurrentCoordinate().getX() - 1, getCurrentCoordinate().getY() + 2));
        l.add(new Coordinate(getCurrentCoordinate().getX() - 1, getCurrentCoordinate().getY() - 2));

        for (Coordinate co : l)
        {
            if (isOppositePiece(co, PieceType.Knight))
                return true;
        }

        System.out.println("->FALSE");

        // TODO:
        return false;
    }

    public boolean underChecked(List<IFunc<Coordinate, Coordinate>> directions, PieceType... types)
    {
        ChessSet cs = getChessSet();
        List<PieceType> l = Arrays.asList(types);
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
                    continue;
                }

                if (p.getColour() == getColour())
                    break;

                if (p.getColour() != getColour() && l.contains(p.getPieceType()))
                    return true;
            }
        }
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
