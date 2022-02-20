package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Intent;
import android.os.Bundle;

import com.daclink.drew.sp22.cst438_project01_starter.databinding.MainPageBinding;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

public class MainPage extends AppCompatActivity {
    private static final String USER_ID_KEY = "userIdKey";
    private MainPageBinding binding;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        binding = MainPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userId = getIntent().getIntExtra(USER_ID_KEY, -1);

        binding.myaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ModifyAccount.class);
                intent.putExtra(USER_ID_KEY, userId);
                startActivity(intent);
            }
        });

        binding.recipebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RecipeList.class);
                intent.putExtra(USER_ID_KEY, userId);
                startActivity(intent);
            }
        });

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra(USER_ID_KEY, -1);
                startActivity(intent);
            }
        });
    }
}