package com.renhui.opengles20study.camera;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.renhui.opengles20study.R;

/**
 * 摄像头处理页面
 * Created by renhui on 2018/1/5.
 */
public class CameraActivity extends Activity {

    private CameraView mCameraView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mCameraView = findViewById(R.id.camera_view);
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (mCameraView != null) {
            mCameraView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCameraView != null) {
            mCameraView.onPause();
        }
    }
}
