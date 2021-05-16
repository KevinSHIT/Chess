package Test;

import Record.Record;

public class RecordTest
{
    public static void main(String[] args)
    {
        Record r = new Record();
        r.add('K', "A1", "A3", "");
        r.addCastling(false);
        r.addCastling(true);
        r.add('Q', "B1", "C2", "#");
        System.out.println(r);

        System.out.println("CSV");
        System.out.println(r.toCsv());

        System.out.println("FROM CSV");
        System.out.println(Record.parseFromCsv(r.toCsv()));
    }
}
