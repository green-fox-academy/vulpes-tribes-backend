package com.tribesbackend.tribes.services;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJsonConverter {
    public static String asJsonString(final Object o) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(o);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
