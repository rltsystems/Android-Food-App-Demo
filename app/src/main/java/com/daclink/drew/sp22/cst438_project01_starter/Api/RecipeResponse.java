package com.daclink.drew.sp22.cst438_project01_starter.Api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RecipeResponse {

    @SerializedName("offset")
    @Expose
    public int offset;
    @SerializedName("number")
    @Expose
    public int number;
    @SerializedName("results")
    @Expose
    public List<Result> results = null;
    @SerializedName("totalResults")
    @Expose
    public int totalResults;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
