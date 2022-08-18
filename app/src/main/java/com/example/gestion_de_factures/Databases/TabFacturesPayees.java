package com.example.gestion_de_factures.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.gestion_de_factures.Classes.Facture;

import java.util.ArrayList;
import java.util.List;

public class TabFacturesPayees {
    public static final String TAG = "TabFacturesPayees";

    // Database fields
    private SQLiteDatabase myDatabase;
    private DBOpenHelper myDbHelper;
    private Context myContext;
    private String[] myAllColumns = { DBOpenHelper.COLUMN_FACTURE_PAYE_ID,
            DBOpenHelper.COLUMN_FACTURE_PAYE_TYPE, DBOpenHelper.COLUMN_FACTURE_PAYE_DATE,
            DBOpenHelper.COLUMN_FACTURE_PAYE_PRIX,
            DBOpenHelper.COLUMN_FACTURE_PAYE_NUMERO };

    public TabFacturesPayees(Context context) {
        this.myContext = context;
        myDbHelper = new DBOpenHelper(context);
        // open the database
        /*try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "Problème d'écriture " + e.getMessage());
            e.printStackTrace();
        }*/
    }

    public void open() throws SQLException {
        myDatabase = myDbHelper.getWritableDatabase();
    }

    public void close() {
        myDbHelper.close();
    }

    public Facture createFacturePayee( String type_facture, String date, int prix, int numb_fact) {
        myDatabase = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBOpenHelper.COLUMN_FACTURE_PAYE_TYPE, type_facture);
        values.put(DBOpenHelper.COLUMN_FACTURE_PAYE_DATE, date);
        values.put(DBOpenHelper.COLUMN_FACTURE_PAYE_PRIX, prix);
        values.put(DBOpenHelper.COLUMN_FACTURE_PAYE_NUMERO, numb_fact);

        long insertId = myDatabase.insert(DBOpenHelper.TABLE_FACTURE_PAYEE, null, values);
        Cursor cursor = myDatabase.query(DBOpenHelper.TABLE_FACTURE_PAYEE, myAllColumns,
                DBOpenHelper.COLUMN_FACTURE_PAYE_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Facture newFacture = cursorToFacture(cursor);
        cursor.close();
        myDbHelper.close();
        return newFacture;
    }

    private Facture cursorToFacture(Cursor cursor) {
        Facture facture = new Facture();
        facture.setIdFacture(cursor.getInt(0));
        facture.setType_facture(cursor.getString(1));
        facture.setDate(cursor.getString(2));
        facture.setPrix(cursor.getInt(3));
        facture.setNumb_fact(cursor.getInt(4));

        return facture;
    }

    public void deleteFacturePayee(Facture facture) {
        int id = facture.getIdFacture();
        myDatabase = myDbHelper.getWritableDatabase();
        System.out.println("the deleted employee has the id: " + id);
        myDatabase.delete(DBOpenHelper.TABLE_FACTURE_PAYEE, DBOpenHelper.COLUMN_FACTURE_PAYE_ID
                + " = " + id, null);
        myDatabase.close();
    }

    //Récupérer les factures
    public List<Facture> getAllFacturesPayee() {
        List<Facture> listCompte = new ArrayList<Facture>();
        myDatabase = myDbHelper.getWritableDatabase();
        Cursor cursor = myDatabase.query(DBOpenHelper.TABLE_FACTURE_PAYEE, myAllColumns,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Facture facture = cursorToFacture(cursor);
                listCompte.add(facture);
                cursor.moveToNext();
            }

            // make sure to close the cursor
            cursor.close();
        }
        return listCompte;
    }


    public Facture getFactureById(int id) {
        Cursor cursor = myDatabase.query(DBOpenHelper.TABLE_FACTURE_PAYEE, myAllColumns,
                DBOpenHelper.COLUMN_FACTURE_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Facture facture = cursorToFacture(cursor);
        return facture;
    }
}
