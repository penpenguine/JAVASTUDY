package json.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JSONDecodingTestApp {

	/// main Method
	public static void main(String[] args) {
	
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//JSONValue.
		String data =  "{\"address\":\"����\",\"age\":1000,\"name\":\"ȫ�浿\"}";
		JSONObject jsonObj = (JSONObject)JSONValue.parse(data);
		//==> �Է°� Ȯ��
		System.out.println("JSON Object Ȯ�� : " +jsonObj);
		//==> Data ����
		System.out.println("==>JSON Object Data ���� ");
		System.out.println(jsonObj.get("address"));
		System.out.println("\n\n");
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//JSONValue Array.
		String arrayData =  "[\"����\",1000,\"ȫ�浿\"]";
		JSONArray jsonArray = (JSONArray)JSONValue.parse(arrayData);
		//==> �Է°� Ȯ��
		System.out.println("JSON Object Ȯ�� : " +jsonArray);
		//==> Data ����
		System.out.println("==>JSON Object Data ���� ");
		System.out.println(jsonArray.get(0));
	}
}