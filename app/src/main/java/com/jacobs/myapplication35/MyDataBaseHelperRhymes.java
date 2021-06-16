package com.jacobs.myapplication35;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDataBaseHelperRhymes  extends SQLiteOpenHelper {
    public static final String DBNAME = "test0.db";
    private Context mContext;
    public static  String DBLOCATION = "/data/data/com.jacobs.myapplication35/databases/";
    private SQLiteDatabase mDatabase;



    public MyDataBaseHelperRhymes(Context context) {
        super(context, DBNAME, null, 2);
        this.mContext = context;
        if(android.os.Build.VERSION.SDK_INT >= 17) {
            DBLOCATION = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DBLOCATION = "/data/data/" + context.getPackageName() + "/databases/";
        }


    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.disableWriteAheadLogging();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if(mDatabase!=null) {
            mDatabase.close();
        }
    }

    public List<Rhyme> getListProduct() {
        Rhyme product = null;
        List<Rhyme> productList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM rhyme", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            product = new Rhyme(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            productList.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return productList;
    }

    public List<Rhyme> getRhymes(String rhm) {
        String substring = rhm.length() > 2 ? rhm.substring(rhm.length() - 2) : rhm;
        Rhyme rhmT = null;
        List<Rhyme> productList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery(
                "select name from rhyme where two_last like ? ", new String[] { "%" + substring + "%" });
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(0);
            rhmT = new Rhyme(name);
            System.out.println(rhmT);
            productList.add(rhmT);
            cursor.moveToNext();
        }

        cursor.close();
        closeDatabase();

        return productList;
    }

    public List<Rhyme> getRhymes_copy(String rhm) {
        String substring = rhm.length() > 2 ? rhm.substring(rhm.length() - 2) : rhm;
        Rhyme rhmT = null;
        List<Rhyme> productList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery(
                "select name from rhyme where two_last  like ? ", new String[] { "%" + substring + "%" });
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(0);
            rhmT = new Rhyme(name);
            productList.add(rhmT);
            cursor.moveToNext();
        }

        cursor.close();
        closeDatabase();
        return productList;
    }
}
