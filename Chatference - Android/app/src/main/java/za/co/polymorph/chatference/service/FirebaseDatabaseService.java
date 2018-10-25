package za.co.polymorph.chatference.service;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import za.co.polymorph.chatference.callbacks.CreateRoomCallback;
import za.co.polymorph.chatference.callbacks.GetRoomCallback;
import za.co.polymorph.chatference.interfaces.IDatabaseService;
import za.co.polymorph.chatference.model.Room;

public class FirebaseDatabaseService implements IDatabaseService {

    private DatabaseReference database;
    private static FirebaseDatabaseService firebaseService = null;

    private FirebaseDatabaseService(DatabaseReference database) {
        this.database = database;
    }

    public static FirebaseDatabaseService getInstance() {
        if (firebaseService == null) {
            return new FirebaseDatabaseService(FirebaseDatabase.getInstance().getReference());
        } else {
            return firebaseService;
        }
    }

    @Override
    public void createRoom(String code, String name, final CreateRoomCallback createRoomCallback) {
        Room room = new Room(code, name, 1);

        database.child("Room").child(database.push().getKey()).setValue(room, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError == null) {
                    createRoomCallback.success(databaseReference.getKey());
                } else {
                    createRoomCallback.error(databaseError.getMessage());
                }
            }
        });
    }

    @Override
    public void getRoom(String code, GetRoomCallback getRoomCallback) {
    }
}
