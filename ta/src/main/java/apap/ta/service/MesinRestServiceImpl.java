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

    public MesinRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.mesinUrl).build();
    }

}
