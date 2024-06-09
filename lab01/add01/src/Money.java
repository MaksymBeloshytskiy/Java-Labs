import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Money {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть грошову суму: ");
        String input = scanner.nextLine();

        // Регулярний вираз для перевірки грошової суми
        String regex = "^\\$?\\d+(\\.\\d{1,2})?$";

        // Створення об'єкта Pattern
        Pattern pattern = Pattern.compile(regex);

        // Створення об'єкта Matcher
        Matcher matcher = pattern.matcher(input);

        // Перевірка відповідності введеному рядку шаблону
        if (matcher.matches()) {
            System.out.println("Грошова сума введена правильно.");
        } else {
            System.out.println("Введена грошова сума має неправильний формат.");
        }

        scanner.close();
    }
}
