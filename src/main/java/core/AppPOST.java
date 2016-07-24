package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class AppPOST {
	
	public static void main(String ars[]) throws ParseException, IOException {
		
		String url = "http://httpbin.org/post";
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		
		// add header
		String USER_AGENT = "Mozilla/5.0";
		post.setHeader("user-agent", USER_AGENT);
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("hello", "world"));
		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		
		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " + 
                                    response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		// #1
		System.out.println(result.toString());
		
		// #2
		System.out.println("OR");
		Header[] headers = response.getAllHeaders();
		for (Header header : headers) {
			System.out.println("Key:" + header.getName() 
	                           + ", Value:" + header.getValue());
		}
	}
}