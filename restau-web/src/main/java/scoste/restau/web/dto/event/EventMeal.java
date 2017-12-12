package scoste.restau.web.dto.event;

import scoste.restau.web.dto.event.data.Next;
import scoste.restau.web.dto.event.data.Previous;

public class EventMeal<P,N>  extends Event<P,N>  {

    private final String meal;
    private final EventType type;
    private final MealAction mealAction;

    private EventMeal(String restaurant, String meal, EventType type, MealAction mealAction, Previous<P> previous, Next<N> next, String comment){
        this.mealAction = mealAction;
        this.previous = previous;
        this.next = next;
        this.restaurant = restaurant;
        this.meal = meal;
        this.comment = comment;
        this.type = type;
    }

    @Override
    public EventType getType() {
        return type;
    }

    @Override
    public EventScope getScope() {
        return EventScope.SERVICE;
    }

    @Override
    public String toString() {
        return "EventMeal{" +
                "meal='" + meal + '\'' +
                ", comment='" + comment + '\'' +
                ", type=" + type +
                ", mealAction=" + mealAction +
                ", restaurant='" + restaurant + '\'' +
                ", previous=" + previous +
                ", next=" + next +
                '}';
    }


    private enum MealAction {
        ADD_CLIENT,
        REMOVE_CLIENT;
    }

    public static class Builder<P,N>{

        private String restaurant;
        private String meal;
        private String comment;
        private EventType type;
        private MealAction mealAction;
        public Previous<P> previous;
        public Next<N> next;

        public Builder withComment(String comment){
            this.comment = comment;
            return this;
        }

        public Builder withType(EventType type){
            this.type = type;
            return this;
        }

        public Builder addClient(){
            this.mealAction = MealAction.ADD_CLIENT;
            return this;
        }

        public Builder removeClient(){
            this.mealAction = MealAction.REMOVE_CLIENT;
            return this;
        }

        public Builder from (Previous<P> previous){
            this.previous = previous;
            return this;
        }

        public Builder to (Next<N> next){
            this.next = next;
            return this;
        }

        public Builder(String restaurant, String meal){
            this.meal = meal;
            this.restaurant = restaurant;
            this.type = EventType.OK;
        }

        public EventMeal build(){
            return new EventMeal(restaurant, meal, type, mealAction, previous, next, comment);
        }
    }


}
