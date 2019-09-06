package com.loyaltyone.airmiles.text.controller;

import com.loyaltyone.airmiles.text.model.Text;
import com.loyaltyone.airmiles.text.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class TextController {

    @Autowired
    TextService textService;

    @PostMapping("/texts")
    public ResponseEntity<Text> createText(@RequestBody Map<String, String> param) {
        return textService.createText(param);
    }

    @PostMapping("/texts/{textId}/subtexts")
    public ResponseEntity<List<Text>> createSubtext(@PathVariable String textId, @RequestBody Map<String, String> param) {
        return textService.createSubtext(textId, param);
    }

}
