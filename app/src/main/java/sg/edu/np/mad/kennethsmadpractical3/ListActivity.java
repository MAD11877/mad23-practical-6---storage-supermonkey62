package sg.edu.np.mad.kennethsmadpractical3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class ListActivity extends AppCompatActivity {
    final String TITLE = "List Activity";

    public int generateRan() {
        int min = 100000000; // 10 digit number
        int max = 999999999;
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TITLE, "On Create!");
        setContentView(R.layout.activity_list);

        ImageView madimage = findViewById(R.id.madness);
        madimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
                builder.setTitle("Profile")
                        .setMessage("MADness")
                        .setPositiveButton("View", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // do something when View is clicked
                                TextView headerText = findViewById(R.id.header);
                                String newHeaderText = " MAD " + generateRan();
                                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                                intent.putExtra("NEW_HEADER_TEXT", newHeaderText);
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
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
