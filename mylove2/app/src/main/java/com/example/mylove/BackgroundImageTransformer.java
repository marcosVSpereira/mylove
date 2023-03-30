package com.example.mylove;
import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;
import android.view.View;

public class BackgroundImageTransformer implements ViewPager2.PageTransformer {
    private final int switchDuration;

    public BackgroundImageTransformer(int switchDuration) {
        this.switchDuration = switchDuration;
    }

    @Override
    public void transformPage(@NonNull View page, float position) {
        int pageWidth = page.getWidth();
        float translationX = -position * (pageWidth / 2);
        page.setTranslationX(translationX);

        float alpha = 1 - Math.abs(position);
        page.setAlpha(alpha);

        if (position <= 0) {
            page.setTranslationZ(-1);
        } else {
            page.setTranslationZ(0);
        }

        if (position == 0) {
            page.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ViewPager2 viewPager = (ViewPager2) page.getParent().getParent();
                    if (viewPager != null) {
                        int currentItem = viewPager.getCurrentItem();
                        viewPager.setCurrentItem(currentItem + 1, true);
                    }
                }
            }, switchDuration);
        }
    }
}
