package com.example.bob.gymapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by marmm on 9/18/17.
 */

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.Viewholder> {


    private ReminderClickListener mReminderClickListener;
    private Cursor mCursor;


    public interface ReminderClickListener{
        void reminderOnClick (long index);
        void reminderOnLongClick (long index);
    }

    public ReminderAdapter(ReminderClickListener mReminderClickListener, Cursor mCursor) {
        this.mReminderClickListener = mReminderClickListener;
        this.mCursor = mCursor;
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        public View mView;
        public TextView textView;

        public Viewholder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
            mView = itemView;
        }
    }


    @Override
    public ReminderAdapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null);

// Return a new holder instance
        ReminderAdapter.Viewholder viewHolder = new ReminderAdapter.Viewholder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ReminderAdapter.Viewholder holder, final int position) {
        String name = null;
        // Move the mCursor to the position of the item to be displayed
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null
        // Update the view holder with the information needed to display
        final long index=
                mCursor.getLong(mCursor.getColumnIndex(RemindersContract.ReminderEntry._ID));

        name = mCursor.getString(mCursor.getColumnIndex(RemindersContract.ReminderEntry.COLUMN_NAME_WORKOUT));
       /* if(MainActivity.newString == "PUSH"){
            name = mCursor.getString(mCursor.getColumnIndex(RemindersContract.ReminderEntry.COLUMN_NAME_PUSH));
        }
        if(MainActivity.newString == "PULL"){
            name = mCursor.getString(mCursor.getColumnIndex(RemindersContract.ReminderEntry.COLUMN_NAME_PULL));
        }
        if(MainActivity.newString == "LEGS"){
            name = mCursor.getString(mCursor.getColumnIndex(RemindersContract.ReminderEntry.COLUMN_NAME_LEGS));
        }*/

        holder.textView.setText(name);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mReminderClickListener.reminderOnClick(index);
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mReminderClickListener.reminderOnLongClick(index);
                return true;
            }
        });

    }


    public void swapCursor(Cursor newCursor) {

        if (mCursor != null) mCursor.close();

        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }



    @Override
    public int getItemCount() {
        return (mCursor == null ? 0 : mCursor.getCount());
    }

}