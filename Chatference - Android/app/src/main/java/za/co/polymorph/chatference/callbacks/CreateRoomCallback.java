package za.co.polymorph.chatference.callbacks;

import za.co.polymorph.chatference.model.Room;

public interface CreateRoomCallback {
    void success(Room room);
    void error(String errorDescription);
}
