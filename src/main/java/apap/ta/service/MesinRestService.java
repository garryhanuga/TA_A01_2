package apap.ta.service;

import apap.ta.model.MesinModel;

import java.util.List;

public interface MesinRestService {
    List<MesinModel> retrieveListMesin();
    List<MesinModel> retrieveListMesinByKategori(Long id);
    MesinModel getMesinById(Long id);
    void updateMesin(MesinModel mesin);
}
