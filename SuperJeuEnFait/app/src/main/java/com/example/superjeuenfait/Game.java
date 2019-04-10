package com.example.superjeuenfait;

public class Game {

    protected static final int slotMax=9;
    protected static final int shootPerSlotMax=10;
    protected static final int slotLevelMax=10;
    protected static final int numberOfProductsMax=4;
    protected static final long timerDustCollector=1800; //seconds
    public static final int salesHistorySize=10;
    public static final int maxProductPerSale=99;
    public static final long timerUpdateMarket=30; //seconds
    public static final long baseTimeToGrow=30; //seconds
    public static final long timeReductionPerLevel=2500; //milliseconds

    private Player player;
    private Market market;
    private SalesCount playerSalesCount;
    private int unlockedProducts;
    private long startTime;
    private DustCollector tomatoDustCollector;
    private DustCollector eggplantDustCollector;
    private DustCollector pumpkinDustCollector;
    private DustCollector broccoliDustCollector;

    public Game(String name){
        player=new Player(name);
        unlockedProducts=1;
        market=new Market(unlockedProducts);
        playerSalesCount=new SalesCount();
        startTime=(System.currentTimeMillis())/1000;
        tomatoDustCollector=new DustCollector();
        pumpkinDustCollector=new DustCollector();
        eggplantDustCollector=new DustCollector();
        broccoliDustCollector=new DustCollector();

    }

    public Game(Player player,int unlockedProducts,Market market,SalesCount playerSalesCount,long time, DustCollector tomato, DustCollector pumpkin, DustCollector eggplant, DustCollector broccoli){
        this.player=player;
        this.unlockedProducts=unlockedProducts;
        this.market=market;
        this.playerSalesCount=playerSalesCount;
        this.startTime=time;
        this.tomatoDustCollector=tomato;
        this.eggplantDustCollector=eggplant;
        this.pumpkinDustCollector=pumpkin;
        this.broccoliDustCollector=broccoli;

    }

    public long getStartTime() {
        return startTime/1000;
    }

    public SalesCount getPlayerSalesCount() {
        return playerSalesCount;
    }

    public int getUnlockedProducts() {
        return unlockedProducts;
    }

    public Market getMarket() {
        return market;
    }

    public Player getPlayer() {
        return player;
    }

    private void updateStartTime(){
        this.startTime=System.currentTimeMillis()/1000;
    }

    public void unlockDustCollector(Products p){
        switch (p){
            case TOMATO:
                tomatoDustCollector.unlock();
                break;
            case EGGPLANT:
                eggplantDustCollector.unlock();
                break;
            case PUMPKIN:
                pumpkinDustCollector.unlock();
                break;
            case BROCCOLI:
                broccoliDustCollector.unlock();
                break;
            default:
                break;
        }
    }

    public void disableDustCollector(Products p){
        switch (p){
            case TOMATO:
                tomatoDustCollector.disable((System.currentTimeMillis()/1000)+timerDustCollector);
                break;
            case EGGPLANT:
                if(!eggplantDustCollector.isLocked())
                    eggplantDustCollector.disable((System.currentTimeMillis()/1000)+timerDustCollector);
                break;
            case PUMPKIN:
                if(!pumpkinDustCollector.isLocked())
                    pumpkinDustCollector.disable((System.currentTimeMillis()/1000)+timerDustCollector);
                break;
            case BROCCOLI:
                if(!broccoliDustCollector.isLocked())
                    broccoliDustCollector.disable((System.currentTimeMillis()/1000)+timerDustCollector);
                break;
            default:
                break;
        }
    }

    public void enableDustCollector(Products p){
        switch (p){
            case TOMATO:
                tomatoDustCollector.enable();
                break;
            case EGGPLANT:
                if(!eggplantDustCollector.isLocked())
                    eggplantDustCollector.enable();
                break;
            case PUMPKIN:
                if(!pumpkinDustCollector.isLocked())
                    pumpkinDustCollector.enable();
                break;
            case BROCCOLI:
                if(!broccoliDustCollector.isLocked())
                    broccoliDustCollector.enable();
                break;
            default:
                break;
        }
    }

    public boolean timeToUpdate(){
        return ((System.currentTimeMillis()/1000)>=(startTime+timerUpdateMarket));
    }

    public void gameUpdate(){
        updateStartTime();
        market.update(playerSalesCount);
        playerSalesCount.clean();
    }

    public int howManyUpdates(){
        int i=0;
        if(timeToUpdate()){
            long test=(System.currentTimeMillis()/1000)-(startTime+timerUpdateMarket);
            while(test>0){
                i++;
                test-=timerUpdateMarket;
            }
        }
        return i;
    }

