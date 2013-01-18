package nl.neh1.whoopwhoop;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MessagesDataSource {

  // Database fields
  private SQLiteDatabase database;
  private MySQLiteHelper dbHelper;
  private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
      MySQLiteHelper.COLUMN_MESSAGE, MySQLiteHelper.COLUMN_GENDER };

  public MessagesDataSource(Context context) {
    dbHelper = new MySQLiteHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public Message createMessage(Message message) {
    ContentValues values = new ContentValues();
    values.put(MySQLiteHelper.COLUMN_MESSAGE, message.getMessage());
    values.put(MySQLiteHelper.COLUMN_GENDER, message.getGender());
    long insertId = database.insert(MySQLiteHelper.TABLE_MESSAGES, null,
        values);
    Cursor cursor = database.query(MySQLiteHelper.TABLE_MESSAGES,
        allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
        null, null, null);
    cursor.moveToFirst();
    Message newMessage = cursorToMessage(cursor);
    cursor.close();
    return newMessage;
  }

  public void deleteMessage(Message msg) {
    long id = msg.getId();
    System.out.println("Message deleted with id: " + id);
    database.delete(MySQLiteHelper.TABLE_MESSAGES, MySQLiteHelper.COLUMN_ID
        + " = " + id, null);
  }

  public List<Message> getAllMessages() {
    List<Message> msgs = new ArrayList<Message>();

    Cursor cursor = database.query(MySQLiteHelper.TABLE_MESSAGES,
        allColumns, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      Message msg = cursorToMessage(cursor);
      msgs.add(msg);
      cursor.moveToNext();
    }
    // Make sure to close the cursor
    cursor.close();
    return msgs;
  }

  private Message cursorToMessage(Cursor cursor) {
    Message message = new Message();
    message.setId(cursor.getLong(0));
    message.setMessage(cursor.getString(1));
    message.setGender((cursor.getInt(2)));    
    return message;
  }
} 