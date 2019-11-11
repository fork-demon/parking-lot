
package services.model;

import java.util.Date;
import java.util.Objects;

import services.event.ParkEvent;
import services.event.LeaveEvent;

/**
 * This {@link Vehicle} class is a basic pojo used to demonstrate user data sent along with
 * the {@link ParkEvent} and {@link LeaveEvent} events.
 */
public class Vehicle {

  private String color;
  private String vehicleNumber;
  private Date startTime;
  private Date endTime;

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getVehicleNumber() {
    return vehicleNumber;
  }

  public void setVehicleNumber(String vehicleNumber) {
    this.vehicleNumber = vehicleNumber;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Vehicle)) return false;
    Vehicle vehicle = (Vehicle) o;
    return getVehicleNumber().equals(vehicle.getVehicleNumber());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getVehicleNumber());
  }
}
