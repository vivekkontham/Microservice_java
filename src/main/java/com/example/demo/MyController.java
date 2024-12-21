// package com.example.demo;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Map;

// @RestController
// public class MyController {

//     @GetMapping("/users")
//     public List<Map<String, Object>> getUsers() {
//         return Arrays.asList(
//             Map.of("id", 1, "name", "Alice"),
//             Map.of("id", 2, "name", "Bob")
//         );
//     }

//     @GetMapping("/data")
//     public Map<String, String> getData() {
//         return Map.of(
//             "key1", "value1",
//             "key2", "value2"
//         );
//     }

//     @GetMapping("/status")
//     public Map<String, String> getStatus() {
//         return Map.of("status", "running");
//     }
// }

package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class MyController {

    private List<Map<String, Object>> users = Arrays.asList(
        Map.of("id", 1, "name", "Alice"),
        Map.of("id", 2, "name", "Bob")
    );

    @GetMapping("/users")
    public List<Map<String, Object>> getUsers() {
        return users;
    }

    // Vulnerable endpoint
    @GetMapping("/users/{id}")
    public Map<String, Object> getUserById(@PathVariable int id) {
        // No validation or authorization check
        return users.stream()
            .filter(user -> (int) user.get("id") == id)
            .findFirst()
            .orElse(Map.of("error", "User not found"));
    }

    @GetMapping("/data")
    public Map<String, String> getData() {
        return Map.of(
            "key1", "value1",
            "key2", "value2"
        );
    }

    @GetMapping("/status")
    public Map<String, String> getStatus() {
        return Map.of("status", "running");
    }
}
