package testing.satrio.backend.model;
import javax.persistence.*;

@Entity
@Table(name = "cif")
public class Cif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "no_ktp")
    private long no_ktp;

    @Column(name = "jenis_kelamin")
    private String jenis_kelamin;

    public Cif() {
    }

    public Cif(String name, String alamat, long no_ktp, String jenis_kelamin) {
        this.name = name;
        this.alamat = alamat;
        this.no_ktp = no_ktp;
        this.jenis_kelamin = jenis_kelamin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public long getNo_ktp() {
        return no_ktp;
    }

    public void setNo_ktp(long no_ktp) {
        this.no_ktp = no_ktp;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }
}
