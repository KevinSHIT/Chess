package Test;

import ADT.ChessSet;
import ADT.Coordinate;
import Foundamental.Player;
import Pieces.*;

public class ChessSetTest
{
    public static void main(String[] args)
    {
        ChessSet cs = new ChessSet(false);
        Castle c = new Castle(cs, new Coordinate(0, 0), Player.WHITE);
        cs.setPiece(0, 0, c);
        for (Coordinate cc : c.getValidCoordinates())
        {
            System.out.println(cc);
        }

        System.out.println("LLL");
        Pawn p = new Pawn(cs, new Coordinate(0, 1), Player.BLACK);
        cs.setPiece(0, 1, p);
        for (Coordinate cc : c.getValidCoordinates())
        {
            System.out.println(cc);
        }

        Bishop b = new Bishop(cs, new Coordinate(1, 0), Player.WHITE);
        cs.setPiece(1,0, b);
        System.out.println("CASTLE:");
        for (Coordinate cc : c.getValidCoordinates())
        {
            System.out.println(cc);
        }
        System.out.println("B");
        for (Coordinate cc : b.getValidCoordinates())
        {
            System.out.println(cc);
        }
    }
}
