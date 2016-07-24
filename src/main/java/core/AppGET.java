package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.parser.ParseException;

public class AppGET {
	public static void main(String ars[]) throws ParseException, IOException {
		
		String url = "http://httpbin.org/user-agent";
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		
		try {
			HttpResponse response = httpclient.execute(request);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();

			if (statusCode == 200) {
				System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

				StringBuffer result = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
				System.out.println("JSON Content: \n" + result.toString());
				rd.close();
				
				// #1
				Header[] headers = response.getAllHeaders();
				for (Header header : headers) {
					System.out.println("Key:" + header.getName() 
			                           + ", Value:" + header.getValue());
				}
				
				// #2
				System.out.println("OR");
				HttpClient client2 = HttpClientBuilder.create().build();
				HttpGet request2 = new HttpGet(url);
				HttpResponse response2 = client2.execute(request2);
				
				Header[] headers2 = response2.getAllHeaders();
				for (Header header : headers2) {
					System.out.println("Key:" + header.getName() 
			                           + ", Value:" + header.getValue());
				}
			} else {
				System.out.println("Failed reciev JSON file");
			}
		} catch (Exception e) {
			System.out.println("Error parse JSON fields");
		}
	}
}