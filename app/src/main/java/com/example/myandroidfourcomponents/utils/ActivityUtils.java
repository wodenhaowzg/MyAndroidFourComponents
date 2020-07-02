package com.example.myandroidfourcomponents.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

public class ActivityUtils {

    public static String getActivityInfos(Context context){
        StringBuilder sb = new StringBuilder();
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        // 获取程序关联的任务栈列表
        List<ActivityManager.AppTask> appTasks = manager.getAppTasks();
        for (ActivityManager.AppTask appTask : appTasks) {
            ActivityManager.RecentTaskInfo taskInfo = appTask.getTaskInfo();
            sb.append("AppTask : ").append("\n")
                    .append("\t[").append(" id : ").append(taskInfo.id).append(" // 如果此任务当前正在运行，则为它的标识符。 如果未运行，则为-1。").append("\n")
                    .append("\t[").append(" persistent id : ").append(taskInfo.persistentId).append(" 此任务的真实标识符，即使未运行也有效。 ").append("\n")
                    .append("\t[").append(" base intent : ").append(taskInfo.baseIntent.toString()).append(" // 用于启动任务的原始Intent。 您可以使用此Intent重新启动任务（如果它不再运行）或将当前任务放在最前面。 ").append("\n")
                    .append("\t[").append(" origin activity : ").append(taskInfo.origActivity).append(" // 如果此任务是从别名启动的，则这是最初启动的实际活动组件。 在这种情况下，baseIntent的组件是别名所引用的实际活动实现的名称。 否则为空。 ").append("\n")
                    .append("\t[").append(" description : ").append(taskInfo.description).append(" // 任务的最后状态的描述。 ").append("\n")
                    .append("\t[").append(" task description : ").append(taskInfo.taskDescription).append(" // The recent activity values for the highest activity in the stack to have set the values.").append("\n")
                    .append("\t[").append(" affiliated task id : ").append(taskInfo.affiliatedTaskId).append(" // 与其他任务栈分组的任务栈从属关系").append("\n")
                    .append("\t[").append(" base activity : ").append(taskInfo.baseActivity).append(" // 任务栈第一个启动的 activity ").append("\n")
                    .append("\t[").append(" top activity : ").append(taskInfo.topActivity).append(" // 任务栈顶端 activity ").append("\n")
                    .append("\t[").append(" num activitys : ").append(taskInfo.numActivities).append(" // 当前任务栈包含的 activity 数量 ").append("\n");
        }
//        List<ActivityManager.RunningTaskInfo> runningTasks = manager.getRunningTasks(5);
//        for (ActivityManager.RunningTaskInfo runningTask : runningTasks) {
//            MyLog.d(TAG, "iterator runningTask : " + runningTask.toString());
//        }
        return sb.toString();
    }
}
