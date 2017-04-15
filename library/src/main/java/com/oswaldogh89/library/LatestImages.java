package com.oswaldogh89.library;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by oswaldogh89 on 14/04/17.
 */

public class LatestImages extends LinearLayout {

    private int mImagesNumber;
    private ItemAdapter adapter;
    public LatestImages(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);
        inflate(context, R.layout.template, this);
        init(attrs);


        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);
        my_recycler_view.setHasFixedSize(true);
        ArrayList<Image> imagesArray = getList(context);

        adapter = new ItemAdapter(context, imagesArray);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        my_recycler_view.setAdapter(adapter);
    }

    private void init(final AttributeSet attrs) {
        if (attrs != null) {
            String packageName = "http://schemas.android.com/apk/res-auto";
            mImagesNumber = attrs.getAttributeIntValue(packageName, "quantity", 10);
        }
    }

    public ArrayList<Image> getSelectedImages(){
       return adapter.getSelected();
    }

    public ArrayList<Image> getList(Context ctx) {
        ArrayList<String> latestImages = getCameraImages(ctx);
        ArrayList<Image> result = new ArrayList<>();
        int countImages = 0;
        for (int i = 0; i <= latestImages.size(); i++) {
            try {
                String path = latestImages.get(i);
                if (isValidImage(path)) {
                    if (countImages < mImagesNumber) {
                        File imgFile = new File(path);
                        Image im = new Image();
                        im.setImgPath(imgFile);
                        result.add(im);
                        countImages++;
                    } else {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private ArrayList<String> getCameraImages(Context context) {
        final String[] projection = new String[]{
                MediaStore.Images.ImageColumns._ID,
                MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
                MediaStore.Images.ImageColumns.DATE_TAKEN,
                MediaStore.Images.ImageColumns.MIME_TYPE
        };
        final Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC");
        ArrayList<String> result = new ArrayList<>(cursor != null ? cursor.getCount() : 0);
        assert cursor != null;
        if (cursor.moveToFirst()) {
            final int dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            do {
                final String data = cursor.getString(dataColumn);
                if (result.size() >= mImagesNumber)
                    break;
                result.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    private boolean isValidImage(String path) {
        return path.toUpperCase().contains(".JPG") || path.toUpperCase().contains(".JPGE") || path.toUpperCase().contains(".PNG") || path.toUpperCase().contains(".GIF") || path.toUpperCase().contains(".BMP");
    }
}
