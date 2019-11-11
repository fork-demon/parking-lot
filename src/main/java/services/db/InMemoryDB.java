package services.db;

import services.model.Pair;
import services.model.ParkingSlot;
import services.model.Vehicle;

import java.util.*;

public class InMemoryDB {

    private   Map<String, List<Pair>>  colorPairMap = new HashMap<>();
    private   Map<String, String>  slotRegistrationMap = new HashMap<>();
    private PriorityQueue<ParkingSlot> priorityQueue = null;
    private Map<ParkingSlot, Vehicle> currentVehicleSlotMap = null;

    private InMemoryDB(){

    }

    public static InMemoryDB getInstance(){
        return SingletonHolder.INSTNACE;
    }

    public static class SingletonHolder {

        private static final InMemoryDB INSTNACE = new InMemoryDB();

    }

    public Map<String, List<Pair>> getColorPairMap(){
        return colorPairMap;
    }
    public  Map<String,String> getSlotRegistrationMap(){
        return slotRegistrationMap;
    }
    public PriorityQueue<ParkingSlot> getPriorityQueue(){
        return priorityQueue;
    }
    public Map<ParkingSlot, Vehicle> getCurrentVehicleSlotMap(){
        return currentVehicleSlotMap;
    }


    public void init(Integer capacity) {
        priorityQueue = new PriorityQueue<>(capacity, new Comparator<ParkingSlot>() {
            @Override
            public int compare(ParkingSlot o1, ParkingSlot o2) {
                return o1.getSlotNumber().compareTo(o2.getSlotNumber());
            }
        });
        currentVehicleSlotMap = new HashMap<>(capacity);
        for(int i=1;i<=capacity;i++){
            addParkingSlot(String.valueOf(i));
        }
    }

    public void clear(){
        slotRegistrationMap.clear();
        colorPairMap.clear();
        if(null != priorityQueue){
            priorityQueue.clear();
        }
        if(null != currentVehicleSlotMap){
            currentVehicleSlotMap.clear();
        }
    }

    public void addParkingSlot (String slot) {
        ParkingSlot ps = new ParkingSlot(slot);
        getPriorityQueue().add(ps);
    }


}
