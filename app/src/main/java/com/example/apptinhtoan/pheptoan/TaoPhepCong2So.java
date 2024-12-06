package com.example.apptinhtoan.pheptoan;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaoPhepCong2So {

    private static final Random random = new Random();

    // Tạo phép cộng với hai số có một chữ số và trả về đối tượng PhepCong
    public static PhepCong taoBaiToan() {
        // Tạo hai số ngẫu nhiên từ 10 đến 99
        int so1 = random.nextInt(99) + 1;
        int so2 = random.nextInt(99) + 1;

        // Trả về đối tượng PhepCong
        return new PhepCong(so1, so2);
    }

    // Tạo danh sách các bài toán phép cộng ngẫu nhiên dưới dạng PhepCong
    public static List<PhepCong> taoDanhSachBaiToan(int soLuong) {
        List<PhepCong> danhSachBaiToan = new ArrayList<>(soLuong);
        for (int i = 0; i < soLuong; i++) {
            danhSachBaiToan.add(taoBaiToan());  // Thêm bài toán phép cộng vào danh sách dưới dạng PhepCong
        }
        return danhSachBaiToan;
    }
}