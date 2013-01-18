package nl.neh1.whoopwhoop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends ListActivity {
	  private MessagesDataSource datasource;

	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);

	    datasource = new MessagesDataSource(this);
	    datasource.open();

	    List<Message> values = datasource.getAllMessages();
        MessageAdapter adapter = new MessageAdapter(this, R.layout.message_item, (ArrayList)values);
        setListAdapter(adapter);
	    
	  }

	  public void onClick(View view) {
	    MessageAdapter adapter = (MessageAdapter) getListAdapter();
	    Message msg = null;
	    switch (view.getId()) {
	    case R.id.add:

	      msg = new Message();
	      msg.setMessage(new String[] { "Cool", "Very nice", "Hate it" }[new Random().nextInt(3)]);
    	  msg.setGender(new Random().nextInt(2));
	      adapter.insert(msg,0); // add the item to the top. 
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