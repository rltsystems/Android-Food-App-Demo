package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Intent;
import android.os.Bundle;

import com.daclink.drew.sp22.cst438_project01_starter.Api.EdamamApi;
import com.daclink.drew.sp22.cst438_project01_starter.Api.Post;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.Recipe;
import com.daclink.drew.sp22.cst438_project01_starter.db.User;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import com.daclink.drew.sp22.cst438_project01_starter.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "userIdKey";

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private UserDao userDao;
    private int userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getDatabase();
        predifinedUser();
        Intent intent = new Intent(getApplicationContext(), RecipeList.class);
        intent.putExtra(USER_ID_KEY, userId);
        startActivity(intent);
    }

    public void predifinedUser(){
        if(userDao.getUserByUsername("demoUser") == null){
            User user = new User("demoUser","123");
            Recipe recipeOne = new Recipe("Fish Tacos", 1600.0, "Tacos with fish i guess");
            Recipe recipeTwo = new Recipe("Ramen", 2300.0, "Tonkotsu ramen");
            Recipe recipeThree = new Recipe("Sushi", 800.0, "California Roll");
            user.getRecipes().add(recipeOne);
            user.getRecipes().add(recipeTwo);
            user.getRecipes().add(recipeThree);
            userDao.insert(user);
            userId = userDao.getUserByUsername("demoUser").getId();
        }
    }

    private void getDatabase() {
        userDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserDao();
    }
}
