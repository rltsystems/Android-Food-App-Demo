package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.daclink.drew.sp22.cst438_project01_starter.databinding.ActivityCreateAccountBinding;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.ActivityMainBinding;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.ActivityModifyAccountBinding;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.User;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;

public class CreateAccount extends AppCompatActivity {

    private static final String USER_ID_KEY = "userIdKey";

    private ActivityCreateAccountBinding binding;
    private UserDao userDao;

    private String username;
    private String password;
    private String passwordConfirm;
    private User user;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getDatabase();

        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = binding.editTextUsername.getText().toString();
                password = binding.editTextPassword.getText().toString();
                passwordConfirm = binding.editTextPasswordConfirm.getText().toString();

                if (verifyAccount()){
                    createUser();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * This method checks to make sure an account with the inputted username isn't in the DB already.
     * Also checks to make password fields match
     * @return boolean Returns if account is unique and passwords match
     */
    private boolean verifyAccount(){
        user = userDao.getUserByUsername(username);
        if (user != null){
            Toast.makeText(this, "Account with username " + username + " already exists", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!password.equals(passwordConfirm)){
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void createUser(){
        User user = new User(username, password);
        userDao.insert(user);
    }

    private void getDatabase(){
        userDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserDao();
    }
}