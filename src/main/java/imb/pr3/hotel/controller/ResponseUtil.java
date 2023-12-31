package imb.pr3.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    private ResponseUtil() {
        // Constructor privado para evitar instanciación
    }

    public static <T> ResponseEntity<APIResponse<T>> success(T data) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.OK.value(), null, data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    public static <T> ResponseEntity<APIResponse<T>> created(T data) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.CREATED.value(), null, data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } 

    public static <T> ResponseEntity<APIResponse<T>> error(HttpStatus status, String message) {
        APIResponse<T> response = new APIResponse<>(status.value(), addSingleMessage(message), null);
        return ResponseEntity.status(status).body(response);
    }    

    public static <T> ResponseEntity<APIResponse<T>> notFound(String message) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.NOT_FOUND.value(), addSingleMessage(message), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }    

    public static <T> ResponseEntity<APIResponse<T>> badRequest(String message) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), addSingleMessage(message), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    } 
    
    private static List<String> addSingleMessage(String message) {
        List<String> messages = new ArrayList<>();
        messages.add(message);
        return messages;
    }
}

