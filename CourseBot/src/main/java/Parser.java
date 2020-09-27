import ORM.Course;
import ORM.Hibernate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Parser extends Thread {
    private final static String URL = "https://www.raiffeisen.ru/currency_rates/";
    protected List<Course> courseList = new ArrayList<>();
    Hibernate hibernate = new Hibernate();
    private static final Logger logger = LogManager.getLogger(Parser.class);

    @Override
    public void run () {
        Document doc = null;
        try {
            doc = Jsoup.connect(URL)
                    .maxBodySize(0)
                    .userAgent("Chrome/4.2.240 Safari/525")
                    .referrer("https://www.yandex.com/")
                    .get();
        } catch (IOException e) {
            logger.error("Упал парсер, нет соединения с сайтом." + e.toString());
        }

        Elements namesOfStations = doc.getElementsByClass("b-table__row");
        List<String> strings = new ArrayList<>();
        for (Element el : namesOfStations) {
            el.children().forEach(element -> {
                String nameStation = element.getElementsByClass("b-table__td").text();
                strings.add(nameStation);
            });
        }
        if (strings == null) {
            logger.error("Упал парсер, нет соединения с сайтом, либо структура сайта была измененена");
        }

        courseList.add(new Course(1, strings.get(3)));
        courseList.add(new Course(2, strings.get(4)));
        courseList.add(new Course(3, strings.get(8)));
        courseList.add(new Course(4, strings.get(9)));

        logger.info("Класс Парсер отработал.");
        courseList.forEach(System.out::println);


        Session session = hibernate.getSession();
        Course course1 = session.get(Course.class, 1);
        Course course2 = session.get(Course.class, 2);
        Course course3 = session.get(Course.class, 3);
        Course course4 = session.get(Course.class, 4);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            course1.setValue(courseList.get(0).toString());
            course2.setValue(courseList.get(1).toString());
            course3.setValue(courseList.get(2).toString());
            course4.setValue(courseList.get(3).toString());
            transaction.commit();
        } catch (Exception e) {
            logger.error("Возникли проблемы с транзакцией, проверь, что там с БД " + e.toString());
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}

