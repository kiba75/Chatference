package za.co.polymorph.chatference.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import za.co.polymorph.chatference.R;
import za.co.polymorph.chatference.callbacks.PostCommentCallback;
import za.co.polymorph.chatference.interfaces.IDatabaseService;
import za.co.polymorph.chatference.model.Comment;
import za.co.polymorph.chatference.service.FirebaseDatabaseService;

public class MainPageActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvRoomName;
    private EditText etComment;
    private Button btnPostComment;

    private String roomUuid;

    private IDatabaseService databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main_page);

        initViews();
        initListeners();

        roomUuid = getIntent().getStringExtra("RoomUuid");
        displayRoomName(getIntent().getStringExtra("RoomName"), getIntent().getStringExtra("RoomCode"));

        databaseService = FirebaseDatabaseService.getInstance();
    }

    private void initViews() {
        tvRoomName = findViewById(R.id.tv_room_title);
        etComment = findViewById(R.id.et_comment);
        btnPostComment = findViewById(R.id.btn_post_comment);
    }

    private void initListeners() {
        btnPostComment.setOnClickListener(this);

        etComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    btnPostComment.setEnabled(!(editable.length() == 0));
                } else {

                }
            }
        });
    }

    private void displayRoomName(String name, String code) {
        tvRoomName.setText(name + " - " + code);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_post_comment:
                postCommentSetEnabled(false);
                postComment(etComment.getText().toString());
                break;
        }
    }

    private void postComment(final String comment) {
        databaseService.postComment(roomUuid, comment, new PostCommentCallback() {
            @Override
            public void success(Comment comment) {
                etComment.setText("");
                postCommentSetEnabled(true);
            }

            @Override
            public void error(String errorDescription) {
                postCommentSetEnabled(true);
                Toast.makeText(MainPageActivity.this, "There was an Error posting your comment:  " + errorDescription, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void postCommentSetEnabled(boolean enabled) {
        etComment.setEnabled(enabled);
        btnPostComment.setEnabled(enabled);
    }
}
