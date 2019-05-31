    package com.mohammedthowfiq.quizzler;

    import android.content.DialogInterface;
    import android.support.v7.app.AlertDialog;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.ProgressBar;
    import android.widget.TextView;
    import android.widget.Toast;

    public class MainActivity extends AppCompatActivity {



        //TODO: Declare member variables here:

            Button mtruebutton;
            Button mfalsebutton;
            TextView mQuestionTextView;
            int mIndex;
            int mQuestion;
            TextView mScoreTextView;
            ProgressBar mProgressBar;
            int mScore;



        //TODO: Uncomment to create question bank



        private TrueFalse[] mQuestionBank= new TrueFalse[]{

                new TrueFalse(R.string.question_1,true),
                new TrueFalse(R.string.question_2,true),
                new TrueFalse(R.string.question_3,true),
                new TrueFalse(R.string.question_4,true),
                new TrueFalse(R.string.question_5,true),
                new TrueFalse(R.string.question_6,false),
                new TrueFalse(R.string.question_7,true),
                new TrueFalse(R.string.question_8,false),
                new TrueFalse(R.string.question_9,true),
                new TrueFalse(R.string.question_10,true),
                new TrueFalse(R.string.question_11,false),
                new TrueFalse(R.string.question_12,false),
                new TrueFalse(R.string.question_13,true)


        };


        // TODO: Declare Constants here
        final int PROGRESS_BAR_INCREMENT=(int)Math.ceil(100/mQuestionBank.length);



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            if(savedInstanceState != null)
            {
               mScore = savedInstanceState.getInt("StringKey");
               mIndex = savedInstanceState.getInt("IndexKey");


            }
            else
            {
                mIndex=0;
                mScore=0;

            }

            mtruebutton =(Button)findViewById(R.id.true_button);
            mfalsebutton=(Button)findViewById(R.id.false_button);
            mQuestionTextView=(TextView)findViewById(R.id.question_text_view);
            mScoreTextView=(TextView)findViewById(R.id.score);
            mProgressBar=(ProgressBar)findViewById(R.id.progress_bar);

            int mQuestion= mQuestionBank[mIndex].getQuestionID();

            mQuestionTextView.setText(mQuestion);

            mScoreTextView.setText("Score"+mScore+"/"+mQuestionBank.length);



            mtruebutton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   Toast.makeText(getApplicationContext(),"You did it!",Toast.LENGTH_SHORT).show();

                   checkAnswer(true);
                   updateQuestion();


               }
           });

            mfalsebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getApplicationContext(),"Wrong",Toast.LENGTH_SHORT).show();

                    checkAnswer(false);
                    updateQuestion();
                }
            });


        }

        private  void updateQuestion(){



            mIndex=(mIndex+1)% mQuestionBank.length; // Incrementing the Array Index and we checking if it cross more than the value in the Array index then dividing it by the Array.length of the array to get appropriate solution.

            if(mIndex==0)
            {
                AlertDialog.Builder alert= new AlertDialog.Builder(this);
                alert.setTitle("Game Over");
                alert.setCancelable(false);
                alert.setMessage("You scored"+mScore+"points!");
                alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();

                    }
                });
                alert.show();
            }

            mQuestion=mQuestionBank[mIndex].getQuestionID();
            mQuestionTextView.setText(mQuestion);
            mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
            mScoreTextView.setText("Score"+mScore+"/"+mQuestionBank.length);

        }

        private void checkAnswer(boolean userSelection){

            boolean correctAnswer=mQuestionBank[mIndex].isAnswer();
            if( userSelection == correctAnswer){

                Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();
                mScore = mScore+1;
            }
            else
            {

                Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onSaveInstanceState(Bundle OutState)
        {

            super.onSaveInstanceState(OutState);

            OutState.putInt("ScoreKey",mScore);
            OutState.putInt("IndexKey",mIndex);

        }



    }
