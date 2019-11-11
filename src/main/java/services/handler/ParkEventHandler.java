
package services.handler;

import services.db.InMemoryDB;
import services.event.ParkEvent;
import services.facade.Handler;
import services.facade.Event;
import services.model.Pair;
import services.model.ParkingSlot;
import services.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Handles the {@link ParkEvent} message.
 */
public class ParkEventHandler implements Handler<ParkEvent> {


  /**
   * Save vehicle details in db
   * @param event the {@link Event} object to be handled.
   */
  public void onEvent(ParkEvent event) {
    Vehicle car = event.getVehicle();
    //park vehicle and update main priority queue
    ParkingSlot ps = park(car);

    if( null != ps) {
      InMemoryDB.getInstance().getCurrentVehicleSlotMap().put(ps, car);
      // update slot color map
      Map<String, List<Pair>> colorPairMap = InMemoryDB.getInstance().getColorPairMap();
      if (colorPairMap.containsKey(ps.getVehicle().getColor().toLowerCase())) {
        colorPairMap.get(ps.getVehicle().getColor().toLowerCase()).add(Pair.of(ps.getSlotNumber(), event.getVehicle().getVehicleNumber()));
      } else {
        List<Pair> slotList = new ArrayList<>();
        slotList.add(Pair.of(ps.getSlotNumber(), event.getVehicle().getVehicleNumber()));
        colorPairMap.put(ps.getVehicle().getColor().toLowerCase(), slotList);
      }
      // update slot registration map
      Map<String, String> slotRegistrationMap = InMemoryDB.getInstance().getSlotRegistrationMap();
      slotRegistrationMap.put(event.getVehicle().getVehicleNumber().toLowerCase(),ps.getSlotNumber());
      System.out.println("Allocated slot number: " + ps.getSlotNumber());
    }

  }

  private  ParkingSlot park(Vehicle vehicle){
    PriorityQueue<ParkingSlot> pq = InMemoryDB.getInstance().getPriorityQueue();
    ParkingSlot ps = getNextAvailable();
    if(ps == null) {
      System.out.println("Sorry, parking lot is full");
      return null;
    }
    ps.setVehicle(vehicle);
    pq.remove(ps);
    return ps;
  }
  private  ParkingSlot getNextAvailable(){
    PriorityQueue<ParkingSlot> pq = InMemoryDB.getInstance().getPriorityQueue();
    if(pq.size() > 0)
    {
      return pq.peek();
    }
    return null;
  }


}
