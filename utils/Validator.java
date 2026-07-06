public class Validator {
    private Validator() {
    }

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    public static boolean isPositiveNumber(double number) {
        return number >= 0;
    }

    public static boolean isGreaterThanZero(int number) {
        return number > 0;
    }

    public static boolean isGreaterThanZero(double number) {
        return number > 0;
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("0\\d{9,10}");
    }

    public static boolean isValidId(String id, String prefix) {
        if (id == null || prefix == null) {
            return false;
        }
        return id.matches(prefix + "\\d{3}");
    }

    public static boolean isValidPrice(double price) {
        return price >= 0;
    }

    public static boolean isValidGram(int gram) {
        return gram > 0;
    }

    public static boolean isValidYear(int year) {
        return year >= 1900 && year <= 2100;
    }

    public static boolean isValidCapacity(int capacityMl) {
        return capacityMl > 0;
    }

    public static boolean isValidChoice(int choice, int min, int max) {
        return choice >= min && choice <= max;
    }

    public static String requireNonEmpty(String value, String fieldName) {
        if (isEmpty(value)) {
            throw new IllegalArgumentException(fieldName + " khong duoc de trong");
        }
        return value.trim();
    }

    public static void requirePositiveNumber(double value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " khong duoc am");
        }
    }

    public static void requireGreaterThanZero(int value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " phai lon hon 0");
        }
    }
}
