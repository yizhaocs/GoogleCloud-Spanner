package com.yizhao.app;

import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.Key;
import com.google.cloud.spanner.ResultSet;
import com.google.cloud.spanner.Statement;
import com.google.cloud.spanner.Struct;
import com.google.cloud.spanner.TimestampBound;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by yzhao on 7/19/17.
 */
public class ReadFromSpanner {
    public static void spannerReadTest(String type, DatabaseClient dbClient, String statement, int i) {
      /*  TimestampBound timestampBound;
        Properties properties = getProperties();
        int boundedStalenessSeconds = Integer.parseInt(properties.getProperty(
                CloudSpannerProperties.BOUNDED_STALENESS, "0"));
        timestampBound = (boundedStalenessSeconds <= 0) ?
                TimestampBound.strong() : TimestampBound.ofMaxStaleness(boundedStalenessSeconds, TimeUnit.SECONDS);
*/


        long startTime2 = System.nanoTime();
        // Queries the database
        // Struct resultSet = dbClient.singleUse().executeQuery(Statement.of(statement));
//        Struct resultSet = dbClient.readOnlyTransaction(TimestampBound.ofExactStaleness(20, TimeUnit.MILLISECONDS)).readRow("ckvmap", Key.of(i), Arrays.asList("key","value1","value2"));
        // TimestampBound.ofMaxStaleness(0, TimeUnit.SECONDS)
        if(type.equals("singleUse")){
            Struct resultSet = dbClient.singleUse().readRow("ckvmap", Key.of(i), Arrays.asList("key","value1","value2"));
        }else if(type.equals("singleUseReadOnlyTransaction")) {
            Struct resultSet = dbClient.singleUseReadOnlyTransaction().readRow("ckvmap", Key.of(i), Arrays.asList("key", "value1", "value2"));
        }else if(type.equals("readOnlyTransaction")){
            Struct resultSet = dbClient.readOnlyTransaction().readRow("ckvmap", Key.of(i), Arrays.asList("key","value1","value2"));
        }

        //System.out.println( "row.getLong(0):" + resultSet.getLong(0) + " ,row.getLong(1):" + resultSet.getLong(1) +  " ,row.getLong(2):" + resultSet.getLong(2));
        long endTime2 = System.nanoTime();

        long duration2 = (endTime2 - startTime2)/1000000; // in milliseconds

        System.out.println("total time used for reading:" + duration2 + " milliseconds"); // total time used:126457 milliseconds, 63.3552104 ms per request

       // Prints the results
        /*
            row.getLong(0):1966 ,row.getLong(1):42926342449010285 ,row.getLong(2):1966
            row.getLong(0):1967 ,row.getLong(1):42926342508992285 ,row.getLong(2):1967
            row.getLong(0):1968 ,row.getLong(1):42926342567963285 ,row.getLong(2):1968
            row.getLong(0):1969 ,row.getLong(1):42926342626704285 ,row.getLong(2):1969
            row.getLong(0):1970 ,row.getLong(1):42926342685951285 ,row.getLong(2):1970
            row.getLong(0):1971 ,row.getLong(1):42926342745767285 ,row.getLong(2):1971
            row.getLong(0):1972 ,row.getLong(1):42926342804348285 ,row.getLong(2):1972
            row.getLong(0):1973 ,row.getLong(1):42926342863566285 ,row.getLong(2):1973
            row.getLong(0):1974 ,row.getLong(1):42926342923226285 ,row.getLong(2):1974
            row.getLong(0):1975 ,row.getLong(1):42926342983970285 ,row.getLong(2):1975
            row.getLong(0):1976 ,row.getLong(1):42926343042640285 ,row.getLong(2):1976
            row.getLong(0):1977 ,row.getLong(1):42926343100952285 ,row.getLong(2):1977
            row.getLong(0):1978 ,row.getLong(1):42926343159958285 ,row.getLong(2):1978
            row.getLong(0):1979 ,row.getLong(1):42926343218417285 ,row.getLong(2):1979
        */
        /*

        while (resultSet.next()) {
            Struct row = resultSet.getCurrentRowAsStruct();
            row.getLong(0);
            row.getLong(1);
            row.getLong(2);
            System.out.println( "row.getLong(0):" + row.getLong(0) + " ,row.getLong(1):" + row.getLong(1) +  " ,row.getLong(2):" + row.getLong(2));
        }*/
    }
}
