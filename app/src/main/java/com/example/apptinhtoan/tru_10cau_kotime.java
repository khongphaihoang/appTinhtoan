package com.example.apptinhtoan;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class tru_10cau_kotime extends AppCompatActivity {

    private ImageView imgLevel1, imgLevel2, imgLevel3, imgCross;
    private Button btnPlay;
    private MediaPlayer mediaPlayer;
    private int currentImgLevel1 = R.drawable.img_9; // Trạng thái gốc
    private int currentImgLevel2 = R.drawable.img_11; // Trạng thái gốc
    private int currentImgLevel3 = R.drawable.img_13; // Trạng thái gốc
    private String selectedLevel = "";  // Biến lưu trữ cấp độ đã chọn


    @Override
    public void onBackPressed() {
        phatAmThanh();
        Intent intent1 = new Intent(tru_10cau_kotime.this, MainActivity.class);
        startActivity(intent1);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tru_10cau_kotime);

        // Liên kết các ImageView và Button
        imgLevel1 = findViewById(R.id.img_level_1);
        imgLevel2 = findViewById(R.id.img_level_2);
        imgLevel3 = findViewById(R.id.img_level_3);
        imgCross = findViewById(R.id.img_cross);
        btnPlay = findViewById(R.id.btn_play);

        // Cài đặt ảnh ban đầu
        imgLevel1.setImageResource(currentImgLevel1);
        imgLevel2.setImageResource(currentImgLevel2);
        imgLevel3.setImageResource(currentImgLevel3);

        // Xử lý sự kiện nhấn cho imgLevel1
        imgLevel1.setOnClickListener(v -> {
            resetImages(); // Đặt lại trạng thái các ảnh khác
            if (currentImgLevel1 == R.drawable.img_9) {
                currentImgLevel1 = R.drawable.img_10; // Chuyển sang ảnh 10
                selectedLevel = "level1";  // Lưu cấp độ người dùng đã chọn
                phatAmThanh();
            } else {
                currentImgLevel1 = R.drawable.img_9; // Quay lại ảnh 9
                selectedLevel = ""; // Xóa lựa chọn
            }
            imgLevel1.setImageResource(currentImgLevel1); // Cập nhật ảnh
        });

        // Xử lý sự kiện nhấn cho imgLevel2
        imgLevel2.setOnClickListener(v -> {
            resetImages(); // Đặt lại trạng thái các ảnh khác
            if (currentImgLevel2 == R.drawable.img_11) {
                currentImgLevel2 = R.drawable.img_12; // Chuyển sang ảnh 12
                selectedLevel = "level2";  // Lưu cấp độ người dùng đã chọn
                phatAmThanh();
            } else {
                currentImgLevel2 = R.drawable.img_11; // Quay lại ảnh 11
                selectedLevel = ""; // Xóa lựa chọn
            }
            imgLevel2.setImageResource(currentImgLevel2); // Cập nhật ảnh
        });

        // Xử lý sự kiện nhấn cho imgLevel3
        imgLevel3.setOnClickListener(v -> {
            resetImages(); // Đặt lại trạng thái các ảnh khác
            if (currentImgLevel3 == R.drawable.img_13) {
                currentImgLevel3 = R.drawable.img_14; // Chuyển sang ảnh 14
                selectedLevel = "level3";  // Lưu cấp độ người dùng đã chọn
                phatAmThanh();
            } else {
                currentImgLevel3 = R.drawable.img_13; // Quay lại ảnh 13
                selectedLevel = ""; // Xóa lựa chọn
            }
            imgLevel3.setImageResource(currentImgLevel3); // Cập nhật ảnh
        });

        imgCross.setOnClickListener(v -> {
            Intent intent = new Intent(tru_10cau_kotime.this, MainActivity.class);
            phatAmThanh();
            startActivity(intent); // Quay lại MainActivity
            finish(); // Đóng LevelActivity
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        });

        // Xử lý sự kiện nhấn nút "Chơi"
        btnPlay.setOnClickListener(v -> {
            if (!selectedLevel.isEmpty()) {
                // Nếu người dùng đã chọn một cấp độ, chuyển đến Activity tương ứng
                Intent intent;
                if (selectedLevel.equals("level1")) {
                    intent = new Intent(tru_10cau_kotime.this, tru_10cau_kotime_sc.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                } else if (selectedLevel.equals("level2")) {
                    intent = new Intent(tru_10cau_kotime.this, tru_10cau_kotime_tc.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                } else if (selectedLevel.equals("level3")) {
                    intent = new Intent(tru_10cau_kotime.this, tru_10cau_kotime_cc.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                } else {
                    return; // Nếu không có cấp độ được chọn, không làm gì
                }

            } else {
                // Thông báo nếu người dùng chưa chọn cấp độ
                Toast.makeText(this, "Vui lòng chọn cấp độ!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Hàm đặt lại các ảnh về trạng thái ban đầu
    private void resetImages() {
        if (currentImgLevel1 != R.drawable.img_9) {
            currentImgLevel1 = R.drawable.img_9;
            imgLevel1.setImageResource(currentImgLevel1);
        }
        if (currentImgLevel2 != R.drawable.img_11) {
            currentImgLevel2 = R.drawable.img_11;
            imgLevel2.setImageResource(currentImgLevel2);
        }
        if (currentImgLevel3 != R.drawable.img_13) {
            currentImgLevel3 = R.drawable.img_13;
            imgLevel3.setImageResource(currentImgLevel3);
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
