package no.hvl.dat110.aciotdevice.client;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.io.IOException;
import com.google.gson.Gson;

public class RestClient {

	public RestClient() {
		// TODO Auto-generated constructor stub
	}

	private static String logpath = "/accessdevice/log";

	public void doPostAccessEntry(String message) {

		
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(new AccessMessage(message)));
		Request request = new Request.Builder().url("http://localhost:8080" + logpath).post(body).build();
		

		System.out.println(request.toString());
		
		try (Response response = client.newCall(request).execute()) {
			System.out.println(response.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String codepath = "/accessdevice/code";
	
	public AccessCode doGetAccessCode() {

		OkHttpClient client = new OkHttpClient();
		client.newBuilder().build();
		Request request = new Request.Builder().url("http://localhost:8080" + codepath).get().build();

		System.out.println(request.toString());
		
		AccessCode code = null;
		try {
			Gson gson = new Gson();

			Response response = client.newCall(request).execute();

			if (response != null) {

				String responseBody = response.body().string();
				System.out.println(responseBody);
				code = gson.fromJson(responseBody, AccessCode.class);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return code;
	}
}
