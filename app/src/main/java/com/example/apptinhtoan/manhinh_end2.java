package com.example.apptinhtoan;
import  android.media.MediaPlayer;
import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class manhinh_end2 extends AppCompatActivity {
    private TextView timerTextView;
    private TextView tvTongCau;
    private TextView tvCauSai;
    private TextView tvStar;

    private ImageView imgCross;
    private MediaPlayer mediaPlayer;
    @Override
    public void onBackPressed() {
        phatAmThanh();
        Intent intent1 = new Intent(manhinh_end2.this, MainActivity.class);
        startActivity(intent1);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_end_phepcong);

        // Khởi tạo các View
        timerTextView = findViewById(R.id.timer);
        tvTongCau = findViewById(R.id.tv_tongcau);
        tvCauSai = findViewById(R.id.tv_causai);
        imgCross = findViewById(R.id.img_cross);
        tvStar = findViewById(R.id.star_count);
        // Nhận các giá trị từ Intent
        Intent intent = getIntent();
        long totalTime = intent.getLongExtra("totalTime", 0);
        int totalQuestions = intent.getIntExtra("totalQuestions", 0);
        int incorrectAnswers = intent.getIntExtra("incorrectAnswers", 0);
        int truyensao = intent.getIntExtra("truyensao", 0);
        // Cập nhật các TextViews

        timerTextView.setText(String.valueOf(totalTime) + "");
        tvTongCau.setText("Tổng câu số câu đã làm: " + totalQuestions);
        tvCauSai.setText("        Tổng số câu sai: " + incorrectAnswers);
        tvStar.setText(String.valueOf(truyensao));


        // Sự kiện cho imgCross (Nút Đóng)
        imgCross.setOnClickListener(view -> {
            phatAmThanh();
            Intent intent1 = new Intent(manhinh_end2.this, MainActivity.class);
            startActivity(intent1);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }

    private void phatAmThanh() {
        if (mediaPlayer != null) {
            mediaPlayer.release(); // Giải phóng MediaPlayer trước khi tạo mới
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.sound);
        mediaPlayer.start();
    }
}
