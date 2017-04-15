package com.oswaldogh89.library;

import java.io.File;

/**
 * Created by oswaldogh89 on 14/04/17.
 */

public class Image {
    private File imgPath;
    private boolean isSelected;

    public File getImgPath() {
        return imgPath;
    }

    public void setImgPath(File imgPath) {
        this.imgPath = imgPath;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}