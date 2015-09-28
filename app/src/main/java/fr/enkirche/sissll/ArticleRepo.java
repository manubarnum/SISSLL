package fr.enkirche.sissll;

/**
 * Created by Emmanuel on 19/07/2015 à partir de http://instinctcoder.com/android-studio-sqlite-database-example/.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;

public class ArticleRepo {
    private DBHelper dbHelper;

    public ArticleRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Article article) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Article.KEY_qtite, article.qtite);
        values.put(Article.KEY_code,article.code);
        values.put(Article.KEY_denom, article.denom);

        // Inserting Row
        long article_Id = db.insert(Article.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) article_Id;
    }

    public void delete(int article_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Article.TABLE, Article.KEY_ID + "= ?", new String[] { String.valueOf(article_Id) });
        db.close(); // Closing database connection
    }

    public void update(Article article) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Article.KEY_qtite, article.qtite);
        values.put(Article.KEY_code,article.code);
        values.put(Article.KEY_denom, article.denom);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Article.TABLE, values, Article.KEY_ID + "= ?", new String[] { String.valueOf(article.article_ID) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>>  getArticleList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Article.KEY_ID + "," +
                Article.KEY_denom + "," +
                Article.KEY_code + "," +
                Article.KEY_qtite +
                " FROM " + Article.TABLE;

        //Article article = new Article();
        ArrayList<HashMap<String, String>> articleList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> article = new HashMap<String, String>();
                article.put("id", cursor.getString(cursor.getColumnIndex(Article.KEY_ID)));
                article.put("name", cursor.getString(cursor.getColumnIndex(Article.KEY_denom)));
                articleList.add(article);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return articleList;

    }

    public Article getArticleById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Article.KEY_ID + "," +
                Article.KEY_denom + "," +
                Article.KEY_code + "," +
                Article.KEY_qtite +
                " FROM " + Article.TABLE
                + " WHERE " +
                Article.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        //int iCount =0;
        Article article = new Article();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                article.article_ID =cursor.getInt(cursor.getColumnIndex(Article.KEY_ID));
                article.denom =cursor.getString(cursor.getColumnIndex(Article.KEY_denom));
                article.code  =cursor.getString(cursor.getColumnIndex(Article.KEY_code));
                article.qtite =cursor.getInt(cursor.getColumnIndex(Article.KEY_qtite));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return article;
    }

}