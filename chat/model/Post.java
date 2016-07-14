package fr.insalyon.telecom.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable; //Cette classe est Serializable car on veut pouvoir lire et écrire dans l'état d'une instance de la classe

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post implements Serializable {

    private String author;
    private String message;

    public Post(String author, String message) {
        this.author = author;
        this.message = message;
    }

    public Post() {
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Post{" +
                "author='" + author + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}