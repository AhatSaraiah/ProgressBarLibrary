package com.example.progressbar;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.progress_bar.Progressbar;

public class MainActivity extends AppCompatActivity {

    private Progressbar progBar;
    private Button mainBtn;
    private int progressStatus=0;
    private int maxValue=100;


    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progBar = findViewById(R.id.main_progressbar);
        mainBtn = findViewById(R.id.main_BTN_start);
        progBar.setVisibility(View.GONE);
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        while (progressStatus < maxValue) {
                            progressStatus += 1;
                            // Update the progress bar
                            handler.post(new Runnable() {
                                public void run() {
                                    progBar.setVisibility(View.VISIBLE);
                                    mainBtn.setVisibility(View.INVISIBLE);
                                    progBar.setLoading(String.valueOf(progressStatus) + '/' + String.valueOf(maxValue));

                                    //change progressImg size
                                    progBar.enlarge(3);
                                }
                            });
                            try {
                                // Sleep for 1000 milliseconds.
                                Thread.sleep(100);
                            } catch (InterruptedException e) {

                            }
                        }
                        progBar.setVisibility(View.INVISIBLE);
                        mainBtn.setVisibility(View.VISIBLE);



                    }
                }).start();

            }

        });


    }

}