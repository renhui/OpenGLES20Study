package com.renhui.opengles20study.glsv;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.renhui.opengles20study.base.BaseGLSurfaceView;
import com.renhui.opengles20study.image.ImageRenderer;
import com.renhui.opengles20study.image.ImageTransformRenderer;

import java.io.IOException;

/**
 * 展示图片的 GLSurfaceView
 */
public class ImageGLSurfaceView extends BaseGLSurfaceView {

    public ImageGLSurfaceView(Context context) throws IOException {
        super(context);

        setRenderer(new ImageRenderer(context));  // 展示图片渲染器

        // setRenderer(new ImageTransformRenderer(context, ImageTransformRenderer.Filter.MAGN));  // 展示图片处理渲染器

        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        requestRender();
    }
}
