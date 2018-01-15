package com.renhui.opengles20study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.renhui.opengles20study.camera.preview.PreviewCameraActivity;
import com.renhui.opengles20study.camera.takepic.TakePictureActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // setContentView(new TriangleGLSurfaceView(this)); // 绘制三角形

        // setContentView(new SquareGLSurfaceView(this));  // 绘制正方形

        // setContentView(new OvalGLSurfaceView(this)); // 绘制圆形

        // setContentView(new PaintPointGLSurfaceView(this)); // 手绘点

        // setContentView(new RotateTriangleGLSurfaceView(this)); // 旋转三角形

        // setContentView(new ImageGLSurfaceView(this)); // 加载图片

        // startActivity(new Intent(this, PreviewCameraActivity.class));  // OpenGL预览摄像头

        startActivity(new Intent(this, TakePictureActivity.class));  // OpenGL 拍照
    }
}
