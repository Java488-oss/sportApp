package com.example.sportapp;

import static com.example.sportapp.R.drawable.side_nav_bar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.sportapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private ImageButton btnImage;
    private ImageView imageView;
    private RelativeLayout rectangles;

    private boolean imageClick=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        setTheme(R.style.Theme_SportApp);


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_settings)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        btnImage = findViewById(R.id.btnImage);

        imageView = findViewById(R.id.imageView);

        //переход на активити с информацией о пользователе при нажатии на кнопку аккаунта
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AccountUserActivity.class);
                startActivity(intent);
            }
        });


        // Изменение темы при нажатии на луну/солнце
        btnImage.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"ResourceAsColor"})
            @Override
            public void onClick(View view) {
                if (!imageClick) {
                    //Установка картинки на кнопку
                    btnImage.setImageResource(R.drawable.moon);
                    // Установка цвета иконки кнопки
                    btnImage.setColorFilter(0xFFFFFFFF);
                    //Установка Background для заднего фона
                    rectangles = findViewById(R.id.rectangles);
                    rectangles.setBackgroundColor(Color.BLACK);
                    //установка цвета для imageview
                    imageView.setColorFilter(0xFFFFFFFF);
                    imageClick=true;
                }else {
                    btnImage.setImageResource(R.drawable.sun);
                    btnImage.setColorFilter(0x00000000);
                    rectangles = findViewById(R.id.rectangles);
                    rectangles.setBackgroundResource(side_nav_bar);
//                    imageView.setColorFilter(0x00000000);
                    imageClick=false;
                }
            }
        });
        createFile();
        return true;
    }

    public void createFile() {
        try {
            String path = getRootOfExternalStorage()+"/data.cfg";
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
                FileOutputStream writer = new FileOutputStream(path);
                writer.write(("1234Zz").getBytes());
                writer.close();
                Log.d("my","test"+ path);
            }
        } catch (IOException e) {
            Log.d("my","test error "+ e);

            e.printStackTrace();
        }

    }

    //////////////////////////////////////
    // Получение системных путей
    private String getRootOfExternalStorage() {
        File[] externalStorageFiles = ContextCompat.getExternalFilesDirs(this,null);
        for(File file : externalStorageFiles) {
            // Получение полного пути приложения  /storage/emulated/0/Android/data/com.example.sportapp/files
//            return file.getAbsolutePath();
            // получение системного пути /storage/emulated/0
            return file.getAbsolutePath().replaceAll("/Android/data/" + getPackageName() + "/files", "");
        }
        return null;
    }
    ////////////////////////////////

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}