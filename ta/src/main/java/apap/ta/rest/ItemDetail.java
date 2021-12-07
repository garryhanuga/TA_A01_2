package apap.ta.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.result.NoMoreReturnsException;
import org.springframework.util.MultiValueMap;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)

@Data
public class ItemDetail {
    @JsonProperty("status")
    private int status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private Result result;

    @Setter
    @Getter
    public class Result{
        private String uuid;
        private String nama;
        private int harga;
        private int stok;
        private String kategori;

        public String getUUID(){
            return uuid;
        }
        public String getNama(){
            return nama;
        }
        public int getHarga(){
            return harga;
        }

        public int getStok(){
            return stok;
        }
        public String getKategori(){
            return kategori;
        }
    }
}
