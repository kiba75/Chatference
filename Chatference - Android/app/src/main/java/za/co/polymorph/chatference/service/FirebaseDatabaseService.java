package za.co.polymorph.chatference.service;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
}
