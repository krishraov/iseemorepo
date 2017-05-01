package com.example.krishna.iseemo;


import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class VideoViewActivity extends Activity {

    // Declare variables
    ProgressDialog pDialog;
    VideoView videoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Get the layout from video_main.xml
        setContentView(R.layout.activity_video_view);

        // extract the URL sent from the main screen activity.
        String VideoURL = getIntent().getStringExtra(EXTRA_MESSAGE);

        // Find your VideoView in your video_main.xml layout
        videoview = (VideoView) findViewById(R.id.VideoView);

        // Execute StreamVideo AsyncTask
        // Create a progressbar
        pDialog = new ProgressDialog(VideoViewActivity.this);

        // Set progressbar title
        pDialog.setTitle("Almost there ..");

        // Set progressbar message
        pDialog.setMessage("We're putting together the magic!");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);

        // Show progressbar
        pDialog.show();

        try {

            // Start the MediaController
            MediaController mediacontroller = new MediaController(
                    VideoViewActivity.this);
            mediacontroller.setAnchorView(videoview);

            // Get the URL from String VideoURL
            Uri video = Uri.parse(VideoURL);
            videoview.setMediaController(mediacontroller);
            videoview.setVideoURI(video);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoview.requestFocus();
        videoview.setOnPreparedListener(new OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoview.start();
            }
        });

    }

}
