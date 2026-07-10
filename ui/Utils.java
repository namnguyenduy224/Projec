package ui;

public class Utils {
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String GREEN = "\u001B[32m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";

    public static void printHeader(String title) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println(CYAN + BOLD + "          " + title.toUpperCase() + RESET);
        System.out.println("=".repeat(70));
    }

    public static void printFooter() {
        System.out.println("=".repeat(70));
    }

    public static void printOption(int num, String text) {
        System.out.println(BLUE + num + ". " + text + RESET);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
    }

    public static void printPagination(int current, int total) {
        System.out.println(YELLOW + "\nTrang " + current + "/" + total + 
                          " | Enter = tiếp | 0 = quay lại" + RESET);
    }
}
