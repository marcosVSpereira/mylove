package com.example.mylove;
import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;
import android.view.View;

public class BackgroundImageTransformer implements ViewPager2.PageTransformer {
    private final int duration;

    public BackgroundImageTransformer(int duration) {
        this.duration = duration;
    }

    @Override
    public void transformPage(@NonNull View page, float position) {
        float alpha = 0.5f;
        if (position < -1 || position > 1) {
            page.setAlpha(alpha);
        } else if (position <= 0 || position <= 1) {
            page.setAlpha(alpha + (1 - alpha) * (1 - Math.abs(position)));
        } else if (position <= 0.5) {
            page.setAlpha(alpha + (1 - alpha) * (1 - Math.abs(position)));
            page.setScaleX(0.5f + 0.5f * (1 - Math.abs(position)));
            page.setScaleY(0.5f + 0.5f * (1 - Math.abs(position)));
        } else {
            page.setAlpha(0.5f + (1 - 0.5f) * (1 - Math.abs(position)));
            page.setScaleX(1.0f - 0.5f * (1 - Math.abs(position)));
            page.setScaleY(1.0f - 0.5f * (1 - Math.abs(position)));
        }
    }
}
