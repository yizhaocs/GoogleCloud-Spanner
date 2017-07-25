package com.yizhao.app;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.spanner.Spanner;
import com.google.cloud.spanner.SpannerOptions;

import java.io.FileInputStream;

/**
 * Created by yzhao on 7/19/17.
 */
public class GetSpannerService {
    public static Spanner getSpannerService(String credentialPath) throws Exception{
        SpannerOptions options = SpannerOptions.newBuilder().setCredentials(GoogleCredentials.fromStream(new FileInputStream(credentialPath))).setProjectId("adara-bigtable1").build();
        Spanner spanner = options.getService();
        return spanner;
    }
}
