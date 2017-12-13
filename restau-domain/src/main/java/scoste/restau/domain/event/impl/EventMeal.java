package scoste.restau.domain.event.impl;

import scoste.restau.domain.event.*;
import scoste.restau.domain.event.data.Next;
import scoste.restau.domain.event.data.Previous;

import java.io.Serializable;

public class EventMeal<P extends Serializable,N extends Serializable>  extends Event<P,N> {

    private final String meal;
    private final EventType type;
    private final MealAction mealAction;

    private EventMeal(EventId eventId, EventTime eventTime, String meal, EventType type, MealAction mealAction, Previous<P> previous, Next<N> next, String comment){
        this.id = eventId;
        this.eventTime = eventTime;
        this.mealAction = mealAction;
        this.previous = previous;
        this.next = next;
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
                ", type=" + type +
                ", mealAction=" + mealAction +
                ", id=" + id +
                ", eventTime=" + eventTime +
                ", previous=" + previous +
                ", next=" + next +
                ", comment='" + comment + '\'' +
                '}';
    }


    private enum MealAction {
        ADD_CLIENT,
        REMOVE_CLIENT
    }

    public static class Builder<P extends Serializable,N extends Serializable>{

        private EventTime eventTime;
        private EventId eventId;
        private String meal;
        private String comment;
        private EventType type;
        private MealAction mealAction;
        private Previous<P> previous;
        private Next<N> next;

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

        public Builder(EventId eventId, EventTime eventTime, String meal){
            this.meal = meal;
            this.eventId = eventId;
            this.eventTime = eventTime;
            this.type = EventType.OK;
        }

        public EventMeal build(){
            return new EventMeal(eventId, eventTime, meal, type, mealAction, previous, next, comment);
        }
    }


}
