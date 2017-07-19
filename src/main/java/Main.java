import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.Spanner;
import com.google.cloud.spanner.SpannerOptions;

import java.io.PrintWriter;

/**
 * Created by yzhao on 7/19/17.
 */
public class Main {
    public static void main(String[] args){
        Spanner spanner = GetSpannerService.getSpannerService();
        DatabaseClient dbClient = GetDatabaseClient.getDbClient(spanner, "test-instance", "adara");
        WriteToSpanner.spannerWriteTest(dbClient);
        ReadFromSpanner.spannerReadTest(dbClient);
    }
}
