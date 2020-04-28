package dictionary;

import javax.persistence.*;

@Entity
@Table(name="main")

public class DictEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    protected int id;

    @Column(name="polish")
    protected String polish;

    @Column(name="english")
    protected String english;


    public DictEntity(){};

    public DictEntity(String word_0, String word_1){
        this.polish = polish;
        this.english = english;
    }

    @Override
    public String toString(){
        return (this.id + " " + this.polish + " " + this.english);
    }


}