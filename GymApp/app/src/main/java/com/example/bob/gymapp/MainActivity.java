package com.example.bob.gymapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements ReminderAdapter.ReminderClickListener, LoaderManager.LoaderCallbacks<Cursor> {


    private RecyclerView mRecyclerView;
    private EditText mNewReminderText;
    private ReminderAdapter mAdapter;

    //Database related local variables
    private Cursor mCursor;

    //Constants used when calling the detail activity
    public static final String INTENT_DETAIL_ROW_NUMBER = "Row number";
    public static final String INTENT_DETAIL_REMINDER_TEXT = "Reminder text";
    public static final int REQUESTCODE = 2;
    public static String newString = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("STRING_I_NEED");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }

        Log.d("Oncreate", newString);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportLoaderManager().initLoader(0, null, this);
        // mListView = (ListView) findViewById(R.id.listView_main);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        //mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        mRecyclerView.setLayoutManager(mLayoutManager);

        mNewReminderText = (EditText) findViewById(R.id.editText_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get the user text from the textfield
                String text = mNewReminderText.getText().toString();
                Reminder newReminder = new Reminder(text);

                //Check if some text has been added
                if (!(TextUtils.isEmpty(text))) {
                    //Add the text to the list (datamodel)
                    // mReminders.add(newReminder);
                    //mDataSource.createReminder(text);

                    ContentValues values = new ContentValues();

                    /*switch(newString){
                        case "PUSH":
                            values.put(RemindersContract.ReminderEntry.COLUMN_NAME_PUSH, text);
                        case "PULL":
                            values.put(RemindersContract.ReminderEntry.COLUMN_NAME_PULL, text);
                        case "LEGS":
                            values.put(RemindersContract.ReminderEntry.COLUMN_NAME_LEGS, text);
                        default:
                            break;
                    } */
                    values.put(RemindersContract.ReminderEntry.COLUMN_NAME_WORKOUT, text);
                    getContentResolver().insert(RemindersProvider.CONTENT_URI, values);

                    //Tell the adapter that the data set has been modified: the screen will be refreshed.
                    //       updateUI();
                    //Initialize the EditText for the next item
                    mNewReminderText.setText("");
                } else {
                    //Show a message to the user if the textfield is empty
                    Snackbar.make(view, "Please enter some text in the textfield", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }

            }
        });

        /*
Add a touch helper to the RecyclerView to recognize when a user swipes to delete a list entry.
An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
and uses callbacks to signal when a user is performing these actions.
*/
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }

                    //Called when a user swipes left or right on a ViewHolder
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                        //Get the index corresponding to the selected position
                        mCursor.moveToPosition(viewHolder.getAdapterPosition());
                        long id = mCursor.getLong(mCursor.getColumnIndex(RemindersContract.ReminderEntry._ID));

                        //Delete the entry
                        Uri singleUri = ContentUris.withAppendedId(RemindersProvider.CONTENT_URI,id);
                        getContentResolver().delete(singleUri, null, null);
                    }
                };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUESTCODE) {
            if (resultCode == RESULT_OK) {
            */
/*    int positionOfReminder = data.getIntExtra(MainActivity.INTENT_DETAIL_ROW_NUMBER, -1);
                //-1 being the default value in case of failure. Can be used to detect an issue.
                Reminder updatedReminder = new Reminder ( data.getStringExtra(MainActivity.INTENT_DETAIL_REMINDER_TEXT) ) ;
                // New timestamp: timestamp of update
  //              mReminders.set(positionOfReminder, updatedReminder);
*//*
                updateUI();
            }
        }
    }
*/


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


    @Override
    protected void onResume() {
        super.onResume();
        //Starts a new or restarts an existing Loader in this manager
        getSupportLoaderManager().restartLoader(0, null, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCursor != null && !mCursor.isClosed()) mCursor.close();

    }



    @Override
    public void reminderOnClick(long index) {
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        intent.putExtra(INTENT_DETAIL_ROW_NUMBER, index);
        startActivity(intent);


    }



    @Override
    public void reminderOnLongClick(long id) {

        //Create an instance of the DetailFragment class
        final DetailFragment detailFragment = DetailFragment.newInstance(id);

        //and add it to the screen
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detailFragment, detailFragment, "detailFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {

        CursorLoader cursorLoader = new CursorLoader(this, RemindersProvider.CONTENT_URI, null,
                null, null, null);
        return cursorLoader;
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        this.mCursor = cursor;
        mAdapter = new ReminderAdapter(this, mCursor);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(mCursor);
    }
}