    public void performGameUpdate(){
        int n=howManyUpdates();
        for(int i=0;i<n;i++){
            gameUpdate();
        }
    }

    public void unlockNewProduct(){
        if(unlockedProducts<numberOfProductsMax) {
            player.unlockNewProduct();
            unlockedProducts++;
            market=new Market(unlockedProducts);
            playerSalesCount.clean();
            switch(unlockedProducts){
                case 1:
                    unlockDustCollector(Products.TOMATO);
                    break;
                case 2:
                    unlockDustCollector(Products.EGGPLANT);
                    break;
                case 3:
                    unlockDustCollector(Products.PUMPKIN);
                    break;
                case 4:
                    unlockDustCollector(Products.BROCCOLI);
                    break;
                default:
                    break;
            }
        }

    }

    public void addSale(Products p, int quantity){
        int price=0;
        switch (p){
            case TOMATO:
                price=market.getPriceTomato()*quantity;
                break;
            case EGGPLANT:
                if(unlockedProducts>1)
                    price=market.getPriceEggplant()*quantity;
                break;
            case PUMPKIN:
                if(unlockedProducts>2)
                    price=market.getPricePumpkin()*quantity;
                break;
            case BROCCOLI:
                if(unlockedProducts>3)
                    price=market.getPriceBroccoli()*quantity;
                break;
            default:
                break;
        }
        if(price>0){
            playerSalesCount.addMultipleSale(p,quantity);
            player.addSale(p,price);
        }
    }

    public void unlockNewSlot(Products p){
        switch (p){
            case TOMATO:
                player.getSpaceship().getTomatoGarden().unlockNextSlot();
                break;
            case EGGPLANT:
                if(unlockedProducts>1)
                    player.getSpaceship().getEggplantGarden().unlockNextSlot();
                break;
            case PUMPKIN:
                if(unlockedProducts>2)
                    player.getSpaceship().getPumpkinGarden().unlockNextSlot();
                break;
            case BROCCOLI:
                if(unlockedProducts>3)
                    player.getSpaceship().getBroccoliGarden().unlockNextSlot();
                break;
            default:
                break;
        }
    }

    public void upgradeShootNumber(Products p,int indice){
        if(indice>=0 && indice<shootPerSlotMax) {
            switch (p) {
                case TOMATO:
                    player.getSpaceship().getTomatoGarden().increaseShootNumber(indice);
                    break;
                case EGGPLANT:
                    if (unlockedProducts > 1)
                        player.getSpaceship().getEggplantGarden().increaseShootNumber(indice);
                    break;
                case PUMPKIN:
                    if (unlockedProducts > 2)
                        player.getSpaceship().getPumpkinGarden().increaseShootNumber(indice);
                    break;
                case BROCCOLI:
                    if (unlockedProducts > 3)
                        player.getSpaceship().getBroccoliGarden().increaseShootNumber(indice);
                    break;
                default:
                    break;
            }
        }
    }

    public void upgradeSlotLevel(Products p,int indice){
        if(indice>=0 && indice<shootPerSlotMax) {
            switch (p) {
                case TOMATO:
                    player.getSpaceship().getTomatoGarden().increaseLevelSlot(indice);
                    break;
                case EGGPLANT:
                    if (unlockedProducts > 1)
                        player.getSpaceship().getEggplantGarden().increaseLevelSlot(indice);
                    break;
                case PUMPKIN:
                    if (unlockedProducts > 2)
                        player.getSpaceship().getPumpkinGarden().increaseLevelSlot(indice);
                    break;
                case BROCCOLI:
                    if (unlockedProducts > 3)
                        player.getSpaceship().getBroccoliGarden().increaseLevelSlot(indice);
                    break;
                default:
                    break;
            }
        }
    }

    //return time in MILLISECOND
    public long getTimeToGrow(Products p,int indice){
        int level=0;
        if(indice>=0 && indice<shootPerSlotMax) {
            switch (p) {
                case TOMATO:
                    level=player.getSpaceship().getTomatoGarden().getLevelSlot(indice);
                    break;
                case EGGPLANT:
                    if (unlockedProducts > 1)
                        level=player.getSpaceship().getEggplantGarden().getLevelSlot(indice);
                    break;
                case PUMPKIN:
                    if (unlockedProducts > 2)
                        level=player.getSpaceship().getPumpkinGarden().getLevelSlot(indice);
                    break;
                case BROCCOLI:
                    if (unlockedProducts > 3)
                        player.getSpaceship().getBroccoliGarden().getLevelSlot(indice);
                    break;
                default:
                    break;
            }
        }
        return ((baseTimeToGrow*1000)-(level*timeReductionPerLevel));
    }


}
