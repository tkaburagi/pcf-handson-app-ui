package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UiService {

    @Value( "${api.url.allbooks}" )
    private String apiUrl1;

    @Value( "${api.url.book}" )
    private String apiUrl2;

    @Value( "${api.url.dummy}" )
    private String dummyUrl;

    ObjectMapper mapper = new ObjectMapper();

    RestTemplate restTemplate = new RestTemplate();

    public Model getAllBooks(Model model) throws Exception {
        String result = restTemplate.getForObject(apiUrl1, String.class);
        Book[] bList = mapper.readValue(result, Book[].class);
        model.addAttribute("allbooks", bList);
        return  model;
    }

    public Model getBookById(@RequestParam("id") String id, Model model) throws Exception {
        String targetUrl = UriComponentsBuilder.fromUriString(apiUrl2).queryParam("id", id).build().toString();
        String result = restTemplate.getForObject(targetUrl, String.class);
        System.out.println("targetUrl: " + targetUrl);
        Book b = mapper.readValue(result, Book.class);
        model.addAttribute("searchedBook", b);
        return  model;
    }

    @HystrixCommand(fallbackMethod = "executeFallback")
    public Model dummy(Model model) {
        restTemplate.getForObject(dummyUrl, String.class);
        return model;
    }

    public Model executeFallback(Model model, Throwable e) {
        return model.addAttribute("message", "No available");
    }
}
