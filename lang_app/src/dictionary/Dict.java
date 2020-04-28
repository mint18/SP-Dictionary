package dictionary;

import org.hibernate.Session;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Dict {

    Random rand = new Random();

    public Dict(){};

    public void connect_hib(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
    }



    public int getSizeOfDict(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<WordEntity> words = session.createQuery("from dictionary.WordEntity ").getResultList();
        session.getTransaction().commit();
        session.close();
        return words.size();
    }

    public void addTranslation(String word_PL, String word_EN){
        Session session = HibernateUtil.getSessionFactory().openSession();
        WordEntity word = new WordEntity(word_PL, word_EN);
        session.beginTransaction();
        session.save(word);
        session.close();
    }

    public void removeTranslation(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(session.get(WordEntity.class, id));
        session.getTransaction().commit();
        session.close();
    }

    public List<WordEntity> searchForDetails(String word, String lang){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<WordEntity> words = session.createQuery("from dictionary.WordEntity ").getResultList();
        List<WordEntity> searched_words = new LinkedList<>();

        List<WordEntity> additional_words = new LinkedList<>();


        if(lang.equals("pl")) {
            for (WordEntity word_from_dict : words) {
                if(word_from_dict.word_0.equals(word)){
                    searched_words.add(word_from_dict);
                }
                else if (word_from_dict.word_0.matches(word+".*")) {
                    additional_words.add(word_from_dict);
                }
            }
            for(WordEntity extra_word : additional_words){
                searched_words.add(extra_word);
            }
            session.close();
            return searched_words;
        }
        for (WordEntity word_from_dict : words) {
            if (word_from_dict.word_1.equals(word)){
                searched_words.add(word_from_dict);
            }
            else if (word_from_dict.word_1.matches(word+".*")) {
                additional_words.add(word_from_dict);
            }
        }
        for(WordEntity extra_word : additional_words){
            searched_words.add(extra_word);
        }
        session.close();
        return searched_words;
    }

    public List<WordEntity> getAllTranslations(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<WordEntity> words = session.createQuery("from dictionary.WordEntity ").getResultList();
        session.close();
        return words;
    }

    // TD
//    public void searchForTranslations(String word, String lang){
//        for(WordEntity trans : searchForDetails(word, lang)){
//            System.out.println(trans.word_0 + " - " + trans.word_1);
//        }
//    }


    public String loadHint(String word){
        String hint_word="";
        for(int i=0; i< word.length(); i++){
            hint_word+="_";
        }
        return hint_word;
    }

    public String loadHint(String word, String hint_word){
        int r;
        int low_dashes = 0;
        for(int i =0; i<hint_word.length(); i++ ){
            if(hint_word.charAt(i)=='_') low_dashes++;
        }
        if(low_dashes>0){
            while(true) {

                r = rand.nextInt(hint_word.length());
                if(hint_word.charAt(r)=='_'){
                    char[] hint_word_chars = hint_word.toCharArray();
                    hint_word_chars[r] = word.charAt(r);
                    hint_word = String.valueOf(hint_word_chars);
                    break;
                }
            }

        }
        return hint_word;
    }


    public String loadRandomWord(String lang){
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        List<WordEntity> words = session.createQuery("from dictionary.WordEntity ").getResultList();
        DictUtils.setAbility(words);
        session.getTransaction().commit();

        session.beginTransaction();
        DictUtils.setCount(words);
        session.getTransaction().commit();


        int w_max = words.get(words.size() - 1).max_ab;

        int r = rand.nextInt(w_max)+1;
        int r_it=0;

        for(int i=0 ; i<words.size(); i++){
            if(words.get(i).min_ab<=r && words.get(i).max_ab>=r){
                r_it=i;
                break;
            }
        }
        if(lang.equals("pl")) {
            return r_it + ":" + words.get(r_it).word_0 + ":" + words.get(r_it).word_1;
        }
        return r_it + ":" + words.get(r_it).word_1 + ":" + words.get(r_it).word_0;
    }
    

    public void correctAnswer(int id, int use_hint){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<WordEntity> words = session.createQuery("from dictionary.WordEntity ").getResultList();
        if(words.get(id).ability>1) {words.get(id).ability+=use_hint;};
        session.getTransaction().commit();
        session.close();
    }

    public boolean wrongAnswer(int id, String temp_word, String lang){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<WordEntity> words = session.createQuery("from dictionary.WordEntity ").getResultList();
        if(words.get(id).count_0==1) return false;
        if(lang.equals("pl")) {
            return DictUtils.checkForOtherTranslations(temp_word, words.get(id).word_0 ,words, lang);
        }
        return DictUtils.checkForOtherTranslations(temp_word, words.get(id).word_1 ,words, lang);
    }

    /* MAIN DICTIONARY FUNCTIONS */
    public List<DictEntity> searchInMainDictionary(String word, String lang){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<DictEntity> words = session.createQuery("from dictionary.DictEntity ").getResultList();
        List<DictEntity> searched_words = new LinkedList<>();
        List<DictEntity> additional_words = new LinkedList<>();


        if(lang.equals("pl")) {
            for (DictEntity word_from_dict : words) {
                if(word_from_dict.polish.equals(word)){
                    searched_words.add(word_from_dict);
                }
                else if (word_from_dict.polish.matches(word+".*")) {
                    additional_words.add(word_from_dict);
                }
            }
            for(DictEntity extra_word : additional_words){
                searched_words.add(extra_word);
            }
            session.close();
            return searched_words;
        }
        for (DictEntity word_from_dict : words) {
            if (word_from_dict.english.equals(word)){
                searched_words.add(word_from_dict);
            }
            else if (word_from_dict.english.matches(word+".*")) {
                additional_words.add(word_from_dict);
            }
        }
        for(DictEntity extra_word : additional_words){
            searched_words.add(extra_word);
        }
        session.close();
        return searched_words;
    }

    public List<DictEntity> getMainDict(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<DictEntity> words = session.createQuery("from dictionary.DictEntity ").getResultList();
        session.close();
        return words;
    }

}

