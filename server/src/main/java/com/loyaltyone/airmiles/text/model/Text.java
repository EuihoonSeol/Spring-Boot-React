package com.loyaltyone.airmiles.text.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.loyaltyone.airmiles.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Text {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime dateentered;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Text parent;

    @JsonBackReference
    @OneToMany(mappedBy="parent")
    private Set<Text> subordinates = new HashSet<Text>();

    public Text() {
    }

    public Text(String content, LocalDateTime dateEntered, User user, Text parent) {
        this.content = content;
        this.dateentered = dateEntered;
        this.user = user;
        this.parent = parent;
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

    public LocalDateTime getDateTime() {
        return dateentered;
    }

    public void setDateTime(LocalDateTime dateEntered) {
        this.dateentered = dateEntered;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Text getParent() { return parent; }

    public void setParent(Text parent) { this.parent = parent; }

}
