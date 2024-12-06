package com.example.apptinhtoan;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.media.MediaPlayer;
import android.view.MotionEvent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import com.example.apptinhtoan.pheptoan.PhepNhan;
import com.example.apptinhtoan.pheptoan.TaoPhepNhan1So;

public class nhan_10cau_kotime_sc extends AppCompatActivity {
    private TextView phepToanTextView;
    private TextView diemSaoTextView;
    private TextView thongBaoKetQua; // TextView hiển thị thông báo "Đúng rồi!" hoặc "Sai rồi!"
    private ImageView imgPause;
    private int soHang1, soHang2;  // Hai số của phép toán
    private int dapAnDung;  // Đáp án đúng của phép toán
    private String dapAnHienTai = "";  // Lưu đáp án hiện tại (dạng String để nhập từng chữ số)
    private int soCauDung = 0; // Đếm số câu đúng
    private int soCauSai = 0; // Đếm số câu sai
    private boolean checkvar =false;
    private int tongSoCau = 10;  // Tổng số câu
    private ImageView imgButton1, imgButton2, imgButton3, imgButton4, imgButton5, imgButtonTru, imgButtonCE;
    private ImageView imgButton6, imgButton7, imgButton8, imgButton9, imgButton0;
    private boolean daNhapDauTru = false;  // Biến cờ đánh dấu khi nhấn dấu trừ
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer1;
    private boolean soHienTaiAm = false;  // Biến cờ đánh dấu khi nhập số âm
    private boolean daKiemTraDapAn = false;  // Biến cờ để theo dõi xem đáp án đã được kiểm tra chưa
    private Handler xuLy;


    @Override
    public void onBackPressed() {
        phatAmThanh();
        hienHopThoaiXacNhanThoat();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cong_10cau_kotime_sc);


        phepToanTextView = findViewById(R.id.math_problem);
        diemSaoTextView = findViewById(R.id.star_count);
        thongBaoKetQua = findViewById(R.id.result_message);
        imgPause = findViewById(R.id.img_pause);

        // các nút
        imgButton1 = findViewById(R.id.button1);
        imgButton2 = findViewById(R.id.button2);
        imgButton3 = findViewById(R.id.button3);
        imgButton4 = findViewById(R.id.button4);
        imgButton5 = findViewById(R.id.button5);
        imgButton6 = findViewById(R.id.button6);
        imgButton7 = findViewById(R.id.button7);
        imgButton8 = findViewById(R.id.button8);
        imgButton9 = findViewById(R.id.button9);
        imgButton0 = findViewById(R.id.button0);
        imgButtonTru = findViewById(R.id.buttontru);
        imgButtonCE = findViewById(R.id.buttonCE);

        // Khởi tạo Handler
        xuLy = new Handler();

        // Khởi tạo phép toán từ TaoPhepNhan1So
        TaoPhepNhan1So TaoPhepNhan1So = new TaoPhepNhan1So();
        PhepNhan PhepNhan = TaoPhepNhan1So.taoBaiToan();
        soHang1 = PhepNhan.getSo1();
        soHang2 = PhepNhan.getSo2();
        dapAnDung = soHang1 * soHang2;  // Cập nhật đáp án đúng

        // Hiển thị phép toán ban đầu
        phepToanTextView.setText(PhepNhan.getBaiToan());

        // Cập nhật số câu ban đầu
        diemSaoTextView.setText(soCauDung + "/" + tongSoCau);

        xuLySuKienNut();

