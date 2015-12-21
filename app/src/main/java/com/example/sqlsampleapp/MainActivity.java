package com.example.sqlsampleapp;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sqlsampleapp.db.DbOpenHelper;
import com.example.sqlsampleapp.db.table.BillTable;
import com.example.sqlsampleapp.db.table.BillUserOwesTable;
import com.example.sqlsampleapp.db.table.BillUserPaidTable;
import com.example.sqlsampleapp.db.table.GroupTable;
import com.example.sqlsampleapp.db.table.GroupUserTable;
import com.example.sqlsampleapp.db.table.NoteTable;
import com.example.sqlsampleapp.db.table.UserTable;
import com.example.sqlsampleapp.model.Bill;
import com.example.sqlsampleapp.model.Group;
import com.example.sqlsampleapp.model.GroupWrapper;
import com.example.sqlsampleapp.model.Note;
import com.example.sqlsampleapp.model.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Inject
    SQLiteOpenHelper sqLiteOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ((SampleApplication) getApplication())
                .getApplicationComponent()
                .inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public String loadJsonFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @OnClick(R.id.store_data)
    public void storeData() {
        if (deleteDatabase(DbOpenHelper.DB_NAME)) {
            Timber.d("Removed database");
        } else {
            Timber.d("Couldn't remove database");
        }
        // Deserialize data to Group object using gson
        Group group = new Gson().fromJson(loadJsonFromAsset(), GroupWrapper.class).group;
        Timber.d(group.toString());

        storeData(group);
    }

    private void storeData(Group group) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.insert(
                    GroupTable.TABLE_NAME,
                    null,
                    new GroupTable.ContentValuesBuilder()
                            .id(group.id)
                            .name(group.name)
                            .balance(group.balance)
                            .build()
            );
            for (User user : group.users) {
                db.insert(
                        UserTable.TABLE_NAME,
                        null,
                        new UserTable.ContentValuesBuilder()
                                .id(user.id)
                                .name(user.name)
                                .email(user.email)
                                .memberSince(user.memberSince)
                                .avatar(user.avatar)
                                .build()
                );
                db.insert(
                        GroupUserTable.TABLE_NAME,
                        null,
                        new GroupUserTable.ContentValuesBuilder()
                                .idGroup(group.id)
                                .idUser(user.id)
                                .balance(user.balance)
                                .build()
                );
            }
            for (Bill bill : group.bills) {
                db.insert(
                        BillTable.TABLE_NAME,
                        null,
                        new BillTable.ContentValuesBuilder()
                                .id(bill.id)
                                .idGroup(group.id)
                                .title(bill.title)
                                .amount(bill.amount)
                                .date(bill.date)
                                .createdBy(bill.createdBy)
                                .build()
                );
                for (User user : bill.users) {
                    if (user.id == bill.createdBy) {
                        db.insert(
                                BillUserPaidTable.TABLE_NAME,
                                null,
                                new BillUserPaidTable.ContentValuesBuilder()
                                        .idBill(bill.id)
                                        .idUser(user.id)
                                        .paid(user.paid)
                                        .build()
                        );
                    } else {
                        db.insert(
                                BillUserOwesTable.TABLE_NAME,
                                null,
                                new BillUserOwesTable.ContentValuesBuilder()
                                        .idBill(bill.id)
                                        .idUser(user.id)
                                        .owes(user.owes)
                                        .build()
                        );
                    }
                }
                for (Note note : bill.notes) {
                    db.insert(
                            NoteTable.TABLE_NAME,
                            null,
                            new NoteTable.ContentValuesBuilder()
                                    .id(note.id)
                                    .idBill(bill.id)
                                    .idUser(note.user.id)
                                    .message(note.message)
                                    .created(note.created)
                                    .build()
                    );
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    /**
     * Currently only selects notes with the corresponding users, however I'd like this function to
     * actually create a group object in approximately the same format as the one that was used to
     * store the information
     */
    @OnClick(R.id.read_data)
    public void readData() {
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                String.format(
                        "SELECT * FROM %s LEFT OUTER JOIN %s ON %s.%s = %s.%s",
                        NoteTable.TABLE_NAME,
                        UserTable.TABLE_NAME,
                        NoteTable.TABLE_NAME, NoteTable.ID_USER,
                        UserTable.TABLE_NAME, UserTable.ID
                ),
                null
        );
        DatabaseUtils.dumpCursor(cursor);
        cursor.close();
        db.close();
    }
}
