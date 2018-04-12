package org.demo.spring_amqp_client_demo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/chatbot")
public class ClientController {
	
	@Autowired
	private Client client;

	@RequestMapping(method = RequestMethod.GET, path = "/")
	public String sayHello() {
		return "Hellow World!";
	}
	/*
	@RequestMapping(method = RequestMethod.POST, path = "/call", consumes = {"text/xml"})
	public String getResponseText(@RequestBody String messageStr) {  
		JSONObject responseJson = new JSONObject();
		String returnStr = null;
		try {
			//String messageStr =(String) message.get("message");
			System.out.println("Received - " + messageStr);
			JSONArray responseObj = client.send(messageStr);
			System.out.println("Response in Controller - " + responseObj);
			responseJson.put("response", responseObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseJson.toJSONString();
		
	}
	*/
	
	@RequestMapping(method = RequestMethod.POST, path = "/call", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String getResponseText(@RequestBody JSONObject message) {  
		JSONObject responseJson = new JSONObject();
		String returnStr = null;
		try {
			String messageStr =(String) message.get("message");
			System.out.println("Received - " + messageStr);
			JSONArray responseObj = client.send(messageStr);
			System.out.println("Response in Controller - " + responseObj);
			responseJson.put("response", responseObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseJson.toJSONString();
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/call")
	public String getResponseText1(@RequestParam(name="message") String message) {  
		JSONObject responseJson = new JSONObject();
		String returnStr = null;
		try {
			
			System.out.println("Received - " + message);
			JSONArray responseObj = client.send(message);
			System.out.println("Response in Controller - " + responseObj);
			responseJson.put("response", responseObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseJson.toJSONString();
		
	}
}
