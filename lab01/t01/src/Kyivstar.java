package lab01.t01.src;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kyivstar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть номер телефону оператора Київстар в міжнародному форматі:");
        String phoneNumber = scanner.nextLine();
        scanner.close();

        // Регулярний вираз для перевірки формату номера телефону Київстар
        String regex = "^\\+380(67|96|97|98|68)\\d{7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        if (matcher.matches()) {
            System.out.println("Введений номер телефону є номером оператора Київстар.");
        } else {
            System.out.println("Введений номер телефону не є номером оператора Київстар.");
        }
    }
}
