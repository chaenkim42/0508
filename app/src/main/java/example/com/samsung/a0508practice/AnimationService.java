package example.com.samsung.a0508practice;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by samsung on 5/8/2018.
 */

public class AnimationService extends IntentService {
    public static volatile boolean shouldContinue = true;

    public AnimationService() {
        super("AnimationService");
        Log.i("TAG", "service created ");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Messenger messenger = (Messenger) extras.get("MESSENGER");
            int startValue = intent.getIntExtra("startValue", 0);
            int endValue = intent.getIntExtra("endValue", 180);
            int increment = intent.getIntExtra("increment", 1);
            int updateTime = intent.getIntExtra("updateTime", 10);

            try {
                while (startValue <= endValue) {
                    Log.d("shouldContinue?", ""+ shouldContinue);
                    if(shouldContinue) {
                        Message msg = Message.obtain();
                        msg.arg1 = startValue;
                        messenger.send(msg);
                        Thread.sleep(updateTime);
                        startValue += increment;
                    } else{
                        startValue = 0;
                        shouldContinue = true;
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
