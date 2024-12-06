package com.example.apptinhtoan.pheptoan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaoPhepNhan2So {

    private static final Random random = new Random();

    // Tạo phép nhân với hai số có một chữ số và trả về đối tượng PhepNhan
    public static PhepNhan taoBaiToan() {
        // Tạo hai số ngẫu nhiên từ 1 đến 9
        int so1 = random.nextInt(99) + 1;
        int so2 = random.nextInt(9) + 1;

        // Trả về đối tượng PhepNhan
        return new PhepNhan(so1, so2);
    }

    // Tạo danh sách các bài toán phép nhân ngẫu nhiên dưới dạng PhepNhan
    public static List<PhepNhan> taoDanhSachBaiToan(int soLuong) {
        List<PhepNhan> danhSachBaiToan = new ArrayList<>(soLuong);
        for (int i = 0; i < soLuong; i++) {
            danhSachBaiToan.add(taoBaiToan());  // Thêm bài toán phép nhân vào danh sách dưới dạng PhepNhan
        }
        return danhSachBaiToan;
    }
}
