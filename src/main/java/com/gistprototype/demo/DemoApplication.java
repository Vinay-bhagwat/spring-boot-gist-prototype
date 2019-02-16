package com.gistprototype.demo;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.gistprototype.demo.model.Gist;
import com.gistprototype.demo.repository.GistRepository;

@SpringBootApplication
public class DemoApplication {
	@Autowired
	GistRepository gistRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	@Bean
	InitializingBean sendDatabase() {
		return () -> {

			List<Gist>gistList=gistRepository.findAll();
			if(gistList.isEmpty()) {
				gistRepository.save(new Gist("gist_comment_info_api",0));
				gistRepository.save(new Gist("gist_create_comment_api",0));
				gistRepository.save(new Gist("gist_info_api",0));

			}	
		};

	}
}


