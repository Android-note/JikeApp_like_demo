package com.yy.jike;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @author yangyi 2017年10月16日15:33:42
 */
public class MainActivity extends AppCompatActivity {

    private static final String SCALE_X = "scaleX";
    private static final String SCALE_Y = "scaleY";
    private static final String ALPHA = "alpha";
    private long lastTime;
    private long time;
    public static long diff;
    private long time_down;

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
        lastTime = 0;

        showLikeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ButtonUtil.isFastDoubleClick(R.id.showLikeLayout)) {
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
                    numberText.changeAnimator(showLikeLayout, like.isSelected());
                }
            }
        });

        showLikeLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        setLikeButtonAnimator(likeLayout, 0.75f);
                        break;
                    case MotionEvent.ACTION_UP:
                        setLikeButtonAnimator(likeLayout, 1f);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void setLikeButtonAnimator(View view, float scale) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, SCALE_X, scale),
                ObjectAnimator.ofFloat(view, SCALE_Y, scale));
        animatorSet.start();
    }

    private void setCircleAnimator(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, ALPHA, 0.8f, 0),
                ObjectAnimator.ofFloat(view, SCALE_X, 0.5f, 1.1f),
                ObjectAnimator.ofFloat(view, SCALE_Y, 0.5f, 1.1f));
        animatorSet.setDuration(300);
        animatorSet.start();
    }

    private void setShiningAnimator(final View view, float... values) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, SCALE_X, values),
                ObjectAnimator.ofFloat(view, SCALE_Y, values));
        animatorSet.start();
    }
}
