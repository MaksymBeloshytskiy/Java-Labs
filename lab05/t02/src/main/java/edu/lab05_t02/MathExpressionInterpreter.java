package edu.lab05_t02;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MathExpressionInterpreter {
    public static void main(String[] args) {
        // Створюємо контекст GraalVM з ввімкненою підтримкою модуля polyglot
        try (Context context = Context.newBuilder("js")
                .allowAllAccess(true)
                .build()) {
            
            // Мапа для зберігання змінних
            Map<String, Object> variables = new HashMap<>();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введіть математичний вираз (або 'exit' для завершення):");

            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                try {
                    // Додаємо всі змінні до контексту перед обчисленням
                    for (Map.Entry<String, Object> entry : variables.entrySet()) {
                        context.getBindings("js").putMember(entry.getKey(), entry.getValue());
                    }

                    // Виконуємо введений вираз
                    Value result = context.eval("js", input);

                    // Зберігаємо результат у змінну, якщо задано ім'я змінної
                    if (input.contains("=")) {
                        String[] parts = input.split("=");
                        if (parts.length == 2) {
                            String varName = parts[0].trim();
                            variables.put(varName, result);
                            System.out.println(varName + " = " + result);
                        }
                    } else {
                        System.out.println("Результат: " + result);
                    }
                } catch (Exception e) {
                    System.out.println("Помилка в виразі: " + e.getMessage());
                }
            }

            scanner.close();
        }
    }
}

