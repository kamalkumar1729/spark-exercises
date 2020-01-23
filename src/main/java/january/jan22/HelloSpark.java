package january.jan22;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class HelloSpark {
    public static void main(String[] args) {
        SparkSession session = SparkSession.builder().appName("jan22").master("local[3]").getOrCreate();
        final String csvPath = "C:/Users/kamal7.kumar/Downloads/kamal.csv";
        final Dataset<Row> csv = session.read().csv(csvPath);


        /* Views are not shared across sessions, and ends on session end */
        csv.createOrReplaceTempView("CSV_DATA");

        /* Global views are shared across sessions */
        csv.createOrReplaceGlobalTempView("CSV_DATA_G");



        final String query = "SELECT * FROM CSV_DATA LIMIT 10";
        final Dataset<Row> sql = session.sql(query);
        sql.show(100);
        // csv.show(100);


        final SparkSession session2 = session.newSession();

        /* Global view are accessible via global_temp.TEMP_VIEW --name */
        final String query2 = "SELECT * FROM global_temp.CSV_DATA_G LIMIT 10";
        final Dataset<Row> sql1 = session2.sql(query2);
        System.out.println("session 2 query");
        sql1.show(12);

        try {
            System.out.println("Sleeping");
            Thread.sleep(990000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
