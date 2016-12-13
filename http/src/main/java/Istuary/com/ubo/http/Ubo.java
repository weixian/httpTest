package Istuary.com.ubo.http;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import Istuary.com.Util.ConfigHelper;
import Istuary.com.Util.Log;
import Istuary.com.Util.ResponseData;

public class Ubo {

	private String index = null;
	private UboHttpClient ubo = new UboHttpClient();
	private String client = null;
	private String accessToken = null;
	private String uid = null;
	private String configFilePath = "./config/Config.xml";
	private  Log log = new Log(this.getClass());
	
	public Ubo(String url) {
		this.index = url;
	}
	public Ubo() {
		this.index = ConfigHelper.GetApiURL(configFilePath);
		if(!this.index.endsWith("/"))
		{
			this.index+="/";
		}
		
		log.info("Api url is: "+this.index);
	}

	public ResponseData Login(String user, String password) {
		ResponseData res = new ResponseData();
		List<NameValuePair> form = new ArrayList<NameValuePair>();
		form.add(new BasicNameValuePair("username", user));
		form.add(new BasicNameValuePair("password", password));

		try {
			String result = ubo.httpRequest("post", this.index, "auth/sign_in", null, form);
			res.setCode(ubo.getResponseCode());
			res.setData(result);
			if (res.getCode() == 200) {
				updateAccessToken();
				getClient();
				getUid();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;

	}

	public ResponseData getUboGroups() {
		ResponseData res = new ResponseData();

		try {

			setRequestHeader();
			String result = ubo.httpRequest("get", index, "ubo_groups", null, null);
			res.setCode(ubo.getResponseCode());
			res.setData(result);
			if (res.getCode() == 200) {
				updateAccessToken();
				getClient();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;

	}

	public void setRequestHeader() {
		ubo.setHeader("access-token", this.accessToken);
		ubo.setHeader("client", this.client);
		ubo.setHeader("token-type", "Bearer");
		ubo.setHeader("uid", this.uid);
	}

	public void updateAccessToken() {
		this.accessToken = ubo.getResponseHeader("access-token")[0].getValue();

	}

	public void getClient() {
		this.client = ubo.getResponseHeader("client")[0].getValue();

	}

	public void getUid() {
		this.uid = ubo.getResponseHeader("uid")[0].getValue();

	}
}
