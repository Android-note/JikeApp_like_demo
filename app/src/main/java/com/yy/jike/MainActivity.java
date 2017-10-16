package com.yy.jike;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @author yangyi 2017年10月16日15:33:42
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        final ImageView like = (ImageView) findViewById(R.id.like);
        final ImageView shining = (ImageView) findViewById(R.id.shining);
        final ImageView circle = (ImageView) findViewById(R.id.circle);
        final LinearLayout likeLayout = (LinearLayout) findViewById(R.id.likeLayout);
        final LinearLayout showLikeLayout = (LinearLayout) findViewById(R.id.showLikeLayout);
        final NumberAnimatorTextView numberText = (NumberAnimatorTextView) findViewById(R.id.numberText);

        showLikeLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        setLikeButtonAnimator(likeLayout, 0.75f);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (like.isSelected()) {
                            like.setSelected(false);
                            shining.setVisibility(View.INVISIBLE);
                            setShiningAnimator(shining, 1f, 0);
                        } else {
                            like.setSelected(true);
                            shining.setVisibility(View.VISIBLE);
                            setCircleAnimator(circle);
                            setShiningAnimator(shining, 0, 1f);
                        }
                        setLikeButtonAnimator(likeLayout, 1f);
                        numberText.changeAnimator(showLikeLayout, like.isSelected());
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void setLikeButtonAnimator(View view, float scale) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "scaleX", scale),
                ObjectAnimator.ofFloat(view, "scaleY", scale));
        animatorSet.start();
    }

    private void setCircleAnimator(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "alpha", 0.8f, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1.1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1.1f));
        animatorSet.setDuration(300);
        animatorSet.start();
    }

    private void setShiningAnimator(final View view, float... values) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "scaleX", values),
                ObjectAnimator.ofFloat(view, "scaleY", values));
        animatorSet.start();
    }
}
