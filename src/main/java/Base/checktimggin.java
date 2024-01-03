package Base;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class checktimggin {
    public static void main(String[] args) {
        // Define the desired time zone (PST)
        ZoneId pstZone = ZoneId.of("America/Los_Angeles");

        // Get the current date and time in the default time zone
        LocalDateTime now = LocalDateTime.now();

        // Convert the current date and time to PST
        ZonedDateTime pstDateTime = now.atZone(pstZone);

        // Define the desired date and time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy hh:mm a");

        // Format the current date and time in PST according to the desired format
        String formattedDateTime = pstDateTime.format(formatter);

        // Convert AM/PM to lowercase
        formattedDateTime = formattedDateTime.replace("AM", "am").replace("PM", "pm");

        // Print the formatted date and time in PST
        System.out.println(formattedDateTime);
    }
}
