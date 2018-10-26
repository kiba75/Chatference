package za.co.polymorph.chatference.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import za.co.polymorph.chatference.R;
import za.co.polymorph.chatference.callbacks.CreateRoomCallback;
import za.co.polymorph.chatference.callbacks.GetCommentsCallback;
import za.co.polymorph.chatference.callbacks.GetQuestionsCallback;
import za.co.polymorph.chatference.callbacks.GetRoomCallback;
import za.co.polymorph.chatference.callbacks.PostCommentCallback;
import za.co.polymorph.chatference.callbacks.PostQuestionCallback;
import za.co.polymorph.chatference.interfaces.IDatabaseService;
import za.co.polymorph.chatference.model.Comment;
import za.co.polymorph.chatference.model.Question;
import za.co.polymorph.chatference.model.Room;
import za.co.polymorph.chatference.service.FirebaseDatabaseService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private IDatabaseService databaseService;
    private GetQuestionsCallback getQuestionsCallback;
    private GetCommentsCallback getCommentsCallback;

    private EditText etRoomCode;
    private Button btnJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        initViews();
        initListeners();

        databaseService = FirebaseDatabaseService.getInstance();

        //getSpecificRoom();
    }

    private void initViews() {
        btnJoin = findViewById(R.id.btn_join_session);
        etRoomCode = findViewById(R.id.et_room_code);
    }

    private void initListeners() {
        btnJoin.setOnClickListener(this);
    }

//    private void createRoom() {
//        databaseService.createRoom("abc", "ABSA PI 4", new CreateRoomCallback() {
//            @Override
//            public void success(Room room) {
//                Toast.makeText(MainActivity.this, "Room created:  " + room.getId(), Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void error(String errorDescription) {
//                Toast.makeText(MainActivity.this, "There was an error:  " + errorDescription, Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
    private void getSpecificRoom() {
        databaseService.getRoom("1234", new GetRoomCallback() {
            @Override
            public void success(Room room) {
                Toast.makeText(MainActivity.this, "Found the Room:  " + room.getId(), Toast.LENGTH_LONG).show();
                //postQuestion(room.getId());
                //getQuestions(room.getId());
                postComment(room.getId());
                //getComments(room.getId());
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
//
//    private void postQuestion(final String roomUuid) {
//        databaseService.postQuestion(roomUuid, "Question for ABSA PI 4?", 0, new PostQuestionCallback() {
//            @Override
//            public void success(Question question) {
//                Toast.makeText(MainActivity.this, "Question posted:  " + question.getId(), Toast.LENGTH_LONG).show();
//
//                getQuestions(roomUuid);
//            }
//
//            @Override
//            public void error(String errorDescription) {
//                Toast.makeText(MainActivity.this, "There was an Error:  " + errorDescription, Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    private void getQuestions(String roomUuid) {
//        getQuestionsCallback = new GetQuestionsCallback() {
//            @Override
//            public void questionsUpdate(Question [] questions) {
//                Toast.makeText(MainActivity.this, "Questions received", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void error(String message) {
//                Toast.makeText(MainActivity.this, "There was an Error while getting questions:  " + message, Toast.LENGTH_LONG).show();
//            }
//        };
//
//        databaseService.getQuestions(roomUuid, getQuestionsCallback);
//    }
//
    private void postComment(final String roomUuid) {
        databaseService.postComment(roomUuid, "Are we there yet?", new PostCommentCallback() {
            @Override
            public void success(Comment comment) {
                Toast.makeText(MainActivity.this, "Comment received", Toast.LENGTH_LONG).show();
            }

            @Override
            public void error(String errorDescription) {
                Toast.makeText(MainActivity.this, "There was an Error:  " + errorDescription, Toast.LENGTH_LONG).show();
            }
        });
    }
//
//    private void getComments(String roomUuid) {
//        getCommentsCallback = new GetCommentsCallback() {
//            @Override
//            public void commentsUpdate(Comment [] comment) {
//                Toast.makeText(MainActivity.this, "Comments received", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void error(String message) {
//                Toast.makeText(MainActivity.this, "There was an Error while getting comments:  " + message, Toast.LENGTH_LONG).show();
//            }
//        };
//
//        databaseService.getComments(roomUuid, getCommentsCallback);
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_join_session:
                getRoom(etRoomCode.getText().toString().trim());
                break;
        }
    }

    private void getRoom(String code) {
        databaseService.getRoom(code, new GetRoomCallback() {
            @Override
            public void success(Room room) {
                Intent i = new Intent(MainActivity.this, MainPageActivity.class);
                i.putExtra("RoomCode", room.code);
                i.putExtra("RoomName", room.name);
                i.putExtra("RoomUuid", room.getId());

                startActivity(i);
                finish();
            }

            @Override
            public void noResult() {
                Toast.makeText(MainActivity.this, "Could not find the Room", Toast.LENGTH_LONG).show();
                clearCode();
            }

            @Override
            public void error(String message) {
                Toast.makeText(MainActivity.this, "There was an Error:  " + message, Toast.LENGTH_LONG).show();
                clearCode();
            }
        });
    }

    private void clearCode() {
        etRoomCode.setText("");
    }
}
