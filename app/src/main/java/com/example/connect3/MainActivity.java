package com.example.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout layout;
    GridLayout grid;
    TextView winnermessage;
    ImageView counter;
    //yellow = 0 , red = 1
    int activePlayer = 0;
    boolean gameIsActive = true;
    //2 means unplayes
    int[] game ={2,2,2,2,2,2,2,2,2};
    //winning positions
    int [][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public  void dropIn(View view)
    {
         counter = (ImageView)view;

        int tapCount = Integer.parseInt(counter.getTag().toString());

        if(game[tapCount]==2 && gameIsActive ) {
            game[tapCount]=activePlayer;
            counter.setTranslationY(-1000f);


            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;

            }
            counter.animate().translationYBy(1000f).rotation(1800).setDuration(300);
            for(int win[] :winningPositions )
            {
             //   gameIsActive = false;
                if(game[win[0]]==game[win[1]] &&
                    game[win[1]]==game[win[2]]&&
                game[win[0]]!=2){
                    gameIsActive = false;
                   String winner = "Red";
                   if(game[win[0]]==0){
                       winner = "Yellow";
                   }
                     winnermessage = (TextView)findViewById(R.id.winnermessage);
                    winnermessage.setText(winner + " has won!");
                     layout = (LinearLayout)findViewById(R.id.playagainlayout);
                    layout.setVisibility(View.VISIBLE);
                }
            }

        }else{
            boolean gameover= true;

            for(int counterState : game ){

                if(counterState == 2)
                {
                    gameover=false;
                }

            }
            if(gameover)
            { TextView winnermessage = (TextView)findViewById(R.id.winnermessage);
                winnermessage.setText("It's a draw");
                LinearLayout layout = (LinearLayout)findViewById(R.id.playagainlayout);
                layout.setVisibility(View.VISIBLE);

            }
        }

    }
        public void clickFunction(View view)
        {
          gameIsActive=true;
            //  System.out.println("Hello");
             layout = (LinearLayout)findViewById(R.id.playagainlayout);
          layout.setVisibility(View.INVISIBLE);
           activePlayer=0;
            for(int i=0; i< game.length;i++)
             {
                game[i]=2;
             }
              grid = (GridLayout)findViewById(R.id.grid);
             for(int i=0;i<grid.getChildCount();i++){
                     ((ImageView)grid.getChildAt(i)).setImageResource(0);
                     }
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
