package com.example.myandroidfourcomponents.utils.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import com.example.myandroidfourcomponents.bean.PermissionBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by wangzhiguo on 18/3/5.
 */

public class PermissionUtils {

    private static PermissionUtilsInter mPermissionUtilsInter;
    private static final int REQUEST_PERMISSION_CODE = 321;
    private static final int REQUEST_SETTING_CODE = 123;

    private static final String PERMISSION_TITLE = "权限申请";
    private static final String PERMISSION_ERROR_TITLE = "权限不可用";
    private static final String PERMISSION_NOTIFICATION_MESSAGE = "App需要通知权限来开启前台服务，请授权...";

    /**
     * 开始检查权限
     */
    public static boolean checkPermission(Activity activity, PermissionUtilsInter Inter) {
        mPermissionUtilsInter = Inter;
        List<PermissionBean> mPermissionList = mPermissionUtilsInter.getApplyPermissions();
        List<PermissionBean> mNeedApply = new ArrayList<>();
        for (int i = 0; i < mPermissionList.size(); i++) {
            PermissionBean permissionBean = mPermissionList.get(i);
            int state = ContextCompat.checkSelfPermission(activity, permissionBean.mPermissionName);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (state != PackageManager.PERMISSION_GRANTED) {
                mNeedApply.add(permissionBean);
            }
        }

        if (mNeedApply.size() > 0) {
            // 如果没有授予该权限，就去提示用户请求
            showDialogTipUserRequestPermission(activity, mNeedApply);
            return false;
        }
        return true;
    }

    public static void checkNotificationPermission(Activity activity) {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(activity);
        if (!notificationManagerCompat.areNotificationsEnabled()) {
            showDefaultDialog(activity, PERMISSION_TITLE, PERMISSION_NOTIFICATION_MESSAGE, new OnDialogClickListener() {
                @Override
                public void onPositiveButtonClickEvent() {
                    goToNotificationSettting(activity);
                }
            });
        }
    }

    /**
     * 开始提交请求权限
     */
    private static void startRequestPermission(Activity mContext, List<PermissionBean> mNeedApply) {
        String[] mTemps = new String[mNeedApply.size()];
        for (int i = 0; i < mNeedApply.size(); i++) {
            PermissionBean permissionBean = mNeedApply.get(i);
            mTemps[i] = permissionBean.mPermissionName;
        }
        ActivityCompat.requestPermissions(mContext, mTemps, REQUEST_PERMISSION_CODE);
    }

