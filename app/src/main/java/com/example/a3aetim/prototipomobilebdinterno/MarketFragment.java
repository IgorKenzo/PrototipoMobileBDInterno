package com.example.a3aetim.prototipomobilebdinterno;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MarketFragment extends Fragment {
    DatabaseHelper helper;
    private LinearLayout linearLayout = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_market, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        helper = new DatabaseHelper(getActivity());
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayoutMarket);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _IdUser, NameUser, PicUser from User", null);
        cursor.moveToFirst();
        int id = 0;
        String name ="";
        for (int i = 0; i < cursor.getCount(); i++) {
            id = cursor.getInt(0);
            name = cursor.getString(1);
            byte[] imgByte = cursor.getBlob(2);
            Button btn = new Button(getActivity());
            btn.setText(id + name);
            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ImageView iv = new ImageView(getActivity());
            iv.setImageBitmap(BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length));
            linearLayout.addView(btn);
            linearLayout.addView(iv);
            cursor.moveToNext();
        }
        cursor.close();
    }
    @Override
    public void onDestroy(){
        helper.close();
        super.onDestroy();
    }
}
