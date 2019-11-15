package com.hzy.face.seeta2.demo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.SnackbarUtils;
import com.blankj.utilcode.util.Utils;
import com.hzy.face.seeta2.Seeta2Detector;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.demo_image)
    ImageView mDemoImage;
    private ExecutorService mExecutor;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mExecutor = Executors.newSingleThreadExecutor();
        mExecutor.submit(() -> Seeta2Detector.INSTANCE.init(this));
        showInitDemo();
    }

    private void showInitDemo() {
        mExecutor.submit(() -> {
            try {
                mBitmap = ImageUtils.getBitmap(getAssets().open("demo.jpg"));
                detectAsync(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void detectAsync(int type) {
        runOnUiThread(() -> {
            showMessage("Start Detect!");
            mDemoImage.setImageBitmap(mBitmap);
        });
        Bitmap bmp = mBitmap.copy(mBitmap.getConfig(), true);
        if (type == 1) {
            Rect[] faces = Seeta2Detector.INSTANCE.detect(bmp);
            BmpDrawUtils.drawRectsOnBitmap(bmp, faces);
        } else if (type == 2) {
            PointF[] points = Seeta2Detector.INSTANCE.mark81(bmp);
            BmpDrawUtils.drawPointsOnBitmap(bmp, points);
        }
        runOnUiThread(() -> {
            mDemoImage.setImageBitmap(bmp);
            showMessage("Detect OK!");
        });
    }

    @OnClick({R.id.button_load, R.id.button_detect, R.id.button_mark})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_load:
                startImageChoose(100);
                break;
            case R.id.button_detect:
                mExecutor.submit(() -> detectAsync(1));
                break;
            case R.id.button_mark:
                mExecutor.submit(() -> detectAsync(2));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        Seeta2Detector.INSTANCE.destory();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    mExecutor.submit(() -> {
                        mBitmap = getBmpFromIntent(data);
                        detectAsync(1);
                    });
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showMessage(String msg) {
        SnackbarUtils.with(mDemoImage).setMessage(msg).show();
    }

    public Bitmap getBmpFromIntent(Intent data) {
        Uri uri = data.getData();
        Bitmap bitmap = null;
        try {
            if (uri != null) {
                bitmap = MediaStore.Images.Media.getBitmap(Utils.getApp().getContentResolver(), uri);
            } else {
                Bundle bundleExtras = data.getExtras();
                if (bundleExtras != null) {
                    bitmap = bundleExtras.getParcelable("data");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public void startImageChoose(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT).setType("image/*")
                .addCategory(Intent.CATEGORY_OPENABLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            String[] mimeTypes = {"image/jpeg", "image/png", "image/gif"};
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        }
        intent = Intent.createChooser(intent, null);
        ActivityUtils.startActivityForResult(this, intent, requestCode);
    }
}
