package com.renhui.opengles20study.glsv;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.renhui.opengles20study.base.BaseGLSurfaceView;
import com.renhui.opengles20study.shape.oval.Ball;
import com.renhui.opengles20study.shape.oval.BallWithLight;
import com.renhui.opengles20study.shape.oval.Cone;
import com.renhui.opengles20study.shape.oval.Cylinder;
import com.renhui.opengles20study.shape.oval.Oval;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 绘制圆形的GLSurfaceView
 */
public class OvalGLSurfaceView extends BaseGLSurfaceView {

    public OvalGLSurfaceView(Context context) {
        super(context);

        setRenderer(new OvalGLSurfaceView.BallWithLightRenderer());

        setRenderMode(RENDERMODE_WHEN_DIRTY);
    }

    class OvaleRenderer implements Renderer {

        Oval oval;

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            oval = new Oval();
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            oval.onSurfaceChanged(width, height);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            oval.draw();
        }
    }


    class ConeRenderer implements Renderer {

        Cone cone = new Cone();

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            cone.onSurfaceCreate();
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            cone.onSurfaceChanged(width, height);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            cone.draw();
        }
    }


    /**
     * 圆柱体渲染器
     */
    class CylinderRenderer implements Renderer {

        Cylinder cylinder = new Cylinder();

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            cylinder.onSurfaceCreated();
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            cylinder.onSurfaceChanged(width, height);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            cylinder.draw();
        }
    }

    /**
     * 球体渲染器
     */
    class BallRenderer implements Renderer {

        Ball ball = new Ball();

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            ball.onSurfaceCreated();
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            ball.onSurfaceChanged(width, height);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            ball.draw();
        }
    }

    /**
     * 带光源球体渲染器
     */
    class BallWithLightRenderer implements Renderer {

        BallWithLight ballWithLight = new BallWithLight();

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            ballWithLight.onSurfaceCreated();
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            ballWithLight.onSurfaceChanged(width, height);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            ballWithLight.draw();
        }
    }

}