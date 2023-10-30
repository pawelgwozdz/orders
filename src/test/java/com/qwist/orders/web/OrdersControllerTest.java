package com.qwist.orders.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qwist.orders.dao.UserRepository;
import com.qwist.orders.dto.*;
import com.qwist.orders.enums.OrderStatus;
import com.qwist.orders.util.TestUtils;
import com.qwist.orders.utils.InitDataCreateOrderDtoUtils;
import com.qwist.orders.utils.InitDataUserUtils;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureDataMongo
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//Tests for both OrderQueryController.class and OrderCommandController.class,
// we could separate it and also make more tests for services, repositories and other classes, but
// it would be much more time-consuming
class OrdersControllerTest {

    private static final String API_PATH = "/api/v1/orders";

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private String orderIdAdmin;
    private String orderIdUser1;

    @BeforeAll
    void init() {
        userRepository.saveAll(InitDataUserUtils.getDefaultUsers());
    }

    @Order(1)
    @Test
    void testDb() {
        assertNotNull(mongoTemplate.getDb());
    }

    @Order(2)
    @Test
    @WithMockUser(username = "user1", roles = "CUSTOMER")
    void createOrderShouldReturn200AndCorrectResp1() throws Exception {
        //GIVEN
        CreateOrderDto req = InitDataCreateOrderDtoUtils.getOrder1();
        Principal mockPrincipal = Mockito.mock(Principal.class);
        Mockito.when(mockPrincipal.getName()).thenReturn("user1");

        //WHEN
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                        .post(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //THEN
        CreateOrderRespDto result = objectMapper.readValue(response.getContentAsString(), CreateOrderRespDto.class);
        orderIdUser1 = result.getOrderId();
    }

    @Order(3)
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void createOrderShouldReturn200AndCorrectResp2() throws Exception {
        //GIVEN
        CreateOrderDto req = InitDataCreateOrderDtoUtils.getOrder1();
        Principal mockPrincipal = Mockito.mock(Principal.class);
        Mockito.when(mockPrincipal.getName()).thenReturn("admin");

        //WHEN
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                        .post(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //THEN
        CreateOrderRespDto result = objectMapper.readValue(response.getContentAsString(), CreateOrderRespDto.class);
        assertEquals(result.getOrderStatus(), OrderStatus.CREATED);
        assertFalse(result.getOrderId().isBlank());
        orderIdAdmin = result.getOrderId();
    }

    @Order(4)
    @Test
    @WithMockUser(username = "user1", roles = "CUSTOMER")
    void getOrdersForCustomerShouldReturn200andCorrectResp() throws Exception {
        //GIVEN
        Authentication mockAuthentication = TestUtils.mockAuth("user1", "ROLE_CUSTOMER");

        //WHEN
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                        .get(API_PATH)
                        .principal(mockAuthentication)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //THEN
        List<UserRespDto> result = objectMapper.readValue(response.getContentAsString(), objectMapper.getTypeFactory().constructCollectionType(List.class, UserRespDto.class));
        assertTrue(result.size() > 0);
        assertTrue(result.get(0).getOrders().size() > 0);
        assertEquals(result.get(0).getOrders().get(0).getId(), orderIdUser1);
    }

    @Order(5)
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void getOrdersFromAdminShouldReturn200andCorrectResp() throws Exception {
        //GIVEN
        Authentication mockAuthentication = TestUtils.mockAuth("admin", "ROLE_ADMIN");

        //WHEN
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                        .get(API_PATH)
                        .principal(mockAuthentication)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //THEN
        List<UserRespDto> result = objectMapper.readValue(response.getContentAsString(), objectMapper.getTypeFactory().constructCollectionType(List.class, UserRespDto.class));
        assertTrue(result.size() > 0);
        //check if contains admin order
        Optional<UserRespDto> admin = result.stream().filter(o -> o.getLogin().equals("admin")).findFirst();
        assertTrue(admin.isPresent());
        assertTrue(admin.get().getOrders().size() > 0 && admin.get().getOrders().get(0).getId().equals(orderIdAdmin));
        //check if contains user order
        Optional<UserRespDto> user1 = result.stream().filter(o -> o.getLogin().equals("user1")).findFirst();
        assertTrue(user1.isPresent());
        assertTrue(user1.get().getOrders().size() > 0 && user1.get().getOrders().get(0).getId().equals(orderIdUser1));
    }

    @Order(6)
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void deleteOrderFromAdminShouldReturn200andCorrectResp() throws Exception {
        //GIVEN
        Authentication mockAuthentication = TestUtils.mockAuth("admin", "ROLE_ADMIN");

        //WHEN
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                        .delete(API_PATH + "/" + orderIdAdmin)
                        .principal(mockAuthentication)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //THEN
        DeleteOrderRespDto result = objectMapper.readValue(response.getContentAsString(), DeleteOrderRespDto.class);
        assertEquals(result.getOrderStatus(), OrderStatus.REMOVED);

    }

    @Order(7)
    @Test
    @WithMockUser(username = "user1", roles = "CUSTOMER")
    void deleteOrderFromCustomerShouldReturn403() throws Exception {
        //GIVEN
        Authentication mockAuthentication = TestUtils.mockAuth("user1", "ROLE_CUSTOMER");

        //WHEN && THEN
        mockMvc.perform(MockMvcRequestBuilders
                        .delete(API_PATH + "/" + orderIdUser1)
                        .principal(mockAuthentication)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn().getResponse();

    }

    @AfterAll
    void cleanDb() {
        userRepository.deleteAll();
    }
}
