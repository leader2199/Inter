package com.example.admin.inter;



import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class couAd extends ArrayAdapter<couModel> {
    private Context context;
    private int res;
    private ArrayList<couModel> arr;


    public couAd(@NonNull Context context, int resource, @NonNull ArrayList<couModel> objects) {
        super(context, resource, objects);
        this.context=context;
        this.res=resource;
        this.arr=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        viewHolder viewHolder = new viewHolder();
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.info,parent,false);
            viewHolder.img=convertView.findViewById(R.id.img);
            viewHolder.texName=convertView.findViewById(R.id.texName);
            convertView.setTag(viewHolder);

        }
        else {
            viewHolder=(viewHolder) convertView.getTag();
        }

        viewHolder.texName.setText(arr.get(position).getName());
        viewHolder.img.setImageResource(arr.get(position).getImgID());
        return convertView;

    }

    public class viewHolder{
        ImageView img;
        TextView texName;
    }
}
