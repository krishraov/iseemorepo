package com.example.krishna.iseemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;

//import com.squareup.picasso.Picasso;

public class ImageDisplayActivity extends AppCompatActivity {

    private ViewFlipper mViewFlipper;
    private GestureDetector mGestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);

        // Get the ViewFlipper
        mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        final String baseURL = getIntent().getStringExtra("BASE_URL");
        final Integer numImages = getIntent().getIntExtra("NUM_ITEMS", 1);
        String[] urlArray = new String[numImages];

        // set up all the image URLs
        // create dynamic image view and add them to ViewFlipper
        for (int i = 0; i < urlArray.length; i++) {
            urlArray[i] = baseURL + "/image" + (i + 1) + ".jpeg";
            ImageView image = new ImageView(getApplicationContext());
            Glide.with(this).load(urlArray[i]).into(image);
            mViewFlipper.addView(image);
        }

        // Set in/out flipping animations
        mViewFlipper.setInAnimation(this, android.R.anim.fade_in);
        mViewFlipper.setOutAnimation(this, android.R.anim.fade_out);

        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        mGestureDetector = new GestureDetector(this, customGestureDetector);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    /**
     * This provides the user with gesture control.
     */
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

}


