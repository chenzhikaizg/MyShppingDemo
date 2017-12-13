package cn.aiyangkeji.util;

import android.net.Uri;
import android.os.Environment;
import android.view.View;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TakePhotoOptions;

import java.io.File;

import cn.aiyangkeji.R;

/**
 * Created by $chenzhikai on 2017/12/5.
 */

public class ImgAndCameraHelper {

    private View rootView;
    public static ImgAndCameraHelper of(){
        return new ImgAndCameraHelper();
    }
    private ImgAndCameraHelper() {

    }
    public void  init(){


    }

    public void onlick(View view, TakePhoto takePhoto,File file) {

        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);

        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        switch (view.getId()) {
            case R.id.btn_sure:
                takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());
                break;
            case R.id.btn_cancel:
                takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
                break;
            default:
                break;
        }
    }

    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();

        takePhoto.setTakePhotoOptions(builder.create());

    }

    private void configCompress(TakePhoto takePhoto) {

        int maxSize = Integer.parseInt(102400 + "");
        int width = Integer.parseInt(800 + "");
        int height = Integer.parseInt(800 + "");

        CompressConfig config;

        config = new CompressConfig.Builder()
                .setMaxSize(maxSize)
                .setMaxPixel(width >= height ? width : height)

                .create();


        takePhoto.onEnableCompress(config, true);


    }

    private CropOptions getCropOptions() {

        int height = Integer.parseInt(800 + "");
        int width = Integer.parseInt(800 + "");


        CropOptions.Builder builder = new CropOptions.Builder();


        builder.setAspectX(width).setAspectY(height);

        builder.setWithOwnCrop(true);
        return builder.create();
    }
}
