package za.co.polymorph.chatference.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Comment {

    public String roomUuid;
    public String comment;
    public int votes;
    public int state;

    private String id;

    public Comment() {
    }

    public Comment(String roomUuid, String comment, int votes, int state) {
        this.roomUuid = roomUuid;
        this.comment = comment;
        this.votes = votes;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
