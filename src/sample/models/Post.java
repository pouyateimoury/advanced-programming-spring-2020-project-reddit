package sample.models;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Date;

public class Post {



    private ArrayList<Comment> comments ;
    private ArrayList<PostLike> postLikes ;
    private int Postlikebalance;
    private String title;
    private String description;
    private Date createdTime;
    private Image imageUrl;


    private static  ArrayList<Post> allPosts = new ArrayList<>();


    private Post(String title, String description, Image imageUrl) {
        this.title = title;
        this.description = description;
        this.comments = new ArrayList<>();
        this.postLikes = new ArrayList<>();
        this.createdTime = new Date();
        this.imageUrl = imageUrl;
        this.Postlikebalance = 0;
        allPosts.add(this);
    }


    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static Post creatPost(String title, String description , Image imageUrl){

        return  new Post(title, description  , imageUrl);
    }


    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }


    public Image getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Image imageUrl) {
        this.imageUrl = imageUrl;
    }


    public ArrayList<PostLike> getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(ArrayList<PostLike> postLikes) {
        this.postLikes = postLikes;
    }


    public int getPostlikebalance() {
        return Postlikebalance;
    }

    public void setPostlikebalance(int postlikebalance) {
        Postlikebalance = postlikebalance;
    }

    public static ArrayList<Post> searchPost(String search){

        ArrayList<Post> posts = new ArrayList<>();


        for (Post post: allPosts) {

            if(post.getTitle().contains(search)){

                posts.add(post);

            }

        }

        return posts;

    }















}
