package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class Application {

	private static final SpringApplication sa = null;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);;
//		 ConfigurableApplicationContext context = sa.run(args);
//	        Person bean = context.getBean(Person.class);
//	        bean.display();
//	        context.start();
//	        context.close();
		
		
	}
}