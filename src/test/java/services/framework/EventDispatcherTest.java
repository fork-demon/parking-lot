
package services.framework;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import services.db.InMemoryDB;
import services.event.CreateParkinglotEvent;
import services.event.LeaveEvent;
import services.event.ParkEvent;
import services.facade.EventDispatcher;
import services.handler.CreateParkingLotEventHandler;
import services.handler.LeaveEventHandler;
import services.handler.ParkEventHandler;
import services.model.Vehicle;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Event Dispatcher unit tests to assert and verify correct event dispatcher behaviour
 */
public class EventDispatcherTest {

  /**
   * This unit test should register events and event handlers correctly with the event dispatcher
   * and events should be dispatched accordingly.
   */

  @AfterEach
  public void clear(){
    InMemoryDB.getInstance().clear();
  }
  @Test
  public void testEventDriverPattern() {

    EventDispatcher dispatcher = spy(new EventDispatcher());
    ParkEventHandler parkEventHandler = spy(new ParkEventHandler());
    LeaveEventHandler leaveEventHandler = spy(new LeaveEventHandler());
    CreateParkingLotEventHandler createParkingLotEventHandler = spy(new CreateParkingLotEventHandler());

    dispatcher.registerHandler(ParkEvent.class, parkEventHandler);
    dispatcher.registerHandler(LeaveEvent.class, leaveEventHandler);
    dispatcher.registerHandler(CreateParkinglotEvent.class, createParkingLotEventHandler);

    Vehicle car = new Vehicle();
    car.setColor("White");
    car.setVehicleNumber("1234");
    ParkEvent parkEvent = new ParkEvent(car);

    LeaveEvent leaveEvent = new LeaveEvent("1");
    CreateParkinglotEvent createParkinglotEvent = new CreateParkinglotEvent(6);



    dispatcher.dispatch(createParkinglotEvent);
    verify(createParkingLotEventHandler).onEvent(createParkinglotEvent);
    verify(dispatcher).dispatch(createParkinglotEvent);

    dispatcher.dispatch(parkEvent);
    verify(parkEventHandler).onEvent(parkEvent);
    verify(dispatcher).dispatch(parkEvent);

    dispatcher.dispatch(leaveEvent);
    verify(leaveEventHandler).onEvent(leaveEvent);
    verify(dispatcher).dispatch(leaveEvent);
  }

  @AfterAll
  public void clearAfterAll(){
    InMemoryDB.getInstance().clear();
  }

}
