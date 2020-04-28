package dictionary;

import java.util.LinkedList;
import java.util.List;

public class DictUtils {

    public static void setAbility(List<WordEntity> words){
        words.get(0).min_ab = 1;
        words.get(0).max_ab = words.get(0).min_ab + words.get(0).ability - 1;

        for(int i = 1; i<words.size(); i++){
            words.get(i).min_ab = words.get(i-1).max_ab + 1;
            words.get(i).max_ab = words.get(i).min_ab + words.get(i).ability - 1;
        }
    }

    public static void setCount(List<WordEntity> words){
        for(WordEntity word : words){
            word.count_0 = DictUtils.searchInList(word.word_0, words, "pl").size();
        }
        for(WordEntity word : words){
            word.count_1 = DictUtils.searchInList(word.word_1, words, "en").size();
        }
    }


    public static List<WordEntity> searchInList(String word, List<WordEntity> words, String lang){

        List<WordEntity> searched_words = new LinkedList<>();

        if(lang.equals("pl")) {
            for (WordEntity word_from_dict : words) {
                if (word_from_dict.word_0.equals(word)) {
                    searched_words.add(word_from_dict);
                }
            }
            return searched_words;
        }
        for(WordEntity word_from_dict : words){
            if(word_from_dict.word_1.equals(word)) {
                searched_words.add(word_from_dict);
            }
        }
        return searched_words;
    }


    @SuppressWarnings("Duplicates")
    public static boolean checkForOtherTranslations(String temp_word, String word_x, List<WordEntity> words, String lang){

        if(lang.equals("pl")) {
            for (WordEntity value : words) {
                if (value.word_1.equals(temp_word) && value.word_0.equals(word_x)) {
                    return true;
                }
            }
            return false;
        }
        for(WordEntity value : words){
            if(value.word_0.equals(temp_word) && value.word_1.equals(word_x)){
                return true;
            }
        }
        return false;
    }
}
