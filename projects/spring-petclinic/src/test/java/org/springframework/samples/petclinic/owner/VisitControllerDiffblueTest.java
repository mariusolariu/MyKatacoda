package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalMatchers.or;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.isNull;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.samples.petclinic.visit.VisitRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = {VisitController.class})
@ExtendWith(SpringExtension.class)
public class VisitControllerDiffblueTest {
  @Autowired
  private VisitController visitController;
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private PetRepository petRepository;
  @MockBean
  private VisitRepository visitRepository;
  @Test
  public void testInitNewVisitForm() throws Exception {
    // Arrange
    LocalDate birthDate = LocalDate.ofEpochDay(1L);
    Pet pet = new Pet();
    pet.setBirthDate(birthDate);
    Owner owner = new Owner();
    owner.setLastName("Doe");
    owner.setId(1);
    owner.setCity("Oxford");
    owner.setPetsInternal(new HashSet<Pet>());
    owner.setAddress("42 Main St");
    owner.setFirstName("Jane");
    owner.setTelephone("4105551212");
    pet.setOwner(owner);
    pet.setVisitsInternal(new ArrayList<Visit>());
    pet.setId(1);
    pet.setName("Bella");
    PetType petType = new PetType();
    petType.setId(1);
    petType.setName("Dog");
    pet.setType(petType);
    when(this.petRepository.findById(or(isA(Integer.class), isNull()))).thenReturn(pet);
    when(this.visitRepository.findByPetId(or(isA(Integer.class), isNull()))).thenReturn(new ArrayList<Visit>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners/*/pets/{petId}/visits/new",
        123456789);

    // Act
    ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    ResultActions resultActions1 = resultActions.andExpect(MockMvcResultMatchers.model().size(2));
    resultActions1.andExpect(MockMvcResultMatchers.model().attributeExists("pet", "visit"));
  }
  @Test
  public void testLoadPetWithVisit() {
    // Arrange
    LocalDate birthDate = LocalDate.ofEpochDay(1L);
    Pet pet = new Pet();
    pet.setBirthDate(birthDate);
    Owner owner = new Owner();
    owner.setLastName("Doe");
    owner.setId(1);
    owner.setCity("Oxford");
    owner.setPetsInternal(new HashSet<Pet>());
    owner.setAddress("42 Main St");
    owner.setFirstName("Jane");
    owner.setTelephone("4105551212");
    pet.setOwner(owner);
    pet.setVisitsInternal(new ArrayList<Visit>());
    pet.setId(1);
    pet.setName("Bella");
    PetType petType = new PetType();
    petType.setId(1);
    petType.setName("Dog");
    pet.setType(petType);
    when(this.petRepository.findById(or(isA(Integer.class), isNull()))).thenReturn(pet);
    when(this.visitRepository.findByPetId(or(isA(Integer.class), isNull()))).thenReturn(new ArrayList<Visit>());

    // Act and Assert
    assertEquals(1, this.visitController.loadPetWithVisit(123, new HashMap<String, Object>()).getPetId().intValue());
  }
  @Test
  public void testProcessNewVisitForm() throws Exception {
    // Arrange
    LocalDate birthDate = LocalDate.ofEpochDay(1L);
    Pet pet = new Pet();
    pet.setBirthDate(birthDate);
    Owner owner = new Owner();
    owner.setLastName("Doe");
    owner.setId(1);
    owner.setCity("Oxford");
    owner.setPetsInternal(new HashSet<Pet>());
    owner.setAddress("42 Main St");
    owner.setFirstName("Jane");
    owner.setTelephone("4105551212");
    pet.setOwner(owner);
    pet.setVisitsInternal(new ArrayList<Visit>());
    pet.setId(1);
    pet.setName("Bella");
    PetType petType = new PetType();
    petType.setId(1);
    petType.setName("Dog");
    pet.setType(petType);
    when(this.petRepository.findById(or(isA(Integer.class), isNull()))).thenReturn(pet);
    when(this.visitRepository.findByPetId(or(isA(Integer.class), isNull()))).thenReturn(new ArrayList<Visit>());

    // Act
    ResultActions actualPerformResult = this.mockMvc
        .perform(MockMvcRequestBuilders.post("/owners/*/pets/{petId}/visits/new", 123456789).param("description",
            "The characteristics of someone or something"));

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isFound());
    resultActions.andExpect(MockMvcResultMatchers.model().size(0));
  }
  @Test
  public void testProcessNewVisitForm2() throws Exception {
    // Arrange
    LocalDate birthDate = LocalDate.ofEpochDay(1L);
    Pet pet = new Pet();
    pet.setBirthDate(birthDate);
    Owner owner = new Owner();
    owner.setLastName("Doe");
    owner.setId(1);
    owner.setCity("Oxford");
    owner.setPetsInternal(new HashSet<Pet>());
    owner.setAddress("42 Main St");
    owner.setFirstName("Jane");
    owner.setTelephone("4105551212");
    pet.setOwner(owner);
    pet.setVisitsInternal(new ArrayList<Visit>());
    pet.setId(1);
    pet.setName("Bella");
    PetType petType = new PetType();
    petType.setId(1);
    petType.setName("Dog");
    pet.setType(petType);
    when(this.petRepository.findById(or(isA(Integer.class), isNull()))).thenReturn(pet);
    when(this.visitRepository.findByPetId(or(isA(Integer.class), isNull()))).thenReturn(new ArrayList<Visit>());

    // Act
    ResultActions actualPerformResult = this.mockMvc
        .perform(MockMvcRequestBuilders.post("/owners/*/pets/{petId}/visits/new", 123456789).param("description", ""));

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    ResultActions resultActions1 = resultActions.andExpect(MockMvcResultMatchers.model().size(2));
    resultActions1.andExpect(MockMvcResultMatchers.model().attributeExists("pet", "visit"));
  }
}

