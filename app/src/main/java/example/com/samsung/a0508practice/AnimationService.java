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

    public AnimationService() {
        super("AnimationService");
        Log.i("TAG", "service created ");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Bundle extras = intent.getExtras();
        if(extras != null){
            Messenger messenger = (Messenger) extras.get("MESSENGER");
            int startValue = intent.getIntExtra("startValue",0);
            int endValue = intent.getIntExtra("endValue",180);
            int increment = intent.getIntExtra("increment",1);
            int updateTime = intent.getIntExtra("updateTime",10);

            try{
                while(startValue <= endValue){
                    Message msg = Message.obtain();
                    msg.arg1 = startValue;
                    messenger.send(msg);
                    Thread.sleep(updateTime);
                    startValue+= increment;
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
