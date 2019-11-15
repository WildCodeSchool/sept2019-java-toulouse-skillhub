package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/skillhub?serverTimezone=GMT";
    private final static String DB_USER = "skillhub";
    private final static String DB_PASSWORD = "5ki!!huB31";

    private static Connection connection = null;
    private static void setConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Question> findAllOwn(Long userId) {

        try {
            setConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM question\n" +
                            "JOIN user ON question.id_user = user.id_user\n" +
                            "JOIN picture ON user.id_picture = picture.id_picture\n" +
                            "JOIN question_skill ON question.id_question = question_skill.id_question\n" +
                            "JOIN skill ON question_skill.id_skill = skill.id_skill\n" +
                            "WHERE question.resolved = false AND question.id_user = ?;"
            );
            statement.setLong(1, userId);
            List<Question> questions = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Long questionId = resultSet.getLong("question.id_question");
                String title = resultSet.getString("title");
                String body = resultSet.getString("body");
                Date date = resultSet.getDate("date");
                boolean resolved = resultSet.getBoolean("resolved");
                String author = resultSet.getString("nickname");
                String authorAvatarUrl = resultSet.getString("url");
                String skill = resultSet.getString("name");
                PreparedStatement statement2 = connection.prepareStatement(
                        "SELECT COUNT(id_answer) AS nbAnswers FROM answer WHERE id_question = ?;"
                );
                statement2.setLong(1, questionId);
                ResultSet resultSet2 = statement2.executeQuery();
                resultSet2.next();
                Long nbAnswers = resultSet2.getLong("nbAnswers");
                Question question = new Question(userId, questionId, title, body, date, resolved, author, authorAvatarUrl, skill);
                question.setNbAnswers(nbAnswers);
                questions.add(question);
            }
                return questions;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Question findQuestion(Long questionId) {

        try {
            setConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM question\n" +
                            "JOIN user ON question.id_user = user.id_user\n" +
                            "JOIN picture ON user.id_picture = picture.id_picture\n" +
                            "JOIN question_skill ON question.id_question = question_skill.id_question\n" +
                            "JOIN skill ON question_skill.id_skill = skill.id_skill\n" +
                            "WHERE question.resolved = false AND question.id_question = ?;"
            );
            statement.setLong(1, questionId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Long userId = resultSet.getLong("id_user");
                String title = resultSet.getString("title");
                String body = resultSet.getString("body");
                Date date = resultSet.getDate("date");
                boolean resolved = resultSet.getBoolean("resolved");
                String author = resultSet.getString("nickname");
                String authorAvatarUrl = resultSet.getString("url");
                String skill = resultSet.getString("name");
                return new Question(userId, questionId, title, body, date, resolved, author, authorAvatarUrl, skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Question> findAllOther(List<Long> skillsId, Long userId) {

        List<Question> questions = new ArrayList<>();

        try {
            for (Long skillId : skillsId) {
                setConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM question\n" +
                                "JOIN user ON question.id_user = user.id_user\n" +
                                "JOIN picture ON user.id_picture = picture.id_picture\n" +
                                "JOIN question_skill ON question.id_question = question_skill.id_question\n" +
                                "JOIN skill ON question_skill.id_skill = skill.id_skill\n" +
                                "WHERE question.resolved = false AND question_skill.id_skill = ? AND question.id_user != ?;"
                );
                statement.setLong(1, skillId);
                statement.setLong(2, userId);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Long questionId = resultSet.getLong("question.id_question");
                    String title = resultSet.getString("title");
                    String body = resultSet.getString("body");
                    Date date = resultSet.getDate("date");
                    boolean resolved = resultSet.getBoolean("resolved");
                    String author = resultSet.getString("nickname");
                    String authorAvatarUrl = resultSet.getString("url");
                    String skill = resultSet.getString("name");
                    PreparedStatement statement2 = connection.prepareStatement(
                            "SELECT COUNT(id_answer) AS nbAnswers FROM answer WHERE id_question = ?;"
                    );
                    statement2.setLong(1, questionId);
                    ResultSet resultSet2 = statement2.executeQuery();
                    resultSet2.next();
                    Long nbAnswers = resultSet2.getLong("nbAnswers");
                    Question question = new Question(userId, questionId, title, body, date, resolved, author, authorAvatarUrl, skill);
                    question.setNbAnswers(nbAnswers);
                    questions.add(question);
                }
            }
            return questions;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Question askQuestion(String title, String body, Date date, boolean resolved, Long userId) {

        try {
            setConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO question (title, body, `date`, resolved, id_user)\n" +
                            "VALUES (?,?,?,?,?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, title);
            statement.setString(2, body);
            statement.setDate(3, date);
            statement.setBoolean(4, resolved);
            statement.setLong(5, userId);

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to insert data");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                Long id = generatedKeys.getLong(1);
                return new Question(userId, id, title, body, date,
                        resolved);
            } else {
                throw new SQLException("failed to get inserted id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addSkillToQuestion (Long idQuestion, Long idSkill) {
        try {
            setConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO question_skill (id_question, id_skill)\n" +
                            "VALUES (?,?);"
            );
            statement.setLong(1, idQuestion);
            statement.setLong(2, idSkill);

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to insert data");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}