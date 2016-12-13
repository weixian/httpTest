package Istuary.com.http;

import Istuary.com.Util.ResponseData;
import Istuary.com.ubo.http.Ubo;

public class Login {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	  Ubo test =new Ubo("http://192.168.28.215:4000/");
	  
	  ResponseData res=  test.Login("euboadmin", "eubo@321#");
	  
	  System.out.println(res.getCode()+"			"+res.getData());
res=  test.getUboGroups();
	  
	  System.out.println(res.getCode()+"			"+res.getData());

	}

}
