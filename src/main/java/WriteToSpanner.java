import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.Mutation;
import com.google.cloud.spanner.TransactionContext;
import com.google.cloud.spanner.TransactionRunner;

/**
 * Created by yzhao on 7/19/17.
 */
public class WriteToSpanner {
    public static void spannerWriteTest(DatabaseClient dbClient) {
        dbClient.readWriteTransaction()
                .run(new TransactionRunner.TransactionCallable<Void>() {
                    public Void run(TransactionContext transaction) throws Exception {
                        transaction.buffer(Mutation.newUpdateBuilder("ckvmap")
                                .set("cookieid")
                                .to(1)
                                .set("value")
                                .to(2)
                                .build());
                        return null;
                    }
                });
    }

}
