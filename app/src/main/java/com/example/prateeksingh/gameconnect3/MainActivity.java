package com.example.prateeksingh.gameconnect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//0=otrans 1=xtrans 2=empty

    int actpla= 0;
    int gamestate[]={2,2,2,2,2,2,2,2,2};
    int winpos[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean active=true;
    TextView tv;

    Button pabutton;

    public void dropin(View v)
    {
        ImageView counter= (ImageView)v;
        int tapped=Integer.parseInt(counter.getTag().toString());

        tv=(TextView)findViewById(R.id.textView);
        //tv.setText("Turn : Player 1");
        if(gamestate[tapped]==2 && active ) {
            gamestate[tapped]=actpla;
            counter.setTranslationY(-1500);

            if (actpla == 0) {
                counter.setImageResource(R.drawable.otrans);
                tv.setText("Turn : Player 2");
                actpla = 1;//c++;
            } else {
                counter.setImageResource(R.drawable.xtrans);
                tv.setText("Turn : Player 1");
                actpla = 0;//c++;
            }

            counter.animate().translationYBy(1500).rotation(1800).setDuration(500);
            String winner="";
            for (int[] wp : winpos) {

                if (gamestate[wp[0]] == gamestate[wp[1]] && gamestate[wp[1]] == gamestate[wp[2]] && gamestate[wp[0]] != 2) {
                    active=false;


                    if (actpla == 1)
                        winner = "Player 1 has won";
                    if (actpla == 0)
                        winner = "Player 2 has won";


                    pabutton=(Button) findViewById(R.id.button);
                    tv.setText(winner);
                    pabutton.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    active=false;
                }
            }
            if(!mycon(gamestate,2) && active)
            {
                active = false;
                tv.setText("There is no winner");
                pabutton.setVisibility(View.VISIBLE);
                tv.setVisibility(View.VISIBLE);

            }
        }

    }
    public boolean mycon(int[] arr, int item) {
        for (int n : arr) {
            if (item == n) {
                return true;
            }
        }
        return false;
    }

    public void playAgain(View v)
    {
        Button pabutton=(Button) findViewById(R.id.button);
        tv=(TextView)findViewById(R.id.textView);

        pabutton.setVisibility(View.INVISIBLE);
        //tv.setVisibility(View.INVISIBLE);
        GridLayout gl=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gl.getChildCount();i++)
        {
            ImageView counter=(ImageView)gl.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;

        }
        actpla= 0;
        tv.setText("Turn : Player 1");
        active=true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.textView);
        tv.setText("Turn : Player 1");


    }
}
