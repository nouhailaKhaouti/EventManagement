package com.example.eventmanagementapp.Services;

import com.example.eventmanagementapp.Repositories.EventRepository;

public class EventService {
    private final EventRepository eventRepository;

    public EventService(){
        eventRepository=new EventRepository();
    }


}
