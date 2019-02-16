package com.gistprototype.demo.controller;


import com.gistprototype.demo.exception.ResourceNotFoundException;
import com.gistprototype.demo.model.Gist;
import com.gistprototype.demo.repository.GistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
//@Configuration(value = { RestTemplateConfig, HttpClientConfig })

public class GistController {

	@Autowired
	GistRepository gistRepository;

	@Autowired
	RestTemplate restTemplate;


	@GetMapping("/gist/count")
	 @CrossOrigin(origins = "http://localhost:3000")
	public List<Gist> getGistApiCount() {

		return gistRepository.findAll();


	}

	@PostMapping("/gist/{id}")
	 @CrossOrigin(origins = "http://localhost:3000")
	public String getGist(@PathVariable(value = "id") String gistId) {

		Gist gist=gistRepository.findEntityByApiType("gist_info_api");
		Integer count=gist.getApiHitCount()+1;
		gistRepository.updateApiCount(count, "gist_info_api");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		return restTemplate.exchange("https://api.github.com/gists/"+gistId, HttpMethod.GET, entity, String.class).getBody();

	}

	@PostMapping("/gist/{id}/comments")
	 @CrossOrigin(origins = "http://localhost:3000")
	public String getGistComment(@PathVariable(value = "id") String gistId) {
		Gist gist=gistRepository.findEntityByApiType("gist_comment_info_api");
		Integer count=gist.getApiHitCount()+1;
		gistRepository.updateApiCount(count, "gist_comment_info_api");

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("https://api.github.com/gists/"+gistId+"/comments", HttpMethod.GET, entity, String.class).getBody();

	}

	@PostMapping("/gist/{id}/post/comment")
	 @CrossOrigin(origins = "http://localhost:3000")
	public String postGistComment(@Valid @RequestBody String body,@PathVariable(value = "id") String gistId) {

		Gist gist=gistRepository.findEntityByApiType("gist_create_comment_api");
		Integer count=gist.getApiHitCount()+1;
		gistRepository.updateApiCount(count, "gist_create_comment_api");

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(body,headers);

		return restTemplate.exchange("https://api.github.com/gists/"+gistId+"/comments", HttpMethod.POST, entity, String.class).getBody();

	}



}
