package fr.insalyon.telecom.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.insalyon.telecom.chat.services.MessageBoard;


@JsonIgnoreProperties(ignoreUnknown = true)
public class GistFile {

    private MessageBoard file;

    public GistFile() {
    }

    public GistFile(MessageBoard messageBoard) {
        file = messageBoard;
    }

    public MessageBoard getfile() {
        return file;
    }

    public MessageBoard getAfile() {
        return file;
    }

    public void setAfile(MessageBoard afile) {
        this.file = afile;
    }

    @Override
    public String toString() {
        return "GistFile{" +
                "file=" + file +
                '}';
    }
}
