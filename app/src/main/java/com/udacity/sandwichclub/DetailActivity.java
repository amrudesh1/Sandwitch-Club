package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    String name;
    String descri;
    String alsoKnown;
    String poo;
    String image_url;
    String ing;
    TextView MainName,Description,PlaceOfOrign,Ingredients,AlsoKn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        MainName =  findViewById(R.id.origin_tv);
        Description =findViewById(R.id.description);
        PlaceOfOrign = findViewById(R.id.origin);
        AlsoKn = findViewById(R.id.known_as);
        Ingredients = findViewById(R.id.ingredients);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION,DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }
        Log.i("TAG",String.valueOf(position));

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }
        name = sandwich.getMainName();
        descri = sandwich.getDescription();
        alsoKnown = sandwich.getAlsoKnownAs().get(0);
        poo =sandwich.getPlaceOfOrigin();
        ing =sandwich.getIngredients().get(0);
        image_url =sandwich.getImage();

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());


    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        MainName.setText(name);
        Description.setText(descri);
        PlaceOfOrign.setText(poo);
        Ingredients.setText(ing);
        AlsoKn.setText(alsoKnown);


    }
}
