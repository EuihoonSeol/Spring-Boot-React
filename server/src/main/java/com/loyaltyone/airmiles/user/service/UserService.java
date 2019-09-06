package com.loyaltyone.airmiles.user.service;

import com.loyaltyone.airmiles.user.model.User;
import com.loyaltyone.airmiles.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> createUser(Map<String, String> param) {
        RestTemplate restTemplate = new RestTemplate();
        String weatherUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + param.get("City") + "&APPID=23e75bf33e0983ab4867e9cfffec1369&units=metric";
        ResponseEntity<String> response = restTemplate.getForEntity(weatherUrl, String.class);
        int statusCode = response.getStatusCode().value();

        User user = new User();
        user.setName(param.get("Name"));
        user.setCity(param.get("City"));

        if (statusCode == 404) {
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }

        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> bodyMap = parser.parseMap(response.getBody());
        String[] coordArr = bodyMap.get("coord").toString().split(",");
        String lon = coordArr[0].split("=")[1];
        String lat = coordArr[1].split("=")[1].replaceAll("}","");
        String[] tempArr = bodyMap.get("main").toString().split(",");
        String temp = tempArr[0].split("=")[1];
        user.setLatitude(lat);
        user.setLongitude(lon);
        user.setTemperature(temp);

        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}