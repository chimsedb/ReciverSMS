package com.example.hungdang.smsreciver;

public class ContactsSMS {
    private String NoiDung;
    private String TinNhan;

    public ContactsSMS() {
    }

    public String getNoiDung() {

        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getTinNhan() {
        return TinNhan;
    }

    public void setTinNhan(String tinNhan) {
        TinNhan = tinNhan;
    }

    public ContactsSMS(String noiDung, String tinNhan) {

        NoiDung = noiDung;
        TinNhan = tinNhan;
    }
}
