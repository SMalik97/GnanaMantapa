package com.goldenfuturecommunication.gnanamantapa;

public class ListDatacomnt {

    String postId,postcatagory,name,email,comment,postingDate;

    public ListDatacomnt(String postId, String postcatagory, String name, String email, String comment, String postingDate) {

        this.postId=postId;
        this.postcatagory=postcatagory;
        this.name=name;
        this.email=email;
        this.comment=comment;
        this.postingDate=postingDate;


    }



    public String getPostId() {
        return postId;
    }
    public String getPostcatagory() {
        return postcatagory;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getComment() {
        return comment;
    }
    public String getPostingDate() {
        return postingDate;
    }

}
