package sample.models;

import javafx.scene.image.Image;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class User {


    private String userName;
    private String email;
    private String password;
    private String name;
    private ArrayList<Post> posts;
    private ArrayList<SubReddit> subReddits ;
    private ArrayList<User> following ;
    private Date joinedDate;
    private String about;
    private int karma;
    private Image profile;
    private Image background;
    private static  ArrayList<User> allUsers = new ArrayList<>();


    private User(String userName, String email, String password) throws FileNotFoundException {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.karma = 1;
        this.posts = new ArrayList<>();
        this.subReddits = new ArrayList<>();
        this.following = new ArrayList<>();
        this.joinedDate = new Date();
        this.profile = new Image("/sample/assets/hi.png");
        this.background = new Image("/sample/assets/back.jpg");
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public Image getProfile() {
        return profile;
    }

    public void setProfile(Image profile) {
        this.profile = profile;
    }

    public Image getBackground() {
        return background;
    }

    public void setBackground(Image background) {
        this.background = background;
    }


    public ArrayList<SubReddit> getSubReddits() {
        return subReddits;
    }

    public void setSubReddits(ArrayList<SubReddit> subReddits) {
        this.subReddits = subReddits;
    }

    public ArrayList<User> getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList<User> usersFollow) {
        this.following = usersFollow;
    }




    //Creat User

    public static User creatUser(String userName, String email, String password) throws FileNotFoundException {
        return  new User(userName , email , password);
    }

    //sign up

    public static User signUp(String userName, String email, String password) throws FileNotFoundException {

        for (User user: allUsers) {

            if(user.getEmail().equals(email) || user.getPassword().equals(password)){

                return  null;

            }

        }

        User newUser =  User.creatUser(userName , email , password);

        allUsers.add(newUser);

        return newUser;


    }

    //login

    public static User logIn(String userName , String  password){

        for (User user:allUsers) {

            if(user.getUserName().equals(userName)){

                if (user.getPassword().equals(password)){

                    return user;

                }
            }
        }

        return null;

    }


    //get the posts of the user to show in Home page
    public ArrayList<Post> getPostsForUser(){

        ArrayList<Post> posts = new ArrayList<>(this.posts);

        for (SubReddit subReddit: this.getSubReddits()) {

            for (Post post: subReddit.getPosts()) {

                posts.add(post);

            }

        }

        for (User user: this.getFollowing()) {

            for (Post post: user.getPosts()) {

                posts.add(post);

            }

        }

        posts.sort(new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                return Integer.compare( o2.getPostlikebalance() , o1.getPostlikebalance());
            }
        });

        return  posts;


    }

    public  static  User findUserByPost(Post post){
        for (User user: allUsers) {

            for (Post post1: user.getPosts()) {

                if(post1.equals(post)){

                    return user;

                }


            }

        }

        return  null;

    }

    public static  int getSubRedditMembers(SubReddit subReddit){

        int a =0;

        for (User user: allUsers) {

            for (SubReddit subreddit: user.getSubReddits()) {

                if(subreddit.equals(subReddit)){

                    a++;

                }

            }

        }

        return a;




    }

    public static  int  getUserRelation(User user ,SubReddit subReddit){

        //return 0--> not member
        //1 --> member
        //2-- > owner

        int a ;

        for (SubReddit subreddit: user.getSubReddits()) {

            if(subreddit.equals(subReddit)){

                if(subreddit.getSubRedditOwner().equals(user)){

                    return  2;

                }
                else {
                    return 1;
                }



            }

        }

        return 0;

        }

    public Boolean isFollowed(User user){

        for (User user1: this.getFollowing() ) {

            if(user1.equals(user)){

                return true;

            }

        }

        return false;

    }


    public static ArrayList<User> searchUser(String search){

        ArrayList<User> users = new ArrayList<>();


        for (User user: allUsers) {

            if(user.getUserName().contains(search)){

                users.add(user);

            }

        }

        return users;

    }






}
