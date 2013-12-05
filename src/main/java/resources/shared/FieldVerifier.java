package resources.shared;

public class FieldVerifier {

    public static boolean isValidName(String name, String password) {
        boolean valid=false;
        if (!name.equals("") && !password.equals("")) {
            valid= true;
        }
        return valid;
    }
}