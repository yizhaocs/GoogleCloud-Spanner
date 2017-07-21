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
        //long startTime = System.nanoTime();
        dbClient.readWriteTransaction()
                .run(new TransactionRunner.TransactionCallable<Void>() {
                    public Void run(TransactionContext transaction) throws Exception {
                        transaction.buffer(Mutation.newInsertBuilder(table)
                                .set("key")
                                .to(value)
                                .set("value1")
                                .to(time)
                                .set("value2")
                                .to(value)
                                .build());
                        return null;
                    }
                });
     /*   long endTime = System.nanoTime();

        long duration = (endTime - startTime)/1000000; // in milliseconds

        System.out.println("total time used for query:" + duration + " milliseconds");*/
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
