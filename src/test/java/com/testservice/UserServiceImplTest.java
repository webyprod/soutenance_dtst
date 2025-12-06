package com.testservice;

import com.model.User;
import com.repository.UserRepository;
import com.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setup() {
        user = new User();
        user.setUsername("john");
        user.setPassword("pass");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setAge(30);
        user.setCountry("France");
    }

    @Test
    void testFindAll() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> result = userService.findAll();

        assertEquals(1, result.size());
        assertEquals("john", result.get(0).getUsername());
    }

    @Test
    void testFindById_UserExists() {
        User userWithId = cloneWithId(user, 1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(userWithId));

        Optional<User> result = userService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("john", result.get().getUsername());
    }

    @Test
    void testFindById_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<User> result = userService.findById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testAddUser() {
        userService.add(user);

        verify(userRepository).save(user);
    }

    @Test
    void testUpdate_UserNotFound() {
        User updatePayload = cloneWithId(user, 1L);
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<User> result = userService.update(1L, updatePayload);

        assertFalse(result.isPresent());
    }

    @Test
    void testDelete_UserExists() {
        User userWithId = cloneWithId(user, 1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(userWithId));

        Optional<User> result = userService.delete(1L);

        assertTrue(result.isPresent());
        verify(userRepository).delete(userWithId);
    }

    @Test
    void testDelete_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<User> result = userService.delete(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testFindByCriteria_Username() {
        when(userRepository.findByUsername("john")).thenReturn(List.of(user));

        List<User> result = userService.findByCriteria("username", "john");

        assertEquals(1, result.size());
    }

    @Test
    void testFindByCriteria_FirstName() {
        when(userRepository.findByFirstName("John")).thenReturn(List.of(user));

        List<User> result = userService.findByCriteria("firstName", "John");

        assertEquals(1, result.size());
    }

    @Test
    void testFindByCriteria_LastName() {
        when(userRepository.findByLastName("Doe")).thenReturn(List.of(user));

        List<User> result = userService.findByCriteria("lastName", "Doe");

        assertEquals(1, result.size());
    }

    @Test
    void testFindByCriteria_AgeValid() {
        when(userRepository.findByAge(30)).thenReturn(List.of(user));

        List<User> result = userService.findByCriteria("age", "30");

        assertEquals(1, result.size());
    }

    @Test
    void testFindByCriteria_AgeInvalid() {
        List<User> result = userService.findByCriteria("age", "invalid");

        assertTrue(result.isEmpty());
    }

    @Test
    void testFindByCriteria_Country() {
        when(userRepository.findByCountry("France")).thenReturn(List.of(user));

        List<User> result = userService.findByCriteria("country", "France");

        assertEquals(1, result.size());
    }

    @Test
    void testFindByCriteria_UnknownCriteria() {
        List<User> result = userService.findByCriteria("unknown", "value");

        assertTrue(result.isEmpty());
    }

    // 🔧 Méthode utilitaire pour cloner un User avec un ID simulé
    private User cloneWithId(User source, Long id) {
        User clone = new User();
        clone.setUsername(source.getUsername());
        clone.setPassword(source.getPassword());
        clone.setFirstName(source.getFirstName());
        clone.setLastName(source.getLastName());
        clone.setAge(source.getAge());
        clone.setCountry(source.getCountry());

        // Simuler l'ID via réflexion (si nécessaire)
        try {
            Field idField = User.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(clone, id);
        } catch (Exception e) {
            throw new RuntimeException("Impossible de setter l'ID en test", e);
        }

        return clone;
    }
}
