package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class JsonUtils {
    static String MainName;
    String Description;
    String Image_URL;
    String Ingredients;

    public static Sandwich parseSandwichJson(String json)  {
        Sandwich sandwich = new Sandwich();
        List<String> setIngred = new ArrayList<String>();
        List<String> AlsoKnown = new ArrayList<String>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> iter = jsonObject.keys();
            while (iter.hasNext())
            {
                sandwich.setDescription(jsonObject.getString("description"));
                sandwich.setImage(jsonObject.getString("image"));
                sandwich.setPlaceOfOrigin(jsonObject.getString("placeOfOrigin"));

                String key = iter.next();
                if ( jsonObject.get(key) instanceof JSONObject ) {
                    Log.i("TAG",String.valueOf(key));
                    JSONObject sandJson = new JSONObject(jsonObject.get(key).toString());
                    sandwich.setMainName(sandJson.getString("mainName"));
                    setIngred.add(jsonObject.getString("ingredients"));
                    AlsoKnown.add(sandJson.getString("alsoKnownAs"));
                    sandwich.setIngredients(setIngred);
                    sandwich.setAlsoKnownAs(AlsoKnown);


                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich ;
    }


}
