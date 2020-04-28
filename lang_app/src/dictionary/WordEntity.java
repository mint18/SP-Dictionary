package dictionary;

import javax.persistence.*;



@Entity
@Table(name="dict")

public class WordEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    protected int id;

    @Column(name="word_0")
    protected String word_0;

    @Column(name="word_1")
    protected String word_1;

    @Column(name="ability")
    protected int ability;

    @Column(name="min_ab")
    protected int min_ab;

    @Column(name="max_ab")
    protected int max_ab;

    @Column(name="count_0")
    protected int count_0;

    @Column(name="count_1")
    protected int count_1;


    public void setId(int value) {
        this.id = value;
    }
    public void setWord_0(String value) {
        this.word_0 = value;
    }
    public void setWord_1(String value) {
        this.word_1 = value;
    }
    public void setAbility(int value) {
        this.ability = value;
    }

    public WordEntity(){};

    public WordEntity(String word_0, String word_1){
        this.word_0 = word_0;
        this.word_1 = word_1;
        this.ability = 7;
        this.min_ab = 0;
        this.max_ab = 0;
        this.count_0 = 1;
        this.count_1 = 1;
    }

    @Override
    public String toString(){
        return (this.id + " " + this.word_0 + " " + this.word_1 + " " + this.ability + " " + min_ab + " " + max_ab);
    }


}

