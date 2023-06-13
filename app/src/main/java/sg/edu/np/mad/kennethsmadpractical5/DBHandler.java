package sg.edu.np.mad.kennethsmadpractical5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DBHandler extends SQLiteOpenHelper {

    String title = "DBHandler";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_FOLLOWED = "followed";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FOLLOWED + " INTEGER"
                + ")";
        Log.i(title, CREATE_USERS_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
        insertData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
    }

    public int generateRan() {
        int min = 100000000; // Minimum 9-digit number
        int max = 999999999; // Maximum 9-digit number
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public List<User> makeUsers(int count) {
        List<User> userList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String name = "Name" + generateRan();
            String description = "Description" + generateRan();
            int id = i + 1;
            boolean followed = random.nextBoolean();

            User user = new User(name, description, id, followed);
            userList.add(user);
            Log.v(title, "User: " + user.getName() + ", ID: " + user.getId() + ", Followed: " + user.isFollowed());
        }

        return userList;
    }

    // To make the 20 initialised users
    private void insertData(SQLiteDatabase db) {
        List<User> userList = makeUsers(20);

        for (User user : userList) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, user.getName());
            values.put(COLUMN_DESCRIPTION, user.getDescription());
            values.put(COLUMN_ID, user.getId());
            values.put(COLUMN_FOLLOWED, user.isFollowed() ? 1 : 0);

            db.insert(TABLE_USERS, null, values);
        }
    }


    public User findUser(int id) {

        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_ID + "=\"" + id + "\"";
        Log.i(title, "Query: " + query);
        User queryResult = new User();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            queryResult.setName(cursor.getString(0));
            queryResult.setDescription(cursor.getString(1));
            queryResult.setId(cursor.getInt(2));

            int followint = cursor.getInt(3);
            boolean followed;
            if (followint == 0) {
                followed = false;
                Log.v(title, "routed to false");
            }
            else {
                followed = true;
                Log.v(title, "routed to true");
            }
            queryResult.setFollowed(followed);
        } else {
            queryResult = null;
        }
        db.close();
        return queryResult;
    }

    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                String description = cursor.getString(1);
                int userId = cursor.getInt(2);
                int followedInt = cursor.getInt(3);

                boolean followed = (followedInt == 1);

                User user = new User(name, description, userId, followed);
                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();


        return userList;
    }

    public void updateUser(int userID,boolean follow) {
        User selectedUser = findUser(userID);
        if (selectedUser != null) {
            // Get a writable database
            SQLiteDatabase db = this.getWritableDatabase();

            // Create a ContentValues object to hold the new values
            ContentValues values = new ContentValues();
            values.put(COLUMN_FOLLOWED, follow);
            // Perform the update operation
            db.update(TABLE_USERS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(userID)});

            // Close the database
            db.close();
        }
    }
}