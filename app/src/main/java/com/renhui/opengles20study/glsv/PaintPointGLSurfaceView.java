package com.renhui.opengles20study.glsv;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import com.renhui.opengles20study.base.BaseGLSurfaceView;
import com.renhui.opengles20study.paint.PaintPoint;
import com.renhui.opengles20study.paint.PaintPointRenderer;

/**
 * 画笔点 GLSurfaceView
 */
public class PaintPointGLSurfaceView extends BaseGLSurfaceView {

    PaintPoint mPoint = new PaintPoint();

    public PaintPointGLSurfaceView(Context context) {
        super(context);
        this.setRenderer(new PaintPointRenderer(mPoint));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 获取touch的事件
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPoint.setPosition(x * 2 / PaintPointRenderer.width - 1.0f, 1.0f - y * 2 / PaintPointRenderer.height);
                mPoint.setColor(0.0f, 1.0f, 0.0f, 1.0f);
                break;
            case MotionEvent.ACTION_UP:
                mPoint.setPosition(x * 2 / PaintPointRenderer.width - 1.0f, 1.0f - y * 2 / PaintPointRenderer.height);
                mPoint.setColor(0.0f, 0.0f, 1.0f, 1.0f);
                break;
        }
        return super.onTouchEvent(event);
    }
}
