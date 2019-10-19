package com.goldenfuturecommunication.gnanamantapa;

public class ListDataArticles {

    String id,title,content,audio;

    public ListDataArticles(String id, String title, String content,String audio) {

        this.id=id;
        this.title=title;
        this.content=content;
        this.audio=audio;


    }



    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getAudio() {
        return audio;
    }

}
