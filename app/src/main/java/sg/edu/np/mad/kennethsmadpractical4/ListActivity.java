package sg.edu.np.mad.kennethsmadpractical4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity implements SelectListener{
    final String TITLE = "List Activity";
    private List<User> userList;
    public int generateRan() {
        int min = 100000000; // 10 digit number
        int max = 999999999;
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
    private List<User> createUserList() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("Name1234567890", "Description 1234567890", 1, true));
        userList.add(new User("Name0987654321", "Description 0987654321", 2, false));
        userList.add(new User("Name9876543210", "Description 9876543210", 3, true));
        userList.add(new User("Name0123456789", "Description 0123456789", 4, false));
        userList.add(new User("Name2345678901", "Description 2345678901", 5, true));
        userList.add(new User("Name5678901234", "Description 5678901234", 6, false));
        userList.add(new User("Name7890123456", "Description 7890123456", 7, true));
        userList.add(new User("Name3456789012", "Description 3456789012", 8, false));
        userList.add(new User("Name4567890123", "Description 4567890123", 9, true));
        userList.add(new User("Name6789012345", "Description 6789012345", 10, false));
        userList.add(new User("Name8901234567", "Description 8901234567", 11, true));
        userList.add(new User("Name5432109876", "Description 5432109876", 12, false));
        userList.add(new User("Name2109876543", "Description 2109876543", 13, true));
        userList.add(new User("Name8765432109", "Description 8765432109", 14, false));
        userList.add(new User("Name7654321098", "Description 7654321098", 15, true));
        userList.add(new User("Name4321098765", "Description 4321098765", 16, false));
        userList.add(new User("Name3210987654", "Description 3210987654", 17, true));
        userList.add(new User("Name0987654321", "Description 0987654321", 18, false));
        userList.add(new User("Name2109876543", "Description 2109876543", 19, true));
        userList.add(new User("Name5432109876", "Description 5432109876", 20, false));

        return userList;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TITLE, "On Create!");
        setContentView(R.layout.activity_list);

        userList = createUserList();
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
                        TextView headerText = findViewById(R.id.header);
                        TextView subheadText = findViewById(R.id.subheader);
                        String newHeaderText = user.getName();
                        String newSubHeaderText = user.getDescription();
                        Intent intent = new Intent(ListActivity.this, MainActivity.class);
                        intent.putExtra("NEW_HEADER_TEXT", newHeaderText);
                        intent.putExtra("NEW_SUB_HEADER_TEXT", newSubHeaderText);
                        startActivity(intent);

                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
