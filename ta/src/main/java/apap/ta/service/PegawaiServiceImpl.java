package apap.ta.service;

import apap.ta.model.PegawaiModel;
import apap.ta.repository.PegawaiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PegawaiServiceImpl implements PegawaiService{
    @Autowired
    PegawaiDb pegawaiDb;

    @Override
    public PegawaiModel addPegawai(PegawaiModel pegawai) {
        String pass = encrypt(pegawai.getPassword());
        pegawai.setPassword(pass);
        return pegawaiDb.save(pegawai);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public PegawaiModel getPegawai(String username) {
        return pegawaiDb.findByUsername(username);
    }

    @Override
    public List<PegawaiModel> getPegawaiList() {
        return pegawaiDb.findAll();
    }

    @Override
    public PegawaiModel updatePegawai(PegawaiModel pegawai) {
        return pegawaiDb.save(pegawai);
    }

//    @Override
//    public boolean checkUsername(String username) {
//        if(getPegawai(username).equals(1)){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }

    @Override
    public List<Integer> getListGajiTiapPegawai() {
        List<PegawaiModel> listPegawai = getPegawaiList();
        List<Integer> listGajiTiapPegawai  = new ArrayList<>();
        for (PegawaiModel pegawai : listPegawai){
            int gaji = pegawai.getCounter() * pegawai.getRole().getBaseWages();
            listGajiTiapPegawai.add(gaji);
        }
        return listGajiTiapPegawai;
    }

//    @Override
//    public PegawaiModel getPegawaiByIdPegawai(Long idPegawai) {
//        return pegawaiDb.findById(idPegawai);
//    }

}
