package rs.raf.project.repositories.vest_tag;

import rs.raf.project.entities.Tag;
import rs.raf.project.entities.Vest;
import rs.raf.project.entities.Vest_Tag;
import rs.raf.project.repositories.MySqlAbstractRepository;
import rs.raf.project.repositories.Tag.TagRepository;
import rs.raf.project.repositories.Vest.VestRepository;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlVest_TagRepository extends MySqlAbstractRepository implements Vest_TagRepository {

    @Inject
    private TagRepository tagRepository;

    @Inject
    private VestRepository vestRepository;


    @Override
    public List<Vest> getVestiByTagId(Integer tagId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Vest> list = new ArrayList<>();
        try {
            connection = this.newConnection();


            preparedStatement = connection.prepareStatement("SELECT * FROM vest_tag where tagId = ?");
            preparedStatement.setInt(1, tagId);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            while(resultSet.next()){
                Integer vestId = resultSet.getInt("vestId");
                list.add(vestRepository.getVest(vestId));
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return list;
    }

    @Override
    public List<Tag> getTagsByVestId(Integer vestId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Tag> list = new ArrayList<>();
        try {
            connection = this.newConnection();


            preparedStatement = connection.prepareStatement("SELECT * FROM vest_tag where vestId = ?");
            preparedStatement.setInt(1, vestId);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            while(resultSet.next()){
                Integer tagId = resultSet.getInt("tagId");
                list.add(tagRepository.getTagById(tagId));
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return list;
    }

    @Override
    public void addVest_Tag(Integer vestId, Integer tagId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO vest_tag (vestId, tagId) VALUES(?, ?)", generatedColumns);
            preparedStatement.setInt(1, vestId);
            preparedStatement.setInt(2, tagId);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }

    @Override
    public List<Vest_Tag> all() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Vest_Tag> list = new ArrayList<>();
        try {
            connection = this.newConnection();


            preparedStatement = connection.prepareStatement("SELECT * FROM vest_tag");
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            while(resultSet.next()){
                Vest_Tag vest_tag = new Vest_Tag();
                Integer vestId = resultSet.getInt("vestId");
                Integer tagId = resultSet.getInt("tagId");
                Integer id = resultSet.getInt("id");
                vest_tag.setVestId(vestId);
                vest_tag.setTagId(tagId);
                vest_tag.setId(id);
                list.add(vest_tag);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return list;
    }
}
