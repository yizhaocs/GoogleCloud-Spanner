import com.google.cloud.spanner.Spanner;
import com.google.cloud.spanner.SpannerOptions;

/**
 * Created by yzhao on 7/19/17.
 */
public class GetSpannerService {
    public static Spanner getSpannerService(){
        SpannerOptions options = SpannerOptions.newBuilder().build();
        Spanner spanner = options.getService();
        return spanner;
    }
}
