package com.example.mylove;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;
import android.os.Looper;

import android.net.Uri;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class IntroductionActivity extends AppCompatActivity {
    private Button buttonOptionA;
    private Button buttonOptionB;
    private TextView tvStory;
    private long textAnimationDuration;
    private MediaPlayer backgroundMusicPlayer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_introduction);

        buttonOptionA = findViewById(R.id.button_option_a);
        buttonOptionB = findViewById(R.id.button_option_b);
        tvStory = findViewById(R.id.tv_story);

        buttonOptionA.setVisibility(View.INVISIBLE);
        buttonOptionB.setVisibility(View.INVISIBLE);

        buttonOptionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroductionActivity.this, Part1Activity.class);
                startActivity(intent);
            }
        });

        buttonOptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroductionActivity.this, Part1Activity.class);
                startActivity(intent);
            }
        });

        String storyText = getString(R.string.story_text);
        int audioDuration = getAudioDuration(R.raw.aud);
        textAnimationDuration = calculateTextAnimationDuration(audioDuration, storyText);
        typeTextAnimation(storyText, textAnimationDuration);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                backgroundMusicPlayer = playBackgroundMusic(R.raw.apo);
            }
        });

        ExoPlayer audioPlayer = playAudioOnce(R.raw.aud);
    }

    private MediaPlayer playBackgroundMusic(int musicResourceId) {
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), musicResourceId);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        Log.d("AudioPlayback", "Background music started");
        return mediaPlayer;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
            backgroundMusicPlayer.release();
            backgroundMusicPlayer = null;
        }
    }

    private void typeTextAnimation(final String fullText, long textAnimationDuration) {
        ValueAnimator textAnimation = ValueAnimator.ofInt(0, fullText.length());
        textAnimation.setDuration(textAnimationDuration);
        textAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentTextLength = (int) animation.getAnimatedValue();
                tvStory.setText(fullText.substring(0, currentTextLength));

                if (currentTextLength == fullText.length()) {
                    buttonOptionA.setVisibility(View.VISIBLE);
                    buttonOptionB.setVisibility(View.VISIBLE);
                }
            }
        });
        textAnimation.start();
    }

    private MediaPlayer createMediaPlayer(int audioResourceId) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, audioResourceId);
        mediaPlayer.setLooping(false);
        return mediaPlayer;
    }

    private ExoPlayer playAudioOnce(int audioResourceId) {
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(C.CONTENT_TYPE_MUSIC)
                .setUsage(C.USAGE_GAME)
                .build();

        SimpleExoPlayer exoPlayer = new SimpleExoPlayer.Builder(this).setAudioAttributes(audioAttributes, true)
                .build();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "MyLove"));
        MediaItem mediaItem = MediaItem.fromUri(
                Uri.parse("android.resource://" + getPackageName() + "/" + audioResourceId));
        ProgressiveMediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(mediaItem);

        exoPlayer.setMediaSource(mediaSource);
        exoPlayer.prepare();

        exoPlayer.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int playbackState) {
                if (playbackState == Player.STATE_ENDED) {
                    exoPlayer.release();
                }
            }
        });

        exoPlayer.play();
        return exoPlayer;
    }

    private long calculateTextAnimationDuration(int audioDuration, String text) {
        int textLength = text.length();
        long durationPerCharacter = (long) audioDuration * 1000 / textLength;
        return durationPerCharacter * textLength;
    }

    private int getAudioDuration(int audioResourceId) {
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), audioResourceId);
        int duration = mediaPlayer.getDuration() / 1000;
        mediaPlayer.release();

        Log.d("Audio Duration", "Duration: " + duration + " seconds");

        return duration;
    }

  
    }

