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

public class TabFActuresNonPayees {
    public static final String TAG = "TabFActuresNonPayees";

    // Database fields
    private SQLiteDatabase myDatabase;
    private DBOpenHelper myDbHelper;
    private Context myContext;
    private String[] myAllColumns = { DBOpenHelper.COLUMN_FACTURE_ID,
            DBOpenHelper.COLUMN_FACTURE_TYPE, DBOpenHelper.COLUMN_FACTURE_DATE,
            DBOpenHelper.COLUMN_FACTURE_PRIX,
            DBOpenHelper.COLUMN_FACTURE_NUMERO };

    public TabFActuresNonPayees(Context context) {
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

    public Facture createFactureNonpayee(String type_facture, String date, int prix, int numb_fact) {
        myDatabase = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.COLUMN_FACTURE_TYPE, type_facture);
        values.put(DBOpenHelper.COLUMN_FACTURE_DATE, date);
        values.put(DBOpenHelper.COLUMN_FACTURE_PRIX, prix);
        values.put(DBOpenHelper.COLUMN_FACTURE_NUMERO, numb_fact);

        long insertId = myDatabase.insert(DBOpenHelper.TABLE_FACTURE_NONPAYEE, null, values);
        Cursor cursor = myDatabase.query(DBOpenHelper.TABLE_FACTURE_NONPAYEE, myAllColumns,
                DBOpenHelper.COLUMN_FACTURE_ID + " = " + insertId, null, null,
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

    public void deleteFactureNonPayee(Facture facture) {
        int id = facture.getIdFacture();
        myDatabase = myDbHelper.getWritableDatabase();
        System.out.println("the deleted employee has the id: " + id);
        myDatabase.delete(DBOpenHelper.TABLE_FACTURE_NONPAYEE, DBOpenHelper.COLUMN_FACTURE_ID
                + " = " + id, null);
        //myDatabase.close();
    }

    /*public List<Facture> getAllFactureNonPayee(){
        SQLiteDatabase bd= myDbHelper.getReadableDatabase();
        myDatabase = myDbHelper.getWritableDatabase();
        List<Facture> arrayList = new ArrayList<>();
        try (Cursor c = bd.rawQuery("SELECT * FROM " + DBOpenHelper.TABLE_FACTURE_NONPAYEE, null)) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                arrayList.add(new Facture(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getInt(4)));
                c.moveToNext();
            }
        }catch (SQLException e) {
            Log.e(TAG, "Problème de lecture " + e.getMessage());
            e.printStackTrace();
        }
        return arrayList;
    }*/

    public List<Facture> getAllFacturesNonPayee() {
        List<Facture> listCompte = new ArrayList<Facture>();
        myDatabase = myDbHelper.getWritableDatabase();
        Cursor cursor = myDatabase.query(DBOpenHelper.TABLE_FACTURE_NONPAYEE, myAllColumns,
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
        Cursor cursor = myDatabase.query(DBOpenHelper.TABLE_FACTURE_NONPAYEE, myAllColumns,
                DBOpenHelper.COLUMN_FACTURE_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Facture facture = cursorToFacture(cursor);
        return facture;
    }
}
