package com.example.motivation.memo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MemoListAdapter extends BaseAdapter {

    Context context;
    ArrayList<MemoItem>  memoItemArrayList;

    public MemoListAdapter(ListFragment listFragment, ArrayList<HashMap<String,String>> personList, ListView memoList, String[] strings) {
    }

    class ViewHolder{
        TextView title;
        TextView date;
    }

    ViewHolder viewHolder;

    public MemoListAdapter(Context context, ArrayList<MemoItem> memoItemArrayList) {
        this.context = context;
        this.memoItemArrayList = memoItemArrayList;
    }

    @Override
    public int getCount() {
        return this.memoItemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return memoItemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_memo, null);

            viewHolder = new ViewHolder();

            viewHolder.title = (TextView) convertView.findViewById(R.id.item_memo_title);
            viewHolder.date = (TextView) convertView.findViewById(R.id.item_memo_date);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(memoItemArrayList.get(position).getTitle());
        viewHolder.date.setText(memoItemArrayList.get(position).getDate());

        return convertView;
    }
}
