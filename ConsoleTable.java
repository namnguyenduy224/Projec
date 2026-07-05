import java.util.List;

public class ConsoleTable {
    private ConsoleTable() {
    }

    public static void printLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printTitle(String title) {
        System.out.println();
        printLine(80);
        System.out.println(center(title, 80));
        printLine(80);
    }

    public static void printHeader(String... headers) {
        printRow(headers);
    }

    public static void printRow(String... values) {
        for (String value : values) {
            System.out.printf("| %-20s", cut(value, 20));
        }
        System.out.println("|");
    }

    public static void printSimpleTable(String[] headers, List<String[]> rows) {
        int width = headers.length * 23 + 1;

        printLine(width);
        printRow(headers);
        printLine(width);

        if (rows == null || rows.isEmpty()) {
            System.out.println("| Khong co du lieu");
            printLine(width);
            return;
        }

        for (String[] row : rows) {
            printRow(row);
        }

        printLine(width);
    }

    public static String cut(String text, int maxLength) {
        if (text == null) {
            return "";
        }

        if (text.length() <= maxLength) {
            return text;
        }

        if (maxLength <= 3) {
            return text.substring(0, maxLength);
        }

        return text.substring(0, maxLength - 3) + "...";
    }

    public static String center(String text, int width) {
        if (text == null) {
            text = "";
        }

        if (text.length() >= width) {
            return text;
        }

        int left = (width - text.length()) / 2;
        int right = width - text.length() - left;

        return " ".repeat(left) + text + " ".repeat(right);
    }

    public static String formatMoney(double money) {
        return String.format("%,.0f VND", money);
    }
}
