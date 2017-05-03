package com.example.krishna.iseemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        // extract the URL sent from the main screen activity.
        String imageURL = "https://s3.ap-south-1.amazonaws.com/iseemo-testing/bugsbunny/00-02-05-2017/image0.png";
        Picasso.with(this).load(imageURL).into(imageView);


    }

}
