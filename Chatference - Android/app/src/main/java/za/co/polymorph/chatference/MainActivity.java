package za.co.polymorph.chatference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import za.co.polymorph.chatference.callbacks.CreateRoomCallback;
import za.co.polymorph.chatference.callbacks.GetRoomCallback;
import za.co.polymorph.chatference.interfaces.IDatabaseService;
import za.co.polymorph.chatference.model.Room;
import za.co.polymorph.chatference.service.FirebaseDatabaseService;

public class MainActivity extends AppCompatActivity {

    private IDatabaseService databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseService = FirebaseDatabaseService.getInstance();

        getSpecificRoom();
    }

    private void createRoom() {
        databaseService.createRoom("abc", "ABSA PI", new CreateRoomCallback() {
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
        databaseService.getRoom("abc", new GetRoomCallback() {
            @Override
            public void success(Room room) {

            }

            @Override
            public void noResult() {

            }

            @Override
            public void error(String message) {

            }
        });
    }
}
