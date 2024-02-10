package model;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    private final int totalSlots;
    private int slotsAvailable;
    private List<Car> slots;
    private int closestPointer;

    public Parking(int totalSlots) {
        this.totalSlots = totalSlots;
        this.slotsAvailable = totalSlots;
        this.slots = new ArrayList<>(totalSlots);
        setAllSlots();
        this.closestPointer = 0;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public int getSlotsAvailable() {
        return slotsAvailable;
    }

    public void setSlotsAvailable(int slotsAvailable) {
        this.slotsAvailable = slotsAvailable;
    }

    public List<Car> getSlots() {
        return slots;
    }

    public void setSlots(List<Car> slots) {
        this.slots = slots;
    }

    public int getClosestPointer() {
        return closestPointer;
    }

    public void setClosestPointer(int closestPointer) {
        this.closestPointer = closestPointer;
    }

    private void setAllSlots(){
        for(int i= 0; i<slots.size(); i++){
            slots.add(null);
        }
    }
}
