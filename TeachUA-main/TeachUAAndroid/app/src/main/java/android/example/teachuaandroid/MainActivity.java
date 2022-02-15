package android.example.teachuaandroid;

import android.content.Context;
import android.content.Intent;
import android.example.teachuaandroid.ui.UnitContent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.example.teachuaandroid.databinding.ActivityMainBinding;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private ActivityMainBinding binding;
    private DrawerLayout drawerLayout;
    private Button button;
    private NavigationView navigationView;
    private MenuItem radioButtonGroups1;
    private MenuItem radioButtonCenter2;
    private MenuItem spinnerCity;
    private MenuItem spinnerCityArea;
    private MenuItem spinnerBusStop;
    private MenuItem checkBoxRemote;
    private MenuItem spinnerCategory;
    private MenuItem spinnerChildrenAge;
    private MenuItem applyButtonInMenu;
    private MenuItem clearButtonInMenu;

    private Map<String, ArrayList<String>> mapForKyiv;
    private Map<String, ArrayList<String>> mapForBusTop;
    private ArrayList<String> areasLviv;
    private ArrayList<String> itemList;

    // adapters tools for connect RecyclerView with CardView
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private Spinner citySpinnerSettings;
    private Spinner cityAreaSpinnerSettings;
    private Spinner childrenGroupsSettings;
    private Spinner spinnerAgeChildren;
    private Spinner spinnerStopBus;

    private ArrayAdapter spinnerAdapter; // адаптер для спіннера
    private ArrayAdapter spinnerAdapterForArea; // адаптер для спіннера
    private ArrayAdapter spinnerChildrenGroupsAdapter; // адаптер для спіннера
    private ArrayAdapter spinnerAgeChildrenAdapter; // адаптер для спіннера
    private ArrayAdapter spinnerStopBusAdapter; // адаптер для спіннера


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        DrawerLayout drawer = binding.drawerLayout;
        navigationView = binding.navView;

        recyclerAndCardConnection();
        componentsFromNavigationView();

        createSpinnerCity();
        createAreaToCity();
        createBusStop();
        createGoupsCategory();
        createChilderAge();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupWithNavController(navigationView, navController);


    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void recyclerAndCardConnection() {
        ArrayList<RecyclerViewItem> recyclerViewItemList = new ArrayList<>();

        button = findViewById(R.id.buttonInMainScreenCardMoreInfo);

        recyclerViewItemList.add(new RecyclerViewItem(R.drawable.about_us, UnitContent.cardTitle_1, button, UnitContent.description_1));
        recyclerViewItemList.add(new RecyclerViewItem(R.drawable.about_clubs_1, UnitContent.cardTitle_2, button, UnitContent.description_2));
        recyclerViewItemList.add(new RecyclerViewItem(R.drawable.challenge, UnitContent.cardTitle_3, button, UnitContent.description_3));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(recyclerViewItemList, this);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }


    public void componentsFromNavigationView() {
        Menu menuNav = navigationView.getMenu();

        radioButtonGroups1 = menuNav.findItem(R.id.radioButtonGroups1);
        radioButtonCenter2 = menuNav.findItem(R.id.radioButtonCenter2);

        spinnerCity = menuNav.findItem(R.id.spinnerCity);
        spinnerCityArea = menuNav.findItem(R.id.spinnerCityArea);
        spinnerBusStop = menuNav.findItem(R.id.spinnerBusStop);
        checkBoxRemote = menuNav.findItem(R.id.checkBoxRemote);
        spinnerCategory = menuNav.findItem(R.id.spinnerCategory);
        spinnerChildrenAge = menuNav.findItem(R.id.spinnerChildrenAge);

        applyButtonInMenu = menuNav.findItem(R.id.applyButtonInMenu);
        clearButtonInMenu = menuNav.findItem(R.id.clearButtonInMenu);

    }


    public void createSpinnerCity() {
        citySpinnerSettings = findViewById(R.id.spinnerCity);
        citySpinnerSettings.setOnItemSelectedListener(this); // присвоємо лісенер щоб наш клас прослуховував cобытие этого спиннера;

        itemList = new ArrayList<String>();
        itemList.add("Львів");
        itemList.add("Київ");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, itemList);   // тут передаємо в context на клас "this" в якому находиться спіннер, далі передаємо переопределенный елемент спіннера, і наш ArrayList з елементами
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // тут за допомогою цієї строки ми добавляємо випадающий список "simple_spinner_dropdown_item" до нашого спіннера
        citySpinnerSettings.setAdapter(spinnerAdapter);      // тут встановлюємо наш адаптера для спіннера

    }

    public void createAreaToCity() {
        cityAreaSpinnerSettings = findViewById(R.id.spinnerCityArea);
        cityAreaSpinnerSettings.setOnItemSelectedListener(this);

        mapForKyiv = new HashMap<String, ArrayList<String>>();

        areasLviv = new ArrayList<>();
        areasLviv.add("Виберіть район");
        areasLviv.add("Шевченківський район");
        areasLviv.add("Личаківський район");
        areasLviv.add("Залізничний район");
        areasLviv.add("Франківський район");
        areasLviv.add("Сихівський район");
        areasLviv.add("Галицький район");

        mapForKyiv.put("Львів", areasLviv);

        ArrayList<String> areasKyiv = new ArrayList<>();
        areasKyiv.add("Виберіть район");
        areasKyiv.add("Деснянський район");
        areasKyiv.add("Святошинський район");
        areasKyiv.add("Дніпровський район");
        areasKyiv.add("Печерський район");
        areasKyiv.add("Голосіївський район");
        areasKyiv.add("Дарницький район");
        areasKyiv.add("Солом’янський район");
        areasKyiv.add("Оболонський район");
        areasKyiv.add("Шевченківський район");
        areasKyiv.add("Подільський район");

        mapForKyiv.put("Київ", areasKyiv);

    }


    public void createBusStop() {
        spinnerStopBus = findViewById(R.id.spinnerBusStop);
        spinnerStopBus.setOnItemSelectedListener(this);

        mapForBusTop = new HashMap<String, ArrayList<String>>();

        ArrayList<String> busStopLviv = new ArrayList<String>();
        busStopLviv.add("Виберіть зупинку");
        busStopLviv.add("Зупинка Личаківська 1");
        busStopLviv.add("Зупинка Личаківська 2");
        busStopLviv.add("Зупинка Личаківська 3");
        busStopLviv.add("Зупинка Личаківська 4");
        busStopLviv.add("Зупинка Личаківська 5");
        busStopLviv.add("Зупинка Личаківська 6");
        busStopLviv.add("Зупинка Личаківська 7");

        mapForBusTop.put("Личаківський район", busStopLviv);

        ArrayList<String> busStopKyiv = new ArrayList<String>();
        busStopKyiv.add("Виберіть зупинку");
        busStopKyiv.add("Зупинка Печерська 1");
        busStopKyiv.add("Зупинка Печерська 2");
        busStopKyiv.add("Зупинка Печерська 3");
        busStopKyiv.add("Зупинка Печерська 4");
        busStopKyiv.add("Зупинка Печерська 5");
        busStopKyiv.add("Зупинка Печерська 6");
        busStopKyiv.add("Зупинка Печерська 7");

        mapForBusTop.put("Печерський район", busStopKyiv);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        cityAreaSpinnerSettings = findViewById(R.id.spinnerCityArea);
        spinnerStopBus = findViewById(R.id.spinnerBusStop);

        chooseModeForAreaSpinner();

        spinnerAdapterForArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityAreaSpinnerSettings.setAdapter(spinnerAdapterForArea);
        cityAreaSpinnerSettings.setSelection(position);


        chooseModeBusStop();

        spinnerStopBusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStopBus.setAdapter(spinnerStopBusAdapter);
        spinnerStopBus.setSelection(position);

    }


    public void chooseModeForAreaSpinner() {

        ArrayList<String> testArrayList = new ArrayList<>();

        for (Map.Entry<String, ArrayList<String>> entry : mapForKyiv.entrySet()) {
            String key = entry.getKey();

            if (citySpinnerSettings.getSelectedItem().toString().equals(key)) {
                ArrayList array = entry.getValue();
                testArrayList.addAll(array);
            }

            spinnerAdapterForArea = new ArrayAdapter(this, android.R.layout.simple_spinner_item, testArrayList);

        }
    }


    public void chooseModeBusStop() {

        ArrayList<String> testArrayList = new ArrayList<>();

        for (Map.Entry<String, ArrayList<String>> entry : mapForBusTop.entrySet()) {
            String key = entry.getKey();

            if (cityAreaSpinnerSettings.getSelectedItem().toString().equals(key)) {
                ArrayList array = entry.getValue();
                testArrayList.addAll(array);
            }
            spinnerStopBusAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, testArrayList);

        }
    }


    public void createGoupsCategory() {
        childrenGroupsSettings = findViewById(R.id.spinnerCategory);
        //  childrenGroupsSettings.setOnItemSelectedListener(this); // присвоємо лісенер щоб наш клас прослуховував cобытие этого спиннера;

        itemList = new ArrayList<String>();
        itemList.add("Виберіть категорію");
        itemList.add("Спортивні секції");
        itemList.add("Спортивні секції");
        itemList.add("Танці, хореографія");
        itemList.add("Студії раннього розвитку");
        itemList.add("Програмування, робототехніка STEM");
        itemList.add("Художня студія, мистецтво, дизайн");
        itemList.add("Вокальна студія, музика, музичні інструменти");
        itemList.add("Акторська майстерність, театр");
        itemList.add("Особистісний розвиток");
        itemList.add("Журналістика, дитяче телебачення, монтаж відео, влогів");
        itemList.add("Центр розвитку");
        itemList.add("Журналістика, дитяче телебачення, монтаж відео");
        itemList.add("Інше");

        spinnerChildrenGroupsAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, itemList);   // тут передаємо в context на клас "this" в якому находиться спіннер, далі передаємо переопределенный елемент спіннера, і наш ArrayList з елементами
        spinnerChildrenGroupsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // тут за допомогою цієї строки ми добавляємо випадающий список "simple_spinner_dropdown_item" до нашого спіннера
        childrenGroupsSettings.setAdapter(spinnerChildrenGroupsAdapter);      // тут встановлюємо наш адаптера для спіннера

    }


    public void createChilderAge() {
        spinnerAgeChildren = findViewById(R.id.spinnerChildrenAge);
        // spinnerAgeChildren.setOnItemSelectedListener(this); // присвоємо лісенер щоб наш клас прослуховував cобытие этого спиннера;

        itemList = new ArrayList<String>();
        itemList.add("Виберіть вік дитини");
        for (int i = 2; i < 18; i++) {
            itemList.add(String.valueOf(i));
        }

        spinnerAgeChildrenAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, itemList);   // тут передаємо в context на клас "this" в якому находиться спіннер, далі передаємо переопределенный елемент спіннера, і наш ArrayList з елементами
        spinnerAgeChildrenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // тут за допомогою цієї строки ми добавляємо випадающий список "simple_spinner_dropdown_item" до нашого спіннера
        spinnerAgeChildren.setAdapter(spinnerAgeChildrenAdapter);      // тут встановлюємо наш адаптера для спіннера

    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}