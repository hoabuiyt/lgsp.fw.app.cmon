package vn.lgsp.fw.cmon.web.vm;

import org.wso2.client.api.ApiClient;
import org.wso2.client.api.Configuration;
import org.wso2.client.api.Administratives.DefaultApi;


public class LgspService {
	public static DefaultApi apiInstance;
	static final String key = "jeXLHy9eIJ9sd7g21pKWDh1s3C4a";
	static final String secret = "iOsb1hhvE3_58dXgfcHOEorpdToa";
	static final String urlToken = "https://192.168.1.180:8244/token";
	static final String basePath = "http://192.168.1.180:8281/administratives/v1.0.0";
	
	/**
	 * Return DefaultApi, used to call functions end point
	 */
	public static DefaultApi getService() {
		if(apiInstance == null) {
			ApiClient defaultClient = Configuration.getDefaultApiClient();
	        String accessToken = getCurrentToken();
	        defaultClient.addDefaultHeader("Authorization", "Bearer " + accessToken);
	        defaultClient.setBasePath(basePath);
	        apiInstance = new DefaultApi();
	        apiInstance.setApiClient(defaultClient);
		}
        return apiInstance;
	}
	
	/**
	 * Return current access token in API management server
	 */
	public static String getCurrentToken() {
		String accessToken = new AccessTokenGenerator(key, secret, urlToken).generateTokenString();
		return accessToken;
	}
}
