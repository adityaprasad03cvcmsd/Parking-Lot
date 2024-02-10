package service;

import model.Car;
import model.Parking;

import java.util.List;

public class ParkingService {

    private Parking parking;

    public ParkingService(int parkingSlots) {
        this.parking = new Parking(parkingSlots);
    }

    public int park(String registrationNumber, String color) {
        int slotsAvailable = parking.getSlotsAvailable();
        int closestPointer = parking.getClosestPointer();
        List<Car> slots = parking.getSlots();

        if (slotsAvailable <= 0 || closestPointer == -1) {
            System.out.println("Sorry, parking lot is full");
            return -1;
        }

        Car newCar = new Car(registrationNumber, color);
        slots.add(closestPointer, newCar);
        parking.setSlots(slots);                       // slots are being updated
        parking.setSlotsAvailable(slotsAvailable-1);  //available slots decreased

        int slotAllotted = closestPointer + 1;
        parking.setClosestPointer(settingClosestPointer()); //closest pointer is being updated

        System.out.println("Allocated slot number: " + slotAllotted);
        return slotAllotted;
    }

    private int settingClosestPointer() {
        int totalSlots = parking.getTotalSlots();
        List<Car> slots = parking.getSlots();

        int i = 0;
        while (i < totalSlots) {
            try {
                if (slots.get(i) == null) {
                    return i;
                }
            } catch (IndexOutOfBoundsException e){
                return i;
            }
            i++;
        }

        return -1;
    }

    public void leave(int slotNumber) {
        if (!isSlotOccupied(slotNumber)) {
            return;
        }

        List<Car> slots = parking.getSlots();
        int slotsAvailable = parking.getSlotsAvailable();

        slots.set(slotNumber-1,null);

        parking.setSlots(slots);    //slots are being updated
        parking.setSlotsAvailable(slotsAvailable+1);    // available slots increases by 1
        parking.setClosestPointer(settingClosestPointer()); //closest pointer is being updated;

        System.out.println("Slot number " + slotNumber + " is free now");
    }

    private boolean isSlotOccupied(int slotNumber) {
        int totalSlots = parking.getTotalSlots();
        List<Car> slots = parking.getSlots();

        if (slotNumber - 1 < 0 || slotNumber - 1 > totalSlots) {
            System.out.println("Slot number doesn't exists");
            return false;
        }

        Car car = slots.get(slotNumber - 1);

        if (car == null) {
            System.out.println("Slot number is not occupied");
            return false;
        }

        return true;
    }

    public void status() {
        List<Car> slots = parking.getSlots();

        System.out.println("Slot No. Registration No Color");
        for(int i= 0; i<slots.size(); i++){
            Car car = slots.get(i);
            if(car == null)continue;

            int slotNumber = i+1;
            System.out.println(slotNumber + " " + car.getRegistrationNumber() + " " + car.getColor());
        }
    }

    public String registrationNumberOfCarsWithColor(String color) {
        StringBuilder result = new StringBuilder();
        for (Car car :  parking.getSlots()) {
            if(car == null) continue;

            if (car.getColor().equalsIgnoreCase(color)) {
                result.append(car.getRegistrationNumber()).append("\n");
            }
        }
        return result.toString();
    }

    public int slotNumberFromRegistrationNumber(String registrationNumber) {
        List<Car> slots = parking.getSlots();

        for(int i = 0; i<slots.size(); i++){
            Car car = slots.get(i);
            if(car==null) continue;

            if(car.getRegistrationNumber().equals(registrationNumber)) return i+1;
        }

        System.out.println("No cars with registration number - "+ registrationNumber+" found");
        return -1;
    }

    public String slotNumbersFromCarColor(String color) {
        List<Car> slots = parking.getSlots();

        StringBuilder result = new StringBuilder();
        for (int i=0; i<slots.size(); i++) {
            Car car  = slots.get(i);
            if(car == null) continue;

            if (car.getColor().equalsIgnoreCase(color)) {
                result.append(i).append("\n");
            }
        }
        return result.toString();
    }

    public String getRegistrationNumberFromSlot(int slotNumber) {
        if(!isSlotOccupied(slotNumber)) return "";

        return parking.getSlots().get(slotNumber-1).getRegistrationNumber();
    }

    public String getCarDetails(String registrationNumber) {
        int slot = slotNumberFromRegistrationNumber(registrationNumber);

        if(slot == -1){
            System.out.println("No cars for the registration number - "+ registrationNumber+" found");
            return null;
        } else{
            return parking.getSlots().get(slot-1).toString();
        }
    }

    public void creatParkingLot(int totalSlots){
        parking = new Parking(totalSlots);
        System.out.println("Created a parking lot with " + totalSlots + " slots");
    }
}
