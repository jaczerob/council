package dev.jaczerob.council.database.toons;

import dev.jaczerob.council.common.toonstats.models.Toon;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "toons")
public class ToonEntity {
    @Id
    private int id;
    private int species;

    @Min(15)
    @Max(140)
    private int laff;

    @Min(0)
    @Max(7)
    @Column(name = "gag_toonup")
    private int gagToonup;

    @Min(0)
    @Max(7)
    @Column(name = "gag_trap")
    private int gagTrap;

    @Min(0)
    @Max(7)
    @Column(name = "gag_lure")
    private int gagLure;

    @Min(0)
    @Max(7)
    @Column(name = "gag_sound")
    private int gagSound;

    @Min(0)
    @Max(7)
    @Column(name = "gag_throw")
    private int gagThrow;

    @Min(0)
    @Max(7)
    @Column(name = "gag_squirt")
    private int gagSquirt;

    @Min(0)
    @Max(7)
    @Column(name = "gag_drop")
    private int gagDrop;

    @Min(0)
    @Max(7)
    private int organic;

    @Min(0)
    @Max(9)
    private int sellbot;

    @Min(0)
    @Max(9)
    private int cashbot;

    @Min(0)
    @Max(9)
    private int lawbot;

    @Min(0)
    @Max(9)
    private int bossbot;

    public ToonEntity() {
        this.id = 0;
        this.species = 0;
        this.laff = 0;
        this.gagToonup = 0;
        this.gagTrap = 0;
        this.gagLure = 0;
        this.gagSound = 0;
        this.gagThrow = 0;
        this.gagSquirt = 0;
        this.gagDrop = 0;
        this.organic = 0;
        this.sellbot = 0;
        this.cashbot = 0;
        this.lawbot = 0;
        this.bossbot = 0;
    }

    public ToonEntity(final int id, final int species, final int laff, final int gagToonup, final int gagTrap, final int gagLure, final int gagSound, final int gagThrow, final int gagSquirt, final int gagDrop, final int organic, final int sellbot, final int cashbot, final int lawbot, final int bossbot) {
        this.id = id;
        this.species = species;
        this.laff = laff;
        this.gagToonup = gagToonup;
        this.gagTrap = gagTrap;
        this.gagLure = gagLure;
        this.gagSound = gagSound;
        this.gagThrow = gagThrow;
        this.gagSquirt = gagSquirt;
        this.gagDrop = gagDrop;
        this.organic = organic;
        this.sellbot = sellbot;
        this.cashbot = cashbot;
        this.lawbot = lawbot;
        this.bossbot = bossbot;
    }

    public int getId() {
        return this.id;
    }

    public int getSpecies() {
        return this.species;
    }

    public int getLaff() {
        return this.laff;
    }

    public int getGagToonup() {
        return this.gagToonup;
    }

    public int getGagTrap() {
        return this.gagTrap;
    }

    public int getGagLure() {
        return this.gagLure;
    }

    public int getGagSound() {
        return this.gagSound;
    }

    public int getGagThrow() {
        return this.gagThrow;
    }

    public int getGagSquirt() {
        return this.gagSquirt;
    }

    public int getGagDrop() {
        return this.gagDrop;
    }

    public int getOrganic() {
        return this.organic;
    }

    public int getSellbot() {
        return this.sellbot;
    }

    public int getCashbot() {
        return this.cashbot;
    }

    public int getLawbot() {
        return this.lawbot;
    }

    public int getBossbot() {
        return this.bossbot;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setSpecies(final int species) {
        this.species = species;
    }

    public void setLaff(final int laff) {
        this.laff = laff;
    }

    public void setGagToonup(final int gagToonup) {
        this.gagToonup = gagToonup;
    }

    public void setGagTrap(final int gagTrap) {
        this.gagTrap = gagTrap;
    }

    public void setGagLure(final int gagLure) {
        this.gagLure = gagLure;
    }

    public void setGagSound(final int gagSound) {
        this.gagSound = gagSound;
    }

    public void setGagThrow(final int gagThrow) {
        this.gagThrow = gagThrow;
    }

    public void setGagSquirt(final int gagSquirt) {
        this.gagSquirt = gagSquirt;
    }

    public void setGagDrop(final int gagDrop) {
        this.gagDrop = gagDrop;
    }

    public void setOrganic(final int organic) {
        this.organic = organic;
    }

    public void setSellbot(final int sellbot) {
        this.sellbot = sellbot;
    }

    public void setCashbot(final int cashbot) {
        this.cashbot = cashbot;
    }

    public void setLawbot(final int lawbot) {
        this.lawbot = lawbot;
    }

    public void setBossbot(final int bossbot) {
        this.bossbot = bossbot;
    }

    public static ToonEntity fromToon(final Toon toon) {
        return new ToonEntity(
            toon.id(),
            toon.species(),
            toon.laff(),
            toon.gagToonup(),
            toon.gagTrap(),
            toon.gagLure(),
            toon.gagSound(),
            toon.gagThrow(),
            toon.gagSquirt(),
            toon.gagDrop(),
            toon.organic(),
            toon.sellbot(),
            toon.cashbot(),
            toon.lawbot(),
            toon.bossbot()
        );
    }

    @Override
    public String toString() {
        return "ToonEntity{" +
                "id=" + id +
                ", species=" + species +
                ", laff=" + laff +
                ", gagToonup=" + gagToonup +
                ", gagTrap=" + gagTrap +
                ", gagLure=" + gagLure +
                ", gagSound=" + gagSound +
                ", gagThrow=" + gagThrow +
                ", gagSquirt=" + gagSquirt +
                ", gagDrop=" + gagDrop +
                ", organic=" + organic +
                ", sellbot=" + sellbot +
                ", cashbot=" + cashbot +
                ", lawbot=" + lawbot +
                ", bossbot=" + bossbot +
                '}';
    }
}
