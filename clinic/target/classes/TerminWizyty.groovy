import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.concurrent.ThreadLocalRandom

// Get the current time
LocalDateTime now = LocalDateTime.now()

// Define the range for the random time (e.g., within the next 30 days)
LocalDateTime end = now.plusDays(30)

// Generate a random number of seconds between now and the end time
long randomSeconds = ThreadLocalRandom.current().nextLong(ChronoUnit.SECONDS.between(now, end))
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
// Add the random number of seconds to the current time
String randomDateTime = now.plusSeconds(randomSeconds).format(formatter);

//println "Random LocalDateTime after now: $randomDateTime
execution.setVariable("terminForm", randomDateTime)