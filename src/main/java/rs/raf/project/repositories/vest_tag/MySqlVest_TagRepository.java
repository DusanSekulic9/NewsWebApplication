package rs.raf.project.repositories.vest_tag;

import rs.raf.project.entities.Tag;
import rs.raf.project.entities.Vest;
import rs.raf.project.entities.Vest_Tag;
import rs.raf.project.repositories.Komentar.KomentarRepository;
import rs.raf.project.repositories.MySqlAbstractRepository;
import rs.raf.project.repositories.Tag.TagRepository;
import rs.raf.project.repositories.Vest.VestRepository;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlVest_TagRepository extends MySqlAbstractRepository implements Vest_TagRepository {

    @Inject
    private TagRepository tagRepository;

    @Inject
    private KomentarRepository komentarRepository;


    @Override
    public List<Vest> getVestiByTagId(Integer tagId, Integer page) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Vest> list = new ArrayList<>();
        try {
            connection = this.newConnection();


            preparedStatement = connection.prepareStatement("SELECT * FROM vest_tag where tagId = ? limit ?, 10");
            preparedStatement.setInt(1, tagId);
            if(page == 1){
                preparedStatement.setInt(2, 0);

            }else{
                preparedStatement.setInt(2, page*10 - 10);
            }
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Integer vestId = resultSet.getInt("vestId");
                list.add(getVest(vestId));
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            //this.closeResultSet(resultSet);
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
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Integer tagId = resultSet.getInt("tagId");
                list.add(tagRepository.getTagById(tagId));
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            //this.closeResultSet(resultSet);
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

            System.out.println("ubacio tag_vest");


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
            resultSet = preparedStatement.executeQuery();

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

    @Override
    public int paginationForTags(Integer tagId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT count(vestId) as count FROM vest_tag where tagId = ?");
            preparedStatement.setInt(1, tagId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return count/10 + 1;
    }

    public Vest getVest(Integer vestId){
        Vest vest = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = this.newConnection();


            preparedStatement = connection.prepareStatement("SELECT * FROM vesti where id = ?");
            preparedStatement.setInt(1, vestId);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                vest = new Vest();
                String naslov = resultSet.getString("naslov");
                String tekst = resultSet.getString("tekst");
                Date datum = resultSet.getDate("datum");
                Integer kreatorId = resultSet.getInt("kreatorId");
                Integer kategorijaId = resultSet.getInt("kategorijaId");
                Integer brojPoseta = resultSet.getInt("brojPoseta");
                Integer ids = resultSet.getInt("id");
                vest.setDatum(datum);
                vest.setId(ids);
                vest.setKategorijaId(kategorijaId);
                vest.setKreatorId(kreatorId);
                vest.setNaslov(naslov);
                vest.setPosete(brojPoseta);
                vest.setTekst(tekst);
                vest.setKomentari(komentarRepository.allCommentsForParentId(ids));
                vest.setTagoviList(this.getTagsByVestId(ids));
            }


        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return vest;
    }
}
