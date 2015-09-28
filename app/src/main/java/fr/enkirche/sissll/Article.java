package fr.enkirche.sissll;

/**
 * Created by Emmanuel on 19/07/2015 à partir de http://instinctcoder.com/android-studio-sqlite-database-example/.
 */

public class Article {
    // Labels table name
    public static final String TABLE = "Article";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_denom = "denom";
    public static final String KEY_code = "code";
    public static final String KEY_qtite = "qtite";

    // property help us to keep data
    public int article_ID;
    public String denom;
    public String code;
    public int qtite;
}