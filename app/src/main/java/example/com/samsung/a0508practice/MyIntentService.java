package example.com.samsung.a0508practice;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by samsung on 5/8/2018.
 */

public class MyIntentService extends IntentService {

    private int result = Activity.RESULT_CANCELED;

    public MyIntentService(){
        super("MyServiceNameHere");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Uri data = intent.getData();
        String urlPath = intent.getStringExtra("urlpath");
        String buffer = " ";

        InputStream stream = null;
        try {
            URL url = new URL(urlPath);
            stream = url.openConnection().getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            int i = 0, next = -1;
            while ((next = reader.read()) != -1) {
                buffer += " " + (char) next;
                if (++i > 100) break;
            }
            result = Activity.RESULT_OK;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(stream != null){
                try{
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Bundle extras = intent.getExtras();
        if(extras != null){
            Messenger messenger = (Messenger) extras.get("MESSENGER");
            Message msg = Message.obtain();
            msg.arg1 = result;
            Log.i("rrrr", result+"");
            msg.obj = buffer;
            try{
                messenger.send(msg);
            } catch (RemoteException e1){
                Log.w(getClass().getName(), "Exception sending message", e1);
            }
        }
    }

//    @Override
//    public IBinder onBind(Intent intent) {
//        return super.onBind(intent);
//    }
//
//    @Override
//    public void onCreate() {
//        // TODO 서비스 생성시에 호출
//        super.onCreate();
//    }
//
//    @Override
//    public void onDestroy() {
//        // TODO Queue에 들어있는 모든서비스 종료시에 호출
//        super.onDestroy();
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        // TODO 서비스 시작시에 호출
//        return super.onStartCommand(intent, flags, startId);
//    }

}
