package sg.edu.np.mad.kennethsmadpractical5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity implements SelectListener{
    final String TITLE = "List Activity";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TITLE, "On Create!");
        setContentView(R.layout.activity_list);

        // Retrieve the userList from DBHandler
        DBHandler dbHandler = new DBHandler(this, "users.db", null, 1);
        List<User> userList = dbHandler.getUsers();
        Log.v(TITLE, "On Start!");
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BrandsAdapter(this, userList, this));

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

    @Override
    public void onItemClicked(User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
        builder.setTitle("Profile")
                .setMessage(user.getName())
                .setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do something when View is clicked
                        String newHeaderText = user.getName();
                        String newSubHeaderText = user.getDescription();
                        Boolean fol = user.isFollowed();
                        Intent intent = new Intent(ListActivity.this, MainActivity.class);
                        intent.putExtra("NEW_HEADER_TEXT", newHeaderText);
                        intent.putExtra("NEW_SUB_HEADER_TEXT", newSubHeaderText);
                        intent.putExtra("USER_ID", user.getId()); // Add user ID to the intent
                        startActivity(intent);

                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
