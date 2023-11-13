package ru.sberbank.edu.repository;


import ru.sberbank.edu.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class CarDbRepositoryImpl implements CarRepository {
    private final Connection connection;
    private static final String CREATE_CAR_SQL = "INSERT INTO car (id, model) VALUES (?,?)";
    private static final String UPDATE_CAR_SQL = "UPDATE car SET model = ? WHERE id = ?";
    private static final String SELECT_CAR_BY_ID = "SELECT * FROM car WHERE id = ?";

    private final PreparedStatement createPreStmt;
    private final PreparedStatement updatePreStmt;
    private final PreparedStatement findByIdPreStmt;


    public CarDbRepositoryImpl(Connection connection) throws SQLException {
        this.connection = connection;
        this.createPreStmt = connection.prepareStatement(CREATE_CAR_SQL);
        this.updatePreStmt = connection.prepareStatement(UPDATE_CAR_SQL);
        this.findByIdPreStmt = connection.prepareStatement(SELECT_CAR_BY_ID);
    }

    @Override
    public Car createOrUpdate(Car car) throws SQLException {
        Optional<Car> optCar = findById(car.getId());
        if (optCar.isEmpty()) {
            createPreStmt.setString(1, car.getId());
            createPreStmt.setString(2, car.getModel());
            createPreStmt.executeUpdate();
        } else {
            updatePreStmt.setString(1, car.getModel());
            updatePreStmt.setString(2, car.getId());
            updatePreStmt.executeUpdate();
        }
        return car;
    }

    @Override
    public Set<Car> createAll(Collection<Car> cars) {
        return cars.stream()
                .map(car -> {
                    try {
                        return createOrUpdate(car);
                    } catch (SQLException ex) {
                        throw new RuntimeException("error(createAll)", ex);
                    }
                })
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Car> findAll() {
        try (PreparedStatement statement = connection.prepareStatement("select * from car")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                Set<Car> carSet = new HashSet<>();
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String model = resultSet.getString("model");
                    Car car = new Car(id, model);
                    carSet.add(car);
                }
                return carSet;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("error(findAll)", ex);
        }
    }

    @Override
    public Optional<Car> findById(String id) throws SQLException {
        // validation
        int rowsCount = countRowsById(id);
        if (rowsCount > 1) {
            throw new RuntimeException("Car with id = " + id + " was found many times (" + rowsCount + ").");
        } else if (rowsCount == 0) {
            return Optional.empty();
        }

        findByIdPreStmt.setString(1, id);
        ResultSet resultSet = findByIdPreStmt.executeQuery();

        resultSet.next();
        Car car = new Car(resultSet.getString(1), resultSet.getString(2));
        return Optional.of(car);
    }

    @Override
    public Boolean deleteById(String id) {
        try (PreparedStatement statement = connection.prepareStatement("delete from car where id=?")) {
            statement.setString(1, id);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("error(deleteById)", ex);
        }
    }

    @Override
    public Boolean deleteAll() {
        try (PreparedStatement statement = connection.prepareStatement("drop table car")) {
            int affectedRouse = statement.executeUpdate();
            return affectedRouse > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("error(deleteAll)", ex);
        }
    }

    private int countRowsById(String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM car where id = ?");
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        int rowCount = 0;
        while (resultSet.next()) {
            rowCount = resultSet.getInt(1);
        }
        return rowCount;
    }

    @Override
    public Set<Car> findByModel(String model) {
        try (PreparedStatement statement = connection.prepareStatement("select * from car where model=?")) {
            statement.setString(1, model);
            try (ResultSet resultSet = statement.executeQuery()) {
                Set<Car> carSet = new HashSet<>();
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String findModel = resultSet.getString("model");
                    Car car = new Car(id, findModel);
                    carSet.add(car);
                }
                return carSet;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("error(findByModel)", ex);
        }
    }

}