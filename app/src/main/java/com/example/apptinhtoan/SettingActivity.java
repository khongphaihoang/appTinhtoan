package com.example.apptinhtoan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    private ImageView imgCross; // Nút đóng

    private MediaPlayer mediaPlayer;
    private ImageView imgSw; // Nút bật/tắt âm thanh
    private boolean isSoundOn; // Trạng thái âm thanh
    private SharedPreferences sharedPreferences; // Lưu trạng thái

    @Override
    public void onBackPressed() {
        phatAmThanh();
        Intent intent1 = new Intent(SettingActivity.this, MainActivity.class);
        startActivity(intent1);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cai_dat);

        // Liên kết các View
        imgCross = findViewById(R.id.img_cross);
        imgSw = findViewById(R.id.img_sw);

        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        isSoundOn = sharedPreferences.getBoolean("SoundState", true); // Lấy trạng thái âm thanh đã lưu

        // Cập nhật hình ảnh ban đầu của imgSw theo trạng thái âm thanh
        updateSwitchImage();

        // Sự kiện cho imgCross (Nút Đóng)
        imgCross.setOnClickListener(view -> {
            Intent intent = new Intent(SettingActivity.this, MainActivity.class);
            phatAmThanh();
            startActivity(intent); // Quay về MainActivity
            finish(); // Đóng SettingActivity
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        // Sự kiện cho imgSw (Nút bật/tắt âm thanh)
        imgSw.setOnClickListener(view -> toggleSound());
    }

    // Hàm bật/tắt âm thanh
    private void toggleSound() {
        isSoundOn = !isSoundOn; // Đảo trạng thái âm thanh
        updateSwitchImage(); // Cập nhật hình ảnh nút gạt
        saveSoundState(); // Lưu trạng thái âm thanh

        if (isSoundOn) {
            unmuteAllSounds(); // Bật âm thanh
            phatAmThanh();
        } else {
            muteAllSounds(); // Tắt âm
        }
    }

    // Hàm cập nhật hình ảnh của nút gạt
    private void updateSwitchImage() {
        if (isSoundOn) {
            imgSw.setImageResource(R.drawable.sw_on); // Ảnh bật
        } else {
            imgSw.setImageResource(R.drawable.sw_off); // Ảnh tắt
        }
    }

    // Lưu trạng thái âm thanh vào SharedPreferences
    private void saveSoundState() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("SoundState", isSoundOn); // Lưu trạng thái âm thanh
        editor.apply();
    }

    // Hàm tắt âm thanh toàn bộ ứng dụng
    private void muteAllSounds() {
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioManager != null) {
            audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true); // Tắt âm thanh
        }
    }

    // Hàm bật lại âm thanh toàn bộ ứng dụng
    private void unmuteAllSounds() {
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioManager != null) {
            audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false); // Bật lại âm thanh
        }
    }

    private void phatAmThanh() {
        // Phát âm thanh khi nhấn nút hoặc hình ảnh
        if (mediaPlayer != null) {
            mediaPlayer.release(); // Giải phóng MediaPlayer trước khi tạo mới
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.sound);
        mediaPlayer.start(); // Phát âm thanh
    }

}
