import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.TreeSet;

public class TimeZoneInfo {
    public static void main(String[] args) {
        // Отримуємо всі доступні часові пояси
        Set<String> zones = ZoneId.getAvailableZoneIds();

        // Створюємо відсортовану колекцію часових поясів
        TreeSet<String> sortedZones = new TreeSet<>(zones);

        // Виводимо відформатовану інформацію про кожен часовий пояс
        System.out.println("Всі доступні часові пояси:");
        for (String zone : sortedZones) {
            System.out.println(formatZoneInfo(zone));
        }
    }

    // Метод для форматування інформації про часовий пояс
    private static String formatZoneInfo(String zoneId) {
        ZoneId zone = ZoneId.of(zoneId);
        Instant instant = Instant.now(); // Example Instant object
        ZoneOffset offset = zone.getRules().getOffset(instant);
        String gmtOffset = offset.getId().replaceAll("Z", "+00:00");
        return "Часовий пояс: " + zoneId + " (GMT" + gmtOffset + ")";
    }
}