        // Sự kiện cho imgPause (Nút Tạm dừng)
        imgPause.setOnClickListener(view -> {
            phatAmThanh();
            hienHopThoaiXacNhanThoat();
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

    private void phatAmThanh1() {
        if (mediaPlayer != null) {
            mediaPlayer.release(); // Giải phóng MediaPlayer trước khi tạo mới
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.sound1);
        mediaPlayer.start();
    }
    private void phatAmThanhd() {
        if (mediaPlayer1 != null) {
            mediaPlayer1.release(); // Giải phóng MediaPlayer trước khi tạo mới
        }
        mediaPlayer1= MediaPlayer.create(this, R.raw.soundd);
        mediaPlayer1.start();
    }
    private void phatAmThanhs() {
        if (mediaPlayer1 != null) {
            mediaPlayer1.release(); // Giải phóng MediaPlayer trước khi tạo mới
        }
        mediaPlayer1 = MediaPlayer.create(this, R.raw.sounds);
        mediaPlayer1.start();
    }

    private void xuLySuKienNut() {

        findViewById(R.id.button1).setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                phatAmThanh1();
                themVaoPhepToan("1");
                imgButton1.setImageResource(R.drawable.img_1on);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                imgButton1.setImageResource(R.drawable.img_1off);
            }
            return true;
        });
        findViewById(R.id.button2).setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                phatAmThanh1();
                themVaoPhepToan("2");
                imgButton2.setImageResource(R.drawable.img_2on);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                imgButton2.setImageResource(R.drawable.img_2off);
            }
            return true;
        });
        findViewById(R.id.button3).setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                phatAmThanh1();
                themVaoPhepToan("3");
                imgButton3.setImageResource(R.drawable.img_3on);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                imgButton3.setImageResource(R.drawable.img_3off);
            }
            return true;
        });

        findViewById(R.id.button4).setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                phatAmThanh1();
                themVaoPhepToan("4");
                imgButton4.setImageResource(R.drawable.img_4on);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {

                imgButton4.setImageResource(R.drawable.img_4off);
            }
            return true;
        });

        findViewById(R.id.button5).setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                phatAmThanh1();
                themVaoPhepToan("5");
                imgButton5.setImageResource(R.drawable.img_5on);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                imgButton5.setImageResource(R.drawable.img_5off);
            }
            return true;
        });

        findViewById(R.id.button6).setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                phatAmThanh1();
                themVaoPhepToan("6");
                imgButton6.setImageResource(R.drawable.img_6on);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                imgButton6.setImageResource(R.drawable.img_6off);
            }
            return true;
        });

        findViewById(R.id.button7).setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                phatAmThanh1();
                themVaoPhepToan("7");
                imgButton7.setImageResource(R.drawable.img_7on);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                imgButton7.setImageResource(R.drawable.img_7off);
            }
            return true;
        });

        findViewById(R.id.button8).setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                phatAmThanh1();
                themVaoPhepToan("8");
                imgButton8.setImageResource(R.drawable.img_8on);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                imgButton8.setImageResource(R.drawable.img_8off);
            }
            return true;
        });

        findViewById(R.id.button9).setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                phatAmThanh1();
                themVaoPhepToan("9");
                imgButton9.setImageResource(R.drawable.img_9on);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                imgButton9.setImageResource(R.drawable.img_9off);
            }
            return true;
        });

        findViewById(R.id.button0).setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                phatAmThanh1();
                themVaoPhepToan("0");
                imgButton0.setImageResource(R.drawable.img_0on);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                imgButton0.setImageResource(R.drawable.img_0off);
            }
            return true;
        });

        findViewById(R.id.buttontru).setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                phatAmThanh1();
                nhanDauTru();
                imgButtonTru.setImageResource(R.drawable.img_truon);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                imgButtonTru.setImageResource(R.drawable.img_truoff);
            }
            return true;
        });

        findViewById(R.id.buttonCE).setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                phatAmThanh1();
                nhanNutCE();
                imgButtonCE.setImageResource(R.drawable.img_ceon);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                imgButtonCE.setImageResource(R.drawable.img_ceoff);
            }
            return true;
        });
    }

    public void themVaoPhepToan(String so) {
        if (daKiemTraDapAn) return;

        if (daNhapDauTru) {
            dapAnHienTai = dapAnHienTai + so;
            phepToanTextView.setText(soHang1 + " * " + soHang2 + " = " + dapAnHienTai);
        } else {
            dapAnHienTai += so;
            phepToanTextView.setText(soHang1 + " * " + soHang2 + " = " + dapAnHienTai);
        }

        if (dapAnHienTai.startsWith("-") && dapAnHienTai.length() - 1 >= String.valueOf(dapAnDung).length() ||
                !dapAnHienTai.startsWith("-") && dapAnHienTai.length() >= String.valueOf(dapAnDung).length()) {
            try {
                int dapAnNhap = Integer.parseInt(dapAnHienTai); // Chuyển đổi trực tiếp chuỗi sang số nguyên
                if (dapAnNhap == dapAnDung) {
                    kiemTraDapAn(true);
                } else {
                    kiemTraDapAn(false);
                }
            } catch (NumberFormatException e) {
                // Xử lý ngoại lệ khi chuỗi không thể chuyển đổi thành số
            }
        }
    }

    public void nhanDauTru() {
        if (dapAnHienTai.isEmpty() && !daKiemTraDapAn) {
            dapAnHienTai = "-";
            phepToanTextView.setText(soHang1 + " * " + soHang2 + " = " + dapAnHienTai);
            daNhapDauTru = true;
            soHienTaiAm = true;
        }
    }

    public void nhanNutCE() {
        dapAnHienTai = "";
        daNhapDauTru = false;
        soHienTaiAm = false;
        daKiemTraDapAn = false;

        phepToanTextView.setText(soHang1 + " * " + soHang2 + " = " + dapAnHienTai);
        phepToanTextView.setTextColor(Color.BLACK);
    }

    public void kiemTraDapAn(boolean dung) {
        if (checkvar) {
            return;
        }
        checkvar = true;


        vohieunut();

        if (dung) {
            // Nếu trả lời đúng
            phepToanTextView.setText(soHang1 + " * " + soHang2 + " = " + dapAnDung);
            phepToanTextView.setTextColor(Color.GREEN);  // Hiển thị màu xanh cho đúng
            hienThongBao("Đúng rồi!", Color.GREEN);  // Hiển thị thông báo đúng
            phatAmThanhd();
            soCauDung++;  // Tăng số câu đúng

            capNhatDiemSao();  // Cập nhật điểm sao

            // Kiểm tra xem đã hoàn thành đủ 10 câu đúng chưa
            if (soCauDung == 10) {
                finishQuiz();  // Nếu đã hoàn thành, gọi hàm finishQuiz
            } else {
                xuLy.postDelayed(this::cauHoiTiepTheo, 1000);  // Chuyển sang câu tiếp theo sau 1 giây
            }
        } else {
            // Nếu trả lời sai
            phepToanTextView.setText(soHang1 + " * " + soHang2 + " = " + dapAnHienTai);  // Hiển thị phép toán sai
            phepToanTextView.setTextColor(Color.RED);  // Hiển thị màu đỏ cho sai
            hienThongBao("Sai rồi!", Color.RED);  // Hiển thị thông báo sai
            phatAmThanhs();
            tongSoCau++;
            soCauSai++;

            xuLy.postDelayed(this::cauHoiTiepTheo, 1000);  // Chuyển sang câu tiếp theo sau 1 giây
        }

        // Sau 1 giây, bật lại các nút và cập nhật lại hình ảnh các nút về trạng thái ban đầu
        new Handler().postDelayed(() -> {
            checkvar = false;
            batlainut();  // Bật lại tất cả các nút
            macdinhnut();  // Đặt lại hình ảnh các nút về trạng thái off
        }, 1000);  // Sau 1 giây

    }

    // Hàm vô hiệu hóa tất cả các nút
    private void vohieunut() {
        findViewById(R.id.button1).setEnabled(false);
        findViewById(R.id.button2).setEnabled(false);
        findViewById(R.id.button3).setEnabled(false);
        findViewById(R.id.button4).setEnabled(false);
        findViewById(R.id.button5).setEnabled(false);
        findViewById(R.id.button6).setEnabled(false);
        findViewById(R.id.button7).setEnabled(false);
        findViewById(R.id.button8).setEnabled(false);
        findViewById(R.id.button9).setEnabled(false);
        findViewById(R.id.button0).setEnabled(false);
        findViewById(R.id.buttontru).setEnabled(false);
        findViewById(R.id.buttonCE).setEnabled(false);
    }

    // Hàm bật lại tất cả các nút
    private void batlainut() {
        findViewById(R.id.button1).setEnabled(true);
        findViewById(R.id.button2).setEnabled(true);
        findViewById(R.id.button3).setEnabled(true);
        findViewById(R.id.button4).setEnabled(true);
        findViewById(R.id.button5).setEnabled(true);
        findViewById(R.id.button6).setEnabled(true);
        findViewById(R.id.button7).setEnabled(true);
        findViewById(R.id.button8).setEnabled(true);
        findViewById(R.id.button9).setEnabled(true);
        findViewById(R.id.button0).setEnabled(true);
        findViewById(R.id.buttontru).setEnabled(true);
        findViewById(R.id.buttonCE).setEnabled(true);
    }

    // Hàm đặt lại hình ảnh các nút về trạng thái off
    private void macdinhnut() {
        imgButton1.setImageResource(R.drawable.img_1off);
        imgButton2.setImageResource(R.drawable.img_2off);
        imgButton3.setImageResource(R.drawable.img_3off);
        imgButton4.setImageResource(R.drawable.img_4off);
        imgButton5.setImageResource(R.drawable.img_5off);
        imgButton6.setImageResource(R.drawable.img_6off);
        imgButton7.setImageResource(R.drawable.img_7off);
        imgButton8.setImageResource(R.drawable.img_8off);
        imgButton9.setImageResource(R.drawable.img_9off);
        imgButton0.setImageResource(R.drawable.img_0off);
        imgButtonTru.setImageResource(R.drawable.img_truoff);
        imgButtonCE.setImageResource(R.drawable.img_ceoff);
    }


    private void hienHopThoaiXacNhanThoat() {
        AlertDialog.Builder builder = new AlertDialog.Builder(nhan_10cau_kotime_sc.this);
        builder.setTitle("Tạm dừng");
        builder.setMessage("Bạn có chắc chắn muốn quay lại màn hình chính?");

        // Nút "Có" để quay lại LevelActivity
        builder.setPositiveButton("Có", (dialog, which) -> {
            Intent intent = new Intent(nhan_10cau_kotime_sc.this, nhan_10cau_kotime.class);
            startActivity(intent); // Mở LevelActivity
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish(); // Đóng Activity hiện tại
        });

        // Nút "Không" để hủy và tiếp tục bộ đếm thời gian
        builder.setNegativeButton("Không", (dialog, which) -> {
            dialog.dismiss(); // Đóng hộp thoại
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void hienThongBao(String thongBao, int mau) {
        thongBaoKetQua.setText(thongBao);
        thongBaoKetQua.setTextColor(mau);
        thongBaoKetQua.setVisibility(View.VISIBLE);

        xuLy.postDelayed(() -> thongBaoKetQua.setVisibility(View.GONE), 1000);
    }

    private void capNhatDiemSao() {
        diemSaoTextView.setText(soCauDung + "/" + 10);

        if (soCauDung == 10) {
            Toast.makeText(this, "Chúc mừng bạn đã hoàn thành!", Toast.LENGTH_SHORT).show();
        }
    }

    private void cauHoiTiepTheo() {
        TaoPhepNhan1So TaoPhepNhan1So = new TaoPhepNhan1So();
        PhepNhan PhepNhan = TaoPhepNhan1So.taoBaiToan();
        soHang1 = PhepNhan.getSo1();
        soHang2 = PhepNhan.getSo2();
        dapAnDung = soHang1 * soHang2;

        phepToanTextView.setText(PhepNhan.getBaiToan());
        dapAnHienTai = "";
        daKiemTraDapAn = false;
        phepToanTextView.setTextColor(Color.BLACK);
    }

    private void finishQuiz() {


        // Chuyển sang màn hình kết quả
        Intent intent = new Intent(nhan_10cau_kotime_sc.this, manhinh_end.class);

        intent.putExtra("totalQuestions", tongSoCau);  // Tổng số câu
        intent.putExtra("incorrectAnswers", soCauSai);  // Số câu sai
        startActivity(intent);
        finish();  // Kết thúc activity hiện tại
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
