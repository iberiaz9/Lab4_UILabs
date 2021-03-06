package course.labs.todomanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;

public class ToDoItem {

	public static final String ITEM_SEP = System.getProperty("line.separator");

	public enum Priority {
		LOW, MED, HIGH
	};

	public enum Status {
		NOTDONE, DONE
	};

	public final static String TITLE = "title";
	public final static String PRIORITY = "priority";
	public final static String STATUS = "status";
	public final static String DATE = "date";

	// Adding note to program 9/23/16
	public final static String NOTE = "note";

	public final static String FILENAME = "filename";

	public final static SimpleDateFormat FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss", Locale.US);

	private String mTitle = new String();

	// Adding note to program 9/23/16
	private String mNote = new String();

	private Priority mPriority = Priority.LOW;
	private Status mStatus = Status.NOTDONE;
	private Date mDate = new Date();

	ToDoItem(String title, Priority priority, Status status, Date date, String note) {
		this.mTitle = title;
		this.mPriority = priority;
		this.mStatus = status;
		this.mDate = date;
		this.mNote = note;
	}

	// Create a new ToDoItem from data packaged in an Intent

	ToDoItem(Intent intent) {

		mTitle = intent.getStringExtra(ToDoItem.TITLE);
		mPriority = Priority.valueOf(intent.getStringExtra(ToDoItem.PRIORITY));
		mStatus = Status.valueOf(intent.getStringExtra(ToDoItem.STATUS));
		// note added to program 9/23/16
		mNote = intent.getStringExtra(ToDoItem.NOTE);

		try {
			mDate = ToDoItem.FORMAT.parse(intent.getStringExtra(ToDoItem.DATE));
		} catch (ParseException e) {
			mDate = new Date();
		}
	}

	public String getTitle() {
		return mTitle;
	}

	// Adding note to program 9/23/16
	public String getNote() {
		return mNote;
	}

    // Adding note to program 9/23/16
    public void setNote(String note) {
        mNote = note;
    }

	public void setTitle(String title) {
		mTitle = title;
	}

	public Priority getPriority() {
		return mPriority;
	}

	public void setPriority(Priority priority) {
		mPriority = priority;
	}

	public Status getStatus() {
		return mStatus;
	}

	public void setStatus(Status status) {
		mStatus = status;
	}

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}

	// Take a set of String data values and 
	// package them for transport in an Intent

	public static void packageIntent(Intent intent, String title,
			Priority priority, Status status, String date, String note) {

		intent.putExtra(ToDoItem.TITLE, title);
		intent.putExtra(ToDoItem.PRIORITY, priority.toString());
		intent.putExtra(ToDoItem.STATUS, status.toString());
		intent.putExtra(ToDoItem.DATE, date);
        // note added to program 9/23/16
        intent.putExtra(ToDoItem.NOTE, note);
	
	}

	public void packageIntent2(Intent intent) {

		intent.putExtra(ToDoItem.TITLE, mTitle);
		// note added to program 9/23/16
		intent.putExtra(ToDoItem.NOTE, mNote);
		intent.putExtra(ToDoItem.PRIORITY, mPriority.toString());
		intent.putExtra(ToDoItem.STATUS, mStatus.toString());
		intent.putExtra(ToDoItem.DATE, FORMAT.format(mDate));

	}

	public String toString() {
		return mTitle + ITEM_SEP + mPriority + ITEM_SEP + mStatus + ITEM_SEP
				+ FORMAT.format(mDate) + ITEM_SEP + mNote;
	}

	public String toLog() {
		return "Title:" + mTitle + ITEM_SEP + "Priority:" + mPriority
				+ ITEM_SEP + "Status:" + mStatus + ITEM_SEP + "Date:"
				+ FORMAT.format(mDate) + "\n";
	}

}
