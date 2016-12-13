package Istuary.com.http;

import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.cookie.Cookie;

public interface HttpClient {
	public String httpRequest(String RequestType, String url, String interf, List<NameValuePair> para,
			List<NameValuePair> form) throws Exception;

	public String urlEncode(String url);

	public List<Cookie> getCookie();

	public void setHeader(String name, String value);
	public Header[] getResponseHeader(String para);
}
