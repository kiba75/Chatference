package za.co.polymorph.chatference.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Room {

    public String code;
    public String name;
    public int state;

    private String id;

    public Room () {
    }

    public Room (String code, String name, int state) {
        this.code = code;
        this.name = name;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
