package com.daclink.drew.sp22.cst438_project01_starter;

import android.os.Bundle;

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

    public void createAccount(View v){

        user.setUsername("Testing");
        user.setPass("Purposes");

        //For when the actual text boxes can be typed in
//        user.setUsername(name.getText().toString());
//        user.setPass(pass.getText().toString());

        user.setId(8888);
        user.setRecipeCount(0);

        mUserDAO.insert(user);
//Test cases to see if the database is storing and returning correct data
        Toast.makeText(this, mUserDAO.getUserById(8888).getUsername() + " " + mUserDAO.getUserById(8888).getPass() +
                " " + mUserDAO.getUserById(8888).getId() + " " + mUserDAO.getUserById(8888).getRecipeCount(), Toast.LENGTH_SHORT).show();
    }

}
