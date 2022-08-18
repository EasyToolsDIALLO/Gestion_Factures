package com.example.gestion_de_factures.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gestion_de_factures.Classes.Compte;
import com.example.gestion_de_factures.Classes.Facture;

import java.util.ArrayList;
import java.util.List;

public class CompteUtilisateur  {
    public static final String TAG = "CompteUtilisateur";

    // Database fields
    private SQLiteDatabase myDatabase;
    private DBOpenHelper myDbHelper;
    private Context myContext;
    private String[] myAllColumns = { DBOpenHelper.COLUMN_COMPTE_ID,
            DBOpenHelper.COLUMN_COMPTE_LOGIN, DBOpenHelper.COLUMN_COMPTE_PASSWORD};

    public CompteUtilisateur(Context context) {
        this.myContext = context;
        myDbHelper = new DBOpenHelper(context);
        // open the database
        /*try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }*/
    }
    public void open() throws SQLException {
        myDatabase = myDbHelper.getWritableDatabase();
    }

    public void close() {
        myDbHelper.close();
    }

    //Lorsqu'un compte est créé, il est directement enregistré dans la base de donnée
    public Compte createCompte( String login, String password) {
        myDatabase = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBOpenHelper.COLUMN_COMPTE_LOGIN, login);
        values.put(DBOpenHelper.COLUMN_COMPTE_PASSWORD, password);

        long insertId = myDatabase.insert(DBOpenHelper.TABLE_COMPTE, null, values);
        Cursor cursor = myDatabase.query(DBOpenHelper.TABLE_COMPTE, myAllColumns,
                DBOpenHelper.COLUMN_COMPTE_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Compte newCompte = cursorToCompte(cursor);
        cursor.close();
        myDbHelper.close();
        return newCompte;
    }

    private Compte cursorToCompte(Cursor cursor) {
        Compte compte = new Compte();
        compte.setIdCompte(cursor.getInt(0));
        compte.setLogin(cursor.getString(1));
        compte.setPassword(cursor.getString(2));

        return compte;
    }

    public void deleteCompte(Compte compte) {
        int id = compte.getIdCompte();
        System.out.println("the deleted employee has the id: " + id);
        myDatabase.delete(DBOpenHelper.TABLE_COMPTE, DBOpenHelper.COLUMN_COMPTE_ID
                + " = " + id, null);
    }

    public List<Compte> getAllComptes() {
        List<Compte> listCompte = new ArrayList<Compte>();
        myDatabase = myDbHelper.getWritableDatabase();
        Cursor cursor = myDatabase.query(DBOpenHelper.TABLE_COMPTE, myAllColumns,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Compte compte = cursorToCompte(cursor);
                listCompte.add(compte);
                cursor.moveToNext();
            }

            // make sure to close the cursor
            cursor.close();
        }
        return listCompte;
    }

    public Compte getCompteById(int id) {
        Cursor cursor = myDatabase.query(DBOpenHelper.TABLE_COMPTE, myAllColumns,
                DBOpenHelper.COLUMN_COMPTE_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursorToCompte(cursor);
    }

    public void updateCompte(Compte compte){
        myDatabase = myDbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("login",compte.getLogin());
        cv.put("password",compte.getPassword());
        myDatabase.update(DBOpenHelper.TABLE_COMPTE,cv,"_id=?",new String[]{String.valueOf(compte.getIdCompte())});
        myDatabase.close();
    }
}
