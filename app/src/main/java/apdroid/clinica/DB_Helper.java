package apdroid.clinica;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by AngeloPaulo on 08/septiembre/2015.
 */
public class DB_Helper extends SQLiteOpenHelper {

    private static final String DB_NAME="DB_DOCTORES.sqlite";
    private static final int DB_SCHEME_VERSION=1;

    public DB_Helper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DB_Manager.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
