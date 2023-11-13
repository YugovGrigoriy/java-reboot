package ru.sberbank.edu;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.sberbank.edu.model.Car;
import ru.sberbank.edu.repository.CarDbRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

 public class CarDbRepositoryImplTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;

    private CarDbRepositoryImpl carDbRepositoryImpl;

    @Before
    public void setup() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        carDbRepositoryImpl = new CarDbRepositoryImpl(connection);
    }

    @Test
    public void testCreateAll() throws SQLException {
        Set<Car> cars = new HashSet<>();
        cars.add(new Car("1", "Model1"));
        cars.add(new Car("2", "Model2"));

        Set<Car> result = carDbRepositoryImpl.createAll(cars);

        verify(preparedStatement, times(cars.size() * 3)).setString(anyInt(), anyString());
        verify(preparedStatement, times(cars.size())).executeUpdate();

        assertEquals(cars, result);
    }

    @Test
    public void testFindAll() throws SQLException {
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getString("id")).thenReturn("1", "2");
        when(resultSet.getString("model")).thenReturn("Model1", "Model2");

        Set<Car> result = carDbRepositoryImpl.findAll();

        verify(preparedStatement, times(1)).executeQuery();
        assertEquals(2, result.size());
    }

    @Test
    public void testDeleteById() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);

        Boolean result = carDbRepositoryImpl.deleteById("1");

        verify(preparedStatement, times(1)).setString(1, "1");
        verify(preparedStatement, times(1)).executeUpdate();
        assertTrue(result);
    }

    @Test
    public void testDeleteAll() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);

        Boolean result = carDbRepositoryImpl.deleteAll();

        verify(preparedStatement, times(1)).executeUpdate();
        assertTrue(result);
    }

    @Test
    public void testFindByModel() throws SQLException {
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getString("id")).thenReturn("1", "2");
        when(resultSet.getString("model")).thenReturn("Model1", "Model1");

        Set<Car> result = carDbRepositoryImpl.findByModel("Model1");

        verify(preparedStatement, times(1)).setString(1, "Model1");
        verify(preparedStatement, times(1)).executeQuery();
        assertEquals(2, result.size());
    }
}