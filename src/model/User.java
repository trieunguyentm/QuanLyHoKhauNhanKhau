package model;

public class User {
    private int id;
    private String tenTaiKhoan;
    private String vaiTro;
    private String tenNguoiDung;
    private String ngaySinh;
    private String gioiTinh;

    public User(int id, String tenTaiKhoan, String vaiTro, String tenNguoiDung, String ngaySinh, String gioiTinh) {
        this.id = id;
        this.tenTaiKhoan = tenTaiKhoan;
        this.vaiTro = vaiTro;
        this.tenNguoiDung = tenNguoiDung;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
}
