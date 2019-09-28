package com.appteam.hillfair2k19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

//import com.appteam.adapters.CategoriesAdapter;
//import com.appteam.listener.RecyclerItemClickListener;
//import com.appteam.model.CatagoriesData;
import com.appteam.model.QuestionData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;

public class QuizCategories extends AppCompatActivity {
    public SharedPreferences categoriesdata;
    public SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_categories);

        categoriesdata= getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        editor = categoriesdata.edit();








        LinearLayout Science_layout = findViewById(R.id.view1);
        LinearLayout Anime_layout = findViewById(R.id.view2);
        LinearLayout WebSeries_layout = findViewById(R.id.view3);
        LinearLayout NIT_layout = findViewById(R.id.view4);
        LinearLayout Sports_layout = findViewById(R.id.view5);
        LinearLayout Mythology_layout = findViewById(R.id.view6);
        LinearLayout Movies_layout = findViewById(R.id.view7);





        if(categoriesdata.getBoolean("Science",false)){
            Science_layout.setVisibility(View.GONE);
        }

        //if(categoriesdata.getBoolean("Anime",false)){
            Anime_layout.setVisibility(View.GONE);
       // }
        if(categoriesdata.getBoolean("Movies",false)){
            Movies_layout.setVisibility(View.GONE);
        }
        if(categoriesdata.getBoolean("WebSeries",false)){
            WebSeries_layout.setVisibility(View.GONE);
        }
        if(categoriesdata.getBoolean("Mythology",false)){
            Mythology_layout.setVisibility(View.GONE);
        }
        if(categoriesdata.getBoolean("Sports",false)){
            Sports_layout.setVisibility(View.GONE);
        }
        if(categoriesdata.getBoolean("Nit",false)){
            NIT_layout.setVisibility(View.GONE);
        }





        Science_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putBoolean("Science",true);
                editor.apply();


                Intent intent=new Intent(QuizCategories.this,Quiz_QnA.class);
                intent.putExtra("Category",1);
                startActivity(intent);
                finish();
            }
        });

        Anime_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putBoolean("Anime",true);
                editor.apply();

                Intent intent=new Intent(QuizCategories.this,Quiz_QnA.class);
                intent.putExtra("Category",2);
                startActivity(intent);
                finish();

            }
        });


        WebSeries_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putBoolean("WebSeries",true);
                editor.apply();

                Intent intent=new Intent(QuizCategories.this,Quiz_QnA.class);
                intent.putExtra("Category",3);
                startActivity(intent);
                finish();

            }
        });


        NIT_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putBoolean("Nit",true);
                editor.apply();

                Intent intent=new Intent(QuizCategories.this,Quiz_QnA.class);
                intent.putExtra("Category",4);
                startActivity(intent);
                finish();

            }
        });


        Sports_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putBoolean("Sports",true);
                editor.apply();


                Intent intent=new Intent(QuizCategories.this,Quiz_QnA.class);
                intent.putExtra("Category",5);
                startActivity(intent);
                finish();

            }
        });


        Mythology_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putBoolean("Mythology",true);
                editor.apply();


                Intent intent=new Intent(QuizCategories.this,Quiz_QnA.class);
                intent.putExtra("Category",6);
                startActivity(intent);
                finish();

            }
        });


        Movies_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putBoolean("Movies",true);
                editor.apply();


                Intent intent=new Intent(QuizCategories.this,Quiz_QnA.class);
                intent.putExtra("Category",7);
                startActivity(intent);
                finish();

            }
        });






    }
}
