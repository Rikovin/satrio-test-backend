package testing.satrio.backend.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cif_account")
public class CifAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy="id")
    private Set<Cif> cif_id;

    @Column(name = "starting_balance")
    private Double starting_balance;

    @Column(name = "debit")
    private Double debit;

    @Column(name = "credit")
    private Double credit;

    @Column(name = "closing_balance")
    private Double closing_balance;

    @Column(name = "cif_nisbah")
    private Double cif_nisbah;

    @Column(name = "bank_nisbah")
    private Double bank_nisbah;

    @Column(name = "nisbah_total")
    private Double nisbah_total;

    public CifAccount(Long id, Set<Cif> cif_id, Double starting_balance, Double debit, Double credit, Double closing_balance, Double cif_nisbah, Double bank_nisbah, Double nisbah_total) {
        this.id = id;
        this.cif_id = cif_id;
        this.starting_balance = starting_balance;
        this.debit = debit;
        this.credit = credit;
        this.closing_balance = closing_balance;
        this.cif_nisbah = cif_nisbah;
        this.bank_nisbah = bank_nisbah;
        this.nisbah_total = nisbah_total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Cif> getCif_id() {
        return cif_id;
    }

    public void setCif_id(Set<Cif> cif_id) {
        this.cif_id = cif_id;
    }

    public Double getStarting_balance() {
        return starting_balance;
    }

    public void setStarting_balance(Double starting_balance) {
        this.starting_balance = starting_balance;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getClosing_balance() {
        return closing_balance;
    }

    public void setClosing_balance(Double closing_balance) {
        this.closing_balance = closing_balance;
    }

    public Double getCif_nisbah() {
        return cif_nisbah;
    }

    public void setCif_nisbah(Double cif_nisbah) {
        this.cif_nisbah = cif_nisbah;
    }

    public Double getBank_nisbah() {
        return bank_nisbah;
    }

    public void setBank_nisbah(Double bank_nisbah) {
        this.bank_nisbah = bank_nisbah;
    }

    public Double getNisbah_total() {
        return nisbah_total;
    }

    public void setNisbah_total(Double nisbah_total) {
        this.nisbah_total = nisbah_total;
    }
}
