package fr.insalyon.telecom.chat.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.insalyon.telecom.chat.model.GistFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class GistRequest {

    private String description;
    private Boolean pub;
    private List<GistFile> files;

    public GistRequest(String description, Boolean pub, MessageBoard messageBoard) {
        this.description = description;
        this.pub = pub;
        files = new ArrayList<>();
        files.add(new GistFile(messageBoard));
    }

    public GistRequest() {
    }

    public Boolean getPublic() {
        return pub;
    }

    public void setPublic(Boolean pub) {
        this.pub = pub;
    }

    public void setFiles(List<GistFile> files) {
        this.files = files;
    }

    public String getDescription() {
        return description;
    }

    public List<GistFile> getFiles() {
        return files;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "GistRequest{" +
                "description='" + description + '\'' +
                ", public=" + pub +
                ", files=" + files +
                '}';
    }
}
