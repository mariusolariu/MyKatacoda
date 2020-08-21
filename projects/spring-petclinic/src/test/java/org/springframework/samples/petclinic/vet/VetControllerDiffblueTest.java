package org.springframework.samples.petclinic.vet;

import static org.mockito.Mockito.when;
import java.util.ArrayList;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = {VetController.class})
@ExtendWith(SpringExtension.class)
public class VetControllerDiffblueTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private VetRepository vetRepository;
  @Test
  public void testShowResourcesVetList() throws Exception {
    // Arrange
    when(this.vetRepository.findAll()).thenReturn(new ArrayList<Vet>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/vets");

    // Act
    ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    ResultActions resultActions1 = resultActions
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    Matcher<String> matcher = Matchers.containsString("{\"vetList\":[]}");
    resultActions1.andExpect(MockMvcResultMatchers.content().string(matcher));
  }
  @Test
  public void testShowVetList() throws Exception {
    // Arrange
    when(this.vetRepository.findAll()).thenReturn(new ArrayList<Vet>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/vets.html");

    // Act
    ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    ResultActions resultActions1 = resultActions.andExpect(MockMvcResultMatchers.model().size(1));
    resultActions1.andExpect(MockMvcResultMatchers.model().attributeExists("vets"));
  }
}

