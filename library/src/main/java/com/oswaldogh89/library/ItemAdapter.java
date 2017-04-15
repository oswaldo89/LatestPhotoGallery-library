package com.oswaldogh89.library;

/**
 * Created by oswaldogh89 on 14/04/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemRowHolder> {
    private Context context;
    private final ArrayList<Image> item;

    public ItemAdapter(Context ctx, ArrayList<Image> bmp) {
        this.item = bmp;
        this.context = ctx;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, null);
        return new ItemRowHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemRowHolder view, final int i) {
        Glide.with(context).load(new File(item.get(i).getImgPath().getAbsolutePath())).into(view._imagenItem);
        view._chkSelected.setChecked(item.get(i).isSelected());
        view._chkSelected.setTag(item.get(i));
        view._chkSelected.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Image contact = (Image) cb.getTag();

                contact.setSelected(cb.isChecked());
                item.get(i).setSelected(cb.isChecked());
            }
        });
    }

    public ArrayList<Image> getSelected() {
        ArrayList<Image> images = new ArrayList<>();
        for (int i = 0; i < item.size(); i++) {
            if (item.get(i).isSelected()) {
                images.add(item.get(i));
            }

        }
        return images;
    }


    protected class ItemRowHolder extends RecyclerView.ViewHolder {
        private ImageView _imagenItem;
        private CheckBox _chkSelected;

        private ItemRowHolder(View view) {
            super(view);
            this._imagenItem = (ImageView) view.findViewById(R.id.imgThumb);
            this._chkSelected = (CheckBox) view.findViewById(R.id.checkBox);
        }
    }

    @Override
    public int getItemCount() {
        return (null != item ? item.size() : 0);
    }

}
