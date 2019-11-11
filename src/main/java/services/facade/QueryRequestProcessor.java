package services.facade;

import services.db.InMemoryDB;
import services.model.Pair;
import services.model.ParkingSlot;
import services.model.Vehicle;
import services.query.QueryType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QueryRequestProcessor implements RequestProcessor <String>{
    @Override
    public void process(String request, Map<String, String> args) {

        if (QueryType.REG_NOS_FOR_COLOR.getQueryType().equals(request)) {
            if (null != args && args.containsKey("criteria")) {
                String color = (String) args.get("criteria");
                List<Pair> colorPairList = InMemoryDB.getInstance().getColorPairMap().get(color.toLowerCase());
                if(!colorPairList.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (Pair pair : colorPairList) {
                        sb.append(pair.getRight()).append(",");
                    }
                    sb.setLength(sb.length() - 1);
                    System.out.println(sb.toString());
                }else{
                    System.out.println("Not Found");
                }
            }

        } else if (QueryType.SLOT_NOS_FOR_COLOR.getQueryType().equals(request)) {
            if (null != args && args.containsKey("criteria")) {
                String color = (String) args.get("criteria");
                List<Pair> colorPairList = InMemoryDB.getInstance().getColorPairMap().get(color.toLowerCase());
                if(!colorPairList.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (Pair pair : colorPairList) {
                        sb.append(pair.getLeft()).append(",");
                    }
                    sb.setLength(sb.length() - 1);
                    System.out.println(sb.toString());
                }else{
                    System.out.println("Not Found");
                }
            }
        } else if (QueryType.SLOT_NOS_FOR_REG_NO.getQueryType().equals(request)) {
            if (null != args && args.containsKey("criteria")) {
                String registrationNo = (String) args.get("criteria");
                String slotNumber = InMemoryDB.getInstance().getSlotRegistrationMap().get(registrationNo.toLowerCase());
                if(null != slotNumber){
                    System.out.println(slotNumber);
                }
                else{
                    System.out.println("Not Found");
                }


            }
        } else if (QueryType.STATUS.getQueryType().equals(request)) {
            Map<ParkingSlot, Vehicle> statusMap = InMemoryDB.getInstance().getCurrentVehicleSlotMap();
            if (null != statusMap && !statusMap.isEmpty()) {
                System.out.printf("%5s %30s %20s ", "Slot No.", "Registration No.", "Color");
                System.out.println();
                for (Map.Entry<ParkingSlot, Vehicle> entry : statusMap.entrySet()) {
                    ParkingSlot ps = entry.getKey();
                    Vehicle car = entry.getValue();
                    System.out.format("%5s %30s %20s", ps.getSlotNumber(), car.getVehicleNumber(), car.getColor());
                    System.out.println();

                }
            }
            else{
                System.out.println("Not Found");
            }
        }

    }
}
