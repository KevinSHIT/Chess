package Pieces;

import ADT.*;
import Foundamental.Player;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece
{

    public Queen(ChessSet csIn, Coordinate co, int colourIn)
    {
        super(csIn, co, colourIn);
    }

    @Override
    public List<Coordinate> getValidCoordinates()
    {
        return getValidCoordinatesByDirections(getDirections());
    }

    @Override
    public char toChar()
    {
        return 'Q';
    }

    @Override
    public String toString()
    {
        if (getColour() == Player.WHITE)
            return "♕";
        return "♛";
    }

    @Override
    public PieceType getPieceType()
    {
        return PieceType.Queen;
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
            direct.add(new Directions.CrossLeftDown());
            direct.add(new Directions.CrossLeftUp());
            direct.add(new Directions.CrossRightDown());
            direct.add(new Directions.CrossRightUp());
        }
        return direct;
    }
}
