package com.practice.quizexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.udacity.droidtermsexample.DroidTermsExampleContract;
import com.practice.quizexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Cursor mData;
    private int mCurrentState,wordCol,defCol;
    private final int STATE_HIDDEN=0;
    private final int STATE_SHOWN=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick();
            }
        });

        MyAsyncTask asynctask = new MyAsyncTask();
        asynctask.execute();
    }
    
    private void onButtonClick(){
        switch (mCurrentState){
            case STATE_HIDDEN:
                showDefination();
                break;
            case STATE_SHOWN:
                nextWord();
                break;
        }

    }

    private void showDefination() {
        if (mData != null) {
            // Show the definition TextView
            binding.defination.setVisibility(View.VISIBLE);

            // Change button text
            binding.button.setText("Next Word");

            mCurrentState = STATE_SHOWN;
        }
    }

    private void nextWord() {
        if(mData!=null){
            if(!mData.moveToNext()){
                mData.moveToFirst();
            }
            binding.defination.setVisibility(View.INVISIBLE);
            binding.button.setText("Show Defination");

            binding.word.setText(mData.getString(wordCol));
            binding.defination.setText(mData.getString(defCol));
            mCurrentState=STATE_HIDDEN;

        }
    }

    public class MyAsyncTask extends AsyncTask<Void,Void, Cursor>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            ContentResolver resolver = getContentResolver();
            Cursor cursor = resolver.query(DroidTermsExampleContract.CONTENT_URI,null,null,null,null);
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            mData= cursor;
             wordCol = mData.getColumnIndex(DroidTermsExampleContract.COLUMN_WORD);
             defCol = mData.getColumnIndex(DroidTermsExampleContract.COLUMN_DEFINITION);
            nextWord();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mData.close();
    }


   


}
