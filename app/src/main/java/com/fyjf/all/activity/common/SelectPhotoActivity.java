package com.fyjf.all.activity.common;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.fyjf.all.activity.BaseActivity;
import com.fyjf.utils.BitmapUtil;
import com.fyjf.all.widget.SelectOrTakePhotoView;
import com.fyjf.utils.SDUtils;

import java.io.File;
import java.net.URI;

/**
 * Created by 任伟伟
 * Datetime: 2016/11/17-11:16
 * Email: renweiwei@ufashion.com
 */

public abstract class SelectPhotoActivity extends BaseActivity implements SelectOrTakePhotoView.OperationListener{
    public static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    public static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    public static final int PHOTO_REQUEST_CUT = 3;// 结果
    protected SelectOrTakePhotoView selectOrTakePhotoView;
    private String PHOTO_FILE_NAME = "temp_photo.jpg";
    @Override
    protected void preInitData() {
        View decorView = getWindow().getDecorView();
        if(decorView!=null){
            try {
                FrameLayout frameLayout = (FrameLayout) decorView.findViewById(getContentId()).getParent();//这个是子activity的根布局的id，如果使用decorView向下找，可能不同的系统不一样的id
                selectOrTakePhotoView = new SelectOrTakePhotoView(mContext);
                selectOrTakePhotoView.setVisibility(View.GONE);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                frameLayout.addView(selectOrTakePhotoView,layoutParams);
                selectOrTakePhotoView.setOperationListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //eg: R.id.content
    protected abstract int getContentId();

    public void showSelectOrTakePhoto(){
        selectOrTakePhotoView.setVisibility(View.VISIBLE);
        PHOTO_FILE_NAME = String.valueOf(System.currentTimeMillis());
    }

    @Override
    public void selectPhoto() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    @Override
    public void takePhoto() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (SDUtils.hasSDCard()) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME)));
        }
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
    }

    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/png");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 图片格式
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAMERA) {
            if (resultCode==RESULT_CANCELED) {

            }else{
                if (SDUtils.hasSDCard()) {
                    File tempFile = new File(Environment.getExternalStorageDirectory(),PHOTO_FILE_NAME);
                    crop(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(mContext, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            try {
                Bitmap picture = data.getParcelableExtra("data");
                URI pictureSavedPath = BitmapUtil.saveBmpToSd(picture,PHOTO_FILE_NAME,90);
                selectOrTakePhotoComplete(pictureSavedPath);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public abstract void selectOrTakePhotoComplete(URI pictureSavedPath);
}
