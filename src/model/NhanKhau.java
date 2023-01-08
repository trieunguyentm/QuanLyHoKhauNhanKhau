package model;

public class NhanKhau {
    private String maNhanKhau;
    private String hoTen;
    private String gioiTinh;
    private String ngaySinh;
    private String queQuan;
    private String ngheNghiep;
    private String maHo;
    private String quanHeVoiChuHo;
    private String ghiChu;

    public NhanKhau(String maNhanKhau, String hoTen, String gioiTinh, String ngaySinh, String queQuan, String ngheNghiep, String maHo, String quanHeVoiChuHo, String ghiChu) {
        this.maNhanKhau = maNhanKhau;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.queQuan = queQuan;
        this.ngheNghiep = ngheNghiep;
        this.maHo = maHo;
        this.quanHeVoiChuHo = quanHeVoiChuHo;
        this.ghiChu = ghiChu;
    }
}
