package com.grafixartist.bottomnav;

/**
 * Created by Suleiman on 03/02/17.
 */


public class Phone {

    private String whois;
    private String about;
    private String time;

    public Phone(String whois, String about, String time){

        this.whois=whois;
        this.about = about;
        this.time = time;
    }

    public String getWhoIs() {
        return this.whois;
    }

    public void setWhoIs(String whois) {
        this.whois = whois;
    }

    public String getAbout() {
        return this.about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}