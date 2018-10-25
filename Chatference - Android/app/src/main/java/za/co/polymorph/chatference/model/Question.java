package za.co.polymorph.chatference.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Question {

    public String roomUuid;
    public String question;
    public int votes;
    public int type;
    public int state;

    private String id;

    public Question() {

    }

    public Question(String roomUuid, String question, int votes, int type, int state) {
        this.roomUuid = roomUuid;
        this.question = question;
        this.votes = votes;
        this.type = type;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
