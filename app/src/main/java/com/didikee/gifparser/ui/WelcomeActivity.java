package com.didikee.gifparser.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.didikee.gifparser.R;

import didikee.com.permissionshelper.PermissionsHelper;
import didikee.com.permissionshelper.permission.DangerousPermissions;

public class WelcomeActivity extends AppCompatActivity {

    static final String[] PERMISSIONS = new String[]{
//            DangerousPermissions.CALENDAR,
//            DangerousPermissions.CAMERA,
//            DangerousPermissions.CONTACTS,
//            DangerousPermissions.LOCATION,
//            DangerousPermissions.MICROPHONE,
//            DangerousPermissions.PHONE,
            DangerousPermissions.STORAGE,
//            DangerousPermissions.SENSORS,
//            DangerousPermissions.SMS
    };
    private PermissionsHelper permissionsHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        checkPermissions();
    }

    private void doOneThing(){
        startActivity(new Intent(this,MainActivity.class));
    }

    private void checkPermissions() {
        permissionsHelper = new PermissionsHelper(this,PERMISSIONS);
        if (permissionsHelper.checkAllPermissions(PERMISSIONS)){
            permissionsHelper.onDestroy();
            //do nomarl
            doOneThing();
        }else {
            //申请权限
            permissionsHelper.startRequestNeedPermissions();
        }
        permissionsHelper.setonAllNeedPermissionsGrantedListener(new PermissionsHelper.onAllNeedPermissionsGrantedListener() {


            @Override
            public void onAllNeedPermissionsGranted() {
                //做原先的业务代码
                Log.d("test","onAllNeedPermissionsGranted");
                doOneThing();
            }

            @Override
            public void onPermissionsDenied() {
                //拒绝了,如何处理?(视情况而定)
                Log.d("test","onPermissionsDenied");
                finish();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionsHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        permissionsHelper.onActivityResult(requestCode, resultCode, data);
    }
}
