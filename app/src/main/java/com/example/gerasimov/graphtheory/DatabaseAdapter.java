package com.example.gerasimov.graphtheory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context) {
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public DatabaseAdapter open() {

        database = dbHelper.open();
        return this;
    }

    public void close() {
        database.close();
    }

    private Cursor getAllEntries() {
        String[] columns = new String[]{DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_TERM, DatabaseHelper.COLUMN_DET};
        return database.query(DatabaseHelper.TABLE_TERMS, columns, null, null, null, null, null);
    }

    public List<TermRepository> getTerms() {
        ArrayList<TermRepository> terms = new ArrayList<>();
        Cursor cursor = getAllEntries();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                String term = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TERM));
                String det = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DET));
                terms.add(new TermRepository(id, term, det));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return terms;
    }

    public long getCount() {
        return DatabaseUtils.queryNumEntries(database, DatabaseHelper.TABLE_TERMS);
    }

    public TermRepository getTerm(long id) {
        TermRepository termRepository = null;
        String query = String.format("SELECT * FROM %s WHERE %s=? ORDER BY term ASC", DatabaseHelper.TABLE_TERMS, DatabaseHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            String term = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TERM));
            String det = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DET));
            termRepository = new TermRepository(id, term, det);
        }
        cursor.close();
        return termRepository;
    }

    public long insert(TermRepository termRepository) {

        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_TERM, termRepository.getTerm());
        cv.put(DatabaseHelper.COLUMN_DET, termRepository.getDet());

        return database.insert(DatabaseHelper.TABLE_TERMS, null, cv);
    }

    public long delete(long termId) {

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(termId)};
        return database.delete(DatabaseHelper.TABLE_TERMS, whereClause, whereArgs);
    }

    public long update(TermRepository termRepository) {

        String whereClause = DatabaseHelper.COLUMN_ID + "=" + String.valueOf(termRepository.getId());
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_TERM, termRepository.getTerm());
        cv.put(DatabaseHelper.COLUMN_DET, termRepository.getDet());
        return database.update(DatabaseHelper.TABLE_TERMS, cv, whereClause, null);
    }
}