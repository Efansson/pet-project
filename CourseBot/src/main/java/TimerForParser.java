import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class TimerForParser implements Runnable {
    Parser thread = new Parser();
    private static final Logger logger = LogManager.getLogger(CourseBot.class);

    @Override
    public void run () {
        thread.setName("Parser thread");
        logger.info("Полёт нормальный, обновления работают. " + thread.getName() + " выполнил работу.");
        synchronized (thread) {
            thread.start();
        }
    }
}