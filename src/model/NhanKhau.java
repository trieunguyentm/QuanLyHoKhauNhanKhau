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

    public String getMaNhanKhau() {
        return maNhanKhau;
    }

    public void setMaNhanKhau(String maNhanKhau) {
        this.maNhanKhau = maNhanKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getMaHo() {
        return maHo;
    }

    public void setMaHo(String maHo) {
        this.maHo = maHo;
    }

    public String getQuanHeVoiChuHo() {
        return quanHeVoiChuHo;
    }

    public void setQuanHeVoiChuHo(String quanHeVoiChuHo) {
        this.quanHeVoiChuHo = quanHeVoiChuHo;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

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

    public NhanKhau(String maNhanKhau, String hoTen, String gioiTinh, String ngaySinh, String queQuan, String ngheNghiep, String maHo, String quanHeVoiChuHo) {
        this.maNhanKhau = maNhanKhau;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.queQuan = queQuan;
        this.ngheNghiep = ngheNghiep;
        this.maHo = maHo;
        this.quanHeVoiChuHo = quanHeVoiChuHo;
    }
}
