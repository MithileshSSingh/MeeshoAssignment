package com.example.mithilesh.meeshoassignment.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BeanPullRequest {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("user")
    @Expose
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BeanPullRequest{" +
                "id=" + id +
                ", number=" + number +
                ", title='" + title + '\'' +
                ", user=" + user +
                '}';
    }
}
