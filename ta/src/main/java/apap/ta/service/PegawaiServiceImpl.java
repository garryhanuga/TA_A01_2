package apap.ta.service;

import apap.ta.model.PegawaiModel;
import apap.ta.repository.PegawaiDb;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PegawaiServiceImpl implements PegawaiService{
    @Autowired
    PegawaiDb pegawaiDb;

    @Override
    public PegawaiModel addPegawai(PegawaiModel pegawai) {
//        String pass = encrypt(pegawai.getPassword());
//        pegawai.setPassword(pass);
        return pegawaiDb.save(pegawai);
    }

//    @Override
//    public String encrypt(String password) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String hashedPassword = passwordEncoder.encode(password);
//        return hashedPassword;
//    }

    @Override
    public PegawaiModel getPegawai(String username) {
        return pegawaiDb.findByUsername(username);
    }

    @Override
    public List<PegawaiModel> getPegawaiList() {
        return pegawaiDb.findAll();
    }

    @Override
    public boolean checkUsername(String username) {
        if(!username.equals(pegawaiDb.findByUsername(username))){
            return true;
        }
        else{
            return false;
        }
    }
}
