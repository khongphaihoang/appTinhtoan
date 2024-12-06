package com.example.apptinhtoan.pheptoan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaoPhepChia3So {

    private static final Random random = new Random();

    // Tạo phép chia với hai số có một chữ số và trả về đối tượng PhepChia
    public static PhepChia taoBaiToan() {
        int so2;
        int so1;

        // Lặp lại cho đến khi tạo ra phép chia có kết quả là số nguyên
        do {
            // Tạo số ngẫu nhiên từ 1 đến 9 cho so2
            so2 = random.nextInt(99) + 1;
            // Tạo số so1 sao cho nó chia hết cho so2
            so1 = random.nextInt(99) * so2; // Đảm bảo so1 chia hết cho so2
        } while (so1 % so2 != 0);

        // Trả về đối tượng PhepChia
        return new PhepChia(so1, so2);
    }

    // Tạo danh sách các bài toán phép chia ngẫu nhiên dưới dạng PhepChia
    public static List<PhepChia> taoDanhSachBaiToan(int soLuong) {
        List<PhepChia> danhSachBaiToan = new ArrayList<>(soLuong);
        for (int i = 0; i < soLuong; i++) {
            danhSachBaiToan.add(taoBaiToan());  // Thêm bài toán phép chia vào danh sách dưới dạng PhepChia
        }
        return danhSachBaiToan;
    }
}
