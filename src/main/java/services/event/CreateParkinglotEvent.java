package services.event;

public class CreateParkinglotEvent extends AbstractEvent {

    private Integer size;

    public CreateParkinglotEvent(Integer capacity){
       size = capacity;
    }

    public Integer getSize(){
        return  size;
    }
}
