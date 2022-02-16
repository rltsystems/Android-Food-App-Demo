package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.Recipe;
import com.daclink.drew.sp22.cst438_project01_starter.db.RecipeAdapter;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;

import java.util.ArrayList;

public class RecipeList extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.example.storefront.userIdKey";
    private RecyclerView rvRecipes;
    private RecipeAdapter adapter;

    private ArrayList<Recipe> recipes = new ArrayList<>();
    private UserDao userDao;
    private int mUserId;

    private int mSecondClick;

    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        getDatabase();

        mUserId = getIntent().getIntExtra(USER_ID_KEY,-1);

        rvRecipes = findViewById(R.id.rvRecipes);
        //TODO: Change the 1 to mUserId
        adapter = new RecipeAdapter(getApplicationContext(), 1, 0);

        rvRecipes.setAdapter(adapter);

        rvRecipes.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvRecipes.addItemDecoration(itemDecoration);

        refreshLayout = findViewById(R.id.swipeRefresh);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
                refreshView();
            }
        });
    }

    public void refreshView() {
        RecipeAdapter adapter = new RecipeAdapter(RecipeList.this, mUserId, mSecondClick);
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