package com.joneikholm.basiclistdemo;

public class Note {

    String id;
    String note;

    public Note(String id, String note) {
        this.id = id;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
