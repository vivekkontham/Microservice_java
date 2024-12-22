// package com.example.demo;

// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// @SpringBootTest
// @AutoConfigureMockMvc
// public class MyControllerTests {

//     @Autowired
//     private MockMvc mockMvc;

//     @Test
//     public void testGetUsers() throws Exception {
//         mockMvc.perform(MockMvcRequestBuilders.get("/users"))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
//                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Alice"))
//                 .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
//                 .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Bob"));
//     }

//     @Test
//     public void testGetData() throws Exception {
//         mockMvc.perform(MockMvcRequestBuilders.get("/data"))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.jsonPath("$.key1").value("value1"))
//                 .andExpect(MockMvcResultMatchers.jsonPath("$.key2").value("value2"));
//     }

//     @Test
//     public void testGetStatus() throws Exception {
//         mockMvc.perform(MockMvcRequestBuilders.get("/status"))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("running"));
//     }
// }


package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(MyController.class)
public class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Alice"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Bob"));
    }

    @Test
    public void testGetUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Alice"));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Bob"));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/3"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("User not found"));
    }

    @Test
    public void testGetData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/data"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.key1").value("value1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.key2").value("value2"));
    }

    @Test
    public void testGetStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/status"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("running"));
    }
}

