package com.daclink.drew.sp22.cst438_project01_starter;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    UserDao mUserDAO;
    User user = new User();

    Button createAccBtn;
    Button loginBtn;

    EditText pass;
    EditText name;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mUserDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getUserDao();

        createAccBtn = view.findViewById(R.id.create_acc);
        name = view.findViewById(R.id.user);
        pass = view.findViewById((R.id.pass));
        loginBtn = view.findViewById(R.id.login);

    }

        //Button for logging in
//    public void login(View v){
//
//        user.setUsername(name.getText().toString());
//
//    }
/*
* createAccount(View v)
* Method for creating account and inputting user input information into user DB
*
*
*/
    public void createAccount(View v){
        //For when the actual text boxes can be typed in
         user.setUsername(name.getText().toString());
         user.setPass(pass.getText().toString());
         mUserDAO.insert(user);

    }
}
