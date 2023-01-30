package model;

public class TamVang {
    private int id;
    private String maNhanKhau;
    private String maGiayTamVang;
    private String noiTamTru;
    private String tuNgay;
    private String denNgay;
    private String lyDo;
    public TamVang(int id, String maNhanKhau, String noiTamTru, String tuNgay, String denNgay, String lyDo) {
        this.id = id;
        this.maNhanKhau = maNhanKhau;
        this.noiTamTru = noiTamTru;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.lyDo = lyDo;
    }
    public TamVang(String maNhanKhau, String maGiayTamVang, String noiTamTru, String tuNgay, String denNgay, String lyDo) {
        this.maNhanKhau = maNhanKhau;
        this.maGiayTamVang = maGiayTamVang;
        this.noiTamTru = noiTamTru;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.lyDo = lyDo;
    }

    public TamVang(String maNhanKhau, String noiTamTru, String tuNgay, String denNgay, String lyDo) {
        this.maNhanKhau = maNhanKhau;
        this.noiTamTru = noiTamTru;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.lyDo = lyDo;
    }

    public String getMaNhanKhau() {
        return maNhanKhau;
    }

    public void setMaNhanKhau(String maNhanKhau) {
        this.maNhanKhau = maNhanKhau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaGiayTamVang() {
        return maGiayTamVang;
    }

    public void setMaGiayTamVang(String maGiayTamVang) {
        this.maGiayTamVang = maGiayTamVang;
    }

    public String getNoiTamTru() {
        return noiTamTru;
    }

    public void setNoiTamTru(String noiTamTru) {
        this.noiTamTru = noiTamTru;
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
}