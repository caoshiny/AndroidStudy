package com.shiny.androiddemo.sqlite;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shiny.androiddemo.R;
import com.shiny.androiddemo.utils.ToastUtil;

import java.util.List;

public class SQLiteHelperActivity extends AppCompatActivity {
    private static final String TAG = "SQLiteHelperActivity";

    private Button write;
    private Button delete;
    private Button update;
    private Button read;
    private TextView show;

    private UserDBHelper userDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_helper);

        write = findViewById(R.id.writeSQLite);
        delete = findViewById(R.id.deleteSQLite);
        update = findViewById(R.id.updateSQLite);
        read = findViewById(R.id.readSQLite);
        show = findViewById(R.id.showSQLite);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User("shiny", 26, 171, 67, false);
                if (userDBHelper.insert(user) > 0) {
                    ToastUtil.show(SQLiteHelperActivity.this, "添加成功");
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userDBHelper.deleteByName("shiny") > 0) {
                    ToastUtil.show(SQLiteHelperActivity.this, "根据姓名删除数据成功");
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User("shiny", 88, 180, 180, true);
                if (userDBHelper.update(user) > 0) {
                    ToastUtil.show(SQLiteHelperActivity.this, "根据姓名修改数据成功");
                }
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> list = userDBHelper.queryAll();
                for (User user : list) {
                    Log.i(TAG, "onClick: " + user.toString());
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        userDBHelper = UserDBHelper.getInstance(this);
        userDBHelper.openReadLink();
        userDBHelper.openWriteLink();
    }

    @Override
    protected void onStop() {
        super.onStop();
        userDBHelper.closeLink();
    }
}
