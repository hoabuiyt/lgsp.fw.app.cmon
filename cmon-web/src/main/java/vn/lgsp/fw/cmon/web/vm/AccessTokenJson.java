package vn.lgsp.fw.cmon.web.vm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AccessTokenJson {
	private String consumerKey;
	private String secretKey;
	private String tokenUrl;
	private JsonObject response;

	public AccessTokenJson() {
	}

	public AccessTokenJson(String consumerKey, String scretKey, String url) {
		this.consumerKey = consumerKey;
		this.secretKey = scretKey;
		this.tokenUrl = url;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getTokenUrl() {
		return tokenUrl;
	}

	public void setTokenUrl(String url) {
		this.tokenUrl = url;
	}

	public String toString() {
		return response != null ? response.toString() : "";
	}

	public JsonObject generateToken() {
		if (getConsumerKey() != null && getSecretKey() != null) {
			if (getTokenUrl() != null && !getTokenUrl().isEmpty()) {
				String grantType = "client_credentials";
				String keyBase64Encrypted = Base64.getEncoder()
						.encodeToString((getConsumerKey() + ":" + getSecretKey()).getBytes());
				String[] command = { "curl", "-k", "-d", "grant_type=" + grantType, "-H",
						"Authorization: Basic " + keyBase64Encrypted, getTokenUrl() };
				ProcessBuilder process = new ProcessBuilder(command);
				Process p;
				try {
					p = process.start();
					BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
					StringBuilder builder = new StringBuilder();
					String line = null;
					while ((line = reader.readLine()) != null) {
						builder.append(line);
						builder.append(System.getProperty("line.separator"));
					}
					String result = builder.toString();
					response = new JsonParser().parse(result).getAsJsonObject();
				} catch (IOException e) {
					System.out.print("error");
					e.printStackTrace();
				}
			} else {
				System.out.println("Token url must not be null");
			}
		} else {
			System.out.println("Consumer key and scret key must not be null");
		}
		return response;
	}
}
