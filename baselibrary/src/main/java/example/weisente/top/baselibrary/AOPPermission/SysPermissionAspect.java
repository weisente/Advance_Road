package example.weisente.top.baselibrary.AOPPermission;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import example.weisente.top.baselibrary.MPermissionUtils;
import example.weisente.top.baselibrary.Util.ContextUtil;
import example.weisente.top.baselibrary.annotation.Permission;

/**
 * Created by san on 2017/11/28.
 * 这个标注需要在
 */
@Aspect
public class SysPermissionAspect {
    @Around("execution(@example.weisente.top.baselibrary.annotation.Permission * *(..)) && @annotation(permission)")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint, final Permission permission) throws Throwable{
//        AppCompatActivity ac = (AppCompatActivity) App.getAppContext().getCurActivity();
        Object object = joinPoint.getThis();
        final Activity context = (Activity) ContextUtil.getContext(object);
        new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("为了应用可以正常使用，请您点击确认申请权限。")
                .setNegativeButton("取消", null)
                .setPositiveButton("允许", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        String[] value = permission.value();
                        MPermissionUtils.requestPermissionsResult(context, 1, permission.value()
                                , new MPermissionUtils.OnPermissionListener() {
                                    @Override
                                    public void onPermissionGranted() {
                                        try {
                                            joinPoint.proceed();//获得权限，执行原方法
                                            Log.e("测试","调试");
                                        } catch (Throwable e) {
                                            e.printStackTrace();
                                            Log.e("测试","出错");
                                        }
                                    }

                                    @Override
                                    public void onPermissionDenied() {
                                        MPermissionUtils.showTipsDialog(context);
                                        Log.e("测试","拒绝");
                                    }
                                });
                    }
                })
                .create()
                .show();
        Log.e("测试","进来了");




    }
}
