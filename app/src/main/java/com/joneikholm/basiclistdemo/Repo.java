package com.joneikholm.basiclistdemo;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repo {

    private static FirebaseFirestore db;
    private static String NOTES = "notes";
    private static List<Note> items = new ArrayList<>();
    static UpdateAble caller;

    public synchronized static void createNote(String text) {
        Map<String, String> docData = new HashMap<>();
        docData.put("txt", text);
        db.collection(NOTES).add(docData);
    }

    public static void updateNoteTxt(Note note) {
        db.collection("notes")
                .document(note.getId()).update("txt", note.getNote());
    }

    public static void deleteNote(String id) {
        db.collection("notes").document(id).delete();
    }

    private static void startListener() {
        db.collection(NOTES).addSnapshotListener((value, error) -> {
            if (error == null){
                items.clear();
                for(DocumentSnapshot snap: value.getDocuments()) {
                    if (snap.get("txt") != null) {
                        items.add(new Note(snap.getId(), snap.getString("txt")));
                    }
                }
                    caller.update(null);
            } else {
                System.out.println("error" + error);
            }
        });
    }

    public static void init(Context context) {
        FirebaseApp.initializeApp(context);
        db = FirebaseFirestore.getInstance();
        System.out.println("init er kørt " + db.getFirestoreSettings());
        startListener();
        caller = (UpdateAble) context;
    }

    public static List<Note> getItems() {
        return items;
    }

    public static Note getItemOnIndex(int index) {
        return items.get(index);
    }

}
