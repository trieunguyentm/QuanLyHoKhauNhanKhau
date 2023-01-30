package model;

public class TamTru {
    private int id;
    private String maNhanKhau;
    private String hoTen;
    private String CMND;
    private String soDienThoaiNguoiDangKy;
    private String tuNgay;
    private String denNgay;
    private String lyDo;
    public TamTru(int id,String hoTen, String cmnd, String soDienThoaiNguoiDangKy, String tuNgay, String denNgay, String lyDo) {
        this.id = id;
        this.hoTen = hoTen;
        this.CMND = cmnd;
//        this.maGiayTamTru = maGiayTamTru;
        this.soDienThoaiNguoiDangKy = soDienThoaiNguoiDangKy;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.lyDo = lyDo;
    }

    public TamTru(String maNhanKhau, String soDienThoaiNguoiDangKy, String tuNgay, String denNgay, String lyDo) {
        this.maNhanKhau = maNhanKhau;
        this.soDienThoaiNguoiDangKy = soDienThoaiNguoiDangKy;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.lyDo = lyDo;
    }

    public TamTru(String hoTen, String CMND, String soDienThoaiNguoiDangKy, String tuNgay, String denNgay, String lyDo) {
        this.hoTen = hoTen;
        this.CMND = CMND;
        this.soDienThoaiNguoiDangKy = soDienThoaiNguoiDangKy;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.lyDo = lyDo;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getMaNhanKhau() {
        return maNhanKhau;
    }

    public void setMaNhanKhau(String maNhanKhau) {
        this.maNhanKhau = maNhanKhau;
    }

    public String getSoDienThoaiNguoiDangKy() {
        return soDienThoaiNguoiDangKy;
    }

    public void setSoDienThoaiNguoiDangKy(String soDienThoaiNguoiDangKy) {
        this.soDienThoaiNguoiDangKy = soDienThoaiNguoiDangKy;
    }

    public String getTuNgay() {
        return tuNgay;
    }

    public void setTuNgay(String tuNgay) {
        this.tuNgay = tuNgay;
    }

    public String getDenNgay() {
        return denNgay;
    }

    public void setDenNgay(String denNgay) {
        this.denNgay = denNgay;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}