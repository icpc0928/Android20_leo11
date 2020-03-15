package tw.org.iii.leo.leo11;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

//輔助程式
public class MyHelper extends SQLiteOpenHelper {
    //創建資料庫 創表的動作  //換行寫的話要雙引號隔開中間＋
    private final String createTable =
            "CREATE TABLE cust(id integer primary key autoincrement,"+
                    "cname text, tel text ,birthday date)";

    public MyHelper(
            @Nullable Context context,
            @Nullable String name,
            @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //on開頭的不用你叫他  進來時會帶個參數進來
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("leo","MyHelper:onCreate");
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {

    }
}
