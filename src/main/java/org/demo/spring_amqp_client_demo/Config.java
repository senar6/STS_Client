package org.demo.spring_amqp_client_demo;

import org.demo.spring_amqp_client_demo.Client;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"rpc"})
@Configuration
public class Config {

		@Bean
		public DirectExchange exchange() {
			return new DirectExchange("demo.rpc");
		}

		@Bean
		public Client client() {
	 	 	return new Client();
		}

}
