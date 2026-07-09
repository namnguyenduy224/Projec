import java.util.ArrayList;
import java.util.List;

public class PairingService implements IPairingService {

    @Override
    public List suggestTeapotsForTea(Tea tea, List teapots) {
        List result = new ArrayList();

        if (tea == null || teapots == null) {
            return result;
        }

        for (Object obj : teapots) {
            if (obj instanceof Teapot) {
                Teapot teapot = (Teapot) obj;

                if (isCompatible(tea, teapot)) {
                    result.add(teapot);
                }
            }
        }

        return result;
    }

    @Override
    public boolean isCompatible(Tea tea, Teapot teapot) {
        if (tea == null || teapot == null || tea.getType() == null) {
            return false;
        }

        String teaType = normalize(tea.getType());
        String material = "";

        if (teapot.getMaterial() != null) {
            material = normalize(teapot.getMaterial().name() + " " + teapot.getMaterial().getDisplayName());
        }

        double capacity = teapot.getCapacity();

        if (containsAny(teaType, "pho nhi", "pho_nhi", "puer", "pu er")) {
            return containsAny(material, "clay", "tu sa", "tu ne", "dat")
                    || capacity >= 120;
        }

        if (containsAny(teaType, "o long", "o_long", "oolong", "thiet quan am")) {
            return containsAny(material, "clay", "tu sa", "gom", "ceramic")
                    && capacity >= 100
                    && capacity <= 250;
        }

        if (containsAny(teaType, "luc tra", "luc_tra", "green")) {
            return containsAny(material, "ceramic", "glass", "gom", "thuy tinh")
                    || capacity <= 180;
        }

        if (containsAny(teaType, "hong tra", "hong_tra", "black")) {
            return containsAny(material, "clay", "ceramic", "gom", "dat")
                    && capacity >= 100;
        }

        if (containsAny(teaType, "bach tra", "bach_tra", "white")) {
            return containsAny(material, "ceramic", "glass", "gom", "thuy tinh")
                    || capacity <= 200;
        }

        if (containsAny(teaType, "hoang tra", "hoang_tra", "yellow")) {
            return containsAny(material, "ceramic", "glass", "gom", "thuy tinh");
        }

        return true;
    }

    @Override
    public String explainPairingRule(Tea tea, Teapot teapot) {
        if (tea == null) {
            return "Khong co thong tin tra de goi y am.";
        }

        if (teapot == null) {
            return "Khong co thong tin am de kiem tra.";
        }

        String teaType = tea.getType() == null ? "" : tea.getType();

        if (isCompatible(tea, teapot)) {
            return "Am " + teapot.getName()
                    + " phu hop voi tra " + teaType
                    + " vi chat lieu/dung tich am dap ung quy tac phoi am - tra.";
        }

        return "Am " + teapot.getName()
                + " chua phu hop voi tra " + teaType
                + ". Nen chon am co chat lieu va dung tich phu hop hon voi loai tra nay.";
    }

    private boolean containsAny(String text, String... keywords) {
        if (text == null) {
            return false;
        }

        for (String keyword : keywords) {
            if (text.contains(normalize(keyword))) {
                return true;
            }
        }

        return false;
    }

    private String normalize(String text) {
        if (text == null) {
            return "";
        }

        return text.trim()
                .toLowerCase()
                .replace("ử", "u")
                .replace("ự", "u")
                .replace("ữ", "u")
                .replace("ừ", "u")
                .replace("ứ", "u")
                .replace("ấ", "a")
                .replace("ầ", "a")
                .replace("ậ", "a")
                .replace("ẩ", "a")
                .replace("ẫ", "a")
                .replace("á", "a")
                .replace("à", "a")
                .replace("ạ", "a")
                .replace("ả", "a")
                .replace("ã", "a")
                .replace("é", "e")
                .replace("è", "e")
                .replace("ẹ", "e")
                .replace("ẻ", "e")
                .replace("ẽ", "e")
                .replace("í", "i")
                .replace("ì", "i")
                .replace("ị", "i")
                .replace("ỉ", "i")
                .replace("ĩ", "i")
                .replace("ó", "o")
                .replace("ò", "o")
                .replace("ọ", "o")
                .replace("ỏ", "o")
                .replace("õ", "o")
                .replace("ớ", "o")
                .replace("ờ", "o")
                .replace("ợ", "o")
                .replace("ở", "o")
                .replace("ỡ", "o")
                .replace("ú", "u")
                .replace("ù", "u")
                .replace("ụ", "u")
                .replace("ủ", "u")
                .replace("ũ", "u")
                .replace("đ", "d");
    }
}
