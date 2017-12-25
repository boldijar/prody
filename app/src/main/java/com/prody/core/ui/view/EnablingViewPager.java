package com.prody.core.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author Paul
 * @since 2017.12.25
 */

public class EnablingViewPager extends ViewPager {

    private boolean mEnabled;

    public EnablingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mEnabled = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.mEnabled) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.mEnabled) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.mEnabled = enabled;
    }
}
