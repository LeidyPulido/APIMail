package com.example.leidy.leacontactos;

import android.content.ContentResolver;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class ChoosePhoneActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_phone);
        setTitle("Choose a phone");



        // Query: contacts with phone shorted by name
        Cursor mCursor = getContentResolver().query(
                Data.CONTENT_URI,
                new String[] { Data._ID, Data.DISPLAY_NAME, Phone.NUMBER,
                        Phone.TYPE },
                Data.MIMETYPE + "='" + Phone.CONTENT_ITEM_TYPE + "' AND "
                        + Phone.NUMBER + " IS NOT NULL", null,
                Data.DISPLAY_NAME + " ASC");

        startManagingCursor(mCursor);

        // Setup the list
        ListAdapter adapter = new SimpleCursorAdapter(this, // context
                android.R.layout.simple_list_item_2, // Layout for the rows
                mCursor, // cursor
                new String[] { Data.DISPLAY_NAME, Phone.NUMBER },
                    // cursor
                // fields
                new int[] { android.R.id.text1, android.R.id.text2 } // view
// fields
        );
        setListAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent result = new Intent();

// Get the data
        Cursor c = (Cursor) getListAdapter().getItem(position);
        Cursor cursor = null;
        int colIdx = c.getColumnIndex(Phone.NUMBER);
        int emailID = c.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA);
        ContentResolver cr = getContentResolver();
        String phone = "";
        String phone2 = "";
        phone = c.getString(colIdx);
        phone2 = c.getString(emailID);
        String id1 = "";
        id1 = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
        cursor = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null,ContactsContract.CommonDataKinds.Email.CONTACT_ID+" = ?", new String[]{id1},null);
        while(cursor.moveToNext()){
            String email = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            if(email!=null){
                System.out.println("Correo es ***********************************"+email);
            }
        }

        System.out.println("*******");
        System.out.println("UNO "+phone);
        System.out.println("DOS "+phone2);

// Save the phone to return it to the caller
        result.putExtra("phone", phone);
        result.putExtra("phone2", phone2);
        setResult(Activity.RESULT_OK, result);

// Close this activity (return to caller)
        finish();
    }
}
