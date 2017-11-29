package com.example.emergencyphonenumber;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.emergencyphonenumber.Adapter.PhoneListAdapter;
import com.example.emergencyphonenumber.Model.PhoneItem;
import com.example.emergencyphonenumber.db.PhoneDBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PhoneDBHelper mHelper;
    private SQLiteDatabase mDB;

    private ArrayList<PhoneItem> mPhoneItemList = new ArrayList<>();
    private PhoneListAdapter  mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new PhoneDBHelper(this);
       mDB = mHelper.getReadableDatabase();

        LoadDataFromDB();

        mAdapter = new PhoneListAdapter(
                this,
                R.layout.item,
                mPhoneItemList
        );

        ListView LV = findViewById(R.id.ListView);
        LV.setAdapter(mAdapter);

        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               PhoneItem item = mPhoneItemList.get(i);
               String PhoneNum = item.Number;

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+PhoneNum));
                startActivity(intent);
            }
        });
        Button InsertButt = findViewById(R.id.InsertButt);

        InsertButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText PhoneTitleEdittext = findViewById(R.id.PhoneTitleEdittext);
                EditText PhoneDeTailEdittext = findViewById(R.id.PhoneDetailEdittext);

                String PhoneTitle = PhoneTitleEdittext.getText().toString();
                String PhonrDetail = PhoneDeTailEdittext.getText().toString();

                ContentValues CV =  new ContentValues();
                CV.put(PhoneDBHelper.COL_TITLE,PhoneTitle);
                CV.put(PhoneDBHelper.COL_NUMBER,PhonrDetail);
                CV.put(PhoneDBHelper.COL_PICTURE,"ic_launcher.png");
                        mDB.insert(PhoneDBHelper.TABLE_NAME,null,CV);


                LoadDataFromDB();
                mAdapter.notifyDataSetChanged();

              /*  mDB.delete(
                        PhoneDBHelper.TABLE_NAME,"number=?",
                        new String[]("191"));*/
            }
        });




    }

    private void LoadDataFromDB() {
        Cursor cursor = mDB.query(
                PhoneDBHelper.TABLE_NAME,
                null,
                null, //"category=1"
                null,
                null,
                null,
                null

        );

        mPhoneItemList.clear();//เคลียร์อันเก่า

        while (cursor.moveToNext()){
           int Id = cursor.getInt(cursor.getColumnIndex(PhoneDBHelper.COL_ID));
           String Title =  cursor.getString(cursor.getColumnIndex(PhoneDBHelper.COL_TITLE));
           String Number =  cursor.getString(cursor.getColumnIndex(PhoneDBHelper.COL_NUMBER));
           String Pic =  cursor.getString(cursor.getColumnIndex(PhoneDBHelper.COL_PICTURE));

            PhoneItem Item = new PhoneItem(Id,Title,Number,Pic);
            mPhoneItemList.add(Item);
        }
    }

}
