package com.example.hasma.sightsofminsk;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Description_Activity extends AppCompatActivity {

    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_);

        TextView namesight = (TextView) findViewById(R.id.textView);
        TextView description = (TextView) findViewById(R.id.textView2);
        ImageView img = (ImageView) findViewById(R.id.imageView2);
        description.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        if (b!=null){
            namesight.setText(b.getString("TIT"));
            img.setImageResource(b.getInt("IMG"));
            description.setText(b.getString("DET"));
            Host.Name_Sight = b.getString("TIT");
            DBHelper dbHelper = new DBHelper(Description_Activity.this);

            final ContentValues contentValues = new ContentValues();

            final SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

            String selection1 = DBHelper.COLUMN_NAME1 + " = ?";
            String[] selectrionArghs1 = {Host.Name_Sight};

            Cursor cursorrr = sqLiteDatabase.query(DBHelper.TABLE_NAME1, null, selection1, selectrionArghs1, null, null, null);

            if(cursorrr.moveToNext()) {
                int id = cursorrr.getColumnIndex(DBHelper.COLUMN_ID1);

                do {
                    Host.ID_Sight = cursorrr.getInt(id);
                    Log.d("mLog", "ID1 = " + cursorrr.getInt(id) + ", HOST - " + Host.Name_Sight);
                } while (cursorrr.moveToNext());
            } else
                Log.d("mLog", "0 rows" + ", HOST - " + Host.Name_Sight + "ID - " + Host.ID_Sight);
        }

        addListenerOnButton();
    }

    public void addListenerOnButton() {

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                DBHelper dbHelper = new DBHelper(Description_Activity.this);

                final ContentValues contentValues = new ContentValues();

                final SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

            //    String table = DBHelper.TABLE_NAME2 + " inner join " + DBHelper.TABLE_NAME1 + " on " + DBHelper.TABLE_NAME2 + "." + DBHelper.COLUMN_ID2 + " = " + DBHelper.TABLE_NAME1 + "." + DBHelper.COLUMN_ID1
            //    //        + "\n"
            //    //        + DBHelper.TABLE_NAME2
            //            + " inner join " + DBHelper.TABLE_NAME + " on " + DBHelper.TABLE_NAME2 + "." + DBHelper.COLUMN_ID2 + " = " + DBHelper.TABLE_NAME + "." + DBHelper.COLUMN_ID;
            //    String selection = DBHelper.COLUMN_NAME + " = ? AND " + DBHelper.COLUMN_NAME1 + " = ?";
            //    String[] selectrionArghs = {Host.Login, Host.Name_Sight};

                //Cursor cursor = sqLiteDatabase.query(table, columns, selection, null, null, null, null);

                String selection = DBHelper.COLUMN_ID + " = ? AND " + DBHelper.COLUMN_ID1 + " = ?";
                String[] selectrionArghs = {"" + Host.ID + "", "" + Host.ID_Sight + ""};

                Cursor cursor = sqLiteDatabase.query(DBHelper.TABLE_NAME2, null, selection, selectrionArghs, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.COLUMN_ID2);
                    int idIndex1 = cursor.getColumnIndex(DBHelper.COLUMN_ID);
                    int idIndex2 = cursor.getColumnIndex(DBHelper.COLUMN_ID1);
                    int ratingg = cursor.getColumnIndex(DBHelper.COLUMNT_RATING);

                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) + "ID1 = " + cursor.getInt(idIndex1) + "ID2 = " + cursor.getInt(idIndex2) + ", rating = " + cursor.getString(ratingg) + ", HOST - " + Host.Name_Sight + "_" + Host.ID_Sight);
                    } while (cursor.moveToNext());
                } else {
                //    Log.d("mLog", "0 rows" + ", HOST - " + Host.Name_Sight + ", ID - " + Host.ID_Sight);
//
                //    contentValues.put(DBHelper.COLUMN_ID, Host.ID);
                //    contentValues.put(DBHelper.COLUMN_ID1, Host.ID_Sight);
                //    contentValues.put(DBHelper.COLUMNT_RATING, (int)rating);
//
                //    sqLiteDatabase.insert(DBHelper.TABLE_NAME2, null, contentValues);

                    Toast.makeText(
                            Description_Activity.this, "Рейтинг сохранён!",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }
}
