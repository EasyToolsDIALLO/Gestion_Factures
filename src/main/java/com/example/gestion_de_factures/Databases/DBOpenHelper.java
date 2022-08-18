package com.example.gestion_de_factures.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {

    public static final String TAG = "DBOpenHelper";

    //Infos Database
    private static final String DATABASE_NAME = "gestionFactures";
    private static final int DATABASE_VERSION = 1;

    //Colonnes de la table Factures non payees
    public static final String TABLE_FACTURE_NONPAYEE = "nonpayee";
    public static final String COLUMN_FACTURE_ID = "_id";
    public static final String COLUMN_FACTURE_TYPE = "type_facture";
    public static final String COLUMN_FACTURE_DATE = "date_facture";
    public static final String COLUMN_FACTURE_PRIX = "prix_facture";
    public static final String COLUMN_FACTURE_NUMERO = "numero_facture";

    //Colonnes de la table Factures payees
    public static final String TABLE_FACTURE_PAYEE = "enregistree";
    public static final String COLUMN_FACTURE_PAYE_ID = "_id";
    public static final String COLUMN_FACTURE_PAYE_TYPE = "type_facture_payee";
    public static final String COLUMN_FACTURE_PAYE_DATE = "date_facture_payee";
    public static final String COLUMN_FACTURE_PAYE_PRIX = "prix_facture_payee";
    public static final String COLUMN_FACTURE_PAYE_NUMERO = "numero_facture_payee";

    //Colonnes de la table Compte
    public static final String TABLE_COMPTE = "compte";
    public static final String COLUMN_COMPTE_ID = "_id";
    public static final String COLUMN_COMPTE_LOGIN = "login";
    public static final String COLUMN_COMPTE_PASSWORD = "password";

    //Requete SQL de creation de la table Factures non payees
    private static final String SQL_CREATE_TABLE_FACTURE_NONPAYEE = "CREATE TABLE " + TABLE_FACTURE_NONPAYEE + "("
            + COLUMN_FACTURE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_FACTURE_TYPE + " TEXT NOT NULL, "
            + COLUMN_FACTURE_DATE + " TEXT NOT NULL, "
            + COLUMN_FACTURE_PRIX + " INTEGER NOT NULL, "
            + COLUMN_FACTURE_NUMERO + " INTEGER NOT NULL "
            +");";

    //Requete SQL de creation de la table Factures payees
    private static final String SQL_CREATE_TABLE_FACTURE_PAYEE = "CREATE TABLE " + TABLE_FACTURE_PAYEE + "("
            + COLUMN_FACTURE_PAYE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_FACTURE_PAYE_TYPE + " TEXT NOT NULL, "
            + COLUMN_FACTURE_PAYE_DATE + " TEXT NOT NULL, "
            + COLUMN_FACTURE_PAYE_PRIX + " INTEGER NOT NULL, "
            + COLUMN_FACTURE_PAYE_NUMERO + " INTEGER NOT NULL "
            +");";

    //Requete SQL de creation de la table Compte
    private static final String SQL_CREATE_TABLE_COMPTE = "CREATE TABLE " + TABLE_COMPTE + "("
            + COLUMN_COMPTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_COMPTE_LOGIN + " TEXT NOT NULL, "
            + COLUMN_COMPTE_PASSWORD + " TEXT NOT NULL "
            +");";


    public DBOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQL_CREATE_TABLE_FACTURE_NONPAYEE);
        database.execSQL(SQL_CREATE_TABLE_FACTURE_PAYEE);
        database.execSQL(SQL_CREATE_TABLE_COMPTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading the database from version " + oldVersion + " to "+ newVersion);
        // clear all data
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FACTURE_NONPAYEE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FACTURE_PAYEE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPTE);

        // recreate the tables
        onCreate(db);
    }

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
}
