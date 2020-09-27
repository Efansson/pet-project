import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class DayСheckTest {
    private int period;

    @Before
    public void setUp () {
        dayСheck();
    }

    public int testGetPeriod () {
        return period;
    }

    public void testSetPeriod ( Integer period ) {
        this.period = period;
    }

    @Test
    public void dayСheck () {
        LocalDate localDateNow = LocalDate.now();
        int act = localDateNow.getDayOfWeek().getValue();
        int outlet = 6;
        int expected = 10;

        if (act == outlet || act == outlet++) {
            testSetPeriod(60);
        } else {
            testSetPeriod(10);
        }
        assertEquals(expected, testGetPeriod());
    }

    @After
    public void tearDown () {
        // testSetPeriod(null);
    }
}