package fr.enkirche.sissll;

/**
 * Created by Emmanuel on 19/07/2015 à partir de http://instinctcoder.com/android-studio-sqlite-database-example/.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number ou alors supprimer le fichier sissll.db!
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "sissll.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_ARTICLE = "CREATE TABLE " + Article.TABLE  + "("
                + Article.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Article.KEY_denom + " TEXT, "
                + Article.KEY_qtite + " INTEGER, "
                + Article.KEY_code + " TEXT )";

        db.execSQL(CREATE_TABLE_ARTICLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Article.TABLE);

        // Create tables again
        onCreate(db);

    }

}