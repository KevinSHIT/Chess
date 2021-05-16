package Gui;

import ADT.ChessSet;
import ADT.Coordinate;
import ADT.Piece;
import ADT.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessSetGui extends JFrame implements ActionListener
{

    ChessSet cs;
    JButton[][] btnPieces = new JButton[8][8];
    JLabel lblCurrentPlayer = new JLabel("", JLabel.CENTER); //TODO: Current
    Coordinate current;

    public ChessSetGui()
    {
        super("Chess");

        cs = new ChessSet();

        setSize(720, 720);

        setLayout(new GridLayout(8, 8));

        for (int y = 8 - 1; y >= 0; --y)
        {
            for (int x = 0; x < 8; ++x)
            {
                btnPieces[x][y] = new JButton();
                btnPieces[x][y].setFont(new Font("Segoe UI Symbol", Font.BOLD, 32)); //TODO
                btnPieces[x][y].setActionCommand(x + " " + y);
                btnPieces[x][y].addActionListener(this);
                btnPieces[x][y].setFocusPainted(false);
                add(btnPieces[x][y]);
            }
        }

        lblCurrentPlayer.setFont(new Font("Segoe UI", Font.BOLD, 24));

       // addItemToPanel(pnl,lblCurrentPlayer, 8, 1, 2, 1, 0,0,0,0);

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
        lblCurrentPlayer.setText("Current Player: " + Player.toString(cs.getCurrentPlayer()));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println(e.getActionCommand());
        String[] co = e.getActionCommand().split(" ");

        Coordinate next = new Coordinate(Integer.parseInt(co[0]), Integer.parseInt(co[1]));

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