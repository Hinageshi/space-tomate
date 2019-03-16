package com.example.superjeuenfait;

public class DustCollector {

    private boolean available;
    private boolean locked;
    private long timer;

    public DustCollector(){
        available=false;
        locked=true;
        timer=0;
    }

    public long getTimer() {
        return timer;
    }

    public void setTimer(long time){
        this.timer=time;
    }

    public boolean isLocked(){
        return locked;
    }

    public void unlock(){
        locked=false;
        available=true;
        timer=0;
    }

    public void disable(long time){
        setTimer(time);
        available=false;
    }

    public void enable(){
        setTimer(0);
        available=true;
    }

    public boolean isAvailable(){
        return (available&&!locked);
    }
}
