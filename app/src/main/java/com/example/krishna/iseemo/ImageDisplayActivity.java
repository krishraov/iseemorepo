package com.example.krishna.iseemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

public class ImageDisplayActivity extends AppCompatActivity {

    private ViewFlipper mViewFlipper;
    private GestureDetector mGestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);

        // Get the ViewFlipper
        mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        final String  baseURL    = getIntent().getStringExtra("BASE_URL");
        final Integer numImages  = getIntent().getIntExtra("NUM_ITEMS", 1);
        //final Integer numImages  = Integer.parseInt(numImgStr);
        //Log.d("NUM_ITEMS", "Value = "+numImages);

        String[] urlArray        = new String[numImages];

        // set up all the image URLs
        // create dynamic image view and add them to ViewFlipper
        for( int i = 0; i < urlArray.length; i++)
        {
            urlArray[i] = baseURL + "/image" + (i+1) + ".jpeg";
            ImageView image = new ImageView(getApplicationContext());
            Picasso.with(this).load(urlArray[i]).into(image);
            mViewFlipper.addView(image);
        }

        // Add all the images to the ViewFlipper
        //for (int i = 0; i < resources.length; i++) {
        //    ImageView imageView = new ImageView(this);
        //    imageView.setImageResource(resources[i]);
        //    mViewFlipper.addView(imageView);
        //}

        // Set in/out flipping animations
        mViewFlipper.setInAnimation(this, android.R.anim.fade_in);
        mViewFlipper.setOutAnimation(this, android.R.anim.fade_out);

        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        mGestureDetector = new GestureDetector(this, customGestureDetector);

}



private class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        // Swipe left (next)
        if (e1.getX() > e2.getX()) {
            mViewFlipper.showNext();
        }

        // Swipe right (previous)
        if (e1.getX() < e2.getX()) {
            mViewFlipper.showPrevious();
        }

        return super.onFling(e1, e2, velocityX, velocityY);
    }
}

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gesture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */
}


