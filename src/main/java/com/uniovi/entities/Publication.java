package com.uniovi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Publication {

    @Id
    @GeneratedValue
    private Long id;
    private String text;
    private String title;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Publication(Long id, String text, String title, Date date) {
	super();
	this.id = id;
	this.text = text;
	this.title = title;
	this.date = date;
    }

    public Publication(String text, String title, Date date, User user) {
	super();
	this.text = text;
	this.title = title;
	this.date = date;
	this.user = user;
    }

    public Publication() {

    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    @DateTimeFormat(pattern = "dd-MMM-YYYY")
    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    @Override
    public String toString() {
	return "Publication [id=" + id + ", text=" + text + ", title=" + title
		+ ", date=" + date + ", user=" + user + "]";
    }

}
