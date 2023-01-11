package model;

public class HoKhau {
    private String maHo;
    private String maChuHo;

    public HoKhau(String maHo, String maChuHo) {
        this.maHo = maHo;
        this.maChuHo = maChuHo;
    }

    public String getMaHo() {
        return maHo;
    }

    public void setMaHo(String maHo) {
        this.maHo = maHo;
    }

    public String getMaChuHo() {
        return maChuHo;
    }

    public void setMaChuHo(String maChuHo) {
        this.maChuHo = maChuHo;
    }
}
