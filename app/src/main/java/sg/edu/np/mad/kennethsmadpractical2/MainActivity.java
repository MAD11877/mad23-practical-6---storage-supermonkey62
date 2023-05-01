package sg.edu.np.mad.kennethsmadpractical2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final String TITLE = "Main Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TITLE, "On Create!");
        TextView header = findViewById(R.id.header);
        header.setTextSize(36);
        header.setTypeface(null, Typeface.BOLD);
        // Preset User
        User Kenneth = new User("Kenneth", "Male, 1.7m, studies at Ngee Ann Polytechnic", 001, false);
        Button follow = findViewById(R.id.btnfollow);
        if (Kenneth.isFollowed()) {
            follow.setText("Followed");
        } else {
            follow.setText("Follow");
        }
        follow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (Kenneth.isFollowed()) {
                    Kenneth.setFollowed(false);
                    follow.setText("Follow");
                } else {
                    Kenneth.setFollowed(true);
                    follow.setText("Followed");
                }
                Log.v(TITLE, "Follow button is clicked. Follow status: " + Kenneth.isFollowed());
            }
        });


        Button messages = findViewById(R.id.btnmessage);
        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TITLE, "messages is pressed");

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


