import model.Affiliation;
import model.Author;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws Exception {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();


        // @TODO Crea una afiliación con nombre "Universidad Politécnica de Madrid" de la
        // ciudad de Madrid, España y guarda dicha afiliación en la base de datos.

        Affiliation upm = new Affiliation("Universidad Politécnica de Madrid", "Madrid", "España");
        session.beginTransaction();
        session.save(upm);
        session.getTransaction().commit();


        // @TODO Lee el fichero CSV authors.csv que encontrarás en resources y recorrelo usando
        // CSVParser para crear los autores que en el se encuentran. Asigna dichos autores a la
        // afiliación creada anteriormente. Guarda estos autores y sus afiliaciones en la base
        // de datos.


        CSVParser parser = new CSVParser(new FileReader("src\\main\\resources\\authors.csv"), CSVFormat.DEFAULT);
        boolean firstRow = true;

        session.beginTransaction();

        for (CSVRecord record : parser) {
            if (firstRow) {
                firstRow = false;
                continue;
            }

            String name = record.get(0);
            double importance = Double.parseDouble(record.get(1));

            Author author = new Author(name, importance);
            session.save(author);

        }

        session.getTransaction().commit();
        session.close();
        parser.close();


        session.close();

        System.out.println("Operación realizada con exito");
    }
}
