package org.springframework.samples.petclinic.owner;

import static org.mockito.AdditionalMatchers.or;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.isNull;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.ArrayList;
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

@WebMvcTest(controllers = {OwnerController.class})
@ExtendWith(SpringExtension.class)
public class OwnerControllerDiffblueTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private OwnerRepository ownerRepository;
  @MockBean
  private VisitRepository visitRepository;
  @Test
  public void testInitCreationForm() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners/new");

    // Act
    ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    ResultActions resultActions1 = resultActions.andExpect(MockMvcResultMatchers.model().size(1));
    resultActions1.andExpect(MockMvcResultMatchers.model().attributeExists("owner"));
  }
  @Test
  public void testInitCreationForm2() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners/new", "uriVars");

    // Act
    ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    ResultActions resultActions1 = resultActions.andExpect(MockMvcResultMatchers.model().size(1));
    resultActions1.andExpect(MockMvcResultMatchers.model().attributeExists("owner"));
  }
  @Test
  public void testInitFindForm() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners/find");

    // Act
    ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    ResultActions resultActions1 = resultActions.andExpect(MockMvcResultMatchers.model().size(1));
    resultActions1.andExpect(MockMvcResultMatchers.model().attributeExists("owner"));
  }
  @Test
  public void testInitUpdateOwnerForm() throws Exception {
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners/{ownerId}/edit", 123456789);

    // Act
    ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    ResultActions resultActions1 = resultActions.andExpect(MockMvcResultMatchers.model().size(1));
    resultActions1.andExpect(MockMvcResultMatchers.model().attributeExists("owner"));
  }
  @Test
  public void testProcessCreationForm() throws Exception {
    // Arrange and Act
    ResultActions actualPerformResult = this.mockMvc
        .perform(MockMvcRequestBuilders.post("/owners/new").param("address", "42 Main St").param("city", "Oxford")
            .param("telephone", "4105551212").param("firstName", "Jane").param("lastName", "Doe"));

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isFound());
    resultActions.andExpect(MockMvcResultMatchers.model().size(0));
  }
  @Test
  public void testProcessCreationForm2() throws Exception {
    // Arrange and Act
    ResultActions actualPerformResult = this.mockMvc
        .perform(MockMvcRequestBuilders.post("/owners/new").param("address", "").param("city", "Oxford")
            .param("telephone", "4105551212").param("firstName", "Jane").param("lastName", "Doe"));

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    ResultActions resultActions1 = resultActions.andExpect(MockMvcResultMatchers.model().size(1));
    resultActions1.andExpect(MockMvcResultMatchers.model().attributeExists("owner"));
  }
  @Test
  public void testProcessFindForm() throws Exception {
    // Arrange
    when(this.ownerRepository.findByLastName(or(isA(String.class), isNull()))).thenReturn(new ArrayList<Owner>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners");

    // Act
    ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    ResultActions resultActions1 = resultActions.andExpect(MockMvcResultMatchers.model().size(1));
    resultActions1.andExpect(MockMvcResultMatchers.model().attributeExists("owner"));
  }
  @Test
  public void testProcessFindForm2() throws Exception {
    // Arrange
    Owner owner = new Owner();
    owner.setLastName("Doe");
    owner.setId(1);
    owner.setCity("Oxford");
    owner.setPetsInternal(new HashSet<Pet>());
    owner.setAddress("42 Main St");
    owner.setFirstName("Jane");
    owner.setTelephone("4105551212");
    ArrayList<Owner> ownerList = new ArrayList<Owner>();
    ownerList.add(owner);
    when(this.ownerRepository.findByLastName(or(isA(String.class), isNull()))).thenReturn(ownerList);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners");

    // Act
    ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isFound());
    resultActions.andExpect(MockMvcResultMatchers.model().size(0));
  }
  @Test
  public void testProcessFindForm3() throws Exception {
    // Arrange
    Owner owner = new Owner();
    owner.setLastName("Doe");
    owner.setId(1);
    owner.setCity("Oxford");
    owner.setPetsInternal(new HashSet<Pet>());
    owner.setAddress("42 Main St");
    owner.setFirstName("Jane");
    owner.setTelephone("4105551212");
    ArrayList<Owner> ownerList = new ArrayList<Owner>();
    ownerList.add(owner);
    Owner owner1 = new Owner();
    owner1.setLastName("Doe");
    owner1.setId(1);
    owner1.setCity("Oxford");
    owner1.setPetsInternal(new HashSet<Pet>());
    owner1.setAddress("42 Main St");
    owner1.setFirstName("Jane");
    owner1.setTelephone("4105551212");
    ownerList.add(owner1);
    when(this.ownerRepository.findByLastName(or(isA(String.class), isNull()))).thenReturn(ownerList);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners");

    // Act
    ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    ResultActions resultActions1 = resultActions.andExpect(MockMvcResultMatchers.model().size(2));
    resultActions1.andExpect(MockMvcResultMatchers.model().attributeExists("owner", "selections"));
  }
  @Test
  public void testProcessUpdateOwnerForm() throws Exception {
    // Arrange and Act
    ResultActions actualPerformResult = this.mockMvc.perform(MockMvcRequestBuilders
        .post("/owners/{ownerId}/edit", 123456789).param("address", "42 Main St").param("city", "Oxford")
        .param("telephone", "4105551212").param("firstName", "Jane").param("lastName", "Doe"));

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isFound());
    resultActions.andExpect(MockMvcResultMatchers.model().size(0));
  }
  @Test
  public void testProcessUpdateOwnerForm2() throws Exception {
    // Arrange and Act
    ResultActions actualPerformResult = this.mockMvc.perform(
        MockMvcRequestBuilders.post("/owners/{ownerId}/edit", 123456789).param("address", "").param("city", "Oxford")
            .param("telephone", "4105551212").param("firstName", "Jane").param("lastName", "Doe"));

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    ResultActions resultActions1 = resultActions.andExpect(MockMvcResultMatchers.model().size(1));
    resultActions1.andExpect(MockMvcResultMatchers.model().attributeExists("owner"));
  }
  @Test
  public void testShowOwner() throws Exception {
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
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners/{ownerId}", 123456789);

    // Act
    ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    ResultActions resultActions1 = resultActions.andExpect(MockMvcResultMatchers.model().size(1));
    resultActions1.andExpect(MockMvcResultMatchers.model().attributeExists("owner"));
  }
  @Test
  public void testShowOwner2() throws Exception {
    // Arrange
    Owner owner = new Owner();
    owner.setLastName("Doe");
    owner.setId(1);
    owner.setCity("Oxford");
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
    HashSet<Pet> petSet = new HashSet<Pet>();
    petSet.add(pet);
    owner.setPetsInternal(petSet);
    owner.setAddress("42 Main St");
    owner.setFirstName("Jane");
    owner.setTelephone("4105551212");
    when(this.ownerRepository.findById(or(isA(Integer.class), isNull()))).thenReturn(owner);
    when(this.visitRepository.findByPetId(or(isA(Integer.class), isNull()))).thenReturn(new ArrayList<Visit>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners/{ownerId}", 123456789);

    // Act
    ResultActions actualPerformResult = this.mockMvc.perform(requestBuilder);

    // Assert
    ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    ResultActions resultActions1 = resultActions.andExpect(MockMvcResultMatchers.model().size(1));
    resultActions1.andExpect(MockMvcResultMatchers.model().attributeExists("owner"));
  }
}

