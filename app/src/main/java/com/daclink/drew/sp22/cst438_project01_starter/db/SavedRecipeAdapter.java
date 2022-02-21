package com.daclink.drew.sp22.cst438_project01_starter.db;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.daclink.drew.sp22.cst438_project01_starter.R;

import java.util.List;

public class SavedRecipeAdapter extends RecyclerView.Adapter<SavedRecipeAdapter.ViewHolder>{
    private List<Recipe> recipes;

    private UserDao mUserDAO;
    private User mUser;
    private Context context;

    public SavedRecipeAdapter(Context context, int userId){
        getDatabase(context);

        mUser = new User();
        Log.i("TEST", String.valueOf(userId));
        mUser = mUserDAO.getUserById(userId);

        recipes = mUser.getRecipes();
        this.context = context;
}

    @Override
    public SavedRecipeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        View contactView;
        LayoutInflater inflater = LayoutInflater.from(context);
        contactView = inflater.inflate(R.layout.recipelist_layout, parent, false);
        ViewHolder viewHolder  = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SavedRecipeAdapter.ViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);


        TextView textView = holder.recipeInfo;

        // made this a little more neat :)
        String s = recipe.displayRecipeBar();
        textView.setText(s);

        textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: The commented code is for the list of savable recipes
                    AlertDialog.Builder alert = new AlertDialog.Builder(textView.getContext());
                    alert.setMessage("Description: " + recipe.getRecipeDescription());

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

    private void getDatabase(Context context) {
        mUserDAO = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserDao();
    }
}
