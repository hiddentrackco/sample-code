package co.hiddentrack.partners_demo;

import org.apache.http.NameValuePair;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.net.ssl.HttpsURLConnection;


public class HttpClient {

	public static String post(String url, List<NameValuePair> headers,
                           String body) {
		return requestWithBody(url, "POST", headers, body);
	}

	public static String put(String url, List<NameValuePair> headers,
                          String body) {
		return requestWithBody(url, "PUT", headers, body);
	}

	private static String requestWithBody(String url, String method, List<NameValuePair> headers,
                                       String body) {
		try {
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod(method);
			con.setRequestProperty("Content-Type", "application/json");

            for (NameValuePair item: headers) {
                con.setRequestProperty(item.getName(), item.getValue());
            }

			byte[] formDataBytes = body.getBytes("UTF-8");
			con.setRequestProperty("Content-Length", String.valueOf(formDataBytes.length));
			OutputStream os = con.getOutputStream();
			os.write(formDataBytes);
			os.flush();

			String responseBody = con.getResponseMessage();
			con.disconnect();

			return responseBody;
		} catch (IOException exception) {
			exception.printStackTrace();
		}

		return null;
	}

	public static String get(String url) {
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");

			int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			return response.toString();
		} catch (IOException exception) {
			exception.printStackTrace();
		}

		return null;
	}
}

