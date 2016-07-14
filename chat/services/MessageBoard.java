package fr.insalyon.telecom.chat.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IList;
import fr.insalyon.telecom.chat.model.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageBoard { //MessageBoard could implement the List interface so that it can be used as a List as well...
                            //But you need to implements all methods
    private final HazelcastInstance instance;
    private String content;


    public MessageBoard() {
        Config config = new Config();
        instance = Hazelcast.newHazelcastInstance(config);
    }

    public List<Post> getPosts() {
        return getList();
    }

    private IList<Post> getList() {
        return instance.getList("message-board");
    }

    public void post(Post post) {
        getList().add(0, post);
    }

    public String getContent() {
        final String[] str = {""};
        getPosts().forEach(post -> {
            str[0] += post.toString() + "\n";
        });
        return str[0];
    }

    @Override
    public String toString() {
        return "MessageBoard{" +
                "content='" + getContent() + '\'' +
                '}';
    }
}