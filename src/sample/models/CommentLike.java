package sample.models;

public class CommentLike {

    private User userLikedBy;
    private TypeOfLike typeOfLike;


    public CommentLike(User userLikedBy, TypeOfLike typeOfLike) {
        this.userLikedBy = userLikedBy;
        this.typeOfLike = typeOfLike;
    }


    public User getUserLikedBy() {
        return userLikedBy;
    }

    public void setUserLikedBy(User userLikedBy) {
        this.userLikedBy = userLikedBy;
    }

    public TypeOfLike getTypeOfLike() {
        return typeOfLike;
    }

    public void setTypeOfLike(TypeOfLike typeOfLike) {
        this.typeOfLike = typeOfLike;
    }





}
