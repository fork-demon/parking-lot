package services.event;

import services.model.Vehicle;

/**
 * The {@link LeaveEvent} should should be dispatched whenever a user has been updated.
 * This class can be extended to contain details about the user has been updated. In this example,
 * the entire {@link Vehicle} object is passed on as data with the event.
 */
public class LeaveEvent extends AbstractEvent {

  private String slotNumber;

  public LeaveEvent(String slot) {
    this.slotNumber = slot;
  }

  public String getSlotNumber(){
    return slotNumber;
  }
}
