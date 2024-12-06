package com.example.apptinhtoan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;

public class MainActivity extends AppCompatActivity {

    private ImageView thuMuc1, thuMuc2, thuMuc3, thuMuc4;
    private MediaPlayer mediaPlayer; // Đối tượng MediaPlayer
    private TextView thongBao; // TextView hiển thị thông báo
    private Handler handler = new Handler(); // Để quản lý thời gian hiển thị thông báo
    private Runnable hideRunnable; // Runnable để ẩn thông báo

    private boolean isThuMuc1Selected = true; // Mặc định chọn Thư mục 1
    private boolean isThuMuc2Selected = false;

    private boolean isThuMuc3Selected = false;
    private boolean isThuMuc4Selected = false;

    private SharedPreferences sharedPreferences; // Để lưu trạng thái Thư mục

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cài đặt giao diện toàn màn hình
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        setContentView(R.layout.activity_main);

        // Áp dụng padding theo thanh trạng thái
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets thanhHeThong = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(thanhHeThong.left, thanhHeThong.top, thanhHeThong.right, thanhHeThong.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        // Liên kết các thư mục và TextView thông báo
        thuMuc1 = findViewById(R.id.img_folder_1);
        thuMuc2 = findViewById(R.id.img_folder_2);
        thuMuc3 = findViewById(R.id.img_folder_3);
        thuMuc4 = findViewById(R.id.img_folder_4);
        thongBao = findViewById(R.id.tv_thong_bao);

        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences("AppState", MODE_PRIVATE);
        isThuMuc1Selected = sharedPreferences.getBoolean("isThuMuc1Selected", true);
        isThuMuc2Selected = sharedPreferences.getBoolean("isThuMuc2Selected", false);
        isThuMuc3Selected = sharedPreferences.getBoolean("isThuMuc3Selected", false);
        isThuMuc4Selected = sharedPreferences.getBoolean("isThuMuc4Selected", false);

        // Cập nhật trạng thái thư mục được chọn lúc khởi động
        if (isThuMuc1Selected) {
            xuLyKhiClick(thuMuc1);
        } else if (isThuMuc2Selected) {
            xuLyKhiClick(thuMuc2);
        } else if (isThuMuc3Selected) {
            xuLyKhiClick(thuMuc3);
        } else if (isThuMuc4Selected) {
            xuLyKhiClick(thuMuc4);
        }

        // Sự kiện nhấn vào từng thư mục
        thuMuc1.setOnClickListener(view -> {
            phatAmThanh();
            isThuMuc1Selected = true;
            isThuMuc2Selected = false;
            isThuMuc3Selected = false;
            isThuMuc4Selected = false;
            saveState(); // Lưu trạng thái
            xuLyKhiClick(thuMuc1);
            hienThongBao("Thử thách tốc độ của 10 câu trả lời");
        });

        thuMuc2.setOnClickListener(view -> {
            phatAmThanh();
            isThuMuc1Selected = false;
            isThuMuc2Selected = true;
            isThuMuc3Selected = false;
            isThuMuc4Selected = false;
            saveState(); // Lưu trạng thái
            xuLyKhiClick(thuMuc2);
            hienThongBao("Thử thách 10 câu trả lời đúng");
        });

        thuMuc3.setOnClickListener(view -> {
            phatAmThanh();
            isThuMuc1Selected = false;
            isThuMuc2Selected = false;
            isThuMuc3Selected = true;
            isThuMuc4Selected = false;
            saveState(); // Lưu trạng thái
            xuLyKhiClick(thuMuc3);
            hienThongBao("Thử thách số lượng câu trả lời đúng. Có giới hạn thời gian");
        });

        thuMuc4.setOnClickListener(view -> {
            phatAmThanh();
            isThuMuc1Selected = false;
            isThuMuc2Selected = false;
            isThuMuc3Selected = false;
            isThuMuc4Selected = true;
            saveState(); // Lưu trạng thái
            xuLyKhiClick(thuMuc4);
            hienThongBao("Thử thách số lượng câu trả lời đúng. Có giới hạn thời gian cho mỗi câu hỏi");
        });


        // Liên kết các nút phép toán
        ImageView ivAdd = findViewById(R.id.iv_add);
        ImageView ivTru = findViewById(R.id.iv_tru);
        ImageView ivNhan = findViewById(R.id.iv_nhan);
        ImageView ivChia = findViewById(R.id.iv_chia);

        // Sự kiện nhấn vào phép cộng
        ivAdd.setOnClickListener(view -> {
            phatAmThanh();
            if (isThuMuc1Selected) { // Kiểm tra trạng thái Thư mục 1
                chuyenSangLevelActivity("Phép cộng");
            } else if (isThuMuc2Selected) { // Kiểm tra trạng thái Thư mục 1
                chuyenSangLevel2Activity("Phép cộng");
            } else if (isThuMuc3Selected) { // Kiểm tra trạng thái Thư mục 1
                chuyenSangLevel3Activity("Phép cộng");
            } else if (isThuMuc4Selected) { // Kiểm tra trạng thái Thư mục 1
                chuyenSangLevel4Activity("Phép cộng");
            } else {
                hienThongBao("Vui lòng chọn Thư mục trước khi thực hiện phép cộng");
            }
        });


        //sự kiện nhấn phép trừ
        ivTru.setOnClickListener(view -> {
            phatAmThanh();
            if (isThuMuc1Selected) { // Kiểm tra trạng thái Thư mục 1
                lvtru("Phép trừ");
            } else if (isThuMuc2Selected) { // Kiểm tra trạng thái Thư mục 2
                lvtru2("Phép trừ");
            } else if (isThuMuc3Selected) { // Kiểm tra trạng thái Thư mục 2
                lvtru3("Phép trừ");
            } else if (isThuMuc4Selected) { // Kiểm tra trạng thái Thư mục 2
                lvtru4("Phép trừ");
            } else {
                hienThongBao("Vui lòng chọn Thư mục trước khi thực hiện phép trừ");
            }
        });

        //sự kiện nhấn phép nhân
        ivNhan.setOnClickListener(view -> {
            phatAmThanh();
            if (isThuMuc1Selected) { // Kiểm tra trạng thái Thư mục 1
                lvnhan("Phép nhân");
            } else if (isThuMuc2Selected) { // Kiểm tra trạng thái Thư mục 2
                lvnhan1("Phép nhân");
            } else if (isThuMuc3Selected) { // Kiểm tra trạng thái Thư mục 2
                lvnhan2("Phép nhân");
            } else if (isThuMuc4Selected) { // Kiểm tra trạng thái Thư mục 2
                lvnhan3("Phép nhân");
            } else {
                hienThongBao("Vui lòng chọn Thư mục trước khi thực hiện phép nhân");
            }
        });

        //sự kiện nhấn phép chia
        ivChia.setOnClickListener(view -> {
            phatAmThanh();
            if (isThuMuc1Selected) { // Kiểm tra trạng thái Thư mục 1
                lvchia("Phép chia");
            } else if (isThuMuc2Selected) { // Kiểm tra trạng thái Thư mục 2
                lvchia1("Phép chia");
            } else if (isThuMuc3Selected) { // Kiểm tra trạng thái Thư mục 2
                lvchia2("Phép chia");
            } else if (isThuMuc4Selected) { // Kiểm tra trạng thái Thư mục 2
                lvchia3("Phép chia");
            } else {
                hienThongBao("Vui lòng chọn Thư mục trước khi thực hiện phép chia");
            }
        });

        // Liên kết nút cài đặt và thoát
        ImageView menuSetting = findViewById(R.id.img_setting);
        ImageView menuExit = findViewById(R.id.btn_exit);

        // Xử lý sự kiện nhấn nút cài đặt
        menuSetting.setOnClickListener(v -> {
            phatAmThanh();
            try {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Xử lý sự kiện nhấn nút thoát
        menuExit.setOnClickListener(v -> {
            phatAmThanh();
            hienHopThoaiXacNhanThoat(); // Hiển thị hộp thoại xác nhận
        });
    }

    private void saveState() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isThuMuc1Selected", isThuMuc1Selected);
        editor.putBoolean("isThuMuc2Selected", isThuMuc2Selected);
        editor.putBoolean("isThuMuc3Selected", isThuMuc3Selected);
        editor.putBoolean("isThuMuc4Selected", isThuMuc4Selected);
        editor.apply();
    }

    private void hienHopThoaiXacNhanThoat() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Xác nhận thoát");
        builder.setMessage("Bạn có chắc chắn muốn thoát ứng dụng không?");

        // Nút "Có" để thoát
        builder.setPositiveButton("Có", (dialog, which) -> {
            phatAmThanh();
            finishAffinity(); // Đóng tất cả Activity liên quan
        });

        // Nút "Không" để hủy
        builder.setNegativeButton("Không", (dialog, which) -> dialog.dismiss());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void xuLyKhiClick(ImageView thuMucDuocChon) {
        datTrangThaiMacDinh();
        thuMucDuocChon.clearColorFilter();
    }

    private ColorMatrixColorFilter locAnhDenTrang(float cuongDo) {
        ColorMatrix maTranMau = new ColorMatrix();
        maTranMau.setSaturation(cuongDo);
        return new ColorMatrixColorFilter(maTranMau);
    }

    private void datTrangThaiMacDinh() {
        ColorMatrixColorFilter boLocDenTrangToanPhan = locAnhDenTrang(0f);
        ColorMatrixColorFilter boLocDenTrangMotPhan = locAnhDenTrang(0.1f);

        thuMuc1.setColorFilter(boLocDenTrangToanPhan);
        thuMuc2.setColorFilter(boLocDenTrangToanPhan);
        thuMuc3.setColorFilter(boLocDenTrangToanPhan);
        thuMuc4.setColorFilter(boLocDenTrangMotPhan);
    }

    private void phatAmThanh() {
        if (mediaPlayer != null) {
            mediaPlayer.release(); // Giải phóng MediaPlayer trước khi tạo mới
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.sound);
        mediaPlayer.start();
    }

    private void hienThongBao(String message) {
        if (hideRunnable != null) {
            handler.removeCallbacks(hideRunnable);
        }
        thongBao.setText(message);
        thongBao.setVisibility(View.VISIBLE);

        hideRunnable = () -> thongBao.setVisibility(View.GONE);
        handler.postDelayed(hideRunnable, 3000);
    }

    private void chuyenSangLevelActivity(String phepToan) {
        Intent intent = new Intent(MainActivity.this, LevelActivity.class);
        intent.putExtra("PHEP_TOAN", phepToan);
        startActivity(intent);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private void chuyenSangLevel2Activity(String phepToan) {
        Intent intent = new Intent(MainActivity.this, cong_10cau_kotime.class);
        intent.putExtra("PHEP_TOAN", phepToan);
        startActivity(intent);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private void chuyenSangLevel3Activity(String phepToan) {
        Intent intent = new Intent(MainActivity.this, cong_allcau_cotime.class);
        intent.putExtra("PHEP_TOAN", phepToan);
        startActivity(intent);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private void chuyenSangLevel4Activity(String phepToan) {
        Intent intent = new Intent(MainActivity.this, cong_allcau_kotime.class);
        intent.putExtra("PHEP_TOAN", phepToan);
        startActivity(intent);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private void lvtru(String phepToan) {
        Intent intent = new Intent(MainActivity.this, tru_10cau_cotime.class);
        intent.putExtra("PHEP_TOAN", phepToan);
        startActivity(intent);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    private void lvtru2(String phepToan) {
        Intent intent = new Intent(MainActivity.this, tru_10cau_kotime.class);
        intent.putExtra("PHEP_TOAN", phepToan);
        startActivity(intent);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    private void lvtru3(String phepToan) {
        Intent intent = new Intent(MainActivity.this, tru_allcau_cotime.class);
        intent.putExtra("PHEP_TOAN", phepToan);
        startActivity(intent);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    private void lvtru4(String phepToan) {
        Intent intent = new Intent(MainActivity.this, tru_allcau_kotime.class);
        intent.putExtra("PHEP_TOAN", phepToan);
        startActivity(intent);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    private void lvnhan(String phepToan) {
        Intent intent = new Intent(MainActivity.this, nhan_10cau_cotime.class);
        intent.putExtra("PHEP_TOAN", phepToan);
        startActivity(intent);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    private void lvnhan1(String phepToan) {
        Intent intent = new Intent(MainActivity.this, nhan_10cau_kotime.class);
        intent.putExtra("PHEP_TOAN", phepToan);
        startActivity(intent);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    private void lvnhan2(String phepToan) {
        Intent intent = new Intent(MainActivity.this, nhan_allcau_cotime.class);
        intent.putExtra("PHEP_TOAN", phepToan);
        startActivity(intent);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    private void lvnhan3(String phepToan) {
        Intent intent = new Intent(MainActivity.this, nhan_allcau_kotime.class);
        intent.putExtra("PHEP_TOAN", phepToan);
        startActivity(intent);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    private void lvchia(String phepToan) {
        Intent intent = new Intent(MainActivity.this, chia_10cau_cotime.class);
        intent.putExtra("PHEP_TOAN", phepToan);
        startActivity(intent);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    private void lvchia1(String phepToan) {
        Intent intent = new Intent(MainActivity.this, chia_10cau_kotime.class);
        intent.putExtra("PHEP_TOAN", phepToan);
        startActivity(intent);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    private void lvchia2(String phepToan) {
        Intent intent = new Intent(MainActivity.this, chia_allcau_cotime.class);
        intent.putExtra("PHEP_TOAN", phepToan);
        startActivity(intent);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    private void lvchia3(String phepToan) {
        Intent intent = new Intent(MainActivity.this, chia_allcau_kotime.class);
        intent.putExtra("PHEP_TOAN", phepToan);
        startActivity(intent);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}
