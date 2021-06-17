package ru.sibdigital.jopsd.service;

import com.ibm.icu.text.Transliterator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TranscriptorServiceImpl implements TranscriptorService{

    public static final String CYRILLIC_TO_LATIN = "Cyrillic-Latin";

    @Override
    public String cyrrilicToLatin(String str) {
        Transliterator toLatinTrans = Transliterator.getInstance(CYRILLIC_TO_LATIN);
        String result = toLatinTrans.transliterate(str);
        return result;
    }

    @Override
    public String getLatinIdentifier(String str) {
        String result = cyrrilicToLatin(str);
        result = result.toLowerCase();
        result = result.replace(" ", "-");
        return result;
    }
}
