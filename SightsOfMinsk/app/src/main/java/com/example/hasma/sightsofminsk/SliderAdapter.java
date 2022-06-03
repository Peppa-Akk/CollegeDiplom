package com.example.hasma.sightsofminsk;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    EditText etpasrep, etpas;
    Button btn;
    int rating;
    int boologin = 1, boologin1 = 0;

    public SliderAdapter(Context context){
        this.context = context;
    }

    public int[] slide_images = {
            R.drawable.egister,
            R.drawable.add
    };

    public String[] slide_heading = {
            "Авторизация",
            "Регистрация"
    };

    public String[] btn_text = {
            "Войти",
            "Создать"
    };

    public int[] invise_tv = {
            0,
            1
    };

    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        final View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.imageView);
        TextView slideHeading = (TextView) view.findViewById(R.id.tvAuthoReg);
        EditText etlogin = (EditText) view.findViewById(R.id.etLogin);
        btn = (Button) view.findViewById(R.id.button);
        etpasrep = (EditText) view.findViewById(R.id.etPasswordRepeat);
        etpas = (EditText) view.findViewById(R.id.etPassword);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DBHelper helper = new DBHelper(context);
                EditText etlogin = (EditText) view.findViewById(R.id.etLogin);
                etpas = (EditText) view.findViewById(R.id.etPassword);

                final String etName = etlogin.getText().toString();
                final String etPas = etpas.getText().toString();
                final String etPasRep = etpasrep.getText().toString();

                SQLiteDatabase database = helper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();

                //String password = helper.searchPass(etName);

                switch (position) {
                    case 0:

                        // ----------------------------ТАБЛИЦА КОНТАКТЫ------------------------------------------

                        Cursor cursorк = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);

                        if (cursorк.moveToFirst()) {
                            int idIndex = cursorк.getColumnIndex(DBHelper.COLUMN_ID);
                            int nameIndex = cursorк.getColumnIndex(DBHelper.COLUMN_NAME);
                            int passIndex = cursorк.getColumnIndex(DBHelper.COLUMN_PASS);
                            int min_rat = cursorк.getColumnIndex(DBHelper.COLUMN_MIN_RATING);
                            do {
                                Log.d("mLog", "ID = " + cursorк.getInt(idIndex) + ", name = " + cursorк.getString(nameIndex) + ", password = " + cursorк.getString(passIndex) + ", rating = " + cursorк.getString(min_rat) + "      " + boologin);
                            } while (cursorк.moveToNext());
                        } else
                            Log.d("mLog", "0 rows");

                        //-----------------------------Таблица ДОСТОПРИМЕЧАТЕЛЬНОСТИ------------------------------

                        Cursor cursor2 = database.query(DBHelper.TABLE_NAME1, null, null, null, null, null, null);

                        if (cursor2.moveToFirst()) {
                            int idIndex = cursor2.getColumnIndex(DBHelper.COLUMN_ID1);
                            int nameIndex = cursor2.getColumnIndex(DBHelper.COLUMN_NAME1);

                            do {
                                Log.d("mLog", "ID = " + cursor2.getInt(idIndex) + ", name = " + cursor2.getString(nameIndex));
                            } while (cursor2.moveToNext());
                        } else
                            Log.d("mLog", "0 rows");

                        //-----------------------------Таблица FULL-----------------------------------------------

                        Cursor cursor3 = database.query(DBHelper.TABLE_NAME2, null, null, null, null, null, null);

                        if (cursor3.moveToFirst()) {
                            int idIndex = cursor3.getColumnIndex(DBHelper.COLUMN_ID2);
                            int idIndex1 = cursor3.getColumnIndex(DBHelper.COLUMN_ID);
                            int idIndex2 = cursor3.getColumnIndex(DBHelper.COLUMN_ID1);
                            int rating = cursor3.getColumnIndex(DBHelper.COLUMNT_RATING);

                            do {
                                Log.d("mLog", "ID = " + cursor3.getInt(idIndex) + "ID1 = " + cursor3.getInt(idIndex1) + "ID2 = " + cursor3.getInt(idIndex2) + ", rating = " + cursor3.getString(rating));
                            } while (cursor3.moveToNext());
                        } else
                            Log.d("mLog", "0 rows");

                        if (etName.equals("") || (etPas.equals(""))){
                            Toast.makeText(
                                    context, "У Вас присутствуют пустые строки. Пожалуйста, заполните их!",
                                    Toast.LENGTH_SHORT
                            ).show();
                        } else {

                            Cursor cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);

                            if (cursor.moveToFirst()) {
                                int NameEt = cursor.getColumnIndex(DBHelper.COLUMN_NAME);
                                int PasEt = cursor.getColumnIndex(DBHelper.COLUMN_PASS);
                                int IDREG = cursor.getColumnIndex(DBHelper.COLUMN_ID);
                                int Min_Rating = cursor.getColumnIndex(DBHelper.COLUMN_MIN_RATING);

                                do{
                                    if (cursor.getString(NameEt).equals(etName)){
                                        if (cursor.getString(PasEt).equals(etPas)){
                                            Host.ID = cursor.getInt(IDREG);
                                            Host.Login = cursor.getString(NameEt);
                                            Host.Password = cursor.getString(PasEt);
                                            Host.Minimal_Rating = cursor.getInt(Min_Rating);
                                            boologin1 = 1;
                                            break;
                                        } else {
                                            Toast.makeText(
                                                    context, "Неверный пароль!",
                                                    Toast.LENGTH_SHORT
                                            ).show();
                                            break;
                                        }
                                    }
                                }while (cursor.moveToNext());
                            }

                            if (boologin1 == 1){
                                Intent intent = new Intent(context, Main_menu.class);
                                context.startActivity(intent);
                            }
                            else {
                                Toast.makeText(
                                        context, "Данного логина не существует!",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }

                            cursor.close();

                        }

                        break;
                    case 1:
                        if (etName.equals("") || (etPas.equals(""))){
                            Toast.makeText(
                                    context, "У Вас присутствуют пустые строки. Пожалуйста, заполните их!",
                                    Toast.LENGTH_SHORT
                            ).show();
                        } else {


                            if (etpasrep.getText().toString().equalsIgnoreCase(etpas.getText().toString())) {


                                Cursor cursor1 = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);

                                if (cursor1.moveToFirst()) {
                                    int nameIndex = cursor1.getColumnIndex(DBHelper.COLUMN_NAME);
                                    do {
                                        if (cursor1.getString(nameIndex).equals(etName)) {
                                            boologin = 0;
                                            Toast.makeText(
                                                    context, "Данный логин уже существует!",
                                                    Toast.LENGTH_SHORT
                                            ).show();
                                            break;
                                        }
                                        else boologin = 1;
                                    } while (cursor1.moveToNext());
                                } else
                                    Log.d("mLog", "0 rows");

                                if (boologin == 1) {
                                        contentValues.put(DBHelper.COLUMN_NAME, etName);
                                        contentValues.put(DBHelper.COLUMN_PASS, etPas);
                                        contentValues.put(DBHelper.COLUMN_MIN_RATING, rating);

                                        database.insert(DBHelper.TABLE_NAME, null, contentValues);
                                        Toast.makeText(
                                                context, "Запись добавлена!",
                                                Toast.LENGTH_SHORT
                                        ).show();
                                }

                                cursor1.close();

                            } else {
                                Toast.makeText(
                                        context, "Повторный пароль введён неправильно!",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        }
                        break;
                }
            }
        });

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_heading[position]);
        btn.setText(btn_text[position]);

        switch (invise_tv[position]) {
            case 0:
                etpasrep.setVisibility(View.INVISIBLE);
                break;
            case 1:
                etpasrep.setVisibility(View.VISIBLE);
                break;
        }
        container.addView(view);

        return view;

    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout)object);

    }
}
