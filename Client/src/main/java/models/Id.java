package models;

import views.IdTextView;

/*
 * POJO for an Id object
 */
public class Id {
    private String name;
    private String github;
    private String userid;
    
    public Id (String name, String github, String userid) {
        this.name = name;
        this.github = github;
        this.userid = userid;
    }

    public Id(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        IdTextView idTextView = new IdTextView(this);
        return idTextView.toString();
    }
}