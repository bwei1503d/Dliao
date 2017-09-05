package com.bw.dliao.service;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by muhanxi on 17/8/30.
 */

public class RobService extends AccessibilityService {


//    http://www.jianshu.com/p/4cd8c109cdfb


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                //界面点击
                System.out.println("eventType TYPE_VIEW_CLICKED= " + eventType);
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                //界面文字改动
                break;
        }

    }

    @Override
    public void onInterrupt() {


    }




}
