package net.thumbtack.school.database.jdbc;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.*;

public class JdbcService {

    public static void insertTrainee(Trainee trainee) throws SQLException {
        String insertQuery = "INSERT INTO trainee (id, firstName, lastName, rating, group_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setNull(1, INTEGER);
            preparedStatement.setString(2, trainee.getFirstName());
            preparedStatement.setString(3, trainee.getLastName());
            preparedStatement.setInt(4, trainee.getRating());
            preparedStatement.setNull(5, INTEGER);
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    trainee.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public static void updateTrainee(Trainee trainee) throws SQLException {
        String updateQuery = "UPDATE trainee SET firstName = ? , lastName = ?, rating = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(updateQuery)) {
            preparedStatement.setString(1, trainee.getFirstName());
            preparedStatement.setString(2, trainee.getLastName());
            preparedStatement.setInt(3, trainee.getRating());
            preparedStatement.setInt(4, trainee.getId());
            preparedStatement.executeUpdate();
        }
    }

    public static Trainee getTraineeByIdUsingColNames(int traineeId) throws SQLException {
        String selectQuery = "SELECT id, firstName, lastName, rating FROM trainee WHERE id = ?";
        Trainee trainee = null;
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, traineeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                trainee = new Trainee(
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getInt("rating"));
            }
        }
        return trainee;
    }

    public static Trainee getTraineeByIdUsingColNumbers(int traineeId) throws SQLException {
        String selectQuery = "SELECT id, firstName, lastName, rating FROM trainee WHERE id = ?";
        Trainee trainee = null;
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, traineeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                trainee = new Trainee(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4));
            }
        }
        return trainee;
    }

    public static List<Trainee> getTraineesUsingColNames() throws SQLException {
        String getListQuery = "SELECT id, firstName, lastName, rating FROM trainee";
        Trainee trainee;
        List<Trainee> traineeList = new ArrayList<>();
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(getListQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                trainee = new Trainee(
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getInt("rating"));
                traineeList.add(trainee);
            }
        }
        return traineeList;
    }

    public static List<Trainee> getTraineesUsingColNumbers() throws SQLException {
        String getListQuery = "SELECT id, firstName, lastName, rating FROM trainee";
        Trainee trainee;
        List<Trainee> traineeList = new ArrayList<>();
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(getListQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                trainee = new Trainee(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4));
                traineeList.add(trainee);
            }
        }
        return traineeList;
    }

    public static void deleteTrainee(Trainee trainee) throws SQLException {
        String deleteQuery = "DELETE FROM trainee WHERE id = ?";
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, trainee.getId());
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteTrainees() throws SQLException {
        String deleteQuery = "DELETE FROM trainee";
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(deleteQuery)) {
            preparedStatement.executeUpdate();
        }
    }

    public static void insertSubject(Subject subject) throws SQLException {
        String insertQuery = "INSERT INTO subject (id, name) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setNull(1, INTEGER);
            preparedStatement.setString(2, subject.getName());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    subject.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public static Subject getSubjectByIdUsingColNames(int subjectId) throws SQLException {
        String selectQuery = "SELECT id, name FROM subject WHERE id = ?";
        Subject subject = null;
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, subjectId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subject = new Subject(
                        resultSet.getInt("id"),
                        resultSet.getString("name"));
            }
        }
        return subject;
    }

    public static Subject getSubjectByIdUsingColNumbers(int subjectId) throws SQLException {
        String selectQuery = "SELECT id, name FROM subject WHERE id = ?";
        Subject subject = null;
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, subjectId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subject = new Subject(
                        resultSet.getInt(1),
                        resultSet.getString(2));
            }
        }
        return subject;
    }

    public static void deleteSubjects() throws SQLException {
        String deleteQuery = "DELETE FROM subject";
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(deleteQuery)) {
            preparedStatement.executeUpdate();
        }
    }

    public static void insertSchool(School school) throws SQLException {
        String insertQuery = "INSERT INTO school (id, name, year) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setNull(1, INTEGER);
            preparedStatement.setString(2, school.getName());
            preparedStatement.setInt(3, school.getYear());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    school.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public static School getSchoolByIdUsingColNames(int schoolId) throws SQLException {
        String selectQuery = "SELECT id, name, year FROM school WHERE id = ?";
        School school = null;
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, schoolId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                school = new School(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("year"));
            }
        }
        return school;
    }

    public static School getSchoolByIdUsingColNumbers(int schoolId) throws SQLException {
        String selectQuery = "SELECT id, name, year FROM school WHERE id = ?";
        School school = null;
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, schoolId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                school = new School(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3));
            }
        }
        return school;
    }

    public static void deleteSchools() throws SQLException {
        String deleteQuery = "DELETE FROM school";
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(deleteQuery)) {
            preparedStatement.executeUpdate();
        }
    }

    public static void insertGroup(School school, Group group) throws SQLException {
        String insertQuery = "INSERT INTO groups (id, name, room, school_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setNull(1, INTEGER);
            preparedStatement.setString(2, group.getName());
            preparedStatement.setString(3, group.getRoom());
            preparedStatement.setInt(4, school.getId());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    group.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public static School getSchoolByIdWithGroups(int id) throws SQLException {
        String selectQuery = "SELECT school.id, school.name, school.year, groups.id, groups.name, groups.room "
                + "FROM school LEFT JOIN groups ON school_id = school.id WHERE school.id = ?";
        School school = null;
        Group group;
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (school == null) {
                    school = new School(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getInt(3));
                }
                group = new Group(
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6));
                school.addGroup(group);
            }
        }
        return school;
    }

    public static List<School> getSchoolsWithGroups() throws SQLException {
        String selectQuery = "SELECT school.id, school.name, school.year, groups.id, groups.name, groups.room "
                + "FROM school LEFT JOIN groups ON school_id = school.id";
        List<School> schools = new ArrayList<>();
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            School school = null;
            Group group;
            while (resultSet.next()) {
                if (school == null || resultSet.getInt(1) != school.getId()) {
                    school = new School(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getInt(3));
                    schools.add(school);
                }
                group = new Group(
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6));
                school.addGroup(group);
            }
        }
        return schools;
    }
}
