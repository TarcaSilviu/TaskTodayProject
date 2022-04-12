package Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalDatabase  extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME="LocalTasks.db";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME="my_tasks";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_TITLE="task_title";
    private static final String COLUMN_DESCRIPTION="task_description";
    public static final String COLUMN_DATE="task_date_txt";


    public LocalDatabase(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query=
                " CREATE TABLE "+TABLE_NAME+" ("+COLUMN_ID+
                        " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                       COLUMN_TITLE+" TEXT, "+
                        COLUMN_DESCRIPTION +" TEXT, " +
                        COLUMN_DATE+ " TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    onCreate(sqLiteDatabase);
    }
//
    public void addPersonalTask(String title, String description, Date date){
        SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_DESCRIPTION,description);
        cv.put(COLUMN_DATE,simple.format(date).toString());
        long result=db.insert(TABLE_NAME,null,cv);
        if(result==-1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Added succesfull",Toast.LENGTH_SHORT).show();
        }
    }

    //pentru citirea si afisarea datelor
    public Cursor readAllData(){
        String query ="SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=null;
        if(db!=null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    public void updateData(String row_id,String title, String description){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_DESCRIPTION,description);
        //cv.put(COLUMN_TITLE,title);

        long result=db.update(TABLE_NAME,cv,"_id=?", new String[]{row_id});
        if(result==-1){
            Toast.makeText(context,"Failed to update!",Toast.LENGTH_SHORT).show();
        }
        else {
        Toast.makeText(context,"Successfully updated!",Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteOneRow(String row_id){
        SQLiteDatabase db=this.getWritableDatabase();
        long result =db.delete(TABLE_NAME,"_id=?",new String[]{row_id});
        if(result==-1){
            Toast.makeText(context,"Something went wrong!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Successfully deleted!",Toast.LENGTH_SHORT).show();
        }

    }
    public void deleteAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME);
    }

    public Cursor searchTroughData(Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

        String query ="SELECT * FROM " + TABLE_NAME+" WHERE "+COLUMN_DATE+" = ? ";
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=null;
        if(db!=null){
            cursor = db.rawQuery(query,new String[]{simpleDateFormat.format(date)});
        }
        return cursor;
    }
}
