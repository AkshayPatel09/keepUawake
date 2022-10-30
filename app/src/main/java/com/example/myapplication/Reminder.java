package com.example.myapplication;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.core.Context;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Reminder {
    Timer timer;

    public Reminder(Activity activity, int seconds, Camera mCamera, Camera.PictureCallback mPicture) {
        timer = new Timer();

        timer.schedule(new RemindTask(activity,mCamera,mPicture), seconds*1000);
    }

    class RemindTask extends TimerTask {
        Activity activity;
        Camera mCamera;
        Camera.PictureCallback mPicture;
        public RemindTask(Activity activity,Camera mCamera, Camera.PictureCallback mPicture){
            this.activity = activity;
            this.mCamera = mCamera;
            this.mPicture = mPicture;
        }
        public void run() {
            Looper.prepare();
            final Toast toast = Toast.makeText(this.activity, "....", Toast.LENGTH_SHORT);
            toast.show();
            mCamera.takePicture(null,null,mPicture);
            Log.d("error","hello akshay");
//            System.out.println("Time's up!");
            timer.cancel(); //Terminate the timer thread
            //Call looper.prepare()
//
//            Handler mHandler = new Handler() {
//                @Override
//                public void publish(LogRecord record) {
//
//                }
//
//                @Override
//                public void flush() {
//
//                }
//
//                @Override
//                public void close() throws SecurityException {
//
//                }
//
//                public void handleMessage(Message msg) {
//                    Toast.makeText(activity, "Finco is Daddy", Toast.LENGTH_LONG);
//
//                }
//            };
//
//            Looper.loop();
//
        }
    }


}
