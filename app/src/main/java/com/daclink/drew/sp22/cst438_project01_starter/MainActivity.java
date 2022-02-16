package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Intent;
import android.os.Bundle;

import com.daclink.drew.sp22.cst438_project01_starter.Api.EdamamApi;
import com.daclink.drew.sp22.cst438_project01_starter.Api.Post;
import com.google.android.material.snackbar.Snackbar;

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
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private UserDao userDao;
    private User user;

    private Button createAccBtn;
    private Button loginBtn;

    private String pass;
    private String name;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(binding.getRoot());

        getDatabase();

        createAccBtn = view.findViewById(R.id.create_acc);
        name = view.findViewById(R.id.user).toString();
        pass = view.findViewById((R.id.pass)).toString();
        loginBtn = view.findViewById(R.id.login);

    }

        //Button for logging in
    public void login(View v){
        if(verifyAccountLogin(name, pass)){
            Intent intent = new Intent(getApplicationContext(), CreateAccount.class);
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

    private boolean verifyAccountLogin(String username, String password){
        user = userDao.getUserByUsername(username);

        if (user.getUsername() != username){
            Toast.makeText(this, "Incorrect account name or password", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(user.getPass() != password){
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

