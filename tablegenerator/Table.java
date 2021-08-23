package tablegenerator;

import java.util.LinkedList;
import java.util.List;

public class Table {

    public static void main(String[] args) {
        var table = new Table(Align.LEFT, Align.CENTER, Align.RIGHT)
            .addRow("a", "a", "a")
            .addRow("aa", "aa", "aa")
            .addRow("aaa", "aaa", "aaa")
            .addRow("aaaa", "aaaa", "aaaa")
            .addRow("aaaaa", "aaaaa", "aaaaa")
            .addRow("aaaaaa", "aaaaaa", "aaaaaa")
            .addRow("aaaaaaa", "aaaaaaa", "aaaaaaa")
            .addRow("aaaaaaaa", "aaaaaaaa", "aaaaaaaa")
            .addRow("aaaaaaa", "aaaaaaa", "aaaaaaa")
            .addRow("aaaaaa", "aaaaaa", "aaaaaa")
            .addRow("aaaaa", "aaaaa", "aaaaa")
            .addRow("aaaa", "aaaa", "aaaa")
            .addRow("aaa", "aaa", "aaa")
            .addRow("aa", "aa", "aa")
            .addRow("a", "a", "a");

        /*
            +----------+----------+----------+
            | a        |    a     |        a |
            +----------+----------+----------+
            | aa       |    aa    |       aa |
            +----------+----------+----------+
            | aaa      |   aaa    |      aaa |
            +----------+----------+----------+
            | aaaa     |   aaaa   |     aaaa |
            +----------+----------+----------+
            | aaaaa    |  aaaaa   |    aaaaa |
            +----------+----------+----------+
            | aaaaaa   |  aaaaaa  |   aaaaaa |
            +----------+----------+----------+
            | aaaaaaa  | aaaaaaa  |  aaaaaaa |
            +----------+----------+----------+
            | aaaaaaaa | aaaaaaaa | aaaaaaaa |
            +----------+----------+----------+
            | aaaaaaa  | aaaaaaa  |  aaaaaaa |
            +----------+----------+----------+
            | aaaaaa   |  aaaaaa  |   aaaaaa |
            +----------+----------+----------+
            | aaaaa    |  aaaaa   |    aaaaa |
            +----------+----------+----------+
            | aaaa     |   aaaa   |     aaaa |
            +----------+----------+----------+
            | aaa      |   aaa    |      aaa |
            +----------+----------+----------+
            | aa       |    aa    |       aa |
            +----------+----------+----------+
            | a        |    a     |        a |
            +----------+----------+----------+
        */

        System.out.println(table);
    }

    public enum Align {
        LEFT, CENTER, RIGHT
    }

    private int columns;
    private Align[] alignments;

    private List<String[]> rows;

    public Table(Align... alignments) {
        this.columns = alignments.length;
        this.alignments = alignments;
        this.rows = new LinkedList<>();
    }

    public Table addRow(String... cells) {
        assert cells.length == columns : "The number of cells must be equals to the number of columns";

        this.rows.add(cells);

        return this;
    }

    @Override
    public String toString() {
        int[] maxLengths = new int[columns];

        for (int i = 0; i < maxLengths.length; i++) {
            maxLengths[i] = Integer.MIN_VALUE;
        }

        for (String[] row : rows) {
            for (int i = 0; i < row.length; i++) {
                if (row[i].length() > maxLengths[i]) {
                    maxLengths[i] = row[i].length();
                }
            }
        }

        String separator = buildSeparator(maxLengths);

        var builder = new StringBuilder();
        
        for (int i = 0; i < rows.size(); i++) {
            builder.append(separator);
            
            builder.append("\n\r");
            
            for (int j = 0; j < rows.get(i).length; j++) {
                builder.append("| " + pad(rows.get(i)[j], maxLengths[j], alignments[j]) + " ");
            }
            
            builder.append('|');
            builder.append("\n\r");
        }

        builder.append(separator);

        return builder.toString();
    }

    private static String buildSeparator(int[] maxLengths) {
        var builder = new StringBuilder();

        for (int maxLength : maxLengths) {
            builder.append('+');
            builder.append("-".repeat(maxLength + 2));
        }

        builder.append('+');

        return builder.toString();
    }

    private static String pad(String value, int length, Align alignment) {
        return switch (alignment) {
            case LEFT -> value + " ".repeat(length - value.length());
            case CENTER -> " ".repeat((length - value.length()) >> 1) + value + " ".repeat(((length - value.length()) >> 1) + ((length - value.length() & 1) == 1 ? 1 : 0));
            case RIGHT -> " ".repeat(length - value.length()) + value;
        };
    }

}