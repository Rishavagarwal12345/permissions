package com.example.permissions

import android.content.Intent
import android.content.Intent.ACTION_CALL
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar


const val PERMISSION_REQUEST_PHONE_CALL = 0

class MainActivity : AppCompatActivity(),ActivityCompat.OnRequestPermissionsResultCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val callSupport = findViewById<Button>(R.id.callkaro)
        callSupport.setOnClickListener{
            makePhoneCallAfterPermission(it)
        }
    }

    private fun makePhoneCallAfterPermission(view: View)
    {
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
        {
            makePhoneCall()
        }
        else
        {
            requestCallPermission(view)
        }
    }

    private fun requestCallPermission(view: View)
    {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.CALL_PHONE))
        {
            val snack = Snackbar.make(view,"permission to dena hi hoga bsdk" + "do bhai",Snackbar.LENGTH_INDEFINITE)
            snack.setAction("ok",View.OnClickListener {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),PERMISSION_REQUEST_PHONE_CALL)
            })
            snack.show()
        }
        else
        {
           ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),PERMISSION_REQUEST_PHONE_CALL)
        }
    }

    private fun makePhoneCall()
    {
        val intent = Intent().apply {
            action = ACTION_CALL
            data = Uri.parse("tel: 9339952085")
        }
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_PHONE_CALL)
        {
            if(grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                makePhoneCall()
            }
            else
            {
                Toast.makeText(this,"permission dedo bhai",Toast.LENGTH_SHORT).show()
            }
        }
    }

}