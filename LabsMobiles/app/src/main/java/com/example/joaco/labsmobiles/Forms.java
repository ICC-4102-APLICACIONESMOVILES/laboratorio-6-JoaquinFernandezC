package com.example.joaco.labsmobiles;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.joaco.labsmobiles.SavePreferences;

/**
 * Created by Joaco on 4/3/2018.
 */

@Entity
public class Forms {
    @NonNull
    @PrimaryKey
    private String formId;
    private String name;
    private String date;
    private String category;
    private String comment;

    public Forms() {
    }

    public String getFormId() { return formId; }
    public void setFormId(String formId) { this.formId = formId; }
    public String getName() { return name; }
    public void setName (String name) { this.name = name; }
    public String getDate() { return date; }
    public void setDate (String date) { this.date = date; }
    public String getCategory() { return category; }
    public void setCategory (String category) { this.category = category; }
    public String getComment() { return comment; }
    public void setComment (String comment) { this.comment = comment; }
}
