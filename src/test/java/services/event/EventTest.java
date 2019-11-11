/**
 * The MIT License
 * Copyright (c) 2014-2016 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package services.event;

import org.junit.jupiter.api.Test;
import services.model.Vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link ParkEvent} tests and verifies {@link AbstractEvent} behaviour.
 */
public class EventTest {

  /**
   * This unit test should correctly return the {@link AbstractEvent} class type when calling the
   * {@link AbstractEvent#getType() getType} method.
   */
  @Test
  public void testGetParkEventType() {
    Vehicle car = new Vehicle();
    car.setColor("White");
    car.setVehicleNumber("1234");
    ParkEvent parkEvent = new ParkEvent(car);
    assertEquals(ParkEvent.class, parkEvent.getType());
  }
  @Test
  public void testGetLeaveEventType() {

    LeaveEvent leaveEvent = new LeaveEvent("2");
    assertEquals(LeaveEvent.class, leaveEvent.getType());
  }

  @Test
  public void testGetCreateParkingLotEventType() {

    CreateParkinglotEvent createParkinglotEvent = new CreateParkinglotEvent(6);
    assertEquals(CreateParkinglotEvent.class, createParkinglotEvent.getType());

  }
}
