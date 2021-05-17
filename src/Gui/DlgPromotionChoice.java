package Gui;

import ADT.ChessSet;
import ADT.Piece;
import Pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DlgPromotionChoice extends JDialog implements ActionListener
{
    String type = "Q";
    int colour;
    ChessSet cs;

    public DlgPromotionChoice(ChessSet chessSet, int playerColour)
    {
        super();
        setTitle("Promotion Choice");
        setSize(300, 200);
        setModal(true);
        setLayout(new GridLayout(0, 1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        colour = playerColour;
        cs = chessSet;

        ButtonGroup btnGroup = new ButtonGroup();
        JRadioButton rdoQueen = new JRadioButton("Queen", true);
        JRadioButton rdoCastle = new JRadioButton("Castle");
        JRadioButton rdoKnight = new JRadioButton("Knight");
        JRadioButton rdoBishop = new JRadioButton("Bishop");
        rdoQueen.addActionListener(this);
        rdoCastle.addActionListener(this);
        rdoKnight.addActionListener(this);
        rdoBishop.addActionListener(this);
        rdoQueen.setActionCommand("Q");
        rdoCastle.setActionCommand("C");
        rdoKnight.setActionCommand("N");
        rdoBishop.setActionCommand("B");
        btnGroup.add(rdoQueen);
        btnGroup.add(rdoCastle);
        btnGroup.add(rdoKnight);
        btnGroup.add(rdoBishop);

        add(rdoQueen);
        add(rdoCastle);
        add(rdoKnight);
        add(rdoBishop);

        JButton btnOk = new JButton("OK");
        btnOk.setActionCommand("OK");
        btnOk.addActionListener(this);
        add(btnOk);
    }

    public Piece getPiece()
    {
        setVisible(true);
        switch (type)
        {
            case "C":
                Castle c  = new Castle(cs, null, colour);
                c.setIsMoved();
                return c;
            case "Q":
                return new Queen(cs, null, colour);
            case "N":
                return new Knight(cs, null, colour);
            case "B":
                return new Bishop(cs, null, colour);
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if ("OK".equals(e.getActionCommand()))
        {
            setVisible(false);
            return;
        }
        type = e.getActionCommand();
    }
}
