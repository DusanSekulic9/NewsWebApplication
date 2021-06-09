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
            this.closeResultSet(resultSet);
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
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM korisnici where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()){
                korisnik = new Korisnik(resultSet.getString("email"), resultSet.getString("imeIPrezime"), resultSet.getString("status"), resultSet.getString("tipKorisnika"), resultSet.getString("lozinka"));
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
    public List<Korisnik> all() {
        List<Korisnik> korisnici = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM korisnici");
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while(resultSet.next()){
                korisnici.add(new Korisnik(resultSet.getString("email"), resultSet.getString("imeIPrezime"), resultSet.getString("status"), resultSet.getString("tipKorisnika"), resultSet.getString("lozinka")));
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
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM korisnici where email = ?");
            preparedStatement.setString(1,email);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while(resultSet.next()){
                korsinik = new Korisnik(resultSet.getString("email"), resultSet.getString("imeIPrezime"), resultSet.getString("status"), resultSet.getString("tipKorisnika"), resultSet.getString("lozinka"));
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


}
