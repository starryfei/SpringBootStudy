package com.starry.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName WetherService
 * @Description TODO
 * @Author starryfei
 * @Date 2018/7/29 18:29
 * @Version 1.0
 **/
@Service
public class WetherService {
    @Autowired
    private RestTemplate restTemplate;
    public String getWetherByName(String name) {
        String uri="http://wthrcdn.etouch.cn/weather_mini?city="+name;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String strbody = restTemplate.exchange(uri, HttpMethod.GET, entity,String.class).getBody();
        return strbody;

    }
}
