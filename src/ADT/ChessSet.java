package ADT;

import Foundamental.Player;
import Pieces.*;

public class ChessSet
{
    final int WIDTH = 8;
    final int HEIGHT = 8;

    int currentPlayer = Player.WHITE;

    Piece[][] chessSet = new Piece[WIDTH][HEIGHT];

    public ChessSet(boolean needInit)
    {
        if (!needInit)
            return;
        for (int i = 0; i < WIDTH; i++)
        {
            chessSet[i][1] = new Pawn(this, new Coordinate(i, 1), Player.WHITE);
        }
        chessSet[0][0] = new Castle(this, new Coordinate(0, 0), Player.WHITE);
        chessSet[1][0] = new Knight(this, new Coordinate(1, 0), Player.WHITE);
        chessSet[2][0] = new Bishop(this, new Coordinate(2, 0), Player.WHITE);
        chessSet[3][0] = new Queen(this, new Coordinate(3, 0), Player.WHITE);
        chessSet[4][0] = new King(this, new Coordinate(4, 0), Player.WHITE);
        chessSet[5][0] = new Bishop(this, new Coordinate(5, 0), Player.WHITE);
        chessSet[6][0] = new Knight(this, new Coordinate(6, 0), Player.WHITE);
        chessSet[7][0] = new Castle(this, new Coordinate(7, 0), Player.WHITE);

        for (int i = 0; i < WIDTH; i++)
        {
            chessSet[i][6] = new Pawn(this, new Coordinate(i, 6), Player.BLACK);
        }

        chessSet[0][7] = new Castle(this, new Coordinate(0, 7), Player.BLACK);
        chessSet[1][7] = new Knight(this, new Coordinate(1, 7), Player.BLACK);
        chessSet[2][7] = new Bishop(this, new Coordinate(2, 7), Player.BLACK);
        chessSet[3][7] = new Queen(this, new Coordinate(3, 7), Player.BLACK);
        chessSet[4][7] = new King(this, new Coordinate(4, 7), Player.BLACK);
        chessSet[5][7] = new Bishop(this, new Coordinate(5, 7), Player.BLACK);
        chessSet[6][7] = new Knight(this, new Coordinate(6, 7), Player.BLACK);
        chessSet[7][7] = new Castle(this, new Coordinate(7, 7), Player.BLACK);
    }

    public ChessSet()
    {
        this(true);

    }

    public Piece getPiece(int x, int y)
    {
        return chessSet[x][y];
    }

    public Piece getPiece(Coordinate coordinate)
    {
        return getPiece(coordinate.getX(), coordinate.getY());
    }

    public boolean isInside(Coordinate coordinate)
    {
        if (coordinate.getX() < 0 || coordinate.getX() >= this.getWidth())
            return false;
        if (coordinate.getY() < 0 || coordinate.getY() >= this.getHeight())
            return false;
        return true;
    }

    public int getWidth()
    {
        return WIDTH;
    }

    public int getHeight()
    {
        return HEIGHT;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        // Row
        for (int y = HEIGHT - 1; y >= 0; --y)
        {
            // Col
            sb.append("  ");
            for (int x = 0; x < WIDTH; ++x)
            {
                sb.append("+---");
            }
            sb.append("+\n");
            sb.append(y + 1).append(' ');
            for (int x = 0; x < WIDTH; ++x)
            {

                sb.append("| ").
                        append(chessSet[x][y] == null
                                ? " "
                                : chessSet[x][y])
                        .append(" ");
            }
            sb.append("|\n");
        }

        sb.append("  ");
        for (int x = 0; x < WIDTH; ++x)
        {
            sb.append("+---");
        }
        sb.append("+\n  ");
        for (int x = 0; x < WIDTH; ++x)
        {
            sb.append("  ").append((char) ('A' + x)).append(' ');
        }
        return sb.toString();
    }

    public void setPiece(int x, int y, Piece p)
    {
        chessSet[x][y] = p;
    }

    public void setPiece(Coordinate co, Piece p)
    {
        setPiece(co.getX(), co.getY(), p);
    }

    public boolean move(Coordinate from, Coordinate to)
    {
        Piece p = getPiece(from);
        if (p == null)
            return false;
        if (p.getColour() != currentPlayer)
            return false;

        boolean isMoveSuccess = p.move(to);
        if (isMoveSuccess)
            currentPlayer = -currentPlayer;
        return isMoveSuccess;
    }
}
