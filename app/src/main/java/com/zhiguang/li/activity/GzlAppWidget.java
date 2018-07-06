package com.zhiguang.li.activity;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.jiongbull.jlog.JLog;
import com.zhiguang.li.R;

/**
 * Implementation of App Widget functionality.
 */
public class GzlAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.gzl_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    /**
     * 当小部件被添加时或者每次小部件更新时都会调用一次该方法，配置文件中配置小部件的更新周期 updatePeriodMillis，每次更新都会调用。对应广播 Action 为：ACTION_APPWIDGET_UPDATE 和 ACTION_APPWIDGET_RESTORED 。
     *
     * @param context
     * @param appWidgetManager
     * @param appWidgetIds
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
            JLog.d("桌面小控件____onUpdate");
        }
    }

    /**
     * 当小部件第一次被添加到桌面时回调该方法，可添加多次，但只在第一次调用。对用广播的 Action 为 ACTION_APPWIDGET_ENABLE。
     *
     * @param context
     */
    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        //小窗口创建了
        JLog.d("桌面小控件____onEnabled");
    }

    /**
     * 当最后一个该类型的小部件从桌面移除时调用，对应的广播的 Action 为 ACTION_APPWIDGET_DISABLED。
     *
     * @param context
     */
    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        //小窗口移除了
        JLog.d("桌面小控件____onDisabled");
    }

    /**
     * 每删除一个小部件就调用一次。对应的广播的 Action 为： ACTION_APPWIDGET_DELETED 。
     *
     * @param context
     * @param appWidgetIds
     */
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    /**
     * 当小部件从备份中还原，或者恢复设置的时候，会调用，实际用的比较少。对应广播的 Action 为 ACTION_APPWIDGET_RESTORED。
     *
     * @param context
     * @param oldWidgetIds
     * @param newWidgetIds
     */
    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
    }

    /**
     * 当小部件布局发生更改的时候调用。对应广播的 Action 为 ACTION_APPWIDGET_OPTIONS_CHANGED。
     *
     * @param context
     * @param appWidgetManager
     * @param appWidgetId
     * @param newOptions
     */
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    /**
     * AppWidgetProvider 重写了该方法，用于分发具体的时间给上述的方法。看看源码：
     *
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }
}

