package example.com.samsung.a0508practice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Toast;
//import example.com.samsung.a0508practice.AnimationService;

import static android.app.Activity.RESULT_OK;

/**
 * Created by samsung on 5/8/2018.
 */

public class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRenderer mRenderer;

    public MyGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        setEGLContextClientVersion(2);

        mRenderer = new MyGLRenderer();
        setRenderer(mRenderer);

        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }


    // 1. define and create a handler instance.
    // 2. create a messenger instance with the handler.


    private Messenger messenger = new Messenger(new Handler(){
        public void handleMessage(Message message){
        float angle = message.arg1;
        mRenderer.setAngle((float)angle);
        requestRender();
        }
    });

    public Messenger getMessenger() {
        return messenger;
    }


}
