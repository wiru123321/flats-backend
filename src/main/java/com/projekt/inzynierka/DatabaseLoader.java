
package com.projekt.inzynierka;


import com.projekt.inzynierka.model.*;
import com.projekt.inzynierka.repositories.*;
import com.projekt.inzynierka.services.RoleService;
import com.projekt.inzynierka.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class DatabaseLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final FlatsRepository flatsRepository;
    private final AdressRepository adressRepository;
    private final UserAccountRepository userAccountRepository;
    private final AnnouncementsRepository announcementsRepository;
    private final FaultsRepository faultsRepository
;
    
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.username}")
    private String dataBase;

    @Autowired
    public DatabaseLoader(
            final RoleRepository roleRepository, final UserRepository userRepository,
            FlatsRepository flatsRepository, AdressRepository adressRepository, UserAccountRepository userAccountRepository, AnnouncementsRepository announcementsRepository, FaultsRepository faultsRepository, final RoleService roleService, final UserService userService) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.flatsRepository = flatsRepository;
        this.adressRepository = adressRepository;
        this.userAccountRepository = userAccountRepository;
        this.announcementsRepository = announcementsRepository;
        this.faultsRepository = faultsRepository;


        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void run(final String... strings) throws Exception {

        if (dataBase.equals("postgres")) {

            roleRepository.save(new Role(null, "ADMIN"));
            roleRepository.save(new Role(null, "RESIDENT"));
            Long id = adressRepository.save(new Adress(null,"Kato","Kato","Kato","Kato")).getId();
            Long idAcc = userAccountRepository.save(new UserAccount(null,5,5,5,5,5,5, LocalDateTime.of(2020, 3, 20, 0, 0),LocalDateTime.of(2020, 3, 20, 0, 0),true)).getId();

            Long idFlat = flatsRepository.save(new Flats(null,5,5,5,5,true,true,adressRepository.findById(id).get(),userAccountRepository.findById(idAcc).get())).getId();

            Long id1 = adressRepository.save(new Adress(null,"Katowice","44111","Gaer","5")).getId();
            Long idAcc1 = userAccountRepository.save(new UserAccount(null,365,365,55,0,120,120, LocalDateTime.of(2020, 3, 20, 0, 0),LocalDateTime.of(2020, 3, 20, 0, 0),true)).getId();

            Long idFlat1 = flatsRepository.save(new Flats(null,55,3,2,3,true,true,adressRepository.findById(id1).get(),userAccountRepository.findById(idAcc1).get())).getId();

            Long id2 = adressRepository.save(new Adress(null,"Katowiceeee","44114","Gae3","8")).getId();
            Long idAcc2 = userAccountRepository.save(new UserAccount(null,3655,3645,555,20,120,120, LocalDateTime.of(2020, 3, 20, 0, 0),LocalDateTime.of(2020, 3, 20, 0, 0),true)).getId();

            Long idFlat2 = flatsRepository.save(new Flats(null,553,31,22,32,true,true,adressRepository.findById(id2).get(),userAccountRepository.findById(idAcc2).get())).getId();

            announcementsRepository.save((new Announcements(null,"Podajemy godziny przeprowadzenia przetargu na ustanowienie i zbycie prawa odrębnej własności nieruchomości lokalowej w trybie ustnego przetargu nieograniczonego (licytacji), który odbędzie sie dnia 26.11.2020r.",true,"Przetargi",LocalDateTime.of(2020, 11, 28, 0, 0),105674)));
            announcementsRepository.save((new Announcements(null,"Informujemy, iż dzień 24.12.2020r. jest dniem wolnym od pracy.\n" +
                    "Ewentualne awarie należy zgłaszać do dyspozytora Spółdzielni w godz. 6:00 do 22:00.\n" +
                    "Informujemy również, iż w dniu 31.12.2020r. tj. czwartek pracujemy w godz. 7:00 do 15:00, KASA w tym dniu jest czynna w godz. 7:00 do 11:00.",true,"Praca Spółdzielni",LocalDateTime.of(2020, 11, 28, 0, 0),105346)));
            announcementsRepository.save((new Announcements(null," Informujemy, że od 2 listopada 2020r place zabaw nie będą dezynfekowane. W celu ochrony życia i zdrowia mieszkańców Spółdzielni przed zakażeniem się wirusem COVID-19 prosimy o nie korzystanie z placów zabaw zlokalizowanych w naszych zasobach.",true,"Informacje na temat placów zabaw",LocalDateTime.of(2020, 11, 29, 0, 0),105342)));
            announcementsRepository.save((new Announcements(null,"Informujemy, że Spółdzielnia zleciła dostawcom ciepła rozpoczęcie sezonu grzewczego 2020/2021 od dnia 23.09.2020r.\n" +
                    "Dostawa ciepła dla poszczególnych budynków będzie odbywała się sukcesywnie. Nadmieniamy, że w niektórych budynkach trwają nadal prace związane z napełnianiem sieci oraz robotami instalacyjnymi, w tychże budynkach dostawa ciepła nastąpi po zakończeniu robót.",true,"Sezon grzewczy 2020/21",LocalDateTime.of(2020, 11, 30, 0, 0),101114)));

            faultsRepository.save(new Faults(null,flatsRepository.findById(idFlat1).get(),"Proszę o wysłanie osoby odpowiedzialnej za sprzątanie klatki schodowej do mojego bloku, ponieważ jest strasznie brudno na klatce schodowej.","Brudna klatka schodowa",true));
            faultsRepository.save(new Faults(null,flatsRepository.findById(idFlat1).get(),"Proszę o dopilnowanie porządku przed blokiem po 22.","Hałas po 22",true));
            faultsRepository.save(new Faults(null,flatsRepository.findById(idFlat2).get(),"Proszę o udzielenie zgody na wymiane drzwi wejściowych w moim mieszkaniu.","Zgoda na wymiane drzwi wejściowych.",true));

            userRepository.save(new User(null, "admin123", passwordEncoder.encode("apassword123"), "admin@email.com", "Jan", "Kowalski", "513238338", roleRepository.findByName("ADMIN"),flatsRepository.findById(idFlat).get()));
            userRepository.save(new User(null, "user123", passwordEncoder.encode("upassword123"), "admin4@email.com", "Adam", "Adamski", "513238358", roleRepository.findByName("RESIDENT"),flatsRepository.findById(idFlat1).get()));
            userRepository.save(new User(null, "1123",  passwordEncoder.encode("1223"), "admin43@email.com", "JanAaa", "KowalskiAaa", "514238358", roleRepository.findByName("RESIDENT"),flatsRepository.findById(idFlat2).get()));

        }
    }
}


