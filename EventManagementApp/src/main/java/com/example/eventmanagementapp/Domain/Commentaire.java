package com.example.eventmanagementapp.Domain;

import jakarta.persistence.*;

@Entity
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private Integer evaluation;
    @ManyToOne
    private UserE user;
    @ManyToOne
    private Event event;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }

    public UserE getUser() {
        return user;
    }

    public void setUser(UserE user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Commentaire(String text, Integer evaluation, UserE user, Event event) {
        this.text = text;
        this.evaluation = evaluation;
        this.user = user;
        this.event = event;
    }

    public Commentaire() {
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "text='" + text + '\'' +
                ", evaluation=" + evaluation +
                ", user=" + user +
                ", event=" + event +
                '}';
    }

}
