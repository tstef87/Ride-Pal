package com.example.ride_pal_real.ui.rides.create;

public class Rides {
    public String name, time, destination;
    public boolean monday, tuesday, wednesday, thursday, friday;

    public Rides(){


    }



    public Rides(String name, String time, String destination, boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday){
        this.name = name;
        this.time = time;
        this.destination = destination;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
    }


    public String getName() {
        return name;
    }

    public void setName(String dis) {
        this.name = dis;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isMonday() {
        return monday;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public boolean isFriday() {
        return friday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    @Override
    public String toString() {
        return "Rides{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", destination='" + destination + '\'' +
                ", monday=" + monday +
                ", tuesday=" + tuesday +
                ", wednesday=" + wednesday +
                ", thursday=" + thursday +
                ", friday=" + friday +
                '}';
    }

    public String toStringList(){
        String s = "";
        s += getDestination();
        s += "- ";
        s += getTime();
        s += ": ";

        if(isMonday()){ s += "Monday ";}
        if(isTuesday()){ s += "Tuesday ";}
        if(isWednesday()){ s += "Wednesday ";}
        if(isTuesday()){ s += "Thursday ";}
        if(isFriday()){ s += "Friday ";}

        return s;
    }


}
