package com.example.hasma.sightsofminsk;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context context;
    String[][] namesight;
    int[] sightImg;

    public Adapter (Context context, String[][] name, int[] img){
        this.context = context;
        this.namesight = name;
        this.sightImg = img;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View view1 = inflater.inflate(R.layout.customlist, null, false);

        TextView name = (TextView) view1.findViewById(R.id.namesight);
        TextView description = (TextView) view1.findViewById(R.id.descriptionsight);
        ImageView image = (ImageView) view1.findViewById(R.id.imageView);

        name.setText(namesight[i][0]);
        description.setText(namesight[i][1]);
        image.setImageResource(sightImg[i]);

        image.setTag(i);
        image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VisionImage.class);
                intent.putExtra("IMG", sightImg[(int) v.getTag()]);
                context.startActivity(intent);
            }
        });

        return view1;
    }

    @Override
    public int getCount() {
        return sightImg.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
