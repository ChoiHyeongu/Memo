package com.example.motivation.memo;

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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MemoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MemoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemoFragment extends Fragment implements MainActivity.onBackPressedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String DATABASE_NAME = "MEMO.db";
    private static final String TABLE_NAME = "MEMO";

    private DBHelper dbmemo;
    private SQLiteDatabase db;

    EditText titleEditText;
    EditText contentEditText;
    String date;
    Button save;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_memo, container, false);

        final DBHelper dbHelper = new DBHelper(getContext());

        titleEditText = rootView.findViewById(R.id.memo_title);
        contentEditText = rootView.findViewById(R.id.memo_content);
        save = rootView.findViewById(R.id.memo_saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String date = "2018-10-17";
                Log.d("MEMO DB",title + "//" + date);

                dbHelper.insert(title, date);
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
}
