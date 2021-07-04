package com.redapps.tabib.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import com.redapps.tabib.utils.AppConstants

/*
class ScanActivity : AppCompatActivity(), ResultHandler {
    private var mScannerView: ZXingScannerView? = null
    public override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        // Programmatically initialize the scanner view
        mScannerView = ZXingScannerView(this)
        // Set the scanner view as the content view
        setContentView(mScannerView)
    }

    public override fun onResume() {
        super.onResume()
        // Register ourselves as a handler for scan results.
        mScannerView!!.setResultHandler(this)
        // Start camera on resume
        mScannerView!!.startCamera()
    }

    public override fun onPause() {
        super.onPause()
        // Stop camera on pause
        mScannerView!!.stopCamera()
    }

    override fun handleResult(p0: Result?) {
        val intent = Intent()
        intent.putExtra(AppConstants.KEY_QR_CODE, p0!!.getText())
        setResult(RESULT_OK, intent)
        finish()
    }

    /*
    override fun handleResult(rawResult: Result?) {
        // Do something with the result here
        // Prints scan results
        //Logger.verbose("result", rawResult.getText())
        // Prints the scan format (qrcode, pdf417 etc.)
        //Logger.verbose("result", rawResult.getBarcodeFormat().toString())
        //If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
        val intent = Intent()
        intent.putExtra(AppConstants.KEY_QR_CODE, rawResult.getText())
        setResult(RESULT_OK, intent)
        finish()
    }*/
}*/