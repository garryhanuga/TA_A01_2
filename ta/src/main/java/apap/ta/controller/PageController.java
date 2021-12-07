package apap.ta.controller;

import apap.ta.model.PegawaiModel;
import apap.ta.repository.PegawaiDb;
import apap.ta.service.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @Autowired
    PegawaiService pegawaiService;

    @RequestMapping("/")
    public String home(
            Model model
    ){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PegawaiModel pegawai = pegawaiService.getPegawai(auth.getName());
        model.addAttribute("role", pegawai.getRole().getNamaRole());
        model.addAttribute("namaPegawai", pegawai.getNamaPegawai());
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}