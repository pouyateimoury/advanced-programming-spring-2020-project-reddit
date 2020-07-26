package sample.models;

import java.util.ArrayList;
import java.util.Date;

public class Comment {


    private String commentText;
    private ArrayList<CommentLike> commentLikes ;
    private ArrayList<Comment> commentsRealatedToThisComment;
    private Date createdTime;
    private User commentByUser;
    private int commentLikeBalance;

    private static  ArrayList<Comment> comments = new ArrayList<>();

    public Comment(String commentText , User user ) {
        this.commentText = commentText;
        this.commentLikes = new ArrayList<>();
        this.commentsRealatedToThisComment = new ArrayList<>();
        this.createdTime = new Date();
        this.commentLikeBalance = 0;
        this.commentByUser = user;
        comments.add(this);
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public ArrayList<Comment> getCommentsRealatedToThisComment() {
        return commentsRealatedToThisComment;
    }

    public void setCommentsRealatedToThisComment(ArrayList<Comment> commentsRealatedToThisComment) {
        this.commentsRealatedToThisComment = commentsRealatedToThisComment;
    }


    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }




    public ArrayList<CommentLike> getCommentLikes() {
        return commentLikes;
    }

    public void setCommentLikes(ArrayList<CommentLike> commentLikes) {
        this.commentLikes = commentLikes;
    }

    public int getCommentLikeBalance() {
        return commentLikeBalance;
    }

    public void setCommentLikeBalance(int commentLikeBalance) {
        this.commentLikeBalance = commentLikeBalance;
    }

    public User getCommentByUser() {
        return commentByUser;
    }

    public void setCommentByUser(User commentByUser) {
        this.commentByUser = commentByUser;
    }







}
