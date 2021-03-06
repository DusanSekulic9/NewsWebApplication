package rs.raf.project.repositories.Kategorija;

import rs.raf.project.entities.Kategorija;
import rs.raf.project.repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlKategorijaRepository extends MySqlAbstractRepository implements KategorijaRepository {
    @Override
    public List<Kategorija> all(Integer page) {
        List<Kategorija> kategorije = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM kategorije limit ?, 10");
            if(page == 1){
                preparedStatement.setInt(1, 0);
            }else{
                preparedStatement.setInt(1, page * 10 - 10);
            }

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                kategorije.add(new Kategorija(resultSet.getInt("id"), resultSet.getString("ime"), resultSet.getString("opis")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return kategorije;
    }

    @Override
    public List<Kategorija> allC() {
        List<Kategorija> kategorije = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM kategorije");


            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                kategorije.add(new Kategorija(resultSet.getInt("id"), resultSet.getString("ime"), resultSet.getString("opis")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return kategorije;
    }

    @Override
    public int getPagginationLimitForAllCategories() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT count(id) as count FROM kategorije");

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
    public Kategorija addKategorija(Kategorija kategorija) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("SELECT count(id) as count FROM kategorije WHERE ime like ?", generatedColumns);
            preparedStatement.setString(1, kategorija.getIme());

            preparedStatement.executeQuery();
            resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }


            if(count > 0){
                return null;
            }


            preparedStatement = connection.prepareStatement("INSERT INTO kategorije (ime, opis) VALUES(?, ?)", generatedColumns);
            preparedStatement.setString(1, kategorija.getIme());
            preparedStatement.setString(2, kategorija.getOpis());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                System.out.println("usao da setuje id");
                kategorija.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            //this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return kategorija;
    }

    @Override
    public void deleteKategorija(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM vesti where kategorijaId = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            //resultSet = preparedStatement.getGeneratedKeys();

//            if(resultSet.next()){
//                return;
//            }

            preparedStatement = connection.prepareStatement("DELETE FROM kategorije where id = ?");
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
    public Kategorija updateKategorija(Kategorija kategorija, Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE kategorije set ime=?, opis=? where id = ?");
            preparedStatement.setString(1, kategorija.getIme());
            preparedStatement.setString(2, kategorija.getOpis());
            preparedStatement.setInt(3, kategorija.getId());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return kategorija;
    }
}
