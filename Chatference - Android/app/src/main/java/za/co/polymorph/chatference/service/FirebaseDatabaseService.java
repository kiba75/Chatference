package za.co.polymorph.chatference.service;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import za.co.polymorph.chatference.callbacks.CreateRoomCallback;
import za.co.polymorph.chatference.callbacks.GetQuestionsCallback;
import za.co.polymorph.chatference.callbacks.GetRoomCallback;
import za.co.polymorph.chatference.callbacks.PostQuestionCallback;
import za.co.polymorph.chatference.interfaces.IDatabaseService;
import za.co.polymorph.chatference.model.Question;
import za.co.polymorph.chatference.model.Room;

public class FirebaseDatabaseService implements IDatabaseService {

    private static DatabaseReference database = null;
    private static FirebaseDatabaseService firebaseService = null;
    private static ValueEventListener questionValueEventListener = null;

    private FirebaseDatabaseService() {
        this.database = FirebaseDatabase.getInstance().getReference();
    }

    public static FirebaseDatabaseService getInstance() {
        if (firebaseService == null) {
            return new FirebaseDatabaseService();
        } else {
            return firebaseService;
        }
    }

    @Override
    public void createRoom(String code, String name, final CreateRoomCallback createRoomCallback) {
        final Room room = new Room(code, name, 1);

        database.child("Room").child(database.push().getKey()).setValue(room, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError == null) {
                    room.setId(databaseReference.getKey());
                    createRoomCallback.success(room);
                } else {
                    createRoomCallback.error(databaseError.getMessage());
                }
            }
        });
    }

    @Override
    public void getRoom(String code, final GetRoomCallback getRoomCallback) {
        database.child("Room").orderByChild("code").equalTo(code).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String code = (String) snapshot.child("code").getValue();
                        String name = (String) snapshot.child("name").getValue();
                        long state = (long) snapshot.child("state").getValue();

                        Room room = new Room(code, name, (int) state);
                        room.setId(snapshot.getKey());
                        getRoomCallback.success(room);
                        break; //TODO Cater for more than one result.  There should be only one
                    }
                } else {
                    getRoomCallback.noResult();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                getRoomCallback.error(databaseError.getMessage());
            }
        });
    }

    @Override
    public void postQuestion(String roomUuid, String question, int type, final PostQuestionCallback postQuestionCallback) {
        final Question questionEntry = new Question(roomUuid, question, 0, type, 1);

        database.child("Question").child(database.push().getKey()).setValue(questionEntry, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError == null) {
                    questionEntry.setId(databaseReference.getKey());
                    postQuestionCallback.success(questionEntry);
                } else {
                    postQuestionCallback.error(databaseError.getMessage());
                }

            }
        });
    }

    @Override
    public void getQuestions(String roomUuid, final GetQuestionsCallback getQuestionsCallback) {
        if (questionValueEventListener != null) {
            cancelGettingQuestions();
        }

        questionValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<Question> allQuestion = new ArrayList<>();

                for (DataSnapshot singelQuestion : dataSnapshot.getChildren()) {
                    String id = singelQuestion.getKey();
                    String questionText = (String) singelQuestion.child("question").getValue();
                    String roomUuid = (String) singelQuestion.child("roomUuid").getValue();
                    long state = (long) singelQuestion.child("state").getValue();
                    long type = (long) singelQuestion.child("type").getValue();
                    long votes = (long) singelQuestion.child("votes").getValue();

                    Question question = new Question(roomUuid, questionText, (int) votes, (int) type, (int) state);
                    question.setId(id);

                    allQuestion.add(question);
                }

                Question [] questions =  new Question[allQuestion.size()];
                questions = allQuestion.toArray(questions);
                getQuestionsCallback.questionsUpdate(questions);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                getQuestionsCallback.error(databaseError.getMessage());
            }
        };

        database.child("Question").orderByChild("roomUuid").equalTo(roomUuid).addValueEventListener(questionValueEventListener);
    }

    @Override
    public void cancelGettingQuestions() {
        if (questionValueEventListener != null) {
            database.removeEventListener(questionValueEventListener);
        }

        questionValueEventListener = null;
    }
}
