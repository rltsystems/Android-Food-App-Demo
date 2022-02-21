package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.daclink.drew.sp22.cst438_project01_starter.Api.ApiKey;
import com.daclink.drew.sp22.cst438_project01_starter.Api.RecipeResponse;
import com.daclink.drew.sp22.cst438_project01_starter.Api.SpoontacularSearchAPI;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.ActivityRecipeListBinding;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.Recipe;
import com.daclink.drew.sp22.cst438_project01_starter.db.RecipeAdapter;
import com.daclink.drew.sp22.cst438_project01_starter.db.User;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Activity for the recyclerview that displays information returned from API calls
 */
public class RecipeList extends AppCompatActivity {

    private static final String USER_ID_KEY = "userIdKey";
    private RecyclerView rvRecipes;
    private RecipeAdapter adapter;

    private ArrayList<Recipe> recipes = new ArrayList<>();
    private UserDao userDao;
    private int mUserId;
    private ActivityRecipeListBinding binding;

    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDatabase();

        mUserId = getIntent().getIntExtra(USER_ID_KEY,-1);

        rvRecipes = binding.rvRecipes;
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

        Button addRecipe = binding.addRecipeBtn;

        addRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchRecipes();
            }
        });
    }

    /**
     * method that refreshes the recyclerview when user swipes to refresh
     */
    public void refreshView() {
        RecipeAdapter adapter = new RecipeAdapter(RecipeList.this, mUserId);
        rvRecipes.setAdapter(adapter);

    }

    private void getDatabase(){
        userDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserDao();
    }

    /**
     * Places a call to the API using the user's input as a query
     */
    public void searchRecipes(){
        int SEARCH_RESULT_LIMIT = 5;
        ApiKey keyboi = new ApiKey();
        String recipeSearchBaseString = "https://api.spoonacular.com";
        SpoontacularSearchAPI spoonSearch;

        // getting the text from the search box
        TextInputEditText keywordEditText = binding.foodQueryBox;
        String keyword = keywordEditText.getEditableText().toString();

        // okhttp3 for debugging and log
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        // constructing Retrofit bullshit
        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(recipeSearchBaseString)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        spoonSearch = retrofit.create(SpoontacularSearchAPI.class);

        Call<RecipeResponse> call = spoonSearch.getRecipes(keyword, keyboi.getKey(),
                true, SEARCH_RESULT_LIMIT);

        call.enqueue(new Callback<RecipeResponse>(){
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if(response.body() != null){
                    Log.d("API","onResponse: We did it bois");
                    RecipeResponse test = response.body();
                    // THIS IS ONLY FOR INITIAL SHOWING AND TESTING !!!!!!!!!!!!
                    User user = new User();
                    user = userDao.getUserById(mUserId);
                    for (int i = 0; i < test.results.size();i++){
                        Recipe newRec = new Recipe(test.results.get(i));
                        user.getRecipes().add(newRec);
                        adapter.notifyItemInserted(user.getRecipes().size() - 1);
                    }
                    userDao.insert(user);
                    //adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Log.d("FAIL","onResponse: We did NOT it bois");
            }
        });

    }
}