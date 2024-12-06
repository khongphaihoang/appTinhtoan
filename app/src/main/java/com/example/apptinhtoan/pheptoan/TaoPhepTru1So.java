package com.example.apptinhtoan.pheptoan;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaoPhepTru1So {

    private static final Random random = new Random();

    // Tạo phép trừ với hai số có một chữ số và trả về đối tượng PhepTru
    public static PhepTru taoBaiToan() {
        // Tạo hai số ngẫu nhiên từ 1 đến 9
        int so1 = random.nextInt(9) + 1;
        int so2 = random.nextInt(9) + 1;

        // Đảm bảo so1 >= so2 để tránh kết quả âm
        if (so1 < so2) {
            int temp = so1;
            so1 = so2;
            so2 = temp;
        }

        // Trả về đối tượng PhepTru
        return new PhepTru(so1, so2);
    }

    // Tạo danh sách các bài toán phép trừ ngẫu nhiên dưới dạng PhepTru
    public static List<PhepTru> taoDanhSachBaiToan(int soLuong) {
        List<PhepTru> danhSachBaiToan = new ArrayList<>(soLuong);
        for (int i = 0; i < soLuong; i++) {
            danhSachBaiToan.add(taoBaiToan());  // Thêm bài toán phép trừ vào danh sách
        }
        return danhSachBaiToan;
    }
}
