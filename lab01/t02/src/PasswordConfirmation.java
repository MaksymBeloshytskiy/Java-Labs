import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordConfirmation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введіть пароль:");
            String password = scanner.nextLine();

            // Регулярний вираз для перевірки вимог до пароля
            String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[_\\-*]).{8,}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(password);

            if (matcher.matches()) {
                System.out.println("Пароль відповідає вимогам безпеки.");
                break; // Вийти з циклу, якщо пароль дійсний
            } else {
                System.out.println("Пароль не відповідає вимогам безпеки.");
                System.out.println("Пароль повинен містити принаймні одну маленьку літеру, одну велику літеру, одну цифру, один спеціальний символ (_ - *), і бути принаймні 8 символів у довжину.");
            }
        }

        scanner.close();
    }
}
