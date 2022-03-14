package Classes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LocalDatabase  extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME="LocalTasks.db";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME="my_tasks";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_TITLE="task_title";
    private static final String COLUMN_DESCRIPTION="task_description";
    //public static final String COLUMN_DESCRIPTION="task_description";


    public LocalDatabase(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query=
                " CREATE TABLE "+TABLE_NAME+" ("+COLUMN_ID+"INTEGER PRIMARY KEY AUTOINCREMENT, "+
                       COLUMN_TITLE+" TEXT, "+
                        COLUMN_DESCRIPTION +" TEXT); ";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    onCreate(sqLiteDatabase);
    }
}