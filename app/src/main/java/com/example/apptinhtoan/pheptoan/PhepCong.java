package com.example.apptinhtoan.pheptoan;

import android.os.Parcel;
import android.os.Parcelable;

public class PhepCong implements Parcelable {
    private int so1;
    private int so2;

    // Constructor
    public PhepCong(int so1, int so2) {
        this.so1 = so1;
        this.so2 = so2;
    }

    // Phương thức tính kết quả phép cộng
    public int tinhKetQua() {
        return so1 + so2;
    }

    // Phương thức lấy chuỗi biểu diễn phép cộng
    public String getBaiToan() {
        return so1 + " + " + so2 + " = ";
    }

    // Getter cho so1, so2
    public int getSo1() {
        return so1;
    }

    public int getSo2() {
        return so2;
    }

    // Phương thức implement Parcelable
    protected PhepCong(Parcel in) {
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

    public static final Creator<PhepCong> CREATOR = new Creator<PhepCong>() {
        @Override
        public PhepCong createFromParcel(Parcel in) {
            return new PhepCong(in);
        }

        @Override
        public PhepCong[] newArray(int size) {
            return new PhepCong[size];
        }
    };
}
