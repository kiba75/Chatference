package za.co.polymorph.chatference.interfaces;

import za.co.polymorph.chatference.callbacks.CreateRoomCallback;
import za.co.polymorph.chatference.callbacks.GetRoomCallback;

public interface IDatabaseService {
    void createRoom(String code, String name, CreateRoomCallback createRoomCallback);
    void getRoom(String code, GetRoomCallback getRoomCallback);
}
