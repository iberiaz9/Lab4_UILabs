package course.labs.todomanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ToDoListAdapter extends BaseAdapter {

	private final List<ToDoItem> mItems = new ArrayList<ToDoItem>();
	private final Context mContext;
	private ToDoManagerActivity mToDoManagerActivity;
	private static final String TAG = "Lab-UserInterface";
	// Date for overdue checkbox added 9/25/16
	private Date mDate;

	public ToDoListAdapter(Context context, ToDoManagerActivity toDoManagerActivity) {

		mToDoManagerActivity = toDoManagerActivity;
		mContext = context;

	}

	// Add a ToDoItem to the adapter
	// Notify observers that the data set has changed

	public void add(ToDoItem item) {

		mItems.add(item);
		notifyDataSetChanged();

	}

	public void remove(ToDoItem item) {

		mItems.remove(item);
		notifyDataSetChanged();

	}

	// Clears the list adapter of all items.

	public void clear() {

		mItems.clear();
		notifyDataSetChanged();

	}

	// Returns the number of ToDoItems

	@Override
	public int getCount() {

		return mItems.size();

	}

	// Retrieve the number of ToDoItems

	@Override
	public Object getItem(int pos) {

		return mItems.get(pos);

	}

	// Get the ID for the ToDoItem
	// In this case it's just the position

	@Override
	public long getItemId(int pos) {

		return pos;

	}

	// Create a View for the ToDoItem at specified position
	// Remember to check whether convertView holds an already allocated View
	// before created a new View.
	// Consider using the ViewHolder pattern to make scrolling more efficient
	// See: http://developer.android.com/training/improving-layouts/smooth-scrolling.html
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// TODO - Get the current ToDoItem
		//final ToDoItem toDoItem = null;
		final ToDoItem toDoItem = (ToDoItem) getItem(position);

		// TODO - Inflate the View for this ToDoItem
		// from todo_item.xml
		//RelativeLayout itemLayout = null;
		RelativeLayout itemLayout = (RelativeLayout) LayoutInflater.from (mContext)
				.inflate(R.layout.todo_item, null);

		// Fill in specific ToDoItem data
		// Remember that the data that goes in this View
		// corresponds to the user interface elements defined
		// in the layout file

		// TODO - Display Title in TextView
		//final TextView titleView = null;
		final  TextView titleView = (TextView) itemLayout.findViewById(R.id.titleView);
		titleView.setText(toDoItem.getTitle());


		// TODO - Set up Status CheckBox
		// Set up overdue check box
		final CheckBox overDueChkBox = (CheckBox) itemLayout.findViewById(R.id.overDueCheckBox);



		//final CheckBox statusView = null;
		final CheckBox statusView = (CheckBox) itemLayout.findViewById(R.id.statusCheckBox);
		statusView.setChecked(ToDoItem.Status.DONE.equals(toDoItem.getStatus()));

		mDate = new Date();
		if (toDoItem.getDate().before(mDate) && toDoItem.getStatus() == ToDoItem.Status.NOTDONE) {
			overDueChkBox.setChecked(true);
		}

		// TODO - Must also set up an OnCheckedChangeListener,
		// which is called when the user toggles the status checkbox

		statusView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

                        if (isChecked) {
                            toDoItem.setStatus(ToDoItem.Status.DONE);
                            overDueChkBox.setChecked(false);
                        }
                        else {
                            toDoItem.setStatus(ToDoItem.Status.NOTDONE);
                            if (toDoItem.getDate().before(mDate)) {
                                overDueChkBox.setChecked(true);
                            }
                        }
					}
				});

		itemLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				//TODO - Implement OnClick().
				mToDoManagerActivity.updateTodoItem(toDoItem);
			}
		});

		// TODO - Display Priority in a TextView
		//final TextView priorityView = null;
		final TextView priorityView = (TextView) itemLayout.findViewById(R.id.priorityView);
		priorityView.setText(toDoItem.getPriority().toString());


		// TODO - Display Time and Date.
		// Hint - use ToDoItem.FORMAT.format(toDoItem.getDate()) to get date and
		// time String
		//final TextView dateView = null;
		final TextView dateView = (TextView) itemLayout.findViewById(R.id.dateView);
		dateView.setText(ToDoItem.FORMAT.format(toDoItem.getDate()));

		// Adding note to list adapter 9/23/16

		final TextView noteView = (TextView) itemLayout.findViewById(R.id.noteView);
		noteView.setText(toDoItem.getNote());


		// Return the View you just created
		return itemLayout;

	}
}
