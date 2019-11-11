package services.factory;

import services.event.AbstractEvent;
import services.event.CreateParkinglotEvent;
import services.event.LeaveEvent;
import services.event.ParkEvent;
import services.model.Vehicle;

import java.util.Map;

public class EventFactory {


    public static AbstractEvent getEventType(String[] params){

        AbstractEvent abstractEvent = null;
        String eventName = params[0];
        if("park".equals(eventName)){
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleNumber(params[1]);
            vehicle.setColor(params[2]);
           abstractEvent = new ParkEvent(vehicle);
        }
        else if("leave".equals(eventName)){
            String slotNumber = params[1];
            abstractEvent = new LeaveEvent(slotNumber);
        }
        else if( "create_parking_lot".equals(eventName)){
            abstractEvent = new CreateParkinglotEvent(Integer.valueOf(params[1]));
        }
        return  abstractEvent;
    }
}
