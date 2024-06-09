package main;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.Date;

public class DataFormat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть рядок для перевірки, чи він представляє собою дату у форматі dd.mm.yyyy:");
        String input = scanner.nextLine();
        scanner.close();

        // Регулярний вираз для перевірки формату дати
        String regex = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[012])\\.\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            // Якщо рядок відповідає формату дати, створюємо об'єкти дати
            String[] parts = input.split("\\.");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            LocalDate localDate = LocalDate.of(year, month, day);
            GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month - 1, day);
            Date date = gregorianCalendar.getTime();

            // Виводимо об'єкти дати на консоль
            System.out.println("LocalDate: " + localDate);
            System.out.println("GregorianCalendar: " + gregorianCalendar.getTime());
            System.out.println("Date: " + date);
        } else {
            System.out.println("Введений рядок не відповідає формату дати dd.mm.yyyy");
        }
    }
}
