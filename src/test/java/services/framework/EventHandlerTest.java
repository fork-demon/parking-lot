package services.framework;

import org.junit.jupiter.api.Test;
import services.db.InMemoryDB;
import services.event.CreateParkinglotEvent;
import services.event.LeaveEvent;
import services.event.ParkEvent;
import services.handler.CreateParkingLotEventHandler;
import services.handler.LeaveEventHandler;
import services.handler.ParkEventHandler;
import services.model.Pair;
import services.model.ParkingSlot;
import services.model.Vehicle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventHandlerTest {

    @Test
    public void testParkEvent(){
        Vehicle car = new Vehicle();
        car.setVehicleNumber("123");
        car.setColor("White");

        ParkingSlot ps = new ParkingSlot("1");

        CreateParkinglotEvent createParkinglotEvent = new CreateParkinglotEvent(1);
        CreateParkingLotEventHandler createParkingLotEventHandler = new CreateParkingLotEventHandler();
        createParkingLotEventHandler.onEvent(createParkinglotEvent);

        ParkEventHandler parkEventHandler = new ParkEventHandler();
        parkEventHandler.onEvent(new ParkEvent(car));

        assertEquals(InMemoryDB.getInstance().getColorPairMap().size(), 1);
        assertEquals(InMemoryDB.getInstance().getColorPairMap().get(car.getColor().toLowerCase()).size(),1 );
        List<Pair>  pairList = InMemoryDB.getInstance().getColorPairMap().get(car.getColor().toLowerCase());
        if(null != pairList && !pairList.isEmpty()){
            Pair pair = pairList.get(0);
            assertEquals(pair.getLeft(), ps.getSlotNumber());
            assertEquals(pair.getRight(), car.getVehicleNumber());
        }
        assertEquals(InMemoryDB.getInstance().getSlotRegistrationMap().size(), 1);
        assertEquals(InMemoryDB.getInstance().getSlotRegistrationMap().get(car.getVehicleNumber()), ps.getSlotNumber());
        assertEquals(InMemoryDB.getInstance().getCurrentVehicleSlotMap().size(), 1);
        assertEquals(InMemoryDB.getInstance().getCurrentVehicleSlotMap().get(ps), car);
        assertEquals(InMemoryDB.getInstance().getPriorityQueue().size(), 0);

    }

    @Test
    public void testLeaveEvent(){
        Vehicle car = new Vehicle();
        car.setVehicleNumber("123");
        car.setColor("White");

        ParkingSlot ps = new ParkingSlot("1");

        CreateParkinglotEvent createParkinglotEvent = new CreateParkinglotEvent(1);
        CreateParkingLotEventHandler createParkingLotEventHandler = new CreateParkingLotEventHandler();
        createParkingLotEventHandler.onEvent(createParkinglotEvent);

        ParkEventHandler parkEventHandler = new ParkEventHandler();
        parkEventHandler.onEvent(new ParkEvent(car));


        assertEquals(InMemoryDB.getInstance().getColorPairMap().size(), 1);
        assertEquals(InMemoryDB.getInstance().getColorPairMap().get(car.getColor().toLowerCase()).size(),1 );
        List<Pair>  pairList = InMemoryDB.getInstance().getColorPairMap().get(car.getColor().toLowerCase());
        if(null != pairList && !pairList.isEmpty()){
            Pair pair = pairList.get(0);
            assertEquals(pair.getLeft(), ps.getSlotNumber());
            assertEquals(pair.getRight(), car.getVehicleNumber());
        }
        assertEquals(InMemoryDB.getInstance().getSlotRegistrationMap().size(), 1);
        assertEquals(InMemoryDB.getInstance().getSlotRegistrationMap().get(car.getVehicleNumber()), ps.getSlotNumber());
        assertEquals(InMemoryDB.getInstance().getCurrentVehicleSlotMap().size(), 1);
        assertEquals(InMemoryDB.getInstance().getCurrentVehicleSlotMap().get(ps), car);
        assertEquals(InMemoryDB.getInstance().getPriorityQueue().size(), 0);

        LeaveEvent leaveEvent = new LeaveEvent("1");
        LeaveEventHandler leaveEventHandler = new LeaveEventHandler();
        leaveEventHandler.onEvent(leaveEvent);

        assertEquals(InMemoryDB.getInstance().getColorPairMap().get(car.getColor().toLowerCase()).size(), 0);
        assertEquals(InMemoryDB.getInstance().getSlotRegistrationMap().size(), 0);
        assertEquals(InMemoryDB.getInstance().getPriorityQueue().size(), 1);



    }
}
