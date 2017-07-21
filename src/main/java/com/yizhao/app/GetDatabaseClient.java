package com.yizhao.app;

import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.DatabaseId;
import com.google.cloud.spanner.Spanner;
import com.google.cloud.spanner.SpannerOptions;

/**
 * Created by yzhao on 7/19/17.
 */
public class GetDatabaseClient {
    public static DatabaseClient getDbClient(Spanner spanner, String instance, String database) {
        SpannerOptions options = spanner.getOptions();

        // Creates a database client
        DatabaseClient dbClient = spanner.getDatabaseClient(DatabaseId.of(options.getProjectId(), instance, database));

        return dbClient;
    }

}
