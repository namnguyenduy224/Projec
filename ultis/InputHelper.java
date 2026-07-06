import java.util.Scanner;

public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    private InputHelper() {
    }

    public static String readNonEmpty(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();

            if (!Validator.isEmpty(value)) {
                return value;
            }

            System.out.println("Du lieu khong duoc de trong. Vui long nhap lai!");
        }
    }

    public static String readOptional(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public static int readInt(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);

                if (Validator.isValidChoice(value, min, max)) {
                    return value;
                }

                System.out.println("Vui long nhap so trong khoang " + min + " - " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Du lieu phai la so nguyen. Vui long nhap lai!");
            }
        }
    }

    public static int readPositiveInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);

                if (value > 0) {
                    return value;
                }

                System.out.println("Gia tri phai lon hon 0.");
            } catch (NumberFormatException e) {
                System.out.println("Du lieu phai la so nguyen. Vui long nhap lai!");
            }
        }
    }

    public static double readDouble(String message, double min) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                double value = Double.parseDouble(input);

                if (value >= min) {
                    return value;
                }

                System.out.println("Gia tri phai >= " + min + ".");
            } catch (NumberFormatException e) {
                System.out.println("Du lieu phai la so. Vui long nhap lai!");
            }
        }
    }

    public static String readPhone(String message) {
        while (true) {
            String phone = readNonEmpty(message);

            if (Validator.isValidPhone(phone)) {
                return phone;
            }

            System.out.println("So dien thoai phai bat dau bang 0 va co 10 hoac 11 chu so.");
        }
    }

    public static String readId(String message, String prefix) {
        while (true) {
            String id = readNonEmpty(message).toUpperCase();

            if (Validator.isValidId(id, prefix)) {
                return id;
            }

            System.out.println("Ma khong hop le. Dinh dang dung la " + prefix + "001, " + prefix + "002...");
        }
    }

    public static boolean readYesNo(String message) {
        while (true) {
            System.out.print(message + " (Y/N): ");
            String choice = scanner.nextLine().trim();

            if (choice.equalsIgnoreCase("Y")) {
                return true;
            }

            if (choice.equalsIgnoreCase("N")) {
                return false;
            }

            System.out.println("Vui long nhap Y hoac N.");
        }
    }

    public static void pause() {
        System.out.print("\nNhan Enter de tiep tuc...");
        scanner.nextLine();
    }
}
