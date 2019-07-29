package ru.maratk.dao;

import io.agroal.api.AgroalDataSource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.*;

@ApplicationScoped
public class AccumulatorDao {

    @Inject
    AgroalDataSource defaultDataSource;

    public Integer findCurrent() throws SQLException {
        Connection connection = null; Statement statement = null; ResultSet resultSet = null;
        try {
            connection = defaultDataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT current_value" +
                    " FROM accumulator" +
                    " WHERE id = 1;");
            if(resultSet.next()){
                return resultSet.getInt(1);
            } else {
                throw new SQLException("Can't find accumulator table row with ID = 1");
            }
        } catch (final SQLException e) {
            throw e;
        } finally {
            if(resultSet != null) {try {resultSet.close();} catch (final SQLException e){}}
            if(statement != null) {try {statement.close();} catch (final SQLException e){}}
            if(connection != null) {try {connection.close();} catch (final SQLException e){}}
        }
    }

    public void plus(final Integer plusValue) throws SQLException {
        Connection connection = null; PreparedStatement statement = null; ResultSet resultSet = null;
        try {
            connection = defaultDataSource.getConnection();
            statement = connection.prepareStatement("UPDATE accumulator" +
                    " SET current_value = current_value + ?" +
                    " WHERE id = 1;");
            statement.setInt(1, plusValue);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw e;
        } finally {
            if(resultSet != null) {try {resultSet.close();} catch (final SQLException e){}}
            if(statement != null) {try {statement.close();} catch (final SQLException e){}}
            if(connection != null) {try {connection.close();} catch (final SQLException e){}}
        }
    }

    public void minus(final Integer minusValue) throws SQLException {
        Connection connection = null; PreparedStatement statement = null; ResultSet resultSet = null;
        try {
            connection = defaultDataSource.getConnection();
            statement = connection.prepareStatement("UPDATE accumulator" +
                    " SET current_value = current_value - ?" +
                    " WHERE id = 1;");
            statement.setInt(1, minusValue);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw e;
        } finally {
            if(resultSet != null) {try {resultSet.close();} catch (final SQLException e){}}
            if(statement != null) {try {statement.close();} catch (final SQLException e){}}
            if(connection != null) {try {connection.close();} catch (final SQLException e){}}
        }
    }
}