package Record;

public class RecordItem
{
    private char piece;
    private String from, to, note;

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