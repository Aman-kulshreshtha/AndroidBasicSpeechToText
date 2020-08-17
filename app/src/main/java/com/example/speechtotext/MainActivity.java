package com.example.speechtotext;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView)findViewById(R.id.txtV);  //identified the textView by it's id so that we can grab it to put our answer text in it


    }



    public void getInput(View view){   //This method is called whenever the user presses the mic button
        /*Purpose of this method is to create something that first checks whether speech recognition is available in the device or not
         then it listens to the speech of the user ,recognzes the language and then stores the result*/



        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);              //intent is used to perform the task of speech recognition


        //we tell the intent to use the free form language model this is required by the ACTION_RECOGNIZE_SPEECH
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        //then we say that in case of no connectivity with internet use default language of the local device
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());


        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent,1);//if speech recognition is supported start listening what user says onActivityResult method is called here
        }else
            Toast.makeText(this,"Your Device doesn't supports speech input",Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch(requestCode){
            case 1:
                if(resultCode == RESULT_OK && data != null){   // if the intent successfully listened and stored the data
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS); //store result in an array list

                    txt.setText(result.get(0));//display the result in textView
                }
        }
    }
}