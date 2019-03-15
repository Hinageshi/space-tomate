package com.example.spacetomate;

public class Garden {

    private int shootPerSlot[];
    private int slotLevel[];
    private int slotUnlocked;


    public Garden(){
        slotUnlocked=0;
        shootPerSlot=new int[Game.slotMax];
        slotLevel=new int[Game.slotMax];
        for(int i=0;i<Game.slotMax;i++){
            shootPerSlot[i]=0;
            slotLevel[i]=0;
        }
    }

    public int getShootPerSlot(int i){
        return shootPerSlot[i];
    }

    public int getLevelSlot(int i){
        return slotLevel[i];
    }

    public int getSlotUnlocked(){
        return slotUnlocked;
    }

    public boolean isAvailable(){
        return (slotUnlocked>0);
    }

    public boolean slotAvailable(int i){
        return (slotLevel[i]>0 && shootPerSlot[i]>0);
    }

    public boolean levelCanBeIncreased(int i){
        return (slotAvailable(i) && slotLevel[i]<Game.slotLevelMax);
    }

    public boolean shootNumberCanBeIncreased(int i){
        return (slotAvailable(i) && shootPerSlot[i]<Game.shootPerSlotMax);
    }

    public void increseLevelSlot(int i){
        if(levelCanBeIncreased(i)){
            slotLevel[i]++;
        }
    }

    public void increaseShootNumber(int i){
        if(shootNumberCanBeIncreased(i)){
            shootPerSlot[i]++;
        }
    }

    private void unlockSlot(int i){
        slotLevel[i]=1;
        shootPerSlot[i]=1;
        slotUnlocked++;
    }

    public void unlockNextSlot(){
        unlockSlot(slotUnlocked);
    }
}
