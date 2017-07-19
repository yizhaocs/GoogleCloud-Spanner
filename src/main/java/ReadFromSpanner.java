import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.ResultSet;
import com.google.cloud.spanner.Statement;

import java.io.PrintWriter;

/**
 * Created by yzhao on 7/19/17.
 */
public class ReadFromSpanner {
    public static void spannerReadTest(DatabaseClient dbClient) {
        // Queries the database
        ResultSet resultSet = dbClient.singleUse()
                .executeQuery(Statement.of("SELECT * from ckvmap;"));

        // Prints the results
        while (resultSet.next()) {
            System.out.print("" + resultSet.getString(0));
        }
    }
}
