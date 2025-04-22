package com.example.aimbot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_MEDIA_PROJECTION = 1;
    private static final int REQUEST_ACCESSIBILITY_PERMISSION = 2;

    private Button startServiceButton;
    private Button stopServiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startServiceButton = findViewById(R.id.start_service_button);
        stopServiceButton = findViewById(R.id.stop_service_button);

        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAccessibilityPermission();
            }
        });

        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, ScreenCaptureService.class));
                Toast.makeText(MainActivity.this, "خدمة Aimbot متوقفة", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkAccessibilityPermission() {
        if (!isAccessibilityServiceEnabled()) {
            Toast.makeText(this, "يرجى تمكين خدمة إمكانية الوصول", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivityForResult(intent, REQUEST_ACCESSIBILITY_PERMISSION);
        } else {
            requestMediaProjection();
        }
    }

    private boolean isAccessibilityServiceEnabled() {
        // تبسيط للبناء - سيتم تنفيذ هذا لاحقًا
        return true;
    }

    private void requestMediaProjection() {
        // تبسيط للبناء - سيتم تنفيذ هذا لاحقًا
        Toast.makeText(this, "تم بدء خدمة Aimbot", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_MEDIA_PROJECTION) {
            if (resultCode == Activity.RESULT_OK) {
                // تبسيط للبناء - سيتم تنفيذ هذا لاحقًا
            } else {
                Toast.makeText(this, "تم رفض إذن التقاط الشاشة", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_ACCESSIBILITY_PERMISSION) {
            if (isAccessibilityServiceEnabled()) {
                requestMediaProjection();
            } else {
                Toast.makeText(this, "خدمة إمكانية الوصول غير ممكّنة", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
