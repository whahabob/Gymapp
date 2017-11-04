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

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.Viewholder> {


    private WorkoutClickListener mWorkoutClickListener;
    private Cursor mCursor;


    public interface WorkoutClickListener {
        void workoutOnClick(long index);
        void workoutOnLongClick(long index);
    }

    public WorkoutAdapter(WorkoutClickListener mWorkoutClickListener, Cursor mCursor) {
        this.mWorkoutClickListener = mWorkoutClickListener;
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
    public WorkoutAdapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null);

// Return a new holder instance
        WorkoutAdapter.Viewholder viewHolder = new WorkoutAdapter.Viewholder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(WorkoutAdapter.Viewholder holder, final int position) {
        String name = null;
        // Move the mCursor to the position of the item to be displayed
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null
        // Update the view holder with the information needed to display
        final long index=
                mCursor.getLong(mCursor.getColumnIndex(RemindersContract.PushEntry._ID));

        name = mCursor.getString(mCursor.getColumnIndex(RemindersContract.PushEntry.COLUMN_NAME_PUSH));


        holder.textView.setText(name);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWorkoutClickListener.workoutOnClick(index);
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mWorkoutClickListener.workoutOnLongClick(index);
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