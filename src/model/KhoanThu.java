package model;

public class KhoanThu {
    private String maKhoanThu;
    private String tenKhoanThu;
    private String soTienCanThu;
    private String maHo;
    private String ngayNop;

    public KhoanThu(String maKhoanThu, String tenKhoanThu, String soTienCanThu, String maHo, String ngayNop) {
        this.maKhoanThu = maKhoanThu;
        this.tenKhoanThu = tenKhoanThu;
        this.soTienCanThu = soTienCanThu;
        this.maHo = maHo;
        this.ngayNop = ngayNop;
    }

    public String getMaKhoanThu() {
        return maKhoanThu;
    }

    public void setMaKhoanThu(String maKhoanThu) {
        this.maKhoanThu = maKhoanThu;
    }

    public String getTenKhoanThu() {
        return tenKhoanThu;
    }

    public void setTenKhoanThu(String tenKhoanThu) {
        this.tenKhoanThu = tenKhoanThu;
    }

    public String getSoTienCanThu() {
        return soTienCanThu;
    }

    public void setSoTienCanThu(String soTienCanThu) {
        this.soTienCanThu = soTienCanThu;
    }

    public String getMaHo() {
        return maHo;
    }

    public void setMaHo(String maHo) {
        this.maHo = maHo;
    }

    public String getNgayNop() {
        return ngayNop;
    }

    public void setNgayNop(String ngayNop) {
        this.ngayNop = ngayNop;
    }
}