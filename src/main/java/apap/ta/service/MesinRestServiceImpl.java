package apap.ta.service;
import apap.ta.model.MesinModel;
import apap.ta.repository.MesinDb;
import apap.ta.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MesinRestServiceImpl implements MesinRestService{
    private final WebClient webClient;

    @Autowired
    private MesinDb mesinDb;

    @Override
    public List<MesinModel> retrieveListMesin() {
        return mesinDb.findAll();
    }

    @Override
    public List<MesinModel> retrieveListMesinByKategori(Long id) {
        return mesinDb.findAllByidKategori(id);
    }

    @Override
    public void updateMesin(MesinModel mesin) {
        int newCap = mesin.getKapasitas()-1;
        mesin.setKapasitas(newCap);
    }

    @Override
    public MesinModel getMesinById(Long id) {
        return mesinDb.getById(id);
    }

    public MesinRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.mesinUrl).build();
    }

}
