package example.com.samsung.a0508practice;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler(){
        public void handleMessage(Message message){
            Object path = message.obj;
            if(message.arg1 == RESULT_OK && path != null){
                Toast.makeText(getApplicationContext(),
                        " "+ path.toString() + "을 다운로드하였음.", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(getApplicationContext(), "다운로드 실패",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(this, MyIntentService.class);
//        startService(intent);
    }

    public void onClick(View view){
        Intent intent = new Intent(this, MyIntentService.class);
        Messenger messenger = new Messenger(handler);
        intent.putExtra("MESSENGER", messenger);
//        Log.i("putex", intent.)
        intent.setData(Uri.parse("http://www.google.com/index.html"));
        intent.putExtra("urlpath", "http://www.google.com/index.html");
        startService(intent);
    }

}
