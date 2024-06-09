import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeCorrectness {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть час у форматі HH:mm:ss: ");
        String input = scanner.nextLine();

        // Регулярний вираз для перевірки часу у форматі HH:mm:ss
        String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";

        // Створення об'єкта Pattern
        Pattern pattern = Pattern.compile(regex);

        // Створення об'єкта Matcher
        Matcher matcher = pattern.matcher(input);

        // Перевірка відповідності введеному рядку шаблону
        if (matcher.matches()) {
            System.out.println("Час введений правильно.");
        } else {
            System.out.println("Введений час має неправильний формат.");
        }

        scanner.close();
    }
}
