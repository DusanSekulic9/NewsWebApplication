package rs.raf.project.repositories.Vest;

import com.google.gson.Gson;
import rs.raf.project.entities.PoseteComparator;
import rs.raf.project.entities.Tag;
import rs.raf.project.entities.Vest;
import rs.raf.project.entities.Vest_Tag;
import rs.raf.project.repositories.Komentar.KomentarRepository;
import rs.raf.project.repositories.MySqlAbstractRepository;
import rs.raf.project.repositories.Tag.TagRepository;
import rs.raf.project.repositories.vest_tag.Vest_TagRepository;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlVestRepository extends MySqlAbstractRepository implements VestRepository {

    @Inject
    private KomentarRepository komentarRepository;

    @Inject
    private Vest_TagRepository vest_tagRepository;

    @Inject
    private TagRepository tagRepository;

    Gson gson = new Gson();


    //TO-DO Cookie

    @Override
    public Vest addVest(Vest vest) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id", "datum"};

            preparedStatement = connection.prepareStatement("INSERT INTO vesti (naslov, tekst, brojPoseta, kreatorId, kategorijaId) VALUES(?, ?,?,?,?)", generatedColumns);
            preparedStatement.setString(1, vest.getNaslov());
            preparedStatement.setString(2, vest.getTekst());
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, vest.getKreatorId());
            preparedStatement.setInt(5, vest.getKategorijaId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                vest.setId(resultSet.getInt(1));
                vest.setPosete(0);
            }

            String[] tags = vest.getTagovi().split(" ");
            for(String tag : tags){
                try {
                    tagRepository.addTag(new Tag(tag.trim()));
                }catch (Exception e){
                    e.printStackTrace();
                }
                vest_tagRepository.addVest_Tag(vest.getId(), tagRepository.getTagId(tag));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            //this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vest;
    }

    @Override
    public Vest updateVest(Vest vest) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            System.out.println(vest.getKategorijaId());


            preparedStatement = connection.prepareStatement("UPDATE vesti set naslov = ?, tekst = ?, kreatorId = ?, kategorijaId = ? where id = ?");
            preparedStatement.setString(1, vest.getNaslov());
            preparedStatement.setString(2, vest.getTekst());
            preparedStatement.setInt(3, vest.getKreatorId());
            preparedStatement.setInt(4, vest.getKategorijaId());
            preparedStatement.setInt(5, vest.getId());
            preparedStatement.executeUpdate();

            String[] tags = vest.getTagovi().split(" ");
            for(String tag : tags){
                try {
                    tagRepository.addTag(new Tag(tag.trim()));
                }catch (Exception e){
                    e.printStackTrace();
                }
                vest_tagRepository.addVest_Tag(vest.getId(), tagRepository.getTagId(tag));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            //this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vest;
    }


    @Override
    public void deleteVest(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM vesti where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public List<Vest> RecentVesti(Integer brStrane) {
        List<Vest> vesti = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = this.newConnection();


            preparedStatement = connection.prepareStatement("SELECT * FROM vesti ORDER BY datum DESC limit ?, 10; ");
            if(brStrane == 1){
                preparedStatement.setInt(1, 0);

            }else{
                preparedStatement.setInt(1, brStrane*10 - 10);
            }
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Vest vest = new Vest();
                String naslov = resultSet.getString("naslov");
                String tekst = resultSet.getString("tekst");
                Date datum = resultSet.getDate("datum");
                Integer kreatorId = resultSet.getInt("kreatorId");
                Integer kategorijaId = resultSet.getInt("kategorijaId");
                Integer brojPoseta = resultSet.getInt("brojPoseta");
                Integer id = resultSet.getInt("id");
                vest.setDatum(datum);
                vest.setId(id);
                vest.setKategorijaId(kategorijaId);
                vest.setKreatorId(kreatorId);
                vest.setNaslov(naslov);
                vest.setPosete(brojPoseta);
                vest.setTekst(tekst);
                vest.setKomentari(komentarRepository.allCommentsForParentId(id));
                vest.setTagoviList(vest_tagRepository.getTagsByVestId(id));
                vesti.add(vest);
            }


        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return vesti;
    }

    @Override
    public List<Vest> PopularVesti(Integer brStrane) {
        List<Vest> vesti = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = this.newConnection();


            preparedStatement = connection.prepareStatement("SELECT * FROM vesti WHERE datum BETWEEN DATE_SUB(NOW(), INTERVAL 30 DAY) AND NOW() ORDER BY brojPoseta desc limit ?,10");
            if(brStrane == 1){
                preparedStatement.setInt(1, 0);

            }else{
                preparedStatement.setInt(1, brStrane*10 - 10);
            }
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Vest vest = new Vest();
                String naslov = resultSet.getString("naslov");
                String tekst = resultSet.getString("tekst");
                Date datum = resultSet.getDate("datum");
                Integer kreatorId = resultSet.getInt("kreatorId");
                Integer kategorijaId = resultSet.getInt("kategorijaId");
                Integer brojPoseta = resultSet.getInt("brojPoseta");
                Integer id = resultSet.getInt("id");
                vest.setDatum(datum);
                vest.setId(id);
                vest.setKategorijaId(kategorijaId);
                vest.setKreatorId(kreatorId);
                vest.setNaslov(naslov);
                vest.setPosete(brojPoseta);
                vest.setTekst(tekst);
                vest.setKomentari(komentarRepository.allCommentsForParentId(id));
                vest.setTagoviList(vest_tagRepository.getTagsByVestId(id));
                vesti.add(vest);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return vesti;
    }

    @Override
    public List<Vest> VestIzKategorije(Integer idKategorije, Integer brStrane) {
        List<Vest> vesti = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = this.newConnection();


            preparedStatement = connection.prepareStatement("SELECT * FROM vesti where kategorijaId = ? limit ?, 10");
            preparedStatement.setInt(1, idKategorije);
            if(brStrane == 1){
                preparedStatement.setInt(2, 0);

            }else{
                preparedStatement.setInt(2, brStrane*10 - 10);
            }
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Vest vest = new Vest();
                String naslov = resultSet.getString("naslov");
                String tekst = resultSet.getString("tekst");
                Date datum = resultSet.getDate("datum");
                Integer kreatorId = resultSet.getInt("kreatorId");
                Integer kategorijaId = resultSet.getInt("kategorijaId");
                Integer brojPoseta = resultSet.getInt("brojPoseta");
                Integer id = resultSet.getInt("id");
                vest.setDatum(datum);
                vest.setId(id);
                vest.setKategorijaId(kategorijaId);
                vest.setKreatorId(kreatorId);
                vest.setNaslov(naslov);
                vest.setPosete(brojPoseta);
                vest.setTekst(tekst);
                vest.setKomentari(komentarRepository.allCommentsForParentId(id));
                vest.setTagoviList(vest_tagRepository.getTagsByVestId(id));
                vesti.add(vest);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return vesti;
    }




    @Override
    public List<Vest> all() {
        return null;
    }

    @Override
    public Vest getVest(Integer id) {
        Vest vest = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = this.newConnection();


            preparedStatement = connection.prepareStatement("SELECT * FROM vesti where id = ?");
            preparedStatement.setInt(1, id);
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
                vest.setKomentari(komentarRepository.allCommentsForParentId(id));
                vest.setTagoviList(vest_tagRepository.getTagsByVestId(id));
            }

            preparedStatement = connection.prepareStatement("UPDATE vesti set brojPoseta = ? where id = ?");
            preparedStatement.setInt(1,vest.getPosete() + 1);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return vest;
    }

    @Override
    public int getPagginationForPopularNews() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT count(id) as count FROM vesti WHERE datum BETWEEN DATE_SUB(NOW(), INTERVAL 30 DAY) AND NOW()");

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

    @Override
    public int getPagginationForAllNews() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT count(id) as count FROM vesti");

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

    @Override
    public int getPagginationForSearchedNews(String parameter) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = this.newConnection();

            parameter = gson.fromJson(parameter, String.class);
            parameter = parameter.trim().replace(" ", "");
            preparedStatement = connection.prepareStatement("SELECT count(id) as count FROM vesti WHERE naslov like %?% OR tekst like %?%");
            preparedStatement.setString(1, "%" + parameter.split(":")[1].replace("\"", "").split("\\n")[0] + "%");
            preparedStatement.setString(2, "%" + parameter.split(":")[1].replace("\"", "").split("\\n")[0] + "%");

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            //this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return count/10 + 1;
    }


    @Override
    public List<Vest> getSearchedVesti(String param, Integer brStrane) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Vest> vesti = new ArrayList<>();
        try {
            connection = this.newConnection();

            System.out.println(param);

            param = param.trim().replace(" ", "");

            param = param.replace("}", "");
            param = param.replace("{", "");

            param = param.trim();

            System.out.println(param);

            String s = "SELECT * FROM vesti WHERE naslov like \'%" + param.split(":")[1].replace("\"", "").split("\\n")[0] + "%\' OR tekst like \'%" + param.split(":")[1].replace("\"", "").split("\\n")[0] +"%\'";

            System.out.println(s);

            preparedStatement = connection.prepareStatement("SELECT * FROM vesti WHERE naslov like ? OR tekst like ?");
            preparedStatement.setString(1, "%" + param.split(":")[1].replace("\"", "").split("\\n")[0] + "%");
            preparedStatement.setString(2, "\'%" + param.split(":")[1].replace("\"", "").split("\\n")[0] + "%\'");

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Vest vest = new Vest();
                String naslov = resultSet.getString("naslov");
                String tekst = resultSet.getString("tekst");
                Date datum = resultSet.getDate("datum");
                Integer kreatorId = resultSet.getInt("kreatorId");
                Integer kategorijaId = resultSet.getInt("kategorijaId");
                Integer brojPoseta = resultSet.getInt("brojPoseta");
                Integer id = resultSet.getInt("id");
                vest.setDatum(datum);
                vest.setId(id);
                vest.setKategorijaId(kategorijaId);
                vest.setKreatorId(kreatorId);
                vest.setNaslov(naslov);
                vest.setPosete(brojPoseta);
                vest.setTekst(tekst);
                vest.setKomentari(komentarRepository.allCommentsForParentId(id));
                vest.setTagoviList(vest_tagRepository.getTagsByVestId(id));
                vesti.add(vest);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            //this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vesti;
    }
}
