package model;

public class KhaiTu {
    private String maNguoiKhai;
    private String maNguoiChet;
    private String date;
    private String lyDoChet;

    public KhaiTu(String maNguoiKhai, String maNguoiChet, String date, String lyDoChet) {
        this.maNguoiKhai = maNguoiKhai;
        this.maNguoiChet = maNguoiChet;
        this.date = date;
        this.lyDoChet = lyDoChet;
    }

    public String getMaNguoiKhai() {
        return maNguoiKhai;
    }

    public void setMaNguoiKhai(String maNguoiKhai) {
        this.maNguoiKhai = maNguoiKhai;
    }

    public String getMaNguoiChet() {
        return maNguoiChet;
    }

    public void setMaNguoiChet(String maNguoiChet) {
        this.maNguoiChet = maNguoiChet;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLyDoChet() {
        return lyDoChet;
    }

    public void setLyDoChet(String lyDoChet) {
        this.lyDoChet = lyDoChet;
    }
}
