import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.Spanner;
import com.google.cloud.spanner.SpannerOptions;

import java.io.PrintWriter;

/**
 * Ref:
 *  https://www.youtube.com/watch?v=VAIXpjhjCtc
 *  https://gist.github.com/branflake2267/38836e5f74c5f993f6a621e58743db7a
 */
public class Main {
    public static void main(String[] args){
        Spanner spanner = GetSpannerService.getSpannerService();
        DatabaseClient dbClient = GetDatabaseClient.getDbClient(spanner, "test-instance", "adara");
        WriteToSpanner.spannerWriteTest(dbClient, "ckvmap");
        ReadFromSpanner.spannerReadTest(dbClient, "SELECT * from ckvmap;");
    }
}
