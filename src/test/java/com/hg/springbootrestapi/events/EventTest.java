package com.hg.springbootrestapi.events;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void builder() {
        Event event = Event.builder()
            .name("Inflearn Spring REST API")
            .description("REST API development with Spring")
            .build();
        assertNotNull(event);
    }

    @Test
    void javaBean() {
        // given
        String name = "Event";
        String description = "Spring";

        // when
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);

        // then
        assertEquals(name, event.getName());
        assertEquals(description, event.getDescription());
    }

    @Test
    void testFree() {
        // given
        Event event = Event.builder()
            .basePrice(0)
            .maxPrice(0)
            .build();

        // when
        event.update();

        // then
        assertTrue(event.isFree());

        // given
        event = Event.builder()
            .basePrice(100)
            .maxPrice(0)
            .build();

        // when
        event.update();

        // then
        assertFalse(event.isFree());

        // given
        event = Event.builder()
            .basePrice(0)
            .maxPrice(100)
            .build();

        // when
        event.update();

        // then
        assertFalse(event.isFree());
    }

    @Test
    void testOffline() {
        // given
        Event event = Event.builder()
            .location("강남역")
            .build();

        // when
        event.update();

        // then
        assertTrue(event.isOffline());

        // given
        event = Event.builder()
            .build();

        // when
        event.update();

        // then
        assertFalse(event.isOffline());
    }
}