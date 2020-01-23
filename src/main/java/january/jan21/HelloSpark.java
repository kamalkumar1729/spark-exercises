package january.jan21;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.sum;

public class HelloSpark {
    public static void main(String[] args) {

        SparkSession session = SparkSession
                .builder()
                .appName("jan21")
                .master("local[2]")
                .getOrCreate();

        System.out.println(session);

        final Dataset<Person> map = session.range(100)
                .map(new MapFunction<Long, Person>() {
                    @Override
                    public Person call(Long aLong) throws Exception {
                        Person person = new Person();
                        person.setAge("Twenty Seven");
                        person.setName("Kamal Kumar");
                        return person;
                    }
                }, Encoders.bean(Person.class));

        map.show(100);
        map.printSchema();
        
        final Dataset<Row> dataSet = session
                .read()
                .csv("C:\\Users\\kamal7.kumar\\Downloads\\kamal.csv");

        final Dataset<Row> cache = dataSet.cache();

        final Dataset<Row> agg = cache
                .groupBy("_c2")
                .agg(sum("_c10"));

        agg.rdd().saveAsObjectFile("C:\\Users\\kamal7.kumar\\Desktop\\kamal2.txt");

        agg.show(1000);

        try {
            System.out.println("sleeping");
            Thread.sleep(999000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }







       /* Dataset<Row> databaseDS = ss.read().format("jdbc")
                .option("url", "jdbc:postgresql://10.147.106.37:5432/mydb")
                *//*.option("dbtable", "employee")*//*
                .option("user","postgres")
                .option("password", "mata")
                .option("query","SELECT * FROM EMPLOYEE WHERE ID=2")
                *//*.option("numPartitions", 10)
                .option("partitionColumn", "id")
                .option("lowerBound", 1)
                .option("upperBound", 10000)*//*
                .load();
        try {
            System.out.println("sleeping");
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        databaseDS.printSchema();
        databaseDS.show(100,false);

        */


    }
}
