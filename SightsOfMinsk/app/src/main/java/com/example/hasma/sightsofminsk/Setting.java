package com.example.hasma.sightsofminsk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Setting extends AppCompatActivity {

    private RatingBar rating;
    private TextView text_show;
    private Button btn1, btn2;
    private EditText etPassword, etRepeat, etOldPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        rating = (RatingBar)findViewById(R.id.ratingBar);
        rating.setRating(Host.Minimal_Rating);

        addListenerOnButton();
    }

    //database.delete(DBHelper.TABLE_CONTACTS, null, null);

    public void addListenerOnButton() {

        DBHelper momhelpplease = new DBHelper(this);

        final ContentValues cv = new ContentValues();
        final ContentValues cv2 = new ContentValues();

        final SQLiteDatabase database = momhelpplease.getWritableDatabase();

        text_show = (TextView)findViewById(R.id.tvRating);
        btn1 = (Button)findViewById(R.id.btnSaveRating);
        btn2 = (Button)findViewById(R.id.btnSavePassword);

        etOldPassword = (EditText)findViewById(R.id.etOldPassword);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etRepeat = (EditText)findViewById(R.id.etPasswordRepeat);

        final int[] ratinggg = new int[1];

        rating.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        text_show.setText(String.valueOf(rating));
                        ratinggg[0] = (int)rating;
                    }
                }
        );

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cv.put(DBHelper.COLUMN_MIN_RATING, ratinggg[0]);
                database.update(DBHelper.TABLE_NAME, cv, DBHelper.COLUMN_ID + " = ?", new String[] {String.valueOf(Host.ID)});
                Host.Minimal_Rating = ratinggg[0];
                Toast.makeText(
                        Setting.this, "Минимальный рейтинг сохранён!",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etOldPassword.equals("") || etPassword.equals("") || etRepeat.equals("")) {
                    Toast.makeText(
                            Setting.this, "У Вас присутствуют пустые строки. Пожалуйста, заполните их!",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    if (etOldPassword.getText().toString().equals(Host.Password)){
                        if (etPassword.getText().toString().equals(etRepeat.getText().toString())){
                            cv2.put(DBHelper.COLUMN_PASS, etPassword.getText().toString());
                            database.update(DBHelper.TABLE_NAME, cv2, DBHelper.COLUMN_ID + " = ?", new String[] {String.valueOf(Host.ID)});
                            Host.Password = etPassword.getText().toString();
                            Toast.makeText(
                                    Setting.this, "Пароль сохранён!",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    } else {
                        Toast.makeText(
                                Setting.this, "Действующий пароль введён неверно!",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
            }
        });
    }
}