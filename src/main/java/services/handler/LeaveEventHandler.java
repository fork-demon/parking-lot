
package services.handler;

import services.db.InMemoryDB;
import services.event.LeaveEvent;
import services.facade.Handler;
import services.model.Pair;
import services.model.ParkingSlot;
import services.model.Vehicle;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Handles the {@link LeaveEvent} message.
 */
public class LeaveEventHandler implements Handler<LeaveEvent> {

  public void onEvent(LeaveEvent event) {

    //unpark vehicle
    String  slot = event.getSlotNumber();
    ParkingSlot ps =unpark(slot);
    Vehicle car = InMemoryDB.getInstance().getCurrentVehicleSlotMap().get(ps);
    //update other DBs
    Map<String, List<Pair>> colorPairMap = InMemoryDB.getInstance().getColorPairMap();
    if(colorPairMap.containsKey(car.getColor().toLowerCase())){
      colorPairMap.get(car.getColor().toLowerCase()).remove(Pair.of(event.getSlotNumber(),car.getVehicleNumber()));
    }

    Map<String, String> slotRegistrationMap = InMemoryDB.getInstance().getSlotRegistrationMap();
    slotRegistrationMap.remove(car.getVehicleNumber().toLowerCase());

    InMemoryDB.getInstance().getCurrentVehicleSlotMap().remove(car);
    System.out.println("Slot number "+ps.getSlotNumber()+" is free");
  }

  private ParkingSlot unpark(String slot){
    PriorityQueue<ParkingSlot> pq = InMemoryDB.getInstance().getPriorityQueue();
    ParkingSlot ps = new ParkingSlot(slot);
    if(!pq.contains(ps)) {
      pq.add(ps);
    }
    else {
      throw new IllegalStateException("Invalid Parking Lot.");
    }
    return ps;
  }
}
