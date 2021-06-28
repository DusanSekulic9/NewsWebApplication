package rs.raf.project.repositories.Korisnik;

import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.project.entities.Korisnik;
import rs.raf.project.repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlKorisnikRepository extends MySqlAbstractRepository implements KorisnikRepository {
    @Override
    public Korisnik addKorisnik(Korisnik korisnik) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO korisnici (email, imeIPrezime, status, tipKorisnika,lozinka) VALUES(?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, korisnik.getEmail());
            preparedStatement.setString(2, korisnik.getImeIPrezime());
            preparedStatement.setString(3, korisnik.getStatus());
            preparedStatement.setString(4, korisnik.getTipKorisnika());
            preparedStatement.setString(5, DigestUtils.sha256Hex(korisnik.getLozinka()));
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                korisnik.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            //this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return korisnik;
    }

    @Override
    public Korisnik updateKorisnik(Korisnik korisnik) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE korisnici set email=?, imeIPrezime=?, status=?,tipKorisnika=?, lozinka=? where id = ?");
            preparedStatement.setString(1, korisnik.getEmail());
            preparedStatement.setString(2, korisnik.getImeIPrezime());
            preparedStatement.setString(3, korisnik.getStatus());
            preparedStatement.setString(4, korisnik.getTipKorisnika());
            preparedStatement.setString(5, korisnik.getLozinka());
            preparedStatement.setInt(6, korisnik.getId());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return korisnik;

    }

    @Override
    public Korisnik getKorisnik(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Korisnik korisnik = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM korisnici where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                korisnik = new Korisnik(resultSet.getString("email"), resultSet.getString("imeIPrezime"), resultSet.getString("status"), resultSet.getString("tipKorisnika"), resultSet.getString("lozinka"));
                korisnik.setId(resultSet.getInt("id"));
            }


            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return korisnik;

    }

    @Override
    public List<Korisnik> all(Integer page) {
        List<Korisnik> korisnici = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM korisnici limit ?, 10");
            if(page == 1){
                preparedStatement.setInt(1, 0);

            }else{
                preparedStatement.setInt(1, page*10 - 10);
            }
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                korisnici.add(new Korisnik(resultSet.getString("email"), resultSet.getString("imeIPrezime"), resultSet.getString("status"), resultSet.getString("tipKorisnika"), resultSet.getString("lozinka")));
                korisnici.get(korisnici.size() - 1).setId(resultSet.getInt("id"));
            }


            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return korisnici;
    }

    @Override
    public Korisnik getKorisnikByEmail(String email) {
        Korisnik korsinik = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            System.out.println(email);

            String[] generatedColumns = {"id"};

            //"email", "imeIPrezime", "status", "tipKorisnika", "lozinka"

            preparedStatement = connection.prepareStatement("SELECT * FROM korisnici");

            resultSet = preparedStatement.executeQuery();



            preparedStatement = connection.prepareStatement("SELECT * FROM korisnici where email = ?", generatedColumns);
            preparedStatement.setString(1, email);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();


            if(resultSet.next()){
                korsinik = new Korisnik(resultSet.getString("email"), resultSet.getString("imeIPrezime"), resultSet.getString("status"), resultSet.getString("tipKorisnika"), resultSet.getString("lozinka"));
                korsinik.setId(resultSet.getInt("id"));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return korsinik;
    }

    @Override
    public int getPagginationLimitForAllUsers() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT count(id) as count FROM korisnici");

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


}
