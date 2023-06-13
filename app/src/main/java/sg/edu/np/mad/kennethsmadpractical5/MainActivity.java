package sg.edu.np.mad.kennethsmadpractical5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String TITLE = "Main Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TITLE, "On Create!");

        Button follow = findViewById(R.id.btnfollow);
        TextView headerText = findViewById(R.id.header);
        String newHeaderText = getIntent().getStringExtra("NEW_HEADER_TEXT");
        TextView subheaderText = findViewById(R.id.subheader);
        String newsubHeaderText = getIntent().getStringExtra("NEW_SUB_HEADER_TEXT");
        int userId = getIntent().getIntExtra("USER_ID", -1);
        DBHandler dbHandler = new DBHandler(this, "users.db", null, 1);
        User user = dbHandler.findUser(userId);

        headerText.setText(newHeaderText);
        subheaderText.setText(newsubHeaderText);
        if (user.isFollowed()){
            follow.setText(R.string.followed);
        }
        else{
            follow.setText(R.string.follows);
        }
        Log.v(TITLE, "User ID: " + user.getId());
        Log.v(TITLE, "User Follow Status: " + user.isFollowed());





        follow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (user.isFollowed()) {
                    follow.setText(R.string.follows);
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                    user.setFollowed(false);
                }
                else if(user.isFollowed() == false){
                    follow.setText(R.string.followed);
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                    user.setFollowed(true);
                }

                Log.v(TITLE, "Follow button is clicked. Follow status: " + user.isFollowed());
                dbHandler.updateUser(user.getId(), user.isFollowed());
            }
        });


        Button messages = findViewById(R.id.btnmessage);
        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TITLE, "messages is pressed");
                Intent myintent = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(myintent);


            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.v(TITLE, "On Start!");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TITLE, "On Pause!");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.v(TITLE, "On Resume!");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TITLE, "On Stop!");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.v(TITLE, "On Destroy!");
    }
}

