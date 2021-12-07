package apap.ta.service;
import apap.ta.model.PegawaiModel;

import java.util.List;

public interface PegawaiService {
    PegawaiModel addPegawai(PegawaiModel pegawai);
    public String encrypt(String password);
    PegawaiModel getPegawai(String username);
    List<PegawaiModel> getPegawaiList();
//    boolean checkUsername(String username);

    List<Integer> getListGajiTiapPegawai();
//    PegawaiModel getPegawaiByIdPegawai(Long idPegawai);
}
