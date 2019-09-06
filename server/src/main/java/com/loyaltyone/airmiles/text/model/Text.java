package com.loyaltyone.airmiles.text.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Text {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String content;

    public Text() {
    }

    public Text(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
