package com.yy.jike;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by yy on 2017/10/16.
 */

public class NumberAnimatorTextView extends AppCompatTextView {

    private int unitNumber;
    private String newUnitNumber;
    private TextView newUnitNumberTextView;

    public NumberAnimatorTextView(Context context) {
        super(context);
        init();
    }

    public NumberAnimatorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NumberAnimatorTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        unitNumber = Integer.valueOf(getText().toString()
                .substring(getText().toString().length() - 1,
                        getText().toString().length()));
    }

    private void setSelectedNumber(boolean isSelected) {
        if (isSelected) {
            newUnitNumber = String.valueOf(unitNumber + 1);
        } else {
            newUnitNumber = String.valueOf(unitNumber);
        }
        StringBuilder stringBuilder = new StringBuilder(getText().toString());
        stringBuilder.replace(getText().toString().length() - 1,
                getText().toString().length(), newUnitNumber);
        setText(stringBuilder.toString());
    }

    public void changeAnimator(LinearLayout linearLayout, boolean isSelected) {
        setSelectedNumber(isSelected);
        newUnitNumberTextView = new TextView(getContext());
        newUnitNumberTextView.setText(newUnitNumber);
        newUnitNumberTextView.setTextSize(55);
        newUnitNumberTextView.setTextColor(ContextCompat.getColor(getContext(),
                android.R.color.darker_gray));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(-155, 0, 0, 0);
        linearLayout.addView(newUnitNumberTextView, params);

        if (isSelected) {
            increaseAnimator(linearLayout, newUnitNumberTextView);
        } else {
            decreaseAnimator(linearLayout, newUnitNumberTextView);
        }
    }

    private void increaseAnimator(final LinearLayout linearLayout, final View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "translationY", -1000f),
                ObjectAnimator.ofFloat(view, "alpha", 0.2f)
        );
        animatorSet.setDuration(2000);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                linearLayout.removeView(view);
            }
        });
    }

    private void decreaseAnimator(final LinearLayout linearLayout, final View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "translationY", 1000f),
                ObjectAnimator.ofFloat(view, "alpha", 0.2f)
        );
        animatorSet.setDuration(2000);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                linearLayout.removeView(view);
            }
        });
    }
}
