package com.example.fragmentactivity.data;

public class hotel {
    private String hotelname,hoteladdress,hotelamneties,Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public hotel()
    {

    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public String getHoteladdress() {
        return hoteladdress;
    }

    public void setHoteladdress(String hoteladdress) {
        this.hoteladdress = hoteladdress;
    }

    public String getHotelamneties() {
        return hotelamneties;
    }

    public void setHotelamneties(String hotelamneties) {
        this.hotelamneties = hotelamneties;
    }
}
