package com.islabs.stackanimation;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout upper, lower;
    TextView first, second;
    boolean isUpperExpanded = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        upper = (LinearLayout) findViewById(R.id.upper);
        lower = (LinearLayout) findViewById(R.id.lower);
        first = (TextView) findViewById(R.id.first);
        second = (TextView) findViewById(R.id.second);
        lower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUpperExpanded)
                    expand(lower, upper, second, first);
            }
        });


        upper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isUpperExpanded)
                    expand(upper, lower, first, second);
            }
        });
        lower.performClick();
    }

    private void expand(final LinearLayout expand, final LinearLayout collapse, final TextView expandText, final TextView collapseText) {
        isUpperExpanded = !isUpperExpanded;
        //setup these value according to requirement
        final double finalWidth = 8.4;
        final double finalHeight = 1.4;
        final double finalTextSize = 0.2;
        ValueAnimator valueAnimator = ValueAnimator.ofInt(60, 100/* values in percent */);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                expand.getLayoutParams().height = (int) ((int) animation.getAnimatedValue() * finalHeight);
                expand.getLayoutParams().width = (int) ((int) animation.getAnimatedValue() * finalWidth);
                expandText.setTextSize(TypedValue.COMPLEX_UNIT_SP, (float) ((int) animation.getAnimatedValue() * finalTextSize));
                expand.requestLayout();
            }
        });
        ValueAnimator valueAnimator1 = ValueAnimator.ofInt(100, 60);
        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                collapse.getLayoutParams().height = (int) ((int) animation.getAnimatedValue() * finalHeight);
                collapseText.setTextSize(TypedValue.COMPLEX_UNIT_SP, (float) ((int) animation.getAnimatedValue() * finalTextSize));
                collapse.getLayoutParams().width = (int) ((int) animation.getAnimatedValue() * finalWidth);
                collapse.requestLayout();
            }
        });
        valueAnimator1.setDuration(200);
        valueAnimator.setDuration(200);
        valueAnimator.start();
        valueAnimator1.start();
    }
}
