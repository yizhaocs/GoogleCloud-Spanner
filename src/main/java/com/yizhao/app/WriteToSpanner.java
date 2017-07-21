package com.yizhao.app;

import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.Mutation;
import com.google.cloud.spanner.TransactionContext;
import com.google.cloud.spanner.TransactionRunner;

/**
 * mvn clean package
 * java -jar /Users/yzhao/IdeaProjects/spannertesting/target/spanner.jar
 */
public class WriteToSpanner {
    public static void insert(DatabaseClient dbClient, final String table, final long time, final int value) {
        dbClient.readWriteTransaction()
                .run(new TransactionRunner.TransactionCallable<Void>() {
                    public Void run(TransactionContext transaction) throws Exception {
                        transaction.buffer(Mutation.newInsertBuilder(table)
                                .set("time")
                                .to(time)
                                .set("value")
                                .to(value)
                                .build());
                        return null;
                    }
                });
    }


    public static void update(DatabaseClient dbClient, final String table) {
        dbClient.readWriteTransaction()
                .run(new TransactionRunner.TransactionCallable<Void>() {
                    public Void run(TransactionContext transaction) throws Exception {
                        transaction.buffer(Mutation.newUpdateBuilder(table)
                                .set("cookieid")
                                .to(1)
                                .set("value")
                                .to(1)
                                .build());
                        return null;
                    }
                });
    }

}
