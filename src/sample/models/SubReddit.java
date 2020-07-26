package sample.models;


import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Date;

public class SubReddit {

    private String name;
    private String subRedditId;
    private Date createdDate;
    private Image profile;
    private String about;
    private ArrayList<Post> posts;
    private User subRedditOwner;

    private static  ArrayList<SubReddit> allSubReddits = new ArrayList<>();

    private SubReddit(String name, String subRedditId , Image profile , String about) {
        this.name = name;
        this.subRedditId = subRedditId;
        this.createdDate = new Date();
        this.posts = new ArrayList<>();
        this.profile = profile;
        this.about =   about ;
        allSubReddits.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubRedditId() {
        return subRedditId;
    }

    public void setSubRedditId(String subRedditId) {
        this.subRedditId = subRedditId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public static SubReddit creatSubReddit(String name, String subRedditId , Image profile , String about){

        return  new SubReddit(name,  subRedditId , profile , about);

    }


    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }


    public Image getProfile() {
        return profile;
    }

    public void setProfile(Image profile) {
        this.profile = profile;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public User getSubRedditOwner() {
        return subRedditOwner;
    }

    public void setSubRedditOwner(User subRedditOwner) {
        this.subRedditOwner = subRedditOwner;
    }

    public static SubReddit getSubRedditByPost(Post  post){

        for (SubReddit subReddit: allSubReddits) {

            for (Post post1: subReddit.getPosts()) {

                if(post1.equals(post)){

                    return subReddit;

                }

            }

        }

        return null;

    }

    public static ArrayList<SubReddit> searchSubReddit(String search){

        ArrayList<SubReddit> subReddits = new ArrayList<>();


        for (SubReddit subReddit: allSubReddits) {

            if(subReddit.getSubRedditId().contains(search)){

                subReddits.add(subReddit);

            }

            else if(subReddit.getName().contains(search)){

                subReddits.add(subReddit);
            }

        }

        return subReddits;

    }








}
