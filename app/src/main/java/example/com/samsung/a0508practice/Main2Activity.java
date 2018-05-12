package example.com.samsung.a0508practice;

import android.app.ActivityManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;

import java.util.Iterator;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private MyGLSurfaceView mGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mGLView = (MyGLSurfaceView) findViewById(R.id.glView);
        Log.i("TAG", "start application.");
    }


    public void onClickStart(View view) {
        Intent i = new Intent(this, AnimationService.class);
        Messenger m = mGLView.getMessenger();
        i.putExtra("MESSENGER", m);
        i.putExtra("startValue", 0);
        i.putExtra("endValue", 360);
        i.putExtra("increment",1);
        i.putExtra("updateTime", 10);
        startService(i);
    }


    public void onClickStop(View view) {
        AnimationService.shouldContinue = false;
    }
}
