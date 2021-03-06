/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.testing.espresso.BasicSample;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * An {@link Activity} that gets a text string from the user and displays it back when the user
 * clicks on one of the two buttons. The first one shows it in the same activity and the second
 * one opens another activity and displays the message.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private static int PROCESSING_TIME = 200; // ms

    // The TextView used to display the message inside the Activity.
    private TextView mTextView;

    // The EditText where the user types the message.
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the listeners for the buttons.
        findViewById(R.id.changeTextAsyncTaskBtn).setOnClickListener(this);
        findViewById(R.id.changeTextUserTaskBtn).setOnClickListener(this);

        mTextView = (TextView) findViewById(R.id.textToBeChanged);
        mEditText = (EditText) findViewById(R.id.editTextUserInput);
    }

    @Override
    public void onClick(View view) {
        // Get the text from the EditText view.
        final String text = mEditText.getText().toString();

        switch (view.getId()) {
            case R.id.changeTextAsyncTaskBtn:
                // First button's interaction: set a text in a text view.
                new ChangeTextAsyncTask().execute(text);
                break;
            case R.id.changeTextUserTaskBtn:
                new ChangeTextUserTask().execute(text);
                break;
        }
    }

    private class ChangeTextAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... texts) {
            try {
                Thread.sleep(PROCESSING_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return texts[0];
        }

        @Override
        protected void onPostExecute(String text) {
            mTextView.setText(text);
        }

    }

    private class ChangeTextUserTask extends UserTask<String, Void, String> {

        @Override
        public String doInBackground(String... texts) {
            try {
                Thread.sleep(PROCESSING_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return texts[0];
        }

        @Override
        public void onPostExecute(String text) {
            mTextView.setText(text);
        }

    }

}
