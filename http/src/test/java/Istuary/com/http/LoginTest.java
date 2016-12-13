package Istuary.com.http;

import org.testng.annotations.Test;

import Istuary.com.ubo.http.Ubo;

import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class LoginTest {
	Ubo test=null;
  @Test(dataProvider = "userList")
  public void Login(String user,String password,int code) {
	int n=  test.Login(user, password).getCode();
	Assert.assertEquals(n,code);
  }
  @BeforeMethod
  public void beforeMethod() {
//	  test =new Ubo("http://192.168.28.213:4000/");
	  test =new Ubo();
  }

  @AfterMethod
  public void afterMethod() {
	  
  }


  @DataProvider
  public Object[][] userList() {
    return new Object[][] {
      new Object[] { "euboadmin", "eubo@321#",200 },
      new Object[] { "euboadmin", "b",401 },
      new Object[] { "魏贤", "123",401 },
      new Object[] { "~!@#$%^&*()", "123",401 },
      new Object[] { "ye", "   ",401 },
      new Object[] { "1==1", "1==1",200 },
      new Object[] { "1234567890qwertyuiopasdfghjklzxcvbnm,.", "12334156586765327454577474741==1",401 },
    };
  }
}
