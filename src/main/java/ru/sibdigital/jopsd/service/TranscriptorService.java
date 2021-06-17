package ru.sibdigital.jopsd.service;

public interface TranscriptorService {
    String cyrrilicToLatin(String str);

    String getLatinIdentifier(String str);
}
