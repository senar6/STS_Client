package org.demo.spring_amqp_client_demo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class Client {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private DirectExchange exchange;

	int start = 0;

	
	public JSONArray send(String message) {
		JSONArray recordsJson = new JSONArray();
		System.out.println(" [x] Requesting (" + message  + ")");
		String response = "";
		try {	
				Object responseObj =  template.convertSendAndReceive(exchange.getName(), "rpc", message);
				System.out.println("Object of class received-" + responseObj.getClass());
				System.out.println(responseObj instanceof byte[]);
				if (responseObj instanceof byte[]) {
					response = new String(((byte[])responseObj), "UTF-8");
				} else {
					response = responseObj.toString();
				}
				System.out.println(" [.] Got '" + response + "'");
		} catch (Exception e) {
			System.err.println("Error while getting and converting response ");
			e.printStackTrace();
		}
		System.out.println("parsing - [" + response +"]");
		
		
		System.out.println(response);
		try {
		if (!StringUtils.isEmpty(response)) {
			String[] records = response.split("#@");
			//JSONArray recordsJson = new JSONArray();
			for(String record : records) {
				JSONObject recordObj = new JSONObject();
				String[] fields = record.split("#&");
				for (String field : fields ) {
					String[] keyValuePair = field.split("#:");
					recordObj.put(keyValuePair[0].trim(), keyValuePair[1].trim());
				}
				recordsJson.add(recordObj);
			}
			// responseObj.put("response", recordsJson);
			
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recordsJson;
	}

}
