package apap.ta.rest;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class MesinDetail {
    private String namaMesin;
    private Long idKategori;
    private Date tanggalDibuat;
    
}
