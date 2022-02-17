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

public class RecipeList extends AppCompatActivity {

    private static final String USER_ID_KEY = "userIdKey";
    private RecyclerView rvRecipes;
    private RecipeAdapter adapter;

    private ArrayList<Recipe> recipes = new ArrayList<>();
    private UserDao userDao;
    private int mUserId;
    private ActivityRecipeListBinding binding;

    private int mSecondClick;

    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDatabase();

        mUserId = getIntent().getIntExtra(USER_ID_KEY,-1);

        rvRecipes = binding.rvRecipes;
        //TODO: Change the 1 to mUserId
        adapter = new RecipeAdapter(getApplicationContext(), mUserId, 0);

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

        TextInputEditText keywordEditText = binding.foodQueryBox;
        Button addRecipe = binding.addRecipeBtn;

        String keyword = keywordEditText.getEditableText().toString();

        addRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchRecipes("keyword");
            }
        });
    }

    public void refreshView() { //TODO: Change the 1 to userId
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

    private void addRecipes(){
        User user = new User();
        //TODO: change the 1 to userId;
        user = userDao.getUserById(mUserId);
        Recipe recipeOne = new Recipe("Black Miso Cod", 800.0, "A classic Japanese recipe for black cod that makes an easy, elegant dinner for guests or a quick main dish you can prep over the weekend.");
        Recipe recipeTwo = new Recipe("Miso-Sesame Shrimp & Bacon Ramen", 830.0, "Ramen, topped with crispy pan seared shrimp and added bacon bits to really bring out the flavor. As well as a side of hand made garlic oil to change the flavor of the whole dish");
        Recipe recipeThree = new Recipe("Creamy Rigatoni with pork sausage", 980.0, "Aldente rigatoni covered in a rich sauce made from parmesan cheese and cream cheese, mixed together with steamed broccoli florets and pan cooked chicken");
        user.getRecipes().add(recipeOne);
        user.getRecipes().add(recipeTwo);
        user.getRecipes().add(recipeThree);
        userDao.insert(user);
    }

    public void searchRecipes(String query){
        int SEARCH_RESULT_LIMIT = 20;
        ApiKey keyboi = new ApiKey();
        String recipeSearchBaseString = "https://api.spoonacular.com";
        SpoontacularSearchAPI spoonSearch;

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

        Call<RecipeResponse> call = spoonSearch.getRecipes(query, keyboi.getKey(),
                true, SEARCH_RESULT_LIMIT);

        call.enqueue(new Callback<RecipeResponse>(){
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if(response.body() != null){
                    Log.d("SUCC","onResponse: We did it bois");
                    RecipeResponse test = response.body();
                    // THIS IS ONLY FOR INITIAL SHOWING AND TESTING !!!!!!!!!!!!
                    User user = new User();
                    user = userDao.getUserById(mUserId);
                    for (int i = 0; i < test.results.size();i++){
                        Recipe newRec = new Recipe(test.results.get(i));
                        user.getRecipes().add(newRec);
                    }
                    userDao.insert(user);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Log.d("FAIL","onResponse: We did NOT it bois");
            }
        });

    }
}