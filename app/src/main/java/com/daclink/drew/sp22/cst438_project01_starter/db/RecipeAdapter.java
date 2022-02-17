package com.daclink.drew.sp22.cst438_project01_starter.db;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daclink.drew.sp22.cst438_project01_starter.R;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>{

    private List<Recipe> recipes;

    private UserDao mUserDAO;
    private User mUser;
    private Context context;

    private int mSortNum;

    public RecipeAdapter(Context context, int userId, int sortNum){
        getDatabase(context);

        //TODO: Implement filters?
        mUser = new User();
        Log.i("TEST", String.valueOf(userId));
        mUser = mUserDAO.getUserById(userId);

        recipes = mUser.getRecipes();

        this.context = context;
    }

    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.recipelist_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.ViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);

        TextView textView = holder.recipeInfo;

        // made this a little more neat :)
        String s = recipe.displayRecipeBar();
        //String s = recipe.getRecipeName() + "\nTotal Calories: " + recipe.getTotalCalories();
        textView.setText(s);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: The commented code is for the list of savable recipes
                AlertDialog.Builder alert = new AlertDialog.Builder(textView.getContext());
//                alert.setTitle("Save Recipe: " + recipe.getRecipeName());
                alert.setMessage("Description: " + Html.fromHtml(recipe.getRecipeDescription()) /*(+ "\n\nWould you like to save this recipe?"*/);

//                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        saveRecipe(recipe);
//                    }
//                });
//
//                alert.setNegativeButton("No", null);
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView recipeInfo;

        public ViewHolder(View recipeView){
            super(recipeView);

            recipeInfo = recipeView.findViewById(R.id.itemInfo);
        }
    }

    private void saveRecipe(Recipe recipe){

        mUser.getRecipes().add(recipe);
        Toast.makeText(context, "Recipe Saved Successfully", Toast.LENGTH_SHORT).show();

//        else{
//            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
//        }
    }

    private void getDatabase(Context context) {
        mUserDAO = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserDao();
    }
}
