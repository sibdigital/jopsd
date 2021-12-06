package ru.sibdigital.jopsd.utils;

import com.ibm.icu.text.Transliterator;

public class TranscriptorUtils {
    public static final String CYRILLIC_TO_LATIN = "Cyrillic-Latin";

    public static String cyrrilicToLatin(String str) {
        Transliterator toLatinTrans = Transliterator.getInstance(CYRILLIC_TO_LATIN);
        String result = toLatinTrans.transliterate(str);
        return result;
    }

    public static String getLatinIdentifier(String str) {
        String result = TranscriptorUtils.cyrrilicToLatin(str);
        result = result.toLowerCase();
        result = result.replace(" ", "-");
        return result;
    }
}
