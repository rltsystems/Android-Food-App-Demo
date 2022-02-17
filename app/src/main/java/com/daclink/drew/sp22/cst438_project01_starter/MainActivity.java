package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Intent;
import android.os.Bundle;

import com.daclink.drew.sp22.cst438_project01_starter.Api.EdamamApi;
import com.daclink.drew.sp22.cst438_project01_starter.Api.Post;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.room.Room;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.daclink.drew.sp22.cst438_project01_starter.databinding.ActivityMainBinding;
import com.daclink.drew.sp22.cst438_project01_starter.db.User;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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
    UserDao userDao;
    User user = new User();
    Button createAccBtn;
    Button loginBtn;
    private int userId;

    String pass;
    String name;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getDatabase();

    }

    //Button for logging in
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

    private void getDatabase(){
        userDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserDao();
    }
}
