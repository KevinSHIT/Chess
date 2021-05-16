package Gui;

import ADT.ChessSet;
import ADT.Coordinate;
import ADT.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessSetGui extends JFrame implements ActionListener
{

    ChessSet cs;
    JButton[][] btnPieces = new JButton[8][8];
    Coordinate current;

    public ChessSetGui()
    {
        super("Chess");
        setSize(720, 720);

        cs = new ChessSet();


        setLayout(new GridLayout(8, 8));

        for (int y = 8 - 1; y >= 0; --y)
        {
            for (int x = 0; x < 8; ++x)
            {
                btnPieces[x][y] = new JButton();
                btnPieces[x][y].setFont(new Font("Segoe UI Symbol", Font.BOLD, 32));
                btnPieces[x][y].setActionCommand(x + " " + y);
                btnPieces[x][y].addActionListener(this);
                btnPieces[x][y].setFocusPainted(false);
                add(btnPieces[x][y]);
            }
        }

        flush();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println(e.getActionCommand());
        String[] co = e.getActionCommand().split(" ");

        Coordinate next = new Coordinate(Integer.valueOf(co[0]), Integer.valueOf(co[1]));

        if (current != null)
        {
            boolean b = cs.move(current, next);
            if (b)
                flush();
            System.out.println(b);
            current = null;
        }
        else
            current = next;
        System.out.println(current);
    }
}