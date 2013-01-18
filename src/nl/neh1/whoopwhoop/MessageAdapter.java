package nl.neh1.whoopwhoop;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MessageAdapter extends ArrayAdapter {

	    private static final String TAG = MessageAdapter.class.getSimpleName();
	    ArrayList<Message> listArray;
		private int resource;
		private LayoutInflater inflater;
		private Context context;	    

	    public MessageAdapter (Context ctx, int resourceId,  ArrayList<Message> objects) {
            super( ctx, resourceId, objects );
            resource = resourceId;
            inflater = LayoutInflater.from( ctx );
            context=ctx;
            listArray = objects;
      }

	    @Override
	    public int getCount() {
	        return listArray.size();    // total number of elements in the list
	    }

	    @Override
	    public Object getItem(int i) {
	        return listArray.get(i);    // single item in the list
	    }

	    @Override
	    public long getItemId(int i) {
	        return i;                   // index number
	    }

	    @Override
	    public View getView(int index, View view, final ViewGroup parent) {

	        if (view == null) {
	            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
	            view = inflater.inflate(R.layout.message_item, parent, false);
	        }

	        final Message message = listArray.get(index);

	        TextView textView = (TextView) view.findViewById(R.id.tv_string_data);
	        textView.setText(message.getMessage());

	        ImageView imageView = (ImageView) view.findViewById(R.id.iv_icon);
	        if(message.getGender()==0) {
	        	imageView.setImageResource(R.drawable.man);
	        } else {
	        	imageView.setImageResource(R.drawable.woman);
	        }
	        return view;
	    }
	}