    public static boolean onRequestPermissionsResults(final Activity mContext, int requestCode,
                                                      @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                List<PermissionBean> mPermissionList = mPermissionUtilsInter.getApplyPermissions();
                List<PermissionBean> mApplys = new ArrayList<>();
                StringBuilder sb = new StringBuilder();
                boolean isGoToSetting = false;
                boolean mIsOk = true;
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        mIsOk = false;
                        boolean b = mContext.shouldShowRequestPermissionRationale(permissions[i]);
                        if (!b) {
                            isGoToSetting = true;
                            sb.append(permissions[i]).append("\n").append("没有被同意").append("\n");
                        } else {
                            for (int j = 0; j < mPermissionList.size(); j++) {
                                if (mPermissionList.get(j).mPermissionName.equals(permissions[i])) {
                                    mApplys.add(mPermissionList.get(j));
                                    break;
                                }
                            }
                        }
                    }
                }

                if (mApplys.size() > 0) {
                    showDialogTipUserRequestPermission(mContext, mApplys);
                } else {
                    if (isGoToSetting) {
                        // 用户还是想用我的 APP 的
                        // 提示用户去应用设置界面手动开启权限
                        showDialogTipUserGoToAppSettting(mContext, sb.toString());
                    }
                }
                return mIsOk;
            }
        }
        return true;
    }

    public static boolean onActivityResults(final Activity mContext, int requestCode) {
        if (requestCode == REQUEST_SETTING_CODE) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                List<PermissionBean> mPermissionList = mPermissionUtilsInter.getApplyPermissions();
                List<PermissionBean> mApplys = new ArrayList<>();
                StringBuilder sb = new StringBuilder();
                boolean isGoToSetting = false;
                boolean mIsOk = true;
                for (int i = 0; i < mPermissionList.size(); i++) {
                    PermissionBean permissionBean = mPermissionList.get(i);
                    // 检查该权限是否已经获取
                    int result = ContextCompat.checkSelfPermission(mContext, permissionBean.mPermissionName);
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        mIsOk = false;
                        boolean b = mContext.shouldShowRequestPermissionRationale(permissionBean.mPermissionName);
                        if (!b) {
                            isGoToSetting = true;
                            sb.append(permissionBean.mPermissionName).append("没有被同意").append("\n");
                        } else {
                            mApplys.add(permissionBean);
                        }
                    }
                }

                if (mApplys.size() > 0) {
                    showDialogTipUserRequestPermission(mContext, mApplys);
                } else {
                    if (isGoToSetting) {
                        // 用户还是想用我的 APP 的
                        // 提示用户去应用设置界面手动开启权限
                        showDialogTipUserGoToAppSettting(mContext, sb.toString());
                    }
                }
                return mIsOk;
            }
        }
        return true;
    }

    /**
     * 跳转到当前应用的设置界面
     */
    private static void goToAppSetting(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivityForResult(intent, REQUEST_SETTING_CODE);
    }

    /**
     * 跳转到通知栏权限设置界面
     */
    private static void goToNotificationSettting(Activity activity) {
        try {
            Intent localIntent = new Intent();
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //直接跳转到应用通知设置的代码：
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                localIntent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                localIntent.putExtra(Settings.EXTRA_APP_PACKAGE, activity.getPackageName());
                activity.startActivity(localIntent);
                return;
            }

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                localIntent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                localIntent.putExtra("app_package", activity.getPackageName());
                localIntent.putExtra("app_uid", activity.getApplicationInfo().uid);
                activity.startActivity(localIntent);
                return;
            }

            if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
                localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                localIntent.addCategory(Intent.CATEGORY_DEFAULT);
                localIntent.setData(Uri.parse("package:" + activity.getPackageName()));
                activity.startActivity(localIntent);
                return;
            }

            //4.4以下没有从app跳转到应用通知设置页面的Action，可考虑跳转到应用详情页面,
            if (Build.VERSION.SDK_INT >= 9) {
                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                localIntent.setData(Uri.fromParts("package", activity.getPackageName(), null));
                activity.startActivity(localIntent);
                return;
            }

            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", activity.getPackageName());
        } catch (Exception e) {
            throw new RuntimeException("open notification setting failed : " + e.getLocalizedMessage());
        }
    }

    private static void showDialogTipUserGoToAppSettting(final Activity activity, String message) {
        androidx.appcompat.app.AlertDialog.Builder tipAlertDialog = mPermissionUtilsInter.getTipAppSettingAlertDialog();
        if (tipAlertDialog != null) {
            tipAlertDialog.show();
            return;
        }

        Dialog tipDialog = mPermissionUtilsInter.getTipAppSettingDialog();
        if (tipDialog != null) {
            tipDialog.show();
            return;
        }

        showDefaultDialog(activity, PERMISSION_ERROR_TITLE, message + "\n 请在-应用设置-权限-中,将以上权限打开.", () -> goToAppSetting(activity));
    }

    private static void showDialogTipUserRequestPermission(final Activity activity, final List<PermissionBean> mNeedApply) {
        String message;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mNeedApply.size(); i++) {
            PermissionBean permissionBean = mNeedApply.get(i);
            sb.append(permissionBean.mPermissionName).append("\n").append(permissionBean.mPermissionReason).append("\n");
        }
        message = sb.toString();
        androidx.appcompat.app.AlertDialog.Builder tipAlertDialog = mPermissionUtilsInter.getTipAlertDialog();
        if (tipAlertDialog != null) {
            tipAlertDialog.show();
            return;
        }

        Dialog tipDialog = mPermissionUtilsInter.getTipDialog();
        if (tipDialog != null) {
            tipDialog.show();
            return;
        }

        showDefaultDialog(activity, PERMISSION_TITLE, message, () -> startRequestPermission(activity, mNeedApply));
    }

    private static void showDefaultDialog(Activity activity, String title, String message, OnDialogClickListener onDialogClickListener) {
        AlertDialog.Builder mTipDialog = new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("立即开启", (dialog, which) -> onDialogClickListener.onPositiveButtonClickEvent())
                .setNegativeButton("取消", (dialog, which) -> activity.finish()).setCancelable(false);
        mTipDialog.create();
        mTipDialog.show();
    }

    public interface PermissionUtilsInter {

        List<PermissionBean> getApplyPermissions();

        androidx.appcompat.app.AlertDialog.Builder getTipAlertDialog();

        Dialog getTipDialog();

        androidx.appcompat.app.AlertDialog.Builder getTipAppSettingAlertDialog();

        Dialog getTipAppSettingDialog();
    }

    private interface OnDialogClickListener {

        void onPositiveButtonClickEvent();
    }
}


