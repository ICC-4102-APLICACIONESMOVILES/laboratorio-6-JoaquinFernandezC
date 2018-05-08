package com.example.joaco.labsmobiles;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Joaco on 4/3/2018.
 */

@Dao
public interface DaoAccess {

    @Insert
    void insertOnlySingleForm (Forms forms);
    @Insert
    void insertMultipleForms (List<Forms> formsList);
    @Query ("SELECT * FROM Forms WHERE formId = :formId")
    Forms fetchOneFormbyFormId (int formId);
    @Query ("Select * FROM Forms")
    Forms[] fetchAllForms();
    @Query("DELETE FROM Forms")
    void deleteAllForms();
    @Update
    void updateForm (Forms forms);
    @Delete
    void deleteForm (Forms forms);
}
