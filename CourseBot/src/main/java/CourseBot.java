import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class CourseBot {
    private static final Logger logger = LogManager.getLogger(CourseBot.class);
    private static int PERIOD;
    private static final int PERIOD_DATE = 1440;

    public static void main ( String[] args ) {

        ScheduledExecutorService schedulerDay = Executors.newSingleThreadScheduledExecutor();
        schedulerDay.scheduleAtFixedRate(new DayСheck(), 0, getPeriodDate(), TimeUnit.MINUTES);

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new TimerForParser(), 0, getPERIOD(), TimeUnit.MINUTES);

        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            logger.error("Не удалось инициализировать бота, ошибка: " + e.toString());
            e.printStackTrace();
        }
    }

    public static int getPERIOD () {
        return PERIOD;
    }

    public static int getPeriodDate () {
        return PERIOD_DATE;
    }

    public static void setPERIOD ( int PERIOD ) {
        CourseBot.PERIOD = PERIOD;
    }
}


class DayСheck implements Runnable {
    private static LocalDate localDateNow = LocalDate.now();

    private static void dayСheck () {
        int weekValue = getLocalDateNow().getDayOfWeek().getValue();
        int outlet = 6;
        if (weekValue == outlet || weekValue == outlet++) {
            CourseBot.setPERIOD(60);
        } else {
            CourseBot.setPERIOD(10);
        }
    }

    @Override
    public synchronized void run () {
        dayСheck();
    }

    public static LocalDate getLocalDateNow () {
        return localDateNow;
    }
}


