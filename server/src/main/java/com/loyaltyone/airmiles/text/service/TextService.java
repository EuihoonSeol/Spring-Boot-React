package com.loyaltyone.airmiles.text.service;

import com.loyaltyone.airmiles.text.model.Text;
import com.loyaltyone.airmiles.user.model.User;
import com.loyaltyone.airmiles.user.repository.UserRepository;
import com.loyaltyone.airmiles.text.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TextService {

    @Autowired
    private TextRepository textRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Text> createText(Map<String, String> param) {
        Text text = new Text();
        text.setContent(param.get("Text"));
        text.setDateTime(LocalDateTime.now());
        User user = userRepository
                .findById(Long.valueOf(param.get("UserId")))
                .orElse(null);
        if (user == null) {
            return new ResponseEntity<>(text, HttpStatus.NOT_ACCEPTABLE);
        }

        text.setUser(user);

        textRepository.save(text);

        return new ResponseEntity<>(text, HttpStatus.OK);
    }

    public ResponseEntity<List<Text>> createSubtext(String textId, Map<String, String> param) {
        Text text = new Text();
        text.setContent(param.get("Text"));
        text.setDateTime(LocalDateTime.now());
        text.setUser(null);
        Text parent = textRepository
                .findById(Long.valueOf(textId))
                .orElse(null);
        if (parent == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }

        text.setParent(parent);

        textRepository.save(text);

        List<Text> allTexts = textRepository.findAll();
        List<Text> texts = new ArrayList<Text>();
        for (Text temp : allTexts) {
            if (temp != null && temp.getParent() != null && temp.getParent().getId().equals(parent.getId())) {
                Text text1 = new Text();
                text1.setId(temp.getId());
                text1.setContent(temp.getContent());
                text1.setDateTime(temp.getDateTime());
                texts.add(text1);
            }
        }

        return new ResponseEntity<>(texts, HttpStatus.OK);
    }
}
