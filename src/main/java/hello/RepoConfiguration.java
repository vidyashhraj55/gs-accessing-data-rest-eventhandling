package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepoConfiguration {
	@Bean
	PersonEventHandler personEventHandler()
	{   
//		PersonEventHandler p =new PersonEventHandler();
		
	
		return new PersonEventHandler();
	}
}
