package apap.ta.repository;


import apap.ta.model.MesinModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MesinDb extends JpaRepository<MesinModel, Long> {
    Optional<MesinModel> findByidMesin(Long idMesin);
    List<MesinModel> findAllByidKategori(Long idKategori);
}
