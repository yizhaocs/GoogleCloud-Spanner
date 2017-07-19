import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.Spanner;

/**
 * Ref:
 *  https://www.youtube.com/watch?v=VAIXpjhjCtc
 *  https://gist.github.com/branflake2267/38836e5f74c5f993f6a621e58743db7a
 */
public class Main {
    public static void main(String[] args){
        Spanner spanner = null;
        try {

            spanner = GetSpannerService.getSpannerService();
            DatabaseClient dbClient = GetDatabaseClient.getDbClient(spanner, "test-instance", "adara");
            long startTime = System.nanoTime();
            for(int i = 4; i < 2000; i++){
                WriteToSpanner.insert(dbClient, "ckvmap", i, i+1);
            }
            long endTime = System.nanoTime();

            long duration = (endTime - startTime)/1000000; // in milliseconds

            System.out.println("total time used:" + duration + " milliseconds"); // total time used:126457 milliseconds, 63.3552104 ms per request
            //ReadFromSpanner.spannerReadTest(dbClient, "SELECT * from ckvmap;");
        }finally {
            // Closes the client which will free up the resources used
           /* try {
                spanner.close();
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        }

    }
}
