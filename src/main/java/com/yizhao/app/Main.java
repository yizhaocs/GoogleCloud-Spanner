package com.yizhao.app;

import com.google.api.services.spanner.v1.Spanner;
import com.google.api.services.spanner.v1.model.Database;
import com.yizhao.app.Credentials.GoogleCloudFactory;

/**
 * Ref:
 *  https://www.youtube.com/watch?v=VAIXpjhjCtc
 *  https://gist.github.com/branflake2267/38836e5f74c5f993f6a621e58743db7a
 */
public class Main {
    public static void main(String[] args) throws Exception{
        Spanner spanner = null;
        try {
           spanner = GoogleCloudFactory.getGoogleCloudSpanerInstance("adara-bigtable1@adara-bigtable1.iam.gserviceaccount.com", "/opt/opinmind/conf/credentials/yizhaocs/newcache-8472d9d73511.p12");
           /* ExecuteSqlRequest mExecuteSqlRequest = new ExecuteSqlRequest();
            mExecuteSqlRequest.setSql("select * from ckvmap;");
            Spanner.Projects.Instances.Databases.Sessions sessions =
            Spanner.Projects.Instances.Databases.Sessions.ExecuteSql e = spanner.projects().instances().databases().sessions().executeSql("projects/adara-bigtable1/instances/test-instance/databases/ckvmap/sessions/new",mExecuteSqlRequest);
            e.execute();*/
            Spanner.Projects.Instances.Databases.Get get = spanner.projects().instances().databases().get("projects/adara-bigtable1/instances/test-instance/databases/adara");
            Database d = get.execute();
            System.out.println(d);
            System.out.println(d.get("time"));



           // Table response = request.execute();

            /*  Spanner.Projects.Instances.Databases request = client.tables().insert(projectId, dataset, content);
            //Table response = request.execute();
            //spanner = GetSpannerService.getSpannerService();
            DatabaseClient dbClient = GetDatabaseClient.getDbClient(spanner, "test-instance", "adara");
           // DatabaseClient dbClient = GetDatabaseClient.getDbClient(spanner, "test-instance", "adara");
            long startTime = System.nanoTime();
            for(int i = 0; i < 2000; i++){
                WriteToSpanner.insert(dbClient, "ckvmap", System.nanoTime(), i);
            }
            long endTime = System.nanoTime();

            long duration = (endTime - startTime)/1000000; // in milliseconds

            System.out.println("total time used:" + duration + " milliseconds"); // total time used:126457 milliseconds, 63.3552104 ms per request
            //ReadFromSpanner.spannerReadTest(dbClient, "SELECT * from ckvmap;");*/
        }finally {
            // Closes the client which will free up the resources used
           /* try {
                spanner.close();
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        }

    }

    public static String getUnixTimeStamp(){
        return String.valueOf(System.currentTimeMillis());

    }
}
