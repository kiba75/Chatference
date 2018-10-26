package za.co.polymorph.chatference;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import za.co.polymorph.chatference.callbacks.CreateRoomCallback;
import za.co.polymorph.chatference.callbacks.GetQuestionsCallback;
import za.co.polymorph.chatference.callbacks.GetRoomCallback;
import za.co.polymorph.chatference.callbacks.PostQuestionCallback;
import za.co.polymorph.chatference.interfaces.IDatabaseService;
import za.co.polymorph.chatference.model.Question;
import za.co.polymorph.chatference.model.Room;
import za.co.polymorph.chatference.service.FirebaseDatabaseService;

public class MainActivity extends AppCompatActivity {

    private IDatabaseService databaseService;
    private GetQuestionsCallback getQuestionsCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseService = FirebaseDatabaseService.getInstance();

        getSpecificRoom();
        //createRoom();
    }

    private void createRoom() {
        databaseService.createRoom("abc", "ABSA PI 4", new CreateRoomCallback() {
            @Override
            public void success(Room room) {
                Toast.makeText(MainActivity.this, "Room created:  " + room.getId(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void error(String errorDescription) {
                Toast.makeText(MainActivity.this, "There was an error:  " + errorDescription, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getSpecificRoom() {
        databaseService.getRoom("1234", new GetRoomCallback() {
            @Override
            public void success(Room room) {
                Toast.makeText(MainActivity.this, "Found the Room:  " + room.getId(), Toast.LENGTH_LONG).show();
                //postQuestion(room.getId());
                getQuestions(room.getId());
            }

            @Override
            public void noResult() {
                Toast.makeText(MainActivity.this, "Could not find the Room", Toast.LENGTH_LONG).show();
            }

            @Override
            public void error(String message) {
                Toast.makeText(MainActivity.this, "There was an Error:  " + message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void postQuestion(final String roomUuid) {
        databaseService.postQuestion(roomUuid, "Question for ABSA PI 4?", 0, new PostQuestionCallback() {
            @Override
            public void success(Question question) {
                Toast.makeText(MainActivity.this, "Question posted:  " + question.getId(), Toast.LENGTH_LONG).show();

                getQuestions(roomUuid);
            }

            @Override
            public void error(String errorDescription) {
                Toast.makeText(MainActivity.this, "There was an Error:  " + errorDescription, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getQuestions(String roomUuid) {
        getQuestionsCallback = new GetQuestionsCallback() {
            @Override
            public void questionsUpdate(Question [] questions) {
                Toast.makeText(MainActivity.this, "Questions received", Toast.LENGTH_LONG).show();
            }

            @Override
            public void error(String message) {
                Toast.makeText(MainActivity.this, "There was an Error while getting questions:  " + message, Toast.LENGTH_LONG).show();
            }
        };

        databaseService.getQuestions(roomUuid, getQuestionsCallback);
    }
}
