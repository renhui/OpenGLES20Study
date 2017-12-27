package com.renhui.opengles20study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.renhui.opengles20study.glsurface.PaintPointGLSurfaceView;
import com.renhui.opengles20study.glsurface.TriangleGLSurfaceView;
import com.renhui.opengles20study.glsurface.OvalGLSurfaceView;
import com.renhui.opengles20study.glsurface.SquareGLSurfaceView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // setContentView(new TriangleGLSurfaceView(this)); // 绘制三角形

        // setContentView(new SquareGLSurfaceView(this));  // 绘制正方形

        // setContentView(new OvalGLSurfaceView(this)); // 绘制圆形

        setContentView(new PaintPointGLSurfaceView(this)); // 手绘图
    }
}
