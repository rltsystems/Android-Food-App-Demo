package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Intent;
import android.os.Bundle;

import com.daclink.drew.sp22.cst438_project01_starter.db.Recipe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.room.Room;

import com.daclink.drew.sp22.cst438_project01_starter.databinding.ActivityMainBinding;
import com.daclink.drew.sp22.cst438_project01_starter.db.User;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;

import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "userIdKey";

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private UserDao userDao;
    private User user = new User();
    private Button createAccBtn;
    private Button loginBtn;
    private int userId;

    private String pass;
    private String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getDatabase();
        predefinedUser();
        Intent intent = new Intent(getApplicationContext(), RecipeList.class);
        intent.putExtra(USER_ID_KEY, userId);
        startActivity(intent);
    }

    public void login(View v){
        name = binding.user.getText().toString();
        pass = binding.pass.getText().toString();
        if(verifyAccountLogin(name, pass)){
            userId = userDao.getUserByUsername(name).getId();
            Intent intent = new Intent(getApplicationContext(), ModifyAccount.class);
            intent.putExtra(USER_ID_KEY, userId);
            startActivity(intent);
        }

    }
    /**
     * createAccount(View v)
     * Takes you to new activity called createAccount when clicking on the createAccount button
     */
    public void createAccount(View v){

        Intent intent = new Intent(getApplicationContext(), CreateAccount.class);
        startActivity(intent);

    }

    public void predefinedUser(){
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

    private boolean verifyAccountLogin(String name, String pass){
        User user = userDao.getUserByUsername(name);

        user = userDao.getUserByUsername(name);
        if (user == null){
            Toast.makeText(this, name + " not found", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!user.getPass().equals(pass)){
            Toast.makeText(this, "Incorrect account name or password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void getDatabase() {
        userDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserDao();
    }
}
