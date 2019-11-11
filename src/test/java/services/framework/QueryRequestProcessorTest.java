package services.framework;

import org.junit.jupiter.api.Test;
import services.db.InMemoryDB;
import services.facade.QueryRequestProcessor;
import services.model.Pair;
import services.model.ParkingSlot;
import services.model.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryRequestProcessorTest {

    @Test
    public void testRegistrationNumberForColor() {

        Vehicle car = new Vehicle();
        car.setVehicleNumber("123");
        car.setColor("White");

        ParkingSlot ps = new ParkingSlot("1");
        Pair pair = Pair.of("1",car.getVehicleNumber());
        List<Pair> lst = new ArrayList<>();
        lst.add(pair);
        InMemoryDB.getInstance().init(1);
        InMemoryDB.getInstance().getCurrentVehicleSlotMap().put(ps,car);
        InMemoryDB.getInstance().getColorPairMap().put("white",lst);
        InMemoryDB.getInstance().getSlotRegistrationMap().put(ps.getSlotNumber(),car.getVehicleNumber());
        InMemoryDB.getInstance().addParkingSlot(ps.getSlotNumber());
        Map<String,String> args = new HashMap<>();
        args.put("criteria","white");
        QueryRequestProcessor qp = new QueryRequestProcessor();
        qp.process("registration_numbers_for_cars_with_colour",args);
        qp.process("status",null);

    }

    @Test
    public void testSlotNumbersForColor() {

        Vehicle car = new Vehicle();
        car.setVehicleNumber("123");
        car.setColor("White");

        ParkingSlot ps = new ParkingSlot("1");
        Pair pair = Pair.of("1",car.getVehicleNumber());
        List<Pair> lst = new ArrayList<>();
        lst.add(pair);
        InMemoryDB.getInstance().init(1);
        InMemoryDB.getInstance().getCurrentVehicleSlotMap().put(ps,car);
        InMemoryDB.getInstance().getColorPairMap().put("white",lst);
        InMemoryDB.getInstance().getSlotRegistrationMap().put(ps.getSlotNumber(),car.getVehicleNumber());
        InMemoryDB.getInstance().addParkingSlot(ps.getSlotNumber());
        Map<String,String> args = new HashMap<>();
        args.put("criteria","white");
        QueryRequestProcessor qp = new QueryRequestProcessor();
        qp.process("slot_numbers_for_cars_with_colour",args);
        qp.process("status",null);

    }

    @Test
    public void testSlotNumberForRegNumber() {

        Vehicle car = new Vehicle();
        car.setVehicleNumber("123");
        car.setColor("White");

        ParkingSlot ps = new ParkingSlot("1");
        Pair pair = Pair.of("1",car.getVehicleNumber());
        List<Pair> lst = new ArrayList<>();
        lst.add(pair);
        InMemoryDB.getInstance().init(1);
        InMemoryDB.getInstance().getCurrentVehicleSlotMap().put(ps,car);
        InMemoryDB.getInstance().getColorPairMap().put("white",lst);
        InMemoryDB.getInstance().getSlotRegistrationMap().put(ps.getSlotNumber(),car.getVehicleNumber());
        InMemoryDB.getInstance().addParkingSlot(ps.getSlotNumber());
        Map<String,String> args = new HashMap<>();
        args.put("criteria","white");
        QueryRequestProcessor qp = new QueryRequestProcessor();
        qp.process("slot_number_for_registration_number",args);
        qp.process("status",null);

    }

    }
