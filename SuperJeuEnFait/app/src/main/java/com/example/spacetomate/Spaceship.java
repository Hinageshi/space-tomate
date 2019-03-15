package com.example.spacetomate;

public class Spaceship {

    private Garden tomatoGarden;
    private Garden eggplantGarden;
    private Garden pumpkinGarden;
    private Garden broccoliGarden;
    private int numberOfGardenUnlocked;

    public Spaceship(){
        tomatoGarden=new Garden();
        eggplantGarden=new Garden();
        pumpkinGarden=new Garden();
        broccoliGarden=new Garden();
        tomatoGarden.unlockNextSlot();
        numberOfGardenUnlocked=1;
    }

    public Garden getTomatoGarden() {
        return tomatoGarden;
    }

    public Garden getEggplantGarden() {
        return eggplantGarden;
    }

    public Garden getPumpkinGarden() {
        return pumpkinGarden;
    }

    public Garden getBroccoliGarden() {
        return broccoliGarden;
    }

    public int getNumberOfGardenUnlocked() {
        return numberOfGardenUnlocked;
    }

    public boolean gardenLocked(Products p){
        switch (p){
            case TOMATO:
                return !(tomatoGarden.isAvailable());
            case EGGPLANT:
                return !(eggplantGarden.isAvailable());
            case PUMPKIN:
                return !(pumpkinGarden.isAvailable());
            case BROCCOLI:
                return !(broccoliGarden.isAvailable());
            default:
                return true;
        }
    }

    public void unlockNextGarden(){
        switch(numberOfGardenUnlocked){
            case 1: {
                eggplantGarden.unlockNextSlot();
                numberOfGardenUnlocked++;
                break;
            }
            case 2: {
                pumpkinGarden.unlockNextSlot();
                numberOfGardenUnlocked++;
                break;
            }
            case 3: {
                broccoliGarden.unlockNextSlot();
                numberOfGardenUnlocked++;
                break;
            }
            default:
                break;

        }
    }


}

