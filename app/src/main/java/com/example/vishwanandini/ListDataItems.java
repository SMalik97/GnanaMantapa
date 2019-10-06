package com.example.vishwanandini;

public class ListDataItems {

    String id,head,articles,upanasyas,prashnauttaras;

    public ListDataItems(String id,String head,String articles,String upanasyas,String prashnauttaras) {

        this.id=id;
        this.head=head;
        this.articles=articles;
        this.upanasyas=upanasyas;
        this.prashnauttaras=prashnauttaras;

    }



    public String getId() {
        return id;
    }
    public String getHead() {
        return head;
    }

    public String getArticles() {
        return articles;
    }
    public String getUpanasyas() {
        return upanasyas;
    }
    public String getPrashnauttaras() {
        return prashnauttaras;
    }


}
