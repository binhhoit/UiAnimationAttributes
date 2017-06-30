package com.example.pc_asus.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

/**
 * Created by PC_ASUS on 6/26/2017.
 */

public class AnimationLayout extends LinearLayout {
    int getChild = 0;

    private Animation.AnimationListener animationListener;
    private Context context;

    public AnimationLayout(Context context) {
        super(context);
    }

    public AnimationLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AnimationLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, @Nullable AttributeSet attrs) {
        boolean isShowHorizontal;
        initVariables(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AnimationLayout);

        final int getSize = typedArray.getIndexCount();
        for (int i = 0; i < getSize; ++i) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.AnimationLayout_showIn:
                    isShowHorizontal = typedArray.getBoolean(attr, false);
                    setShowInHorizontal(isShowHorizontal);
                    break;
                case R.styleable.AnimationLayout_showOut:
                    isShowHorizontal = typedArray.getBoolean(attr, false);
                    setShowOutHorizontal(isShowHorizontal);
                    break;
            }
        }
        typedArray.recycle();
    }

    public void setShowOutHorizontal(boolean isShowHorizontal) {

        int sizeChile = getChildCount();
        if (isShowHorizontal == true && getChild < sizeChile) {

            final View child = getChildAt(getChild);
            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    child.startAnimation(initFadeOutAnimation());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            child.setVisibility(View.GONE);
                            getChild++;
                            if (getChild < 4) {
                                setShowOutHorizontal(true);
                            } else {
                                getChild = 0;

                                setOrientation(HORIZONTAL);

                                setShowInHorizontal(true);

                            }

                        }
                    }, 1200);
                }
            }, 100);

        }

    }


    public void setShowInHorizontal(boolean isShowHorizontal) {


        int sizeChile = getChildCount();
        if (isShowHorizontal == true && getChild < sizeChile) {

            final View child = getChildAt(getChild);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    child.setVisibility(View.VISIBLE);

                    ViewGroup.LayoutParams params = child.getLayoutParams();
                    params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    params.width = ViewGroup.LayoutParams.WRAP_CONTENT;

                    child.setLayoutParams(params);
                    child.startAnimation(initFadeInAnimation());

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            getChild++;
                            if (getChild < 4) {
                                setShowInHorizontal(true);
                            } else {
                                getChild = 0;

                                setShowOutHorizontal(true);
                            }
                        }
                    }, 1100);
                }
            }, 100);

        }

    }

    @Override
    public View getChildAt(int index) {
        return super.getChildAt(index);
    }

    private Animation initFadeInAnimation() {
        AlphaAnimation animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initFadeOutAnimation() {
        AlphaAnimation animation = new AlphaAnimation(1f, 0f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private void initVariables(final Context context) {
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
               /* Toast.makeText(context, "Animation Stopped",
                        Toast.LENGTH_SHORT).show();
*/
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }
}
