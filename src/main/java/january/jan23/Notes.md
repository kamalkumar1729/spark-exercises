# January 23rd Notes.

23.1 ```def session.read() : DataFrameReader()```  

23.2 DataFrameReader().options() => return DataFrameReader()

23.3 session.read() -> DataFrameReader
   session.read().options() -> DataFrameReader

23.4 **These methods return DataFrameReader** 
 
* ```.schema()```
* ```.option()```
* ```.options()```
* ```.format()```



23.5  **_load()_ => Generic method for reading data ;**  
        **_format("csv")_ => Generic method for specifying data format ;**
          
 method return dataSet on DataFrameReader  
    _.load() -> DataSet<Row>;  
    format() -> used for mentioning data format.  
        e.g format("csv").load() , format("jdbc").load()  
    ## There are specialised methods for csv, parquet, orc, json etc. (see 3.3) ##  
    load() is used with format()._


23.6  **These methods direct return dataSet<Row>**
  
* ```  .csv()        -> DataSet ```
* ```  .json()       -> DataSet ```
* ```  .jdbc()       -> DataSet ```
* ```  .parquet()    -> DataSet ```
* ```  .orc()        -> DataSet ```
* ```  .text()       -> DataSet ```
* ```  .textFile()   -> DataSet ```


23.7 **DataFrameReader => reads all data sources.** 

    *specialised methods for inbuild data sources*
    * csv
    * json
    * jdbc
    * parquet
    * orc
    * text
    * textFile
    

23.8 All data sources have few input options to set, to modify underlying data source
  e.g. SV-specific options :
         "header, inferSchema, enforceSchema , encoding etc.


23.9 Without schema csv => all fields STRING()


23.10 **Encoders helps in :**

      JVM objects <=> Spark SQL internal representation.

23.11 Running SQL directly on data sources :

    ```session.sql("SELECT _c0 from csv.`C:\\Extras\\sparkdata\\transactions.csv`");```

