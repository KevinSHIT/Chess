import ADT.ChessSet;
import ADT.Coordinate;
import ADT.Piece;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        ChessSet cs = new ChessSet();
        System.out.println(cs);
        Piece p = cs.getPiece(4, 0);
        List<Coordinate> l = p.getValidCoordinates();
        for (Coordinate c : l)
        {
            System.out.println(c);
        }

        cs.move(new Coordinate('E', 1), new Coordinate('C',1));
        System.out.println(cs);
        //  cs.move(new Coordinate('E', 2), new Coordinate('E',4));
        //  System.out.println(cs);
        //  cs.move(new Coordinate('D', 7), new Coordinate('D', 5));
        //  System.out.println(cs);
        //  cs.move(new Coordinate('E', 4), new Coordinate('D', 5));
        //  System.out.println(cs);
    }
}
