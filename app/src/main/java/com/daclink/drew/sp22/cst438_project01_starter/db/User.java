package com.daclink.drew.sp22.cst438_project01_starter.db;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String username;
    private String pass;

    //recipeCount will be a test value to validate database functions until we implement recipes
    private int recipeCount;

    @Ignore
    public User(){ }

    public User(int id, String username, String pass, int recipeCount){
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.recipeCount = recipeCount;
    }

    @Ignore
    public User(String username, String pass, int recipeCount){
        this.username = username;
        this.pass = pass;
        this.recipeCount = recipeCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getRecipeCount() {
        return recipeCount;
    }

    public void setRecipeCount(int recipeCount) {
        this.recipeCount = recipeCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return id == that.id && Double.compare(that.recipeCount, recipeCount) == 0 && username.equals(that.username) && pass.equals(that.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, pass, recipeCount);
    }
}
