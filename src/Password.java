import static java.lang.Math.random;

public class Password {
    public static String generatePassword(){
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890123456789!@#$%^&*()";
        String password = "";
        int passwordLength = (int)((random() * 10) + 5);

        for (int i = 0; i < passwordLength; i++) {
            int j = (int)(random() * alphabet.length());
            password += alphabet.charAt(j);
        }
        return password;
    }
}
