package Gui;

import ADT.*;
import Record.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmChessSet extends JFrame implements ActionListener
{

    Record record;
    ChessSet cs;
    JButton[][] btnPieces = new JButton[8][8];
    JLabel lblCurrentPlayer = new JLabel("", JLabel.CENTER); //TODO: Current
    Coordinate current;

    public FrmChessSet()
    {
        super("Chess");
        setSize(720, 720);
        setLayout(new GridLayout(9, 9));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cs = new ChessSet();
        record = new Record();

        for (int y = 8 - 1; y >= 0; --y)
        {
            JLabel lblNum = new JLabel(Integer.toString(y + 1), JLabel.CENTER);
            lblNum.setFont(new Font(lblNum.getFont().getFontName(), Font.BOLD, 32));
            add(lblNum);
            for (int x = 0; x < 8; ++x)
            {
                btnPieces[x][y] = new JButton();
                // TODO: FIX FONT
                Font font = btnPieces[x][y].getFont();
                btnPieces[x][y].setFont(new Font(font.getFontName(), Font.BOLD, 32)); //TODO
                btnPieces[x][y].setActionCommand(x + " " + y);
                btnPieces[x][y].addActionListener(this);
                btnPieces[x][y].setFocusPainted(false);
                btnPieces[x][y].setBackground((x + y) % 2 == 0 ? Color.ORANGE : Color.WHITE);
                add(btnPieces[x][y]);
            }
        }

        for (int x = 'A' - 1; x <= 'H'; ++x)
        {
            String c = x < 'A' ? " " : Character.toString((char) (x));
            JLabel lblLetter = new JLabel(c, JLabel.CENTER);
            lblLetter.setFont(new Font(lblLetter.getFont().getFontName(), Font.BOLD, 32));
            add(lblLetter);
        }

        lblCurrentPlayer.setFont(new Font("Segoe UI", Font.BOLD, 24)); // FIXME: FONT

        // addItemToPanel(pnl,lblCurrentPlayer, 8, 1, 2, 1, 0,0,0,0);

        flush();
        setVisible(true);
    }

    public void flush()
    {
        for (int x = 0; x < 8; ++x)
        {
            for (int y = 0; y < 8; ++y)
            {
                Piece p = cs.getPiece(x, y);
                String s = p == null ? " " : p.toString();
                btnPieces[x][y].setText(s);
            }
        }
        lblCurrentPlayer.setText("Current Player: " + Player.toString(cs.getCurrentPlayer()));
    }

    public void resetColour()
    {
        for (int x = 0; x < 8; ++x)
        {
            for (int y = 0; y < 8; ++y)
            {
                btnPieces[x][y].setBackground((x + y) % 2 == 0 ? Color.ORANGE : Color.WHITE);
            }
        }
    }

    public void restartGame()
    {
        record = new Record();
        cs = new ChessSet();
        resetColour();
        flush();
    }

    public Coordinate findKing(int playerColour)
    {
        for (int x = 0; x < 8; ++x)
        {
            for (int y = 0; y < 8; ++y)
            {
                Piece p = cs.getPiece(x, y);
                if (p == null)
                    continue;
                if (p.getColour() == playerColour && p.getPieceType() == PieceType.King)
                    return p.getCurrentCoordinate();
            }
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        resetColour();
        System.out.println(e.getActionCommand());
        String[] co = e.getActionCommand().split(" ");

        Coordinate next = new Coordinate(Integer.parseInt(co[0]), Integer.parseInt(co[1]));

        if (current != null)
        {
            boolean b = cs.move(current, next);
            if (b)
            {
                RecordItem ri = new RecordItem(cs.getPiece(next), current, next, "");

                if (cs.getPiece(next).getPieceType() == PieceType.Pawn &&
                        (next.getY() == 0 || next.getY() == 7))
                {
                    DlgPromotionChoice pc = new DlgPromotionChoice(cs, -cs.getCurrentPlayer());
                    Piece p = pc.getPiece();
                    pc.dispose(); // FIXME: Manually GC
                    p.setCurrentCoordinate(next);
                    cs.setPiece(next, p);
                    ri.setNote("=" + p.toChar());
                }

                if (cs.getPiece(next).getPieceType() == PieceType.King)
                {
                    if (next.getX() - current.getX() == -2)
                        record.addCastling(true);
                    else if (next.getX() - current.getX() == 2)
                        record.addCastling(false);
                }
                else
                    record.add(ri);
                System.out.println(record);
            }
            flush();
           /* Coordinate kingCo = findKing(cs.getCurrentPlayer());
            if (kingCo != null)
            {
                King k = (King) cs.getPiece(kingCo);
                if (k.underChecked())
                    btnPieces[kingCo.getX()][kingCo.getY()].setBackground(Color.RED);
            }
            */

            System.out.println(b);
            current = null;
        }
        else
        {
            Piece p = cs.getPiece(next);
            if (p != null && p.getColour() == cs.getCurrentPlayer())
            {
                current = next;
                List<Coordinate> l = p.getValidCoordinates();
                for (Coordinate c : l)
                {
                    btnPieces[c.getX()][c.getY()].setBackground(Color.GREEN);
                }
                btnPieces[current.getX()][current.getY()].setBackground(Color.CYAN);
            }
        }
        System.out.println(current);
    }
}