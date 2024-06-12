package cn.edu.zucc;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import cn.edu.zucc.service.KnowledgePointService;

@SpringBootApplication
public class L11RabbitmqQueueDemoApplication {

	// @Autowired
	// private KnowledgePointService knowledgePointService;

	public static void main(String[] args) {
		SpringApplication.run(L11RabbitmqQueueDemoApplication.class, args);
	}
}