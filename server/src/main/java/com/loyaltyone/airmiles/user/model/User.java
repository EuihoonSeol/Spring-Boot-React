package com.loyaltyone.airmiles.user.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.loyaltyone.airmiles.text.model.Text;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private Text text;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}