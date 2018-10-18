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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;


public class ListFragment extends Fragment implements MainActivity.onBackPressedListener {

    DBHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;
    ListView memoList;
    MemoListAdapter memoListAdapter;

    private static final String KEY_ID = "_id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DATE = "date";
    private static final String TABLE_NAME = "MEMO";

    ArrayList<HashMap<String, String>> memoArrayList;

    public ListFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        memoList = rootView.findViewById(R.id.list_memoList);

        dbHelper = new DBHelper(getContext(), "MEMO.db", null, 1);
        db = dbHelper.getWritableDatabase();

        return rootView;
    }

    @Override
    public void onBack() {
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnBackPressedListener(null);
        activity.onBackPressed();
    }

    protected void showList(){

        try {

            SQLiteDatabase ReadDB = getActivity().openOrCreateDatabase("MEMO.db", MODE_PRIVATE, null);


            //SELECT문을 사용하여 테이블에 있는 데이터를 가져옵니다..
            Cursor c = ReadDB.rawQuery("SELECT * FROM " + TABLE_NAME, null);

            if (c != null) {


                if (c.moveToFirst()) {
                    do {

                        //테이블에서 두개의 컬럼값을 가져와서
                        String title= c.getString(c.getColumnIndex(KEY_TITLE));
                        String date = c.getString(c.getColumnIndex(KEY_DATE));

                        //HashMap에 넣
                        HashMap<String,String> memo = new HashMap<String,String>();

                        memo.put(KEY_TITLE,title);
                        memo.put(KEY_DATE,date);

                        //ArrayList에 추가합니다..
                        memoArrayList.add(memo);

                    } while (c.moveToNext());
                }
            }

            ReadDB.close();


            //새로운 apapter를 생성하여 데이터를 넣은 후..
            memoListAdapter = new MemoListAdapter(
                    this, memoArrayList, memoList,
                    new String[]{KEY_TITLE,KEY_DATE}
            );


            //화면에 보여주기 위해 Listview에 연결합니다.
            memoList.setAdapter(memoListAdapter);


        } catch (SQLiteException se) {
            Toast.makeText(getContext(),  se.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("",  se.getMessage());
        }

    }
}

