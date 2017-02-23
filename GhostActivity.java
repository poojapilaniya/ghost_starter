package com.example.pooja.ghost_starter;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class GhostActivity extends AppCompatActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    //private GhostDictionary dictionary;
    private boolean userTurn = false;
    private Random random = new Random();
    TextView tv1, tv2;
    Button bt1, bt2;
   // SimpleDictionary dictionary;
    private FastDictionary dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost);
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        bt1 = (Button) findViewById(R.id.button);
        bt2 = (Button) findViewById(R.id.button2);
       bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt1();

            }
        });
       /* bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt2();

            }
        });*/
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            dictionary = new FastDictionary(inputStream);
            //dictionary = new FastDictionary(inputStream);
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }
        onStart(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     *
     * @param view
     * @return true
     */
    public boolean onStart(View view) {
        userTurn = random.nextBoolean();
        tv1.setText("");
        if (userTurn) {
            tv2.setText(USER_TURN);
        } else {
            tv2.setText(COMPUTER_TURN);
            computerTurn();
        }
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        char temp = (char) event.getUnicodeChar();
        if (temp >= 97 && temp <= 122) {
            String temp2 = tv1.getText().toString();
            temp2 = temp2 + temp;
            tv1.setText(temp2);
            tv2.setText("Computer Turn");
            computerTurn();
            return true;
        } else {
            Toast.makeText(this, "Please Enter a Valid Keyword", Toast.LENGTH_SHORT).show();
        }
        return super.onKeyUp(keyCode, event);
    }

    private void restartMethod() {
        onStart(null);
    }

    private void computerTurn() {
        String temp_text = tv1.getText().toString();
        if (temp_text.length() >= 4 && dictionary.isWord(temp_text)) {
            Toast.makeText(this, "Computer Turn", Toast.LENGTH_LONG).show();
        } else {
            String word = dictionary.getAnyWordStartingWith(temp_text);
            if (word == null) {
                Toast.makeText(this, "Computer Won", Toast.LENGTH_SHORT).show();
            } else {
                if (word.equals(temp_text)) {
                    Toast.makeText(this, "Computer Won", Toast.LENGTH_SHORT).show();
                } else {
                    char ch[] = word.toCharArray();
                    int len = temp_text.length();
                    temp_text = temp_text + word.charAt(len);
                    tv1.setText(temp_text);
                }

            }
        }
    }

    void bt1() {
      String temp =  tv1.getText().toString();
        if(temp.length()>=4 && dictionary.isWord(temp)){
            Toast.makeText(GhostActivity.this,"you win",Toast.LENGTH_SHORT).show();
        }else{
            String word = dictionary.getAnyWordStartingWith(temp);
            if(word != null){
                Toast.makeText(GhostActivity.this,"computer wins word is"+word,Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(GhostActivity.this,"you wins no word can be made with"+word,Toast.LENGTH_SHORT).show();
            }
        }
    }

   /* void bt2() {
        tv2.setText("Hello");
}*/
}