package apap.ta.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name="pegawai")

public class PegawaiModel {
    @Id
    @Column(name="id_pegawai")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPegawai;

    @NotNull
    @Size(max=50)
    @Column(name="nama", nullable = false)
    private String namaPegawai;

    @NotNull
    @Column(name="tanggal_lahir", nullable = false)
    private Date tanggalLahir;

    @NotNull
    @Size(max=50)
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Size(max=50)
    @Column(name="email", nullable = false)
    private String email;

    @NotNull
    @Lob
    @Column(name="password", nullable = false)
    private String password;

    //Relasi dengan Role
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_role", referencedColumnName = "id_role", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RoleModel role;

//    //Relasi dengan Produksi
//    @OneToMany(mappedBy = "pegawai", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<ProduksiModel> listProduksi;
//
//    //Relasi dengan Delivery
//    @OneToMany(mappedBy = "pegawai", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<DeliveryModel> listDelivery;

}