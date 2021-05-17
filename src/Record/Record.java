package Record;

import ADT.Coordinate;
import ADT.Piece;

import java.util.ArrayList;
import java.util.List;

public class Record
{
    private final List<RecordItem> list = new ArrayList<>();

    public void add(Piece p, Coordinate from, Coordinate to, String note)
    {
        list.add(new RecordItem(p, from, to, note));
    }

    public void add(char p, String from, String to, String note)
    {
        list.add(new RecordItem(p, from, to, note));
    }

    public void add(RecordItem ri)
    {
        if (ri != null)
            list.add(ri);
    }


    public String toCsv()
    {
        StringBuilder sb = new StringBuilder();
        for (RecordItem ri : list)
        {
            sb.append(ri.toCsv())
                    .append("\n");
        }
        return sb.toString();
    }

    public static Record parseFromCsv(String csv)
    {
        Record r = new Record();
        String[] csvItems = csv.split("\n");
        for (String csvItem : csvItems)
        {
            if (!csvItem.trim().equals(""))
            {
                RecordItem ri = new RecordItem();
                if (ri.fromCsv(csvItem))
                {
                    r.add(ri);
                }
            }
        }
        return r;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (RecordItem ri : list)
        {
            ++count;
            if (count % 2 == 0)
            {
                sb.append("\t");
                sb.append(ri);
                sb.append("\n");
            }
            else
            {
                sb.append(count / 2 + 1)
                        .append(". ")
                        .append(ri);
            }
        }
        return sb.toString();
    }

    public void addCastling(boolean isLong)
    {
        add(' ', "o", isLong ? "o-o" : "o", "");
    }


}
