package com.example.krishna.iseemo;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class VideoViewActivity extends Activity {
    private VideoView myVideoView;
    private int position = 0;
    private ProgressDialog progressDialog;
    private MediaController mediaControls;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the main layout of the activity
        setContentView(R.layout.activity_video_view);

        // extract the URL sent from the main screen activity.
        String VideoURL = getIntent().getStringExtra(EXTRA_MESSAGE);
        VideoURL = VideoURL + "/video1.mp4";

        //set the media controller buttons
        if (mediaControls == null) {
            mediaControls = new MediaController(VideoViewActivity.this);
        }

        //initialize the VideoView
        myVideoView = (VideoView) findViewById(R.id.VideoView);

        // create a progress bar while the video file is loading
        progressDialog = new ProgressDialog(VideoViewActivity.this);
        // set a title for the progress bar
        progressDialog.setTitle("Just one minute ... ");
        // set a message for the progress bar
        progressDialog.setMessage("We are fetching something magical :)");
        //set the progress bar not cancelable on users' touch
        progressDialog.setCancelable(false);
        // show the progress bar
        progressDialog.show();

        try {
            //set the media controller in the VideoView
            myVideoView.setMediaController(mediaControls);

            //set the uri of the video to be played
            Uri video = Uri.parse(VideoURL);
            myVideoView.setVideoURI(video);

        } catch (Exception e) {
            Log.e("VIDEOPLAYBACK_ERROR", e.getMessage());
            e.printStackTrace();
        }

        myVideoView.requestFocus();

        //we also set an setOnPreparedListener in order to know when the video file is ready for playback
        myVideoView.setOnPreparedListener(new OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                // close the progress bar and play the video
                progressDialog.dismiss();
                //if we have a position on savedInstanceState, the video playback should start from here
                myVideoView.seekTo(position);
                if (position == 0) {
                    myVideoView.start();
                } else {
                    //if we come from a resumed activity, video playback will be paused
                    myVideoView.pause();
                }
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //we use onSaveInstanceState in order to store the video playback position for orientation change
        savedInstanceState.putInt("Position", myVideoView.getCurrentPosition());
        myVideoView.pause();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //we use onRestoreInstanceState in order to play the video playback from the stored position
        position = savedInstanceState.getInt("Position");
        myVideoView.seekTo(position);
    }

}
