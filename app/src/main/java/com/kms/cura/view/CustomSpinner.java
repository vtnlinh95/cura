package com.kms.cura.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

/**
 * Created by toanbnguyen on 7/18/2016.
 */
public class CustomSpinner extends Spinner {

    private SpinnerEventsListener mListener;
    private boolean mOpenInitiated = false;
    private boolean mToggleFlag = true;

    public CustomSpinner(Context context) {
        super(context);
    }

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean performClick() {
        mOpenInitiated = true;
        if (mListener != null) {
            mListener.onSpinnerOpened();
        }
        // this method shows the list of elements
        // we have to make the getSelectedItemPosition to return 0 (using the flag) so you can
        // fool the Spinner and let it think that the selected item is the first element
        // then set it back for the getSelectedItemPosition method to return the right value later
        mToggleFlag = false;
        boolean result = super.performClick();
        mToggleFlag = true;
        return result;
    }

    public void setSpinnerEventsListener(
            SpinnerEventsListener onSpinnerEventsListener) {
        mListener = onSpinnerEventsListener;
    }

    public void performClosedEvent() {
        mOpenInitiated = false;
        if (mListener != null) {
            mListener.onSpinnerClosed();
        }
    }

    public boolean hasBeenOpened() {
        return mOpenInitiated;
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasBeenOpened() && hasWindowFocus) {
            performClosedEvent();
        }
    }

    @Override
    public int getSelectedItemPosition() {
        if (!mToggleFlag) {
            return 0; // get us to the first element
        }
        return super.getSelectedItemPosition();
    }
}