package za.co.polymorph.chatference.callbacks;

public interface CreateRoomCallback {
    void success(String databaseReference);
    void error(String errorDescription);
}
