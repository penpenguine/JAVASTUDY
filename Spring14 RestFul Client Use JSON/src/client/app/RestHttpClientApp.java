package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import spring.domain.User;

public class RestHttpClientApp {
	
	// main Method
	public static void main(String[] args) throws Exception{
		
		////////////////////////////////////////////////////////////////////////////////////////////
		// �ּ��� �ϳ��� ó���ذ��� �ǽ�
		////////////////////////////////////////////////////////////////////////////////////////////
		
//		System.out.println("\n====================================\n");
		 //1.1 Http Get ��� Request : JsonSimple lib ���
		RestHttpClientApp.ReqeustHttpGet_UseJsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get ��� Request : CodeHaus lib ���
//		RestHttpClientApp.ReqeustHttpGet_UseCodeHaus();
		
		
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Protocol POST ��� Request 
//		//	: Form Data����(JSON �̿�) / JsonSimple lib ��� 
//		RestHttpClientApp.ReqeustHttpPostData_UseJsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 2.2 Http Protocol POST ��� Request 
//		//	: Form Data����(JSON �̿�) / CodeHaus lib ���
		//RestHttpClientApp.ReqeustHttpPostData_UseCodeHaus();
	
	}
	
	
//================================================================//
	//1.1 Http Protocol GET Request : JsonSimple 3rd party lib ���
	public static void ReqeustHttpGet_UseJsonSimple() throws Exception{
		
		// HttpClient : Http Protocol �� Client �߻�ȭ Bean 
		HttpClient httpClient = new DefaultHttpClient();
		
		// Request URL Make
		String url= 	"http://127.0.0.1:8080/Spring14/user/json/user01"
								+"?name=user02&age=10";

		// HttpGet : Http Protocol  GET ��� Request Header ����
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// Request ���� �� Response �ޱ�(?)
		// HttpResponse : Http Protocol ���� Message �߻�ȭ Bean
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response ��� Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response Header/Body �� Body �ޱ�(?)
		// HttpEntity : Http Protocol Body �߻�ȭ Bean		
		HttpEntity responsHttpEntity = httpResponse.getEntity();
		
		//==>Server ���� ���� Data �б����� HttpEntity�� ���� InputStream ����
		InputStream is = responsHttpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> Server���� ���� JSONData => JSONObjcet ��ü����
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
	
	
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib ���
	public static void ReqeustHttpGet_UseCodeHaus() throws Exception{
		
		// HttpClient : Http Protocol �� Client �߻�ȭ Bean
		HttpClient httpClient = new DefaultHttpClient();
		
		// Request URL Make
		String url= 	"http://127.0.0.1:8080/Spring14/user/json/user01"
								+"?name=user02&age=10";

		// HttpGet : Http Protocol  GET ��� Request Header ����
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// Request ���� �� Response �ޱ�(?)
		// HttpResponse : Http Protocol ���� Message �߻�ȭ Bean
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response ��� Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response Header/Body �� Body �ޱ�(?)
		// HttpEntity : Http Protocol Body �߻�ȭ Bean	
		HttpEntity responseHttpEntity = httpResponse.getEntity();
		
		//==>Server ���� ���� Data �б����� HttpEntity�� ���� InputStream ����
		InputStream is = responseHttpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> Server���� ���� JSONData => JSONObjcet ��ü����
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		//==> Server���� ���� JSONData => Domain Object �� Binding
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.get("user").toString(), User.class);
		 System.out.println(user);
	}
//================================================================//	
	
	
//================================================================//
	//2.1 Http Protocol POST Request : FromData ���� / JsonSimple 3rd party lib ���
	public static void ReqeustHttpPostData_UseJsonSimple() throws Exception{
		
		// HttpClient : Http Protocol �� Client �߻�ȭ Bean 
		HttpClient httpClient = new DefaultHttpClient();
		
		// Request URL Make
		String url = "http://127.0.0.1:8080/Spring14/user/json/getUser/user01";
		
		// HttpGet : Http Protocol  POST ��� Request Header ����
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		
		//==> POST ����� Body �� Data ����
		//==> QueryString (name = value)���� �������� �ʰ� 
		//==> JSONData �������� Data Make
		
		//[ ��� 1 : String ���]
		//String data =  "{\"userId\":\"test\",\"userName\":\"ȫ�浿\"}";
		
		//[ ��� 2 : JSONObject ���]
		JSONObject json = new JSONObject();
		json.put("userId", "test");
		json.put("userName", "ȫ�浿");
		

		//==> Request Header/Body �� Body �����(?)
		// HttpEntity : Http Protocol Body �߻�ȭ Bean	
		HttpEntity requestHttpEntity = new StringEntity(json.toString(),"utf-8");
		httpPost.setEntity(requestHttpEntity);
		
		// Request ���� �� Response �ޱ�(?)
		// HttpResponse : Http Protocol ���� Message �߻�ȭ Bean
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response ��� Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response Header/Body �� Body �ޱ�(?)
		// HttpEntity : Http Protocol Body �߻�ȭ Bean		
		HttpEntity responseHttpEntity = httpResponse.getEntity();
		
		//==>Server ���� ���� Data �б����� HttpEntity�� ���� InputStream ����
		InputStream is = responseHttpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> Server���� ���� JSONData => JSONObjcet ��ü����
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	
	}
	
	
	//2.2 Http Protocol POST ��� Request : FromData���� 
	//==> JsonSimple + codehaus 3rd party lib ���
	public static void ReqeustHttpPostData_UseCodeHaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		// Request URL Make
		String url = "http://127.0.0.1:8080/Spring14/user/json/getUser/user01";
		
		// HttpGet : Http Protocol  POST ��� Request Header ����
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");

		
		//==> POST ����� Body �� Data ����
		//==> QueryString (name = value)���� �������� �ʰ� 
		//==> JSONData �������� Data Make
		
//		//[ ��� 1 : String ���]
//		String data =  "{\"userId\":\"test\",\"userName\":\"ȫ�浿\"}";
//		
//		//[ ��� 2 : JSONObject ���]
//		JSONObject json = new JSONObject();
//		json.put("userId", "test");
//		json.put("userName", "ȫ�浿");
		
		//[ ��� 3 : codehaus ���]
		User user = new User("test" , "ȫ�浿" , "1111" , null , 10);
		ObjectMapper objectMapper01 = new ObjectMapper();
		//Domain Object ==> JSON Value ��ȯ
		String jsonValue = objectMapper01.writeValueAsString(user);
		
		
		//==> Request Header/Body �� Body �����(?)
		// HttpEntity : Http Protocol Body �߻�ȭ Bean	
		HttpEntity requesthttpEntity = new StringEntity(jsonValue,"utf-8");
		httpPost.setEntity(requesthttpEntity);
		
		// Request ���� �� Response �ޱ�(?)
		// HttpResponse : Http Protocol ���� Message �߻�ȭ Bean
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response ��� Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response Header/Body �� Body �ޱ�(?)
		// HttpEntity : Http Protocol Body �߻�ȭ Bean		
		HttpEntity reponsehttpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = reponsehttpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> �ٸ� ������� serverData ó�� 
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> Server���� ���� JSONData => JSONObjcet ��ü����
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		//==> Server���� ���� JSONData => Domain Object �� Binding
		ObjectMapper objectMapper = new ObjectMapper();
		User returnUser = objectMapper.readValue(jsonobj.get("user").toString(), User.class);
		System.out.println(returnUser);
	}
	
}