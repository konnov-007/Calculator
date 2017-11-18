package konnov.commr.vk.calculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

//import android.support.v4.app.Fragment;


public class HistoryFragment extends Fragment {

    private View view;
    private Button cleanButton;
    //called each time Android needs the fragment’s layout, and it’s where you say which layout the fragment uses
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {//The first parameter is a LayoutInflator that you can use to inflate the fragment’s layout. Inflating the layout turns your XML views into Java objects.
        //The second parameter is a ViewGroup. This is the ViewGroup in the activity’s layout that will contain the fragment.
        //The final parameter is a Bundle. This is used if you’ve previously saved the fragment’s state, and want to reinstate it.
        view =  inflater.inflate(R.layout.fragment_history, container, false); //pass the correct layout name for the fragment
        cleanButton = (Button) view.findViewById(R.id.button_clear_db);
        cleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(view.getContext());
                dbHelper.deleteDB();
                update();
            }
        });
        return view; //returns a View object that represents the fragment’s user interface (inflates the fragment's layout from xml to java objects
    }

    public HistoryFragment() {
        // Required empty public constructor
    }

    public void update(){
        DBHelper dbHelper = new DBHelper(view.getContext());
        ListAdapter listAdapter = new ArrayAdapter<ArrayList>(getActivity(), android.R.layout.simple_list_item_1, dbHelper.dbToList());
        ListView listView = (ListView) view.findViewById(R.id.listview);
        listView.setAdapter(listAdapter);
    }


}

