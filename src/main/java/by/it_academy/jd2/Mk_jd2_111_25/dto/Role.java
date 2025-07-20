package by.it_academy.jd2.Mk_jd2_111_25.dto;

public enum Role {
    USER,
    ADMIN;

    public static Role fromString(String str) {
        if (str == null) return null;
        switch (str.toUpperCase()) {
            case "USER":
                return USER;
            case "ADMIN":
                return ADMIN;
            default:
                throw new IllegalArgumentException("Unknown role: " + str);
        }
    }


}
