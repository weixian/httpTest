package Istuary.com.Util;

public class ResponseData {
  private int code;
  private String message;
  private String data;
  private long time;
  private String accessToken;

  public int getCode() {

    return code;

  }

  public void setCode(int code) {

    this.code = code;
  }

  public String getMessage() {

    return message;
  }

  public void setMessage(String message) {

    this.message = message;
  }

  public String getData() {

    return data;
  }

  public void setData(String data) {

    this.data = data;
  }

  public long getTime() {

    return time;
  }

  public void setTime(long time) {

    this.time = time;
  }
  public String getAccessToken() {

	    return accessToken;
	  }
public void setAccessToken(String accessToken) {

	    this.accessToken = accessToken;
	  }

}
