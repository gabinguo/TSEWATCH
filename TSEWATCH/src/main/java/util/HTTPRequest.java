package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.params.AllClientPNames;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTTPRequest {

	// public String locaiton = null;

	public static void disableCertificateValidation() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

		} };
		// Ignore differences between given hostname and certificate hostname
		HostnameVerifier hv = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
		} catch (Exception e) {
		}
	}

	public static String[] sendPost(String url, Map<String, String> params) throws Exception {

		disableCertificateValidation();
		// Get a httpClient object
		CloseableHttpClient httpclient = HttpClients.createDefault();
		httpclient = (CloseableHttpClient) wrapClient(httpclient);
		// Creat a list to store params
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

		// Generate a post request
		HttpPost httpPost = new HttpPost(url);
		if (url == Const.BOAMP) {
			httpPost.setHeader("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,/;q=0.8,application/signed-exchange;v=b3");
			httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
			httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.9,zh-TW;q=0.8");
			httpPost.setHeader("Connection", "keep-alive");
			httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
			httpPost.setHeader("Host", "www.boamp.fr");
			httpPost.setHeader("Referer", "https://www.boamp.fr/recherche/avancee");
			httpPost.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");

		}
		httpPost.setEntity(entity);
		CloseableHttpResponse response = null;

		try {
			response = httpclient.execute(httpPost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// get the location from the 302
		Header locationHeader = response.getFirstHeader("Location");
		String location = locationHeader.getValue();
		// System.out.println(location);

		HttpEntity entity1 = response.getEntity();
		String result = "";
		try {
			result = EntityUtils.toString(entity1);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] result302 = new String[2];
		result302[0] = result;
		result302[1] = location;
		return result302;
	}

	public static HttpClient wrapClient(HttpClient base) {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLConnectionSocketFactory ssf = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(ssf).build();
			return httpclient;
		} catch (Exception ex) {
			ex.printStackTrace();
			return HttpClients.createDefault();
		}
	}

	public static String getHTML(String urlToRead) throws Exception {
		StringBuilder result = new StringBuilder();
		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		return result.toString();
	}

	/**
	 * 
	 * @param url
	 * @return html data
	 * @throws Exception This function is for sending the GET request to the site
	 *                   with a fake header and the delay of time to pass the
	 *                   anti-crawler program
	 */
	public final static String sendGET(String url) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		try {

			

			HttpGet httpget = new HttpGet(url);
			httpget.addHeader("Accept", "text/html");
			httpget.addHeader("Accept-Charset", "utf-8");
			httpget.addHeader("Accept-Encoding", "gzip");
			httpget.addHeader("Accept-Language", "en-US,en");
			httpget.addHeader("User-Agent",
					"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.160 Safari/537.22");
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					String location = null;
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						System.out.println(status);
						Date date = new Date();
						System.out.println(date);
						System.exit(0);
						throw new ClientProtocolException("Unexpected response status: " + status);
					}

				}
			};
			String responseBody = httpclient.execute(httpget, responseHandler);
			Thread.currentThread();
			Thread.sleep(200);
			return responseBody;

		} finally {
			httpclient.close();
		}
	}

	// GET request to get the location from the 302
	public static String getLocationUrl(String url) {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(50000).setConnectionRequestTimeout(10000)
				.setSocketTimeout(50000).setRedirectsEnabled(false).build();// 不允许重定向
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();
		String location = null;

		HttpResponse response;
		try {
			response = httpClient.execute(new HttpGet(url));
			//responseCode = response.getStatusLine().getStatusCode();
			Header locationHeader = response.getFirstHeader("Location");
			location = locationHeader.getValue();
			
		} catch (Exception e) {

			e.printStackTrace();
		}

		return location;
	}

	public static void main(String[] args) throws Exception {
		/**
		 * Test here
		 */
	}

}
