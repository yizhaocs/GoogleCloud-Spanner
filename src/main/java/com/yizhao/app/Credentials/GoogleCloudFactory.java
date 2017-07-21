package com.yizhao.app.Credentials;

//Copyright 2014 Google Inc. All Rights Reserved.
//
//Licensed under the Apache License, Version 2.0 (the "License");
//you may not use this file except in compliance with the License.
//You may obtain a copy of the License at
//
//http://www.apache.org/licenses/LICENSE-2.0
//
//Unless required by applicable law or agreed to in writing, software
//distributed under the License is distributed on an "AS IS" BASIS,
//WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//See the License for the specific language governing permissions and
//limitations under the License.

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.spanner.v1.Spanner;
import com.google.api.services.spanner.v1.SpannerScopes;
import com.google.common.collect.ImmutableList;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Utility methods used by all DFA Reporting and Trafficking API samples.
 * 
 * @author api.jimper@gmail.com (Jonathon Imperiosi)
 */
public class GoogleCloudFactory {

	/** The scopes used by the Storage API **/
	private static final List<String> SCOPES = ImmutableList.of("https://www.googleapis.com/auth/devstorage.full_control",
			"https://www.googleapis.com/auth/devstorage.read_write");

	/**
	 * Authorizes the installed application to access user's protected data.
	 * 
	 *
	 */		
	// SERVICE version variables
//	private static final String SERVICE_ACCOUNT_EMAIL = "1045802503707-4vd19voeuisdoap44jo0m9274l6eup44@developer.gserviceaccount.com";
//	private static final String PATH_TO_KEY = "/opt/opinmind/conf/credentials/";	
//	private static final String PATH_TO_P12_FILE = PATH_TO_KEY + "API Project-1c460715139a.p12";
//	private static final String ACCOUNT_TO_IMPERSONATE = "reports@adaramedia.com";
	
	// frank.lin settings
	private static final String SERVICE_ACCOUNT_EMAIL = "adara-bigtable1@adara-bigtable1.iam.gserviceaccount.com";
	private static final String PATH_TO_KEY = "/opt/opinmind/conf/credentials/yizhaocs/";
	private static final String PATH_TO_P12_FILE = PATH_TO_KEY + "newcache-8472d9d73511.p12";
	private static final String ACCOUNT_TO_IMPERSONATE = "yizhao.cs@gmail.com";

	private static Credential getServiceAccountCredential(
			HttpTransport httpTransport, JsonFactory factory, String serviceAccountEmail, String p12File) throws Exception {
				
		Set<String> scopes = new HashSet<String>();
		scopes.addAll(SpannerScopes.all());
		/*
		scopes.addAll(StorageScopes.all());
		scopes.addAll(BigqueryScopes.all());
		*/
		
		// Service account credential.
		GoogleCredential credential = new GoogleCredential.Builder()
				.setTransport(httpTransport)
				.setJsonFactory(factory)
				.setServiceAccountId(serviceAccountEmail)
				.setServiceAccountScopes(scopes)
				.setServiceAccountPrivateKeyFromP12File(new File(p12File))
				// Set the user you are impersonating (this can be yourself).
//				.setServiceAccountUser(ACCOUNT_TO_IMPERSONATE)
				.build();
		

		credential.refreshToken();
		return credential;
		
//		HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
//		JsonFactory jsonFactory = new JacksonFactory();
//		GoogleCredential credential = GoogleCredential.getApplicationDefault(transport, jsonFactory);
//
//		if (credential.createScopedRequired()) {
//			Collection<String> bigqueryScopes = StorageScopes.all();
//			credential = credential.createScoped(bigqueryScopes);
//		}
//		return credential;
	}
/*

	public static Bigquery getGoogleBigQueryInstance(String serviceAccountEmail, String p12File) throws Exception {
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
	    JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

	    // Build service account credential.
	    Credential credential = getServiceAccountCredential(httpTransport, jsonFactory, serviceAccountEmail, p12File);		
		
		// Create DFA Reporting client.
		return new Bigquery.Builder(httpTransport, jsonFactory, credential)
				.setApplicationName("big query client").build();
	}

	*/
/**
	 * Performs all necessary setup steps for running requests against the API.
	 * 
	 * @return An initialized {@link Storage} service object.
	 *//*

	public static Storage getGoogleCloudStorageInstance(String serviceAccountEmail, String p12File) throws Exception {
		
		// SERVICE VERSION
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
	    JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
//	    JsonFactory jsonFactory = new JacksonFactory();

	    // Build service account credential.
	    Credential credential = getServiceAccountCredential(httpTransport, jsonFactory, serviceAccountEmail, p12File);		
		
		// Create DFA Reporting client.
		return new Storage.Builder(httpTransport, jsonFactory, credential)
				.setApplicationName("cloud storage test").build();
	}

*/

	/**
	 * Performs all necessary setup steps for running requests against the API.
	 *
	 * @return An initialized {@link Spanner} service object.
	 */
	public static Spanner getGoogleCloudSpanerInstance(String serviceAccountEmail, String p12File) throws Exception {

		// SERVICE VERSION
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
//	    JsonFactory jsonFactory = new JacksonFactory();

		// Build service account credential.
		Credential credential = getServiceAccountCredential(httpTransport, jsonFactory, serviceAccountEmail, p12File);

		// Create DFA Reporting client.
		return new Spanner.Builder(httpTransport, jsonFactory, credential)
				.setApplicationName("spanner test").build();
	}
}
