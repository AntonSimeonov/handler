package ninja.paranoidandroid.handlertest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //UI
    private TextView mNameTextView;

    //Threads
    private Runnable mThreadJob;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mNameTextView.setText("Cool new name");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        setThreadJob();

        Thread thread = new Thread(mThreadJob);
        thread.start();
    }

    private void initUI(){
        mNameTextView = (TextView) findViewById(R.id.tv_activity_main_name);
    }

    private void setThreadJob(){

        mThreadJob = new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        };
    }
}
