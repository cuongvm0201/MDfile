package vn.techmaster.demotest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.javafaker.Faker;

import vn.techmaster.demotest.model.Employee;

@SpringBootApplication
@Transactional
public class DemotestApplication implements ApplicationRunner {
	@Autowired private EntityManager em;
	public static void main(String[] args) {
		SpringApplication.run(DemotestApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Faker faker = new Faker();
		for (int i = 0; i < 10; i++) {
			var employee = Employee.builder()
			.emailAddress(faker.internet().emailAddress())
			.firstName(faker.name().firstName())
			.lastName(faker.name().lastName())
			.build();
			em.persist(employee);			
		}
		em.flush();
		
	}
}
