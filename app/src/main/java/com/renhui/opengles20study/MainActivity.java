package com.renhui.opengles20study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.renhui.opengles20study.glsv.ImageGLSurfaceView;
import com.renhui.opengles20study.glsv.SquareGLSurfaceView;
import com.renhui.opengles20study.glsv.TriangleGLSurfaceView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // setContentView(new TriangleGLSurfaceView(this)); // 绘制三角形

        setContentView(new SquareGLSurfaceView(this));  // 绘制正方形

        // setContentView(new OvalGLSurfaceView(this)); // 绘制圆形

        // setContentView(new PaintPointGLSurfaceView(this)); // 手绘点

        // setContentView(new RotateTriangleGLSurfaceView(this)); // 旋转三角形

        // setContentView(new ImageGLSurfaceView(this)); // 加载图片


    }
}
