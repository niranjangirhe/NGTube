package com.ngsolutions.ngtube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class MainActivity extends AppCompatActivity {

    EditText ytLink;
    Button watch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ytLink = findViewById(R.id.ytLink);
        watch = findViewById(R.id.watch);
        watch.setOnClickListener(v -> {
            String url = ytLink.getText().toString();
            if (url.isEmpty()) {
                Toast.makeText(this, "Please enter valid youtube link", Toast.LENGTH_SHORT).show();
            } else {
                String video_id = "";
                if (url.contains("youtu.be/")) {
                    video_id = url.substring(url.indexOf("be/") + 3);
                    int ampersandPosition = video_id.indexOf('&');
                    if (ampersandPosition != -1) {
                        video_id = video_id.substring(0, ampersandPosition);
                    }
                    int questionMark = video_id.indexOf('?');
                    if (questionMark != -1) {
                        video_id = video_id.substring(0, questionMark);
                    }
                } else if (url.contains("v=")) {
                    video_id = url.substring(url.indexOf("v=") + 2);
                    int ampersandPosition1 = video_id.indexOf('&');
                    if (ampersandPosition1 != -1) {
                        video_id = video_id.substring(0, ampersandPosition1);
                    }
                }
                Intent videoIntent = new Intent(MainActivity.this, VideoActivity.class);
                videoIntent.putExtra("videoID",video_id);
                startActivity(videoIntent);
            }
        });
    }
}