package tw.org.iii.leo.leo11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private MyHelper myHelper;
    private EditText keyword;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        keyword = findViewById(R.id.keyword);
        tv = findViewById(R.id.data);

        myHelper = new MyHelper(this,"iii",null,1);
        //int 用來做版本的事情
        //readable 跟 writeable 都能做增刪修查
        db = myHelper.getReadableDatabase();
    }

    public void query(View view) {
        // select * from cust  前面的*寫在第二個參數,第二個寫要查的欄位名稱 第三個寫where條件式 因為全部查所以null    後面沒有where...groupby...都沒有所以後面都null
        Cursor cursor = db.query("cust",null,
                "cname like ? or tel like ? or birthday like ?",
                new String[]{"%"+keyword.getText().toString()+"%","%"+keyword.getText().toString()+"%","%"+keyword.getText().toString()+"%"},
                null,null,null);
       // Log.v("leo","col count:  " + cursor.getColumnCount());

        StringBuffer sb = new StringBuffer();

        while (cursor.moveToNext()){
            //原本getString(0)調取第一個欄位的資料 但這樣寫數字不好維護 所以用getCol..Index 抓出欄位名稱符合名稱的第幾個  顯示出來為陣列的index 0開始
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String cname = cursor.getString(cursor.getColumnIndex("cname"));
            String tel = cursor.getString(cursor.getColumnIndex("tel"));
            String birthday = cursor.getString(cursor.getColumnIndex("birthday"));
            String row = "id = " + id+ ":" + cname +":"+tel+":"+birthday;
//            Log.v("leo","id = " + id+ ":" + cname +":"+tel+":"+birthday);
            sb.append(row + "\n");

        }
        tv.setText(sb);
    }


    public void insert(View view) {

//        db.execSQL("INSERT INTO cust (cname,tel,birthday) VALUES ('Leo','123','1999-01-02')");
        ContentValues data = new ContentValues();
        data.put("cname","Tony");
        data.put("tel","456");
        data.put("birthday","1999-12-12");

        db.insert("cust",null,data);
        query(null);

    }

    public void del(View view) {
        //DELETE FROM cust WHERE id = 2 and cname = 'Leo'
        db.delete("cust","id = ? and cname = ?", new String[]{"2","Leo"});
        query(null);
    }

    public void update(View view) {
        // UPDATE cust SET cname ='Eric', tel='789' WHERE id = 3;
        ContentValues data = new ContentValues();
        data.put("cname","Eric");
        data.put("tel","789");
        db.update("cust",data,"id = ?", new String[]{"3"});
        query(null);

    }
}
