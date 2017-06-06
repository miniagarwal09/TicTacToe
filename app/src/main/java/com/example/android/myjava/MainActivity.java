package com.example.android.myjava;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public int counter=0;
    boolean gameIsActive=true;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] won={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int called=0;
    public void drop(View view) {

        ImageView ball = (ImageView) view;


        if (ball.getDrawable() == null && gameIsActive) {
            int tapped=Integer.parseInt(ball.getTag().toString());
            ball.setTranslationY(-1000f);
            //gameState:2 If not tapped
            //gameState:1 for Yellow
            //gameState:0 for Red
            gameState[tapped]=counter;
            if (counter == 0) {
                ball.setImageResource(R.drawable.red);
                System.out.println(tapped+" 1 updated to:"+gameState[tapped]+"\n");
                counter++;

            }
            else {
                ball.setImageResource(R.drawable.yellow);
                System.out.println(tapped+" 2 updated to:"+gameState[tapped]+"\n");
                counter = 0;
            }
            ball.animate().translationYBy(1000f).rotation(360).setDuration(100);
            for(int[] win:won) {
               /* System.out.println("Checking");
                System.out.println(gameState[win[0]]);
                System.out.println(gameState[win[1]]);
                System.out.println(gameState[win[2]]);*/
                if (gameState[win[0]] == gameState[win[1]] && gameState[win[1]] == gameState[win[2]] && gameState[win[0]] != 2) {
                    gameIsActive=false;
                    System.out.println(gameState[win[0]] + " has Won");
                    String message="";
                    if(gameState[win[0]]==0){
                       message="Player Red Won";
                    }
                    else
                    {
                        message="Player Yellow has Won";
                    }
                    TextView textView=(TextView)findViewById(R.id.winnertext);
                    textView.setText(message);
                    LinearLayout linearLayout=(LinearLayout)findViewById(R.id.playagainlayout);
                    linearLayout.setVisibility(View.VISIBLE);
                }

                }
            System.out.println("called:"+called++);
            if(called==9 && gameIsActive){
                TextView textView=(TextView)findViewById(R.id.winnertext);
                String drawMessage="Its a Draw";
                textView.setText(drawMessage);
                LinearLayout linearLayout=(LinearLayout)findViewById(R.id.playagainlayout);
                linearLayout.setVisibility(View.VISIBLE);

        }
}
}
public void playAgain(View View){
    recreate();

}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }
}
