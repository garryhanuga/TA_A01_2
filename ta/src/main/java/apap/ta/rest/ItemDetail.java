package apap.ta.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDetail {
    private int status;

    private String message;
    private String uuid;
    private String nama;
    private int harga;
    private int stok;
    private String kategori;
    @SuppressWarnings("unchecked")
    @JsonProperty("result")
    private void unpackResult(Map<String, Object> result) {
        this.uuid = (String)result.get("uuid");
        this.nama = (String)result.get("nama");
        this.harga = (Integer)result.get("harga");
        this.stok = (Integer)result.get("stok");
        this.kategori = (String)result.get("kategori");
    }
}
