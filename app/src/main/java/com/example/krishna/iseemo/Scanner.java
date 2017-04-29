package com.example.krishna.iseemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

/**
 * This activity has a margin.
 */
public class Scanner extends CaptureActivity {
    @Override
    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.activity_scanner);

        TextView textView = (TextView) findViewById(R.id.zxing_status_view);
        textView.setText("");

        return (DecoratedBarcodeView)findViewById(R.id.zxing_barcode_scanner);
    }
}