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
package services;


import services.event.CreateParkinglotEvent;
import services.event.LeaveEvent;
import services.event.ParkEvent;
import services.executor.AbstractExecutor;
import services.executor.FileExecutor;
import services.executor.InteractiveConsoleExecutor;
import services.facade.DispatcherRequestProcessor;
import services.facade.EventDispatcher;
import services.facade.QueryRequestProcessor;
import services.handler.CreateParkingLotEventHandler;
import services.handler.LeaveEventHandler;
import services.handler.ParkEventHandler;

import java.io.IOException;

public class App {


  public static void main(String[] args) throws IOException {

    // Init dependecies
    EventDispatcher dispatcher = new EventDispatcher();
    dispatcher.registerHandler(ParkEvent.class, new ParkEventHandler());
    dispatcher.registerHandler(LeaveEvent.class, new LeaveEventHandler());
    dispatcher.registerHandler(CreateParkinglotEvent.class, new CreateParkingLotEventHandler());

    QueryRequestProcessor queryRequestProcessor = new QueryRequestProcessor();
    DispatcherRequestProcessor dispatcherRequestProcessor = new DispatcherRequestProcessor();
    dispatcherRequestProcessor.setEventDispatcher(dispatcher);

    // Execution
    AbstractExecutor executor = null;

    System.out.println("**************************************************************");
    System.out.println("******************** GOJEK PARKING LOT ***********************");
    System.out.println("**************************************************************");


    if(args.length >= 1) {
      System.out.println("+++++++++++++++++++++++++ File Mode +++++++++++++++++++++++++");

      String filePath= args[0];
      executor = new FileExecutor(queryRequestProcessor,dispatcherRequestProcessor,filePath);

    } else {
      System.out.println("+++++++++ Interactive Mode, Please enter command++++++++++++");

      executor = new InteractiveConsoleExecutor(queryRequestProcessor,dispatcherRequestProcessor);
    }
    executor.run();

  }

}
