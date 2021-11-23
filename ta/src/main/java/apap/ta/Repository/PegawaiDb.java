package apap.ta.Repository;

import apap.ta.Model.PegawaiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PegawaiDb extends JpaRepository<PegawaiModel, Long> {
    PegawaiModel findByUsername(String Username);

}
