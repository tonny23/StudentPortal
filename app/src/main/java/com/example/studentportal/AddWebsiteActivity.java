package com.example.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddWebsiteActivity extends AppCompatActivity {

    @BindView(R.id.title_text_input_layout)
    TextInputLayout titleInput;

    @BindView(R.id.url_text_input_layout)
    TextInputLayout urlInput;

    @BindView(R.id.btnAdd)
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_website);
        ButterKnife.bind(this);

    }

    /**
     * Check if input is empty and show error message if empty
     * @param input text input
     * @return true if input is empty, false if input is not empty
     */
    public boolean checkEmptyInput(TextInputLayout input){
        boolean empty;
        if (input.getEditText().getText().toString().length() == 0) {
            empty = true;
            input.setError(getString(R.string.input_required));
            input.setErrorEnabled(true);
        } else {
            empty = false;
            input.setErrorEnabled(false);
        }
        return empty;
    }

    @OnClick(R.id.btnAdd)
    public void addPortal(){
        if (!checkEmptyInput(urlInput) && !checkEmptyInput(titleInput)) {
            Intent data = new Intent();
            data.putExtra("title", titleInput.getEditText().getText().toString());
            data.putExtra("url", urlInput.getEditText().getText().toString());

            //Send result back to the WebsiteGridActivity
            setResult(Activity.RESULT_OK, data);

            //Finish the current activity
            finish();
        }
    }
}
