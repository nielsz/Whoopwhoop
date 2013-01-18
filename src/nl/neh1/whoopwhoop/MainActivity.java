package nl.neh1.whoopwhoop;

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {
	  private MessagesDataSource datasource;

	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);

	    datasource = new MessagesDataSource(this);
	    datasource.open();

	    List<Message> values = datasource.getAllComments();

	    // Use the SimpleCursorAdapter to show the
	    // elements in a ListView
	    ArrayAdapter<Message> adapter = new ArrayAdapter<Message>(this,
	        android.R.layout.simple_list_item_1, values);
	    setListAdapter(adapter);
	  }

	  // Will be called via the onClick attribute
	  // of the buttons in main.xml
	  public void onClick(View view) {
	    @SuppressWarnings("unchecked")
	    ArrayAdapter<Message> adapter = (ArrayAdapter<Message>) getListAdapter();
	    Message msg = null;
	    switch (view.getId()) {
	    case R.id.add:
	      String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
	      int nextInt = new Random().nextInt(3);
	      // Save the new comment to the database
	     
	      msg = datasource.createComment(comments[nextInt]);
	      adapter.insert(msg,0);
	      break;
	    case R.id.delete:
	      if (getListAdapter().getCount() > 0) {
	        msg = (Message) getListAdapter().getItem(0);
	        datasource.deleteMessage(msg);
	        adapter.remove(msg);
	      }
	      break;
	    }
	    adapter.notifyDataSetChanged();
	  }

	  @Override
	  protected void onResume() {
	    datasource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    datasource.close();
	    super.onPause();
	  }

	}