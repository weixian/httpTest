package Istuary.com.http;

import org.testng.annotations.Test;

import Istuary.com.ubo.http.Ubo;

import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterTest;

public class UboGroupTest {
	Ubo test=null;
	
  @Test(dataProvider = "dp")
    public void UboGroup(String user,String password,int code) {
	  test.Login(user, password);
	 int n=test.getUboGroups().getCode();
	 Assert.assertEquals(n,code);
  }
  @BeforeMethod
  public void beforeMethod() {
	  test =new Ubo();
	  
  }

  @AfterMethod
  public void afterMethod() {
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
    	  new Object[] { "euboadmin", "eubo@321#",200 },
          new Object[] { "euboadmin", "b",200 }
    };
  }
}
