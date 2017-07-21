package com.yizhao.app;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.Spanner;
import com.yizhao.app.Credentials.GoogleCloudFactory;

/**
 * Ref:
 *  https://www.youtube.com/watch?v=VAIXpjhjCtc
 *  https://gist.github.com/branflake2267/38836e5f74c5f993f6a621e58743db7a
 *
 *  download jetty-alpn-agent-2.0.1.jar from:
 *  https://mvnrepository.com/artifact/org.mortbay.jetty.alpn/jetty-alpn-agent/2.0.1
 *
 *  then:
 *  mvn clean package
 *  /usr/java/jdk/bin/java -javaagent:/home/yzhao/jetty-alpn-agent-2.0.1.jar -jar spanner-jar-with-dependencies.jar
 *
 *  jetty-alpn-agent-2.0.1.jar reference:
 *  https://github.com/grpc/grpc-java/issues/1311
 *
 */
public class Main {
    private static final String SERVICE_ACCOUNT_EMAIL = "adara-bigtable1@adara-bigtable1.iam.gserviceaccount.com";
    //private static final String PATH_TO_KEY = "/opt/opinmind/conf/credentials/yizhaocs/";
    private static final String PATH_TO_KEY = "/home/yzhao/credentials/";
    private static final String PATH_TO_P12_FILE = PATH_TO_KEY + "newcache-8472d9d73511.p12";

    public static void main(String[] args) throws Exception{
        Spanner spanner = GetSpannerService.getSpannerService("/home/yzhao/credentials/adara-bigtable1-a83816086490.json");
        DatabaseClient dbClient = GetDatabaseClient.getDbClient(spanner, "test-instance", "adara");

        if(args[0].equals("writing")) {
            int count = Integer.valueOf(args[1]);
            // SERVICE VERSION
            // HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            // JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

            // Build service account credential.
            // Credential credential = GoogleCloudFactory.getServiceAccountCredential(httpTransport, jsonFactory, SERVICE_ACCOUNT_EMAIL, PATH_TO_P12_FILE);

            long startTime = System.nanoTime();
            for (int i = 0; i < count; i++) {
                WriteToSpanner.insert(dbClient, "ckvmap", System.nanoTime(), i);
            }
            long endTime = System.nanoTime();

            long duration = (endTime - startTime) / 1000000; // in milliseconds

            System.out.println("total time used for writing:" + duration + " milliseconds"); // total time used:126457 milliseconds, 63.3552104 ms per request

        }else if(args[0].equals("reading")){

            for(int i = 0; i < 2000; i++){
                ReadFromSpanner.spannerReadTest(args[1], dbClient, "select * from ckvmap where key=" + i + ";", i);
            }

        }

    }

}
