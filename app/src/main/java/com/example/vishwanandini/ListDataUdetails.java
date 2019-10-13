package com.example.vishwanandini;

public class ListDataUdetails {

    String id,name,mobile,city,email;

    public ListDataUdetails(String id, String name, String mobile,String city,String email) {

        this.id=id;
        this.name=name;
        this.mobile=mobile;
        this.city=city;
        this.email=email;


    }



    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getMobile() {
        return mobile;
    }
    public String getCity() {
        return city;
    }
    public String getEmail() {
        return email;
    }

}
