package com.loyaltyone.airmiles.text.service;

import com.loyaltyone.airmiles.text.model.Text;
import com.loyaltyone.airmiles.text.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TextService {

    @Autowired
    private TextRepository textRepository;

    public ResponseEntity<Text> createText(Map<String, String> param) {
        Text text = new Text();
        text.setContent(param.get("Text"));
        text.setDateTime(LocalDateTime.now());

        textRepository.save(text);

        return new ResponseEntity<>(text, HttpStatus.OK);
    }
}
