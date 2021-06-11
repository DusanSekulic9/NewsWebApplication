package rs.raf.project;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import rs.raf.project.entities.Vest_Tag;
import rs.raf.project.repositories.Kategorija.KategorijaRepository;
import rs.raf.project.repositories.Kategorija.MySqlKategorijaRepository;
import rs.raf.project.repositories.Komentar.KomentarRepository;
import rs.raf.project.repositories.Komentar.MySqlKomentarRepository;
import rs.raf.project.repositories.Korisnik.KorisnikRepository;
import rs.raf.project.repositories.Korisnik.MySqlKorisnikRepository;
import rs.raf.project.repositories.Tag.MySqlTagRepository;
import rs.raf.project.repositories.Tag.TagRepository;
import rs.raf.project.repositories.Vest.MySqlVestRepository;
import rs.raf.project.repositories.Vest.VestRepository;
import rs.raf.project.repositories.vest_tag.MySqlVest_TagRepository;
import rs.raf.project.repositories.vest_tag.Vest_TagRepository;
import rs.raf.project.services.*;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class Application extends ResourceConfig {

    public Application() {
        // Ukljucujemo validaciju
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);


        // Definisemo implementacije u dependency container-u
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(MySqlKategorijaRepository.class).to(KategorijaRepository.class).in(Singleton.class);
                this.bind(MySqlKomentarRepository.class).to(KomentarRepository.class).in(Singleton.class);
                this.bind(MySqlKorisnikRepository.class).to(KorisnikRepository.class).in(Singleton.class);
                this.bind(MySqlTagRepository.class).to(TagRepository.class).in(Singleton.class);
                this.bind(MySqlVestRepository.class).to(VestRepository.class).in(Singleton.class);
                this.bind(MySqlVest_TagRepository.class).to(Vest_TagRepository.class).in(Singleton.class);
                this.bindAsContract(KategorijaService.class);
                this.bindAsContract(KomentarService.class);
                this.bindAsContract(KorisnikService.class);
                this.bindAsContract(TagService.class);
                this.bindAsContract(VestService.class);
                this.bindAsContract(Vest_TagService.class);
            }
        };
        register(binder);

        // Ucitavamo resurse
        packages("rs.raf.project.resources");

    }
}
