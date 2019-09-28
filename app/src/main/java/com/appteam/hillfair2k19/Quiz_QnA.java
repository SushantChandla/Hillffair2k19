package com.appteam.hillfair2k19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appteam.model.QuestionData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quiz_QnA extends AppCompatActivity {
    Button button_1,button_2,button_3,button_4;
    TextView questionview,timeview;
    int Score;
    int counter=0;
    Intent intent;

    CountDownTimer countDownTimer;

    RequestQueue requestQueue;

    List<QuestionData> questions = new ArrayList<>();
    private static final String urlofApp = "http://api.hillffair.com/quiz/questions";
    private static final String urlofScore="http://api.hillffair.com/quiz/answers";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz__qn);
        button_1=findViewById(R.id.button);
        button_2=findViewById(R.id.button2);
        button_3=findViewById(R.id.button3);
        button_4=findViewById(R.id.button4);
        questionview=findViewById(R.id.textView6);
        timeview=findViewById(R.id.textView7);

        requestQueue = Volley.newRequestQueue(this);


        countDownTimer=new CountDownTimer(15000,1000) {
            @Override
            public void onTick(long l) {
                timeview.setText(String.valueOf(l/1000) );

            }

            @Override
            public void onFinish() {
                counter++;
                if(counter<questions.size()) {
                    UpdateQuestion();

                }
                else {

                    Score();
//                    Toast.makeText(Quiz_QnA.this,"Your Score is"+Score,Toast.LENGTH_LONG).show();


                }



            }
        };







        intent=getIntent();



        StringRequest stringRequest = new StringRequest(Request.Method.POST,urlofApp, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(Quiz_QnA.this,response,Toast.LENGTH_SHORT).show();
                Log.e("Loggerreer",response);
                JSONArray jsonArray = null;
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    jsonArray = jsonObject.getJSONArray("questions");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        QuestionData question = new QuestionData(
                                jsonArray.getJSONObject(i).getInt("id"),
                                jsonArray.getJSONObject(i).getString("ques"),
                                jsonArray.getJSONObject(i).getString("option1"),
                                jsonArray.getJSONObject(i).getString("option2"),
                                jsonArray.getJSONObject(i).getString("option3"),
                                jsonArray.getJSONObject(i).getString("option4"));
                        questions.add(question);


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("Error",e.getMessage());
                    }
                }
                UpdateQuestion();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Loggerreer",error.getMessage());

            }
        }){
            @Override
            protected Map<String,String> getParams()  {
                Map<String,String> params = new HashMap<>();
                params.put("category",String.valueOf(intent.getIntExtra("Category",0)));
                return params;
            }
        };

        requestQueue.add(stringRequest);





        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questions.get(counter).setOption_chosen(1);
                countDownTimer.onFinish();


            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questions.get(counter).setOption_chosen(2);
                countDownTimer.onFinish();
            }
        });
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questions.get(counter).setOption_chosen(3);
                countDownTimer.onFinish();
            }
        });
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questions.get(counter).setOption_chosen(4);
                countDownTimer.onFinish();
            }
        });









    }







    private void UpdateQuestion(){
        questionview.setText(questions.get(counter).getQuestion());
        button_1.setText(questions.get(counter).getOption_1());
        button_2.setText(questions.get(counter).getOption_2());
        button_3.setText(questions.get(counter).getOption_3());
        button_4.setText(questions.get(counter).getOption_4());
        countDownTimer.start();




    }















    private void Score() {



        StringRequest stringRequest = new StringRequest(Request.Method.POST,urlofScore, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Loggerreer",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    Toast.makeText(Quiz_QnA.this,String.valueOf(jsonObject.getInt("score")),Toast.LENGTH_SHORT).show();
                    Score = jsonObject.getInt("score");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Loggerreer",error.getMessage());

            }
        }){
            @Override
            protected Map<String,String> getParams()  {
                Map<String,String> params = new HashMap<>();
                Map<String,String> answer = new HashMap<>();

                for(int i=0; i<questions.size();i++){
                answer.put("'id' : '"+questions.get(i).getQuestionid()+"'","'ans' : '"+questions.get(i).getOption_chosen()+"'");
                }
                params.put("firebase_id","12345");
                params.put("answers",answer.toString());
                Log.e("SentRequest",params.toString());
                return params;
            }
        };

        requestQueue.add(stringRequest);


    }











/*

        JSONObject jsonObjectA = new JSONObject();

        List <JSONObject> jsonObjectAnswer = new ArrayList<>();

        for(int i=0;i<questions.size();i++){
            JSONObject jsonObjectanswers = new JSONObject();

            try {
                jsonObjectanswers.put("id",questions.get(i).getQuestionid());
                jsonObjectanswers.put("answer",questions.get(i).getOption_chosen());
                jsonObjectAnswer.add(jsonObjectanswers);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            jsonObjectA.put("firebase_id","UID");
            jsonObjectA.put("answers",jsonObjectAnswer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        volleyService.postJsonDataVolley("POSTAnswer","/quiz/answers",jsonObjectA);

    }
*/








}
