package Istuary.com.ubo.http;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import Istuary.com.Util.Log;
import Istuary.com.http.HttpClient;

public class UboHttpClient implements HttpClient {

	private final CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
	private CloseableHttpResponse response;
	public static CookieStore cookieStore = new BasicCookieStore();
	private HttpClientContext localContext = HttpClientContext.create();
	private RequestConfig requestConfig = null;
	private HttpUriRequest request;
	private String entity = null;
	public HttpHead httpHead = new HttpHead();
	private HeaderIterator it;
	private  Log log = new Log(this.getClass());
	public String httpRequest(String RequestType, String url, String interf, List<NameValuePair> para,
			List<NameValuePair> form) throws Exception {
		if (interf != null) {
			url = url + "/" + interf;
		}

		if (para != null && !para.isEmpty()) {
			Iterator<NameValuePair> li = para.iterator();
			while (li.hasNext()) {
				NameValuePair cur = li.next();
				url = url + "&" + cur.getName() + "=" + cur.getValue();
			}
		}
		System.out.println(url);

		if (RequestType.equalsIgnoreCase("get")) {
			request = new HttpGet(url);
		} else if (RequestType.equalsIgnoreCase("post")) {
			request = new HttpPost(url);
			if (form != null && !form.isEmpty()) {
				((HttpEntityEnclosingRequestBase) request).setEntity(new UrlEncodedFormEntity(form, "utf-8"));
			}
		} else if (RequestType.equalsIgnoreCase("put")) {
			request = new HttpPut(url);
		} else {
			throw new Exception("The request type is incorrect!");
		}
		it = httpHead.headerIterator();
		while (it.hasNext()) {
			Header cur = it.nextHeader();
			request.addHeader(cur);
			// System.out.println(cur.toString().length());
		}

		try {
			// requestConfig =
			// RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
			((HttpRequestBase) request).setConfig(requestConfig);
			localContext.setCookieStore(cookieStore);
			response = httpClient.execute(request, localContext);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			log.error("Request return code is " + response.getStatusLine().getStatusCode());
			}
			entity = EntityUtils.toString(response.getEntity(), "UTF-8");
			log.info(entity);
		
		} catch (IOException e) {
			// // TODO Auto-generated catch block
			e.printStackTrace();
		}

		return entity;
	}
	
	

	public Header[] getResponseHeader(String para)

	{
		return response.getHeaders(para);
	}

	public void setHeader(String name, String value)

	{	httpHead.removeHeaders(name);
		httpHead.addHeader(name, value);
		
	}

	public List<Cookie> getCookie() {

		List<Cookie> cookies = null;
		try {
			cookies = cookieStore.getCookies();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cookies;
	}
	
	public int getResponseCode()
	{
		return response.getStatusLine().getStatusCode();
	}

	public String urlEncode(String url) {

		url = url.replace("{", "%7B");
		url = url.replace("}", "%7D");
		url = url.replace("\"", "%22");
		url = url.replace("[", "%5B");
		url = url.replace("]", "%5D");
		url = url.replace("<", "%3C");
		url = url.replace(">", "%3E");
		url = url.replace("\\/", "%2F");

		return url;
	}
}
