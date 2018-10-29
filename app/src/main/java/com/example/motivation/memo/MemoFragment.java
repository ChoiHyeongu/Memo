package com.example.motivation.memo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MemoFragment extends Fragment implements MainActivity.onBackPressedListener {

    EditText titleEditText;
    EditText contentEditText;
    String date;
    Button save;

    long now;
    Date nowDate;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public MemoFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MemoFragment newInstance(String param1, String param2) {
        MemoFragment fragment = new MemoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_memo, container, false);

        final DBHelper dbHelper = new DBHelper(getContext());

        titleEditText = rootView.findViewById(R.id.memo_title);
        contentEditText = rootView.findViewById(R.id.memo_content);
        save = rootView.findViewById(R.id.memo_saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String content = contentEditText.getText().toString();
                String date = getTime();

                dbHelper.insert(title, content, date);
            }
        });

        return rootView;
    }

    @Override
    public void onBack() {
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnBackPressedListener(null);
        activity.onBackPressed();
    }

    public String getTime(){
        now = System.currentTimeMillis();
        nowDate = new Date(now);
        return dateFormat.format(nowDate);
    }
}
