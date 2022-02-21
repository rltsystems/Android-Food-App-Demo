package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daclink.drew.sp22.cst438_project01_starter.databinding.ActivityRecipeListBinding;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.ActivitySavedRecipesBinding;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.Recipe;
import com.daclink.drew.sp22.cst438_project01_starter.db.RecipeAdapter;
import com.daclink.drew.sp22.cst438_project01_starter.db.SavedRecipeAdapter;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;

import java.util.ArrayList;

/**
 * This class is nearly identical to RecipeList except for the recipes
 * already saved to a users account
 */
public class SavedRecipes extends AppCompatActivity {
    private static final String USER_ID_KEY = "userIdKey";
    private RecyclerView rvRecipes;
    private RecipeAdapter adapter;

    private ArrayList<Recipe> recipes = new ArrayList<>();
    private UserDao userDao;
    private int mUserId;
    private ActivitySavedRecipesBinding binding;

    private SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySavedRecipesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDatabase();

        mUserId = getIntent().getIntExtra(USER_ID_KEY,-1);

        rvRecipes = binding.rvSavedRecipes;
        adapter = new RecipeAdapter(getApplicationContext(), mUserId);

        rvRecipes.setAdapter(adapter);

        rvRecipes.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvRecipes.addItemDecoration(itemDecoration);

        refreshLayout = binding.swipeRefresh;

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
                refreshView();
            }
        });

    }

    public void refreshView() {
        SavedRecipeAdapter adapter = new SavedRecipeAdapter(SavedRecipes.this, mUserId);
        rvRecipes.setAdapter(adapter);

    }

    private void getDatabase(){
        userDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserDao();
    }
}