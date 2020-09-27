package ORM;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Hibernate {

    private static final String CONFIGURE = "hibernate.cfg.xml";

    Session session = sessionFactory(getCONFIGURE()).openSession();

    private Course buyingUSD = session.get(Course.class, 1);
    private Course saleUSD = session.get(Course.class, 2);
    private Course buyingEUR = session.get(Course.class, 3);
    private Course saleEUR = session.get(Course.class, 4);


    private static SessionFactory sessionFactory ( String CONFIGURE ) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure(CONFIGURE).build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        return sessionFactory;
    }


    public Course getBuyingUSD () {
        return buyingUSD;
    }

    public void setBuyingUSD ( Course buyingUSD ) {
        this.buyingUSD = buyingUSD;
    }

    public Course getSaleUSD () {
        return saleUSD;
    }

    public void setSaleUSD ( Course saleUSD ) {
        this.saleUSD = saleUSD;
    }

    public Course getBuyingEUR () {
        return buyingEUR;
    }

    public void setBuyingEUR ( Course buyingEUR ) {
        this.buyingEUR = buyingEUR;
    }

    public Course getSaleEUR () {
        return saleEUR;
    }

    public void setSaleEUR ( Course saleEUR ) {
        this.saleEUR = saleEUR;
    }

    public Session getSession () {
        return session;
    }

    public static String getCONFIGURE () {
        return CONFIGURE;
    }


}
