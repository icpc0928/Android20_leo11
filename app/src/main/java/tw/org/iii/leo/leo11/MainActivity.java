package tw.org.iii.leo.leo11;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private MyHelper myHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myHelper = new MyHelper(this,"iii",null,1);
        //int 用來做版本的事情
        db = myHelper.getReadableDatabase();
    }

    public void query(View view) {
        // select * from cust  前面的*寫在第二個參數,第二個寫要查的欄位名稱 第三個寫where條件式 因為全部查所以null    後面沒有where...groupby...都沒有所以後面都null
        Cursor cursor = db.query("cust",null,null,null,null,null,null);
       // Log.v("leo","col count:  " + cursor.getColumnCount());
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            Log.v("leo","id = " + id);

        }
    }


    public void insert(View view) {

        db.execSQL("INSERT INTO cust (cname,tel,birthday) VALUES ('Leo','123','1999-01-02')");
        query(null);

    }
}
