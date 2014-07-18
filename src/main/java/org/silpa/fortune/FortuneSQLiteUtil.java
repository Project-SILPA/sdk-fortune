package org.silpa.fortune;


/**
 * Note : SQLite database for fortune is made using python script 'silpa_sdk_fortune_make_database.py'
 * which is found in root of this module.
 * SQLite database is stored in res/raw.
 *
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by sujith on 18/7/14.
 */
public class FortuneSQLiteUtil {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "silpa_sdk_fortune.db";
    // Table Name
    private static final String[] TABLE_NAMES = {"chanakya", "malayalam_proverbs", "thirukkural"};

    // context of application
    private final Context mContext;
    // database path
    private final String dbPath;
    // LOG TAG
    private static final String LOG_TAG = "Fortune - SQLiteHelper";

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public FortuneSQLiteUtil(Context context) {
        this.mContext = context;
        this.dbPath = mContext.getFilesDir() + File.separator + FortuneSQLiteUtil.DATABASE_NAME;
        createDataBase();
    }

    /**
     * Copies database if it does not exist
     */
    private void createDataBase() {
        boolean dbExist = checkDataBase();

        if (!dbExist) {
            copyDataBase();
        }
    }

    /**
     * Check if the database already exist to avoid re-copying
     * the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(this.dbPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {

        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    /**
     * This function is used to copy database from res/raw to storage directory
     */
    private void copyDataBase() {

        try {
            InputStream in = mContext.getResources().openRawResource(R.raw.silpa_sdk_fortune);
            OutputStream out = new FileOutputStream(new File(this.dbPath));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.flush();
            out.close();
            in.close();
        } catch (IOException ioe) {
            Log.e(LOG_TAG, "Error while copying database : " + ioe.getMessage());
        }
    }

    protected String getQuote(int quoteSet, String pattern) {

        SQLiteDatabase fortuneDatabase = SQLiteDatabase.openDatabase(this.dbPath, null, SQLiteDatabase.OPEN_READONLY);
        if (fortuneDatabase == null) {
            return "";
        }

        String query = "SELECT * FROM " + TABLE_NAMES[quoteSet] + " WHERE quote LIKE \'%" + pattern + "%\' ORDER BY RANDOM() LIMIT 1";

        Cursor cursor = fortuneDatabase.rawQuery(query, null);

        String result = null;
        if (cursor.moveToFirst()) {
            result = cursor.getString(0);
        }
        cursor.close();
        fortuneDatabase.close();

        return result;
    }
}