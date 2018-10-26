package za.co.polymorph.chatference.interfaces;

import com.google.firebase.database.ValueEventListener;

import za.co.polymorph.chatference.callbacks.CreateRoomCallback;
import za.co.polymorph.chatference.callbacks.GetQuestionsCallback;
import za.co.polymorph.chatference.callbacks.GetRoomCallback;
import za.co.polymorph.chatference.callbacks.PostQuestionCallback;

public interface IDatabaseService {
    void createRoom(String code, String name, CreateRoomCallback createRoomCallback);
    void getRoom(String code, GetRoomCallback getRoomCallback);

    void postQuestion(String roomUuid, String question, int type, PostQuestionCallback postQuestionCallback);
    void getQuestions(String roomUuid, GetQuestionsCallback getQuestionsCallback);
    void cancelGettingQuestions();
}
