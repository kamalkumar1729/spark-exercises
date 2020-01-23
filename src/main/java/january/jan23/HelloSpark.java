package january.jan23;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class HelloSpark {
    public static void main(String[] args) {
        SparkSession session = SparkSession
                .builder()
                .appName("jan23")
                .master("local[2]")
                .getOrCreate();


        StructField[] fields = new StructField[] {
                new StructField("series", DataTypes.StringType, false, Metadata.empty()),
                new StructField("period", DataTypes.StringType, false, Metadata.empty()),
                new StructField("datavalue", DataTypes.DoubleType, false, Metadata.empty()),
                new StructField("supp", DataTypes.DoubleType, false, Metadata.empty()),
                new StructField("status", DataTypes.StringType, false, Metadata.empty()),
        };

        StructType schema = DataTypes.createStructType(fields);

        Dataset<Row> transactionDS = session
                .read()
                .option("header",true)
                .schema(schema)
                .csv("C:\\Extras\\sparkdata\\transactions.csv");

       /* Dataset<Transaction> transactionDS = csv
                .as(Encoders.bean(Transaction.class));*/

        transactionDS.printSchema();
        transactionDS.createOrReplaceTempView("TRANSACTION_TABLE");

        final Dataset<Row> sql = session
                .sql("SELECT * FROM TRANSACTION_TABLE");
                /*.as(Encoders.bean(Transaction.class));*/

        /*sql.limit(10).foreach(transaction -> {
            System.out.println(transaction.toString());
        });*/


        /* direct load from data source */

        final Dataset<Row> sql1 = session
                .sql("SELECT _c0 from csv.`C:\\Extras\\sparkdata\\transactions.csv`");


        sql1.show(100);

        final Dataset<Row> load = session
                .read()
                .format("csv")
                .option("header",true)
                .load("C:\\Extras\\sparkdata\\transactions.csv");

        load.show(5);

        // sql.show(10);

    }
}
