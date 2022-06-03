package com.example.hasma.sightsofminsk;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.MenuItemHoverListener;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

public class Main_menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;

    ListView list;

    String[][] sight = {
            {"Центральный ботанический сад Национальной академии наук Беларуси", "ул. Сурганова 2в, Минск", "Центральный ботанический сад НАН Беларуси является самым крупным в стране центром по сохранению биоразнообразия живых растений, ведущим научным учреждением в области интродукции, акклиматизации, физиологии, молекулярной биологии, биотехнологии, биохимии и экологии растений. Исследования и практическая работа в области интродукции растений обеспечила создание генофонда декоративных и хозяйственно-полезных интродуцированных растений из более 12 тысяч наименований, который составляет национальное достояние Республики Беларусь."},
            {"Белорусский государственный музей истории Великой Отечественной войны", "проспект Победителей 8, Минск", "Белорусский государственный музей истории Великой Отечественной войны – первый в мире, посвященный самой кровопролитной войне ХХ века, и единственный в Беларуси, созданный в годы фашистской оккупации. Сегодня это один из самых значимых и крупных музеев планеты – наряду с богатейшими собраниями в Москве, Киеве, Новом Орлеане – которые рассказывают о событиях Второй мировой войны."},
            {"Центральный детский парк имени Максима Горького", "ул. Фрунзе 2, Минск", "Центральный детский парк им. Максима Горького — парк культуры и отдыха в центре Минска недалеко от Площади Победы. Расположен между улицами Янки Купалы, Фрунзе, Первомайской и проспектом Независимости. Сегодняшний парк занимает площадь в 28 гектаров. В парке растёт более 60 местных пород деревьев и кустарников. Здесь имеются и редкие садово-парковые растения — кедровая сосна, сосна Веймутова, пихта калифорнийская, лиственница европейская, клёны полевой и серебристый, а также и некоторые другие. Сохранились декоративные группы лип и кленов, которым более ста лет. Сегодня в парке действуют аттракционы: Колесо обозрения,Автодром, Сюрприз, Шариковый бассейн, Колокольчик, Солнышко, Детские качели, Лодочки, Цепочная карусель, Детские качели и другие. Гордость парка — обзорное «Колесо обозрения», высотой 54 метра, с которого минчане и гости столицы могут осмотреть город с высоты."},
            {"Белорусский государственный цирк", "проспект Независимости 32, Минск", "Белорусский государственный цирк находится на проспекте Независимости в городе Минске. Вместимость зала цирка — 1667 мест, цирк рассчитан для показа номеров различных жанров — от водных феерий до воздушных номеров."},
            {"Национальный художественный музей Республики Беларусь", "ул. Ленина 20, Минск", "Национальный художественный музей Республики Беларусь — крупнейший музей художественного профиля в Белоруссии, Государственная картинная галерея с 1939 до 1957, Государственный художественный музей с 1957 по 1993. Фонды музея насчитывают более 27 000 произведений. Расположен в столице страны — городе Минске."},
            {"Музей валунов", "ул. Академика Купревича, Минск", "Музей валунов — парк-музей камней в микрорайоне Уручье"},
            {"Национальный исторический музей Республики Беларусь", "ул. Карла Маркса 12, Минск", "Национальный исторический музей Республики Беларусь до 15 сентября 2009 — Национальный музей истории и культуры Беларуси — крупнейший по числу единиц хранения музей Республики Беларусь."},
            {"Музей истории города Минска", "ул. Революционная 10, Минск", "Государственное учреждение «Музей истории города Минска», Музей истории Минска — музей истории города Минска. До открытия временной экспозиции по истории города музей принимает постоянные выставки различной тематики."}
    };

    int[] sightImg = {
            R.drawable.centralbotanicalgardenofthenationalacademyofsciencesofbelarus,
            R.drawable.belarusianstateownedmuseumandhistoryofgreatdomesticwar,
            R.drawable.centralchildsparkgorkogo,
            R.drawable.belarusianstatecircus,
            R.drawable.nationalartisticmuseumoftherepublicofbelarus,
            R.drawable.muzejvalunov,
            R.drawable.nationalhistoricalmuseumoftherepublicofbelarus,
            R.drawable.museumofthehistoryofminsk
    };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_map:
                Toast.makeText(
                        Main_menu.this, "MAP",
                        Toast.LENGTH_SHORT
                ).show();
                break;
            case R.id.nav_settings:
                Intent intent = new Intent(this, Setting.class);
                this.startActivity(intent);
                break;
            case R.id.nav_link:
                DBHelper dbHelper = new DBHelper(this);
                SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
                Cursor cursor = sqLiteDatabase.query(DBHelper.TABLE_NAME2, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.COLUMN_ID2);
                    int idIndex1 = cursor.getColumnIndex(DBHelper.COLUMN_ID);
                    int idIndex2 = cursor.getColumnIndex(DBHelper.COLUMN_ID1);
                    int ratingg = cursor.getColumnIndex(DBHelper.COLUMNT_RATING);

                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) + ", ID1 = " + cursor.getInt(idIndex1) + ", ID2 = " + cursor.getInt(idIndex2) + ", rating = " + cursor.getString(ratingg));
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog", "0 rows" + ", HOST - " + Host.Name_Sight + ", ID - " + Host.ID_Sight);
                break;
            case R.id.nav_help:
                Log.d("mLog", "HOST: \nID - " + Host.ID +"\nLogin - " + Host.Login + "\nPassword - " + Host.Password + "\nRating" + Host.Minimal_Rating);
                break;
            case  R.id.nav_logout:
                finishAffinity();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawerLayout);

        list = (ListView) findViewById(R.id.listView);

        list.setAdapter(new Adapter(this, sight, sightImg));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), Description_Activity.class);
                intent.putExtra("TIT", sight[position][0]);
                intent.putExtra("IMG", sightImg[position]);
                intent.putExtra("DET", sight[position][2]);
                startActivity(intent);
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
