package za.co.polymorph.chatference.callbacks;

import za.co.polymorph.chatference.model.Room;

public interface GetRoomCallback {
    void success(Room room);
    void noResult();
    void error(String message);
}
