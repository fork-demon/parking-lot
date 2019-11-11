package services.model;

import java.util.Objects;

public class ParkingSlot {

    private String slotNumber;
    private Vehicle vehicle;

    public ParkingSlot(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ParkingSlot) {
            ParkingSlot ps = (ParkingSlot) obj;
            return this.getSlotNumber().equals(ps.getSlotNumber());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSlotNumber());
    }
}
