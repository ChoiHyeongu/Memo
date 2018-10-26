package com.example.motivation.memo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;


public class ListFragment extends Fragment implements MainActivity.onBackPressedListener {

    ArrayList<MemoItem> memoItemArrayList;
    ListView memoList;

    public ListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        memoList = rootView.findViewById(R.id.list_memoList);

//        showList();
        return rootView;
    }

    @Override
    public void onBack() {
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnBackPressedListener(null);
        activity.onBackPressed();
    }

//    public void showList(){
//        try{
//
//            SQLiteDatabase ReadDB = getActivity().openOrCreateDatabase("MEMO.db", MODE_PRIVATE, null);
//
//            Cursor c = ReadDB.rawQuery("SELECT * FROM MEMO" , null);
//
//            if(c != null){
//                if(c.moveToFirst()){
//                    do{
//                        String title = c.getString(c.getColumnIndex("title"));
//                        String date = c.getString(c.getColumnIndex("date"));
//
//                        memoItemArrayList.add(new MemoItem(title, date));
//                    } while (c.moveToNext());
//                }
//            }
//            ReadDB.close();
//
//            memoList.setAdapter(new MemoListAdapter(getActivity(), memoItemArrayList));
//        } catch (SQLiteException se){
//            Toast.makeText(getActivity(), se.getMessage(), Toast.LENGTH_SHORT).show();
//            Log.d("", se.getMessage());
//        }
//    }
}