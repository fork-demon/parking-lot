package services.handler;

import services.db.InMemoryDB;
import services.event.CreateParkinglotEvent;
import services.facade.Handler;

public class CreateParkingLotEventHandler implements Handler<CreateParkinglotEvent> {

    @Override
    public void onEvent(CreateParkinglotEvent event) {

        InMemoryDB.getInstance().init(event.getSize());
        System.out.println("Created a parking lot with "+event.getSize()+" slots");
    }
}
