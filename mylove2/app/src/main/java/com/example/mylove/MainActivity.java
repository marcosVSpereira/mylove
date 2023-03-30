package com.example.mylove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private BackgroundImageAdapter backgroundImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);

        List<Integer> backgroundImages = loadDrawableImages();
        backgroundImageAdapter = new BackgroundImageAdapter(this, backgroundImages);
        viewPager.setAdapter(backgroundImageAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
    }

    private List<Integer> loadDrawableImages() {
        List<Integer> imageIds = loadImageIds();
        return imageIds;
    }

    private List<Integer> loadImageIds() {
        List<Integer> imageIds = new ArrayList<>();

        String[] imageNames = {
                "girl",
                "e3",
                "e4",
        };

        for (String imageName : imageNames) {
            int imageId = getResources().getIdentifier(imageName, "drawable", getPackageName());
            if (imageId != 0) {
                imageIds.add(imageId);
            }
        }

        return imageIds;
    }
}
