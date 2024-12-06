package com.example.apptinhtoan.pheptoan;

import android.os.Parcel;
import android.os.Parcelable;

public class PhepChia implements Parcelable {
    private int so1;
    private int so2;

    // Constructor
    public PhepChia(int so1, int so2) {
        // Đảm bảo so2 khác 0 để tránh lỗi chia cho 0
        if (so2 == 0) {
            throw new IllegalArgumentException("Không thể chia cho 0");
        }
        this.so1 = so1;
        this.so2 = so2;
    }

    // Phương thức tính kết quả phép chia (chỉ trả về phần nguyên)
    public int tinhKetQua() {
        return so1 / so2; // Chia số nguyên
    }

    // Phương thức lấy chuỗi biểu diễn phép chia
    public String getBaiToan() {
        return so1 + " / " + so2 + " = ";
    }

    // Getter cho so1, so2
    public int getSo1() {
        return so1;
    }

    public int getSo2() {
        return so2;
    }

    // Phương thức implement Parcelable
    protected PhepChia(Parcel in) {
        so1 = in.readInt();
        so2 = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(so1);
        dest.writeInt(so2);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PhepChia> CREATOR = new Creator<PhepChia>() {
        @Override
        public PhepChia createFromParcel(Parcel in) {
            return new PhepChia(in);
        }

        @Override
        public PhepChia[] newArray(int size) {
            return new PhepChia[size];
        }
    };
}
