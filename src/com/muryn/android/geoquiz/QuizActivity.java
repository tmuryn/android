package com.muryn.android.geoquiz;

import android.R.integer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends ActionBarActivity {
	
	private Button mTrueButton;
	private Button mFalseButton;
	private Button mNextButton;
	private TextView mQuestionTextView;

	private TrueFalse[] mQuestionBank = new TrueFalse[] {
			new TrueFalse(R.string.question_oceans, true),
			new TrueFalse(R.string.question_mideast, false),
			new TrueFalse(R.string.question_africa, false),
			new TrueFalse(R.string.question_americas, true),
			new TrueFalse(R.string.question_asia, true),
			new TrueFalse(R.string.question_turkey, false)
	};
	
	private int mCurrentIndex = 0;
	
	private void updateQuestion() {
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
	}
	
	private void checkAnswer(boolean userPressedTrue) {
		Toast.makeText(this, mQuestionBank[mCurrentIndex].isTrueQuestion() == userPressedTrue ? R.string.correct_toast
						: R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
	}
	
	private void moveToNextQuestion() {
		mCurrentIndex = mCurrentIndex == (mQuestionBank.length - 1)? 0 : ++mCurrentIndex;
	}
	
	private void moveToPreviousQuestion() {
		mCurrentIndex = mCurrentIndex == 0 ? mQuestionBank.length - 1: --mCurrentIndex;
	}
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);        
        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				checkAnswer(true);
			}
		});

        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				checkAnswer(false);
			}
		});
        
        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				moveToNextQuestion();
				updateQuestion();
 			}
		});
        
        mQuestionTextView.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				moveToNextQuestion();
				updateQuestion();
			}
		});
        updateQuestion();
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
