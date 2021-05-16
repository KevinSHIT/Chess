package Pieces;

import ADT.*;

import java.util.ArrayList;
import java.util.List;

public class Castle extends Piece
{
    public Castle(ChessSet csIn, Coordinate co, int colourIn)
    {
        super(csIn, co, colourIn);
    }

    List<IFunc<Coordinate, Coordinate>> direct;


    private List<IFunc<Coordinate, Coordinate>> getDirections()
    {
        if (direct == null)
        {
            direct = new ArrayList<>();
            direct.add(new Directions.Up());
            direct.add(new Directions.Down());
            direct.add(new Directions.Left());
            direct.add(new Directions.Right());
        }
        return direct;
    }

    @Override
    public PieceType getPieceType()
    {
        return PieceType.Castle;
    }

    @Override
    public List<Coordinate> getValidCoordinates()
    {
        return getValidCoordinatesByDirections(getDirections());
    }


    @Override
    public char toChar()
    {
        return 'R';
    }

    @Override
    public String toString()
    {
        if (getColour() == Player.WHITE)
            return "♖";
        return "♜";
    }
}
