package Record;

import ADT.Coordinate;
import ADT.Piece;

public class RecordItem
{
    private char piece;
    private String from, to, note;

    public char getPiece()
    {
        return piece;
    }

    public String getFrom()
    {
        return from;
    }

    public String getTo()
    {
        return to;
    }

    public String getNote()
    {
        return note;
    }

    public void setPiece(char pieceIn)
    {
        piece = pieceIn;
    }

    public void setFrom(String fromIn)
    {
        from = fromIn;
    }

    public void setTo(String toIn)
    {
        to = toIn;
    }

    public void setNote(String noteIn)
    {
        note = noteIn;
    }

    public RecordItem(char pieceIn, String fromIn, String toIn, String noteIn)
    {
        piece = pieceIn;
        from = fromIn;
        to = toIn;
        note = noteIn;
    }

    public RecordItem()
    {
    }

    public RecordItem(Piece p, Coordinate fromIn, Coordinate toIn, String noteIn)
    {
        piece = p.toChar();
        from = convertToRecordString(fromIn);
        to = convertToRecordString(toIn);
        note = noteIn;
    }


    public String convertToRecordString(Coordinate c)
    {
        return (char) ('A' + c.getX()) + Integer.toString(c.getY());
    }


    @Override
    public String toString()
    {
        if (piece != ' ')
        {
            return piece + " " + from + "-" + to + note;
        }
        return from + "-" + to + note;
    }

    public String toCsv()
    {
        return piece + "," + from + "," + to + "," + note;
    }

    public boolean fromCsv(String csv)
    {
        csv = csv.trim();
        if (csv.startsWith(","))
            csv = " " + csv;
        if (csv.endsWith(","))
            csv = csv + " ";
        String[] a = csv.split(",");

        if (a.length != 4)
            return false;
        piece = a[0].charAt(0);
        from = a[1];
        to = a[2];
        note = a[3].trim();
        return true;
    }
}