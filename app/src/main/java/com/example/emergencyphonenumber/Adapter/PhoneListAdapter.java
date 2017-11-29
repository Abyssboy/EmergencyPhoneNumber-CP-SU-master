package com.example.emergencyphonenumber.Adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emergencyphonenumber.Model.PhoneItem;
import com.example.emergencyphonenumber.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by poome on 11/26/2017.
 */

public class PhoneListAdapter  extends ArrayAdapter<PhoneItem> {

    private Context mConText;
    private int mLayoutResId;
    private ArrayList<PhoneItem> mPhoneItemList;

    public PhoneListAdapter(@NonNull Context context, int LayoutResID, @NonNull ArrayList<PhoneItem> PhoneItemList) {
        super(context, LayoutResID, PhoneItemList);

        this.mConText = context;
        this.mLayoutResId = LayoutResID;
        this.mPhoneItemList = PhoneItemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) { //ctrl+o -->getview

        LayoutInflater inflater = LayoutInflater.from(mConText);
       View ItemLayout =  inflater.inflate(mLayoutResId,null);

       PhoneItem Item = mPhoneItemList.get(position);

       ImageView PhoneImageView = ItemLayout.findViewById(R.id.PhoneImageView);
        TextView PhoneTitle = ItemLayout.findViewById(R.id.PhoneTitleTextView);
        TextView PhoneDetail = ItemLayout.findViewById(R.id.PhoneDetailTextView);

        PhoneTitle.setText(Item.Title);
        PhoneDetail.setText(Item.Number);

        String PicFileName = Item.Pic;

        AssetManager AM = mConText.getAssets();
        try {
           InputStream Stream = AM.open(PicFileName);
            Drawable drawable = Drawable.createFromStream(Stream,null);
            PhoneImageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ItemLayout;
    }
}
