package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.AdditionalMatchers.or;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.isNull;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = {PetController.class})
@ExtendWith(SpringExtension.class)
public class PetControllerDiffblueTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private OwnerRepository ownerRepository;
  @Autowired
  private PetController petController;
  @MockBean
  private PetRepository petRepository;
  @Test
  public void testPopulatePetTypes() {
    // Arrange
    ArrayList<PetType> petTypeList = new ArrayList<PetType>();
    when(this.petRepository.findPetTypes()).thenReturn(petTypeList);

    // Act
    Collection<PetType> actualPopulatePetTypesResult = this.petController.populatePetTypes();

    // Assert
    assertSame(petTypeList, actualPopulatePetTypesResult);
    assertEquals(0, actualPopulatePetTypesResult.size());
  }
  @Test
  public void testFindOwner() {
    // Arrange
    Owner owner = new Owner();
    owner.setLastName("Doe");
    owner.setId(1);
    owner.setCity("Oxford");
    owner.setPetsInternal(new HashSet<Pet>());
    owner.setAddress("42 Main St");
    owner.setFirstName("Jane");
    owner.setTelephone("4105551212");
    when(this.ownerRepository.findById(or(isA(Integer.class), isNull()))).thenReturn(owner);

    // Act and Assert
    assertSame(owner, this.petController.findOwner(123));
  }
  @Test
  public void testInitCreationForm() throws Exception {
    // Arrange
    Owner owner = new Owner();
    owner.setLastName("Doe");
    owner.setId(1);
    owner.setCity("Oxford");
    owner.setPetsInternal(new HashSet<Pet>());
    owner.setAddress("42 Main St");
    owner.setFirstName("Jane");
    owner.setTelephone("4105551212");
    when(this.ownerRepository.findById(or(isA(Integer.class), isNull()))).thenReturn(owner);
    when(this.petRepository.findPetTypes()).thenReturn(new ArrayList<PetType>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners/{ownerId}/pets/new", 123456789);

    // Act
    ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    ResultActions resultActions1 = resultActions.andExpect(MockMvcResultMatchers.model().size(3));
    resultActions1.andExpect(MockMvcResultMatchers.model().attributeExists("owner", "pet", "types"));
  }
  @Test
  public void testInitUpdateForm() throws Exception {
    // Arrange
    Owner owner = new Owner();
    owner.setLastName("Doe");
    owner.setId(1);
    owner.setCity("Oxford");
    owner.setPetsInternal(new HashSet<Pet>());
    owner.setAddress("42 Main St");
    owner.setFirstName("Jane");
    owner.setTelephone("4105551212");
    when(this.ownerRepository.findById(or(isA(Integer.class), isNull()))).thenReturn(owner);
    LocalDate birthDate = LocalDate.ofEpochDay(1L);
    Pet pet = new Pet();
    pet.setBirthDate(birthDate);
    Owner owner1 = new Owner();
    owner1.setLastName("Doe");
    owner1.setId(1);
    owner1.setCity("Oxford");
    owner1.setPetsInternal(new HashSet<Pet>());
    owner1.setAddress("42 Main St");
    owner1.setFirstName("Jane");
    owner1.setTelephone("4105551212");
    pet.setOwner(owner1);
    pet.setVisitsInternal(new ArrayList<Visit>());
    pet.setId(1);
    pet.setName("Bella");
    PetType petType = new PetType();
    petType.setId(1);
    petType.setName("Dog");
    pet.setType(petType);
    when(this.petRepository.findById(or(isA(Integer.class), isNull()))).thenReturn(pet);
    when(this.petRepository.findPetTypes()).thenReturn(new ArrayList<PetType>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners/{ownerId}/pets/{petId}/edit",
        123456789, 123456789);

    // Act
    ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    ResultActions resultActions1 = resultActions.andExpect(MockMvcResultMatchers.model().size(3));
    resultActions1.andExpect(MockMvcResultMatchers.model().attributeExists("owner", "pet", "types"));
  }
  @Test
  public void testProcessCreationForm() throws Exception {
    // Arrange
    Owner owner = new Owner();
    owner.setLastName("Doe");
    owner.setId(1);
    owner.setCity("Oxford");
    owner.setPetsInternal(new HashSet<Pet>());
    owner.setAddress("42 Main St");
    owner.setFirstName("Jane");
    owner.setTelephone("4105551212");
    when(this.ownerRepository.findById(or(isA(Integer.class), isNull()))).thenReturn(owner);
    when(this.petRepository.findPetTypes()).thenReturn(new ArrayList<PetType>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/owners/{ownerId}/pets/new", 123456789);

    // Act
    ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    ResultActions resultActions1 = resultActions.andExpect(MockMvcResultMatchers.model().size(3));
    resultActions1.andExpect(MockMvcResultMatchers.model().attributeExists("owner", "pet", "types"));
  }
  @Test
  public void testProcessCreationForm2() throws Exception {
    // Arrange
    Owner owner = new Owner();
    owner.setLastName("Doe");
    owner.setId(1);
    owner.setCity("Oxford");
    owner.setPetsInternal(new HashSet<Pet>());
    owner.setAddress("42 Main St");
    owner.setFirstName("Jane");
    owner.setTelephone("4105551212");
    when(this.ownerRepository.findById(or(isA(Integer.class), isNull()))).thenReturn(owner);
    when(this.petRepository.findPetTypes()).thenReturn(new ArrayList<PetType>());

    // Act
    ResultActions actualPerformResult = this.mockMvc
        .perform(MockMvcRequestBuilders.post("/owners/{ownerId}/pets/new", 123456789).param("name", "Bella"));

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    ResultActions resultActions1 = resultActions.andExpect(MockMvcResultMatchers.model().size(3));
    resultActions1.andExpect(MockMvcResultMatchers.model().attributeExists("owner", "pet", "types"));
  }
  @Test
  public void testProcessCreationForm3() throws Exception {
    // Arrange
    Owner owner = new Owner();
    owner.setLastName("Doe");
    owner.setId(1);
    owner.setCity("Oxford");
    Pet pet = new Pet();
    pet.setBirthDate(null);
    Owner owner1 = new Owner();
    owner1.setLastName("Doe");
    owner1.setId(1);
    owner1.setCity("Oxford");
    owner1.setPetsInternal(new HashSet<Pet>());
    owner1.setAddress("42 Main St");
    owner1.setFirstName("Jane");
    owner1.setTelephone("4105551212");
    pet.setOwner(owner1);
    pet.setVisitsInternal(new ArrayList<Visit>());
    pet.setId(1);
    pet.setName("Bella");
    PetType petType = new PetType();
    petType.setId(1);
    petType.setName("Dog");
    pet.setType(petType);
    HashSet<Pet> petSet = new HashSet<Pet>();
    petSet.add(pet);
    owner.setPetsInternal(petSet);
    owner.setAddress("42 Main St");
    owner.setFirstName("Jane");
    owner.setTelephone("4105551212");
    when(this.ownerRepository.findById(or(isA(Integer.class), isNull()))).thenReturn(owner);
    when(this.petRepository.findPetTypes()).thenReturn(new ArrayList<PetType>());

    // Act
    ResultActions actualPerformResult = this.mockMvc
        .perform(MockMvcRequestBuilders.post("/owners/{ownerId}/pets/new", 123456789).param("name", "Bella"));

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    ResultActions resultActions1 = resultActions.andExpect(MockMvcResultMatchers.model().size(3));
    resultActions1.andExpect(MockMvcResultMatchers.model().attributeExists("owner", "pet", "types"));
  }
  @Test
  public void testProcessUpdateForm() throws Exception {
    // Arrange
    Owner owner = new Owner();
    owner.setLastName("Doe");
    owner.setId(1);
    owner.setCity("Oxford");
    owner.setPetsInternal(new HashSet<Pet>());
    owner.setAddress("42 Main St");
    owner.setFirstName("Jane");
    owner.setTelephone("4105551212");
    when(this.ownerRepository.findById(or(isA(Integer.class), isNull()))).thenReturn(owner);
    when(this.petRepository.findPetTypes()).thenReturn(new ArrayList<PetType>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/owners/{ownerId}/pets/{petId}/edit",
        123456789, 123456789);

    // Act
    ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    ResultActions resultActions1 = resultActions.andExpect(MockMvcResultMatchers.model().size(3));
    resultActions1.andExpect(MockMvcResultMatchers.model().attributeExists("owner", "pet", "types"));
  }
}

