# Anagrams
### This program finds the anagrams of a given word or phrase as well as all the words found in it.

Anagram is a word or phrase made by rearranging the letters of another word or phrase. For
example, the words `melon` and `lemon` are anagrams. If you ignore spaces and capitalization
and allow multiple words, a multi-word phrase can be an anagram of some other word or
phrase. For example, the phrases `Clint Eastwood` and `old west action` are anagrams.

The required program will use command line options to indicate a dictionary file, a phrase with
no spaces to find anagrams of, and a limit on the number of words in the found anagrams (or 0
to indicate no limit). For example, running the program with the following command line:

    dict1.txt hairbrush 0

should output:

    Phrase to scramble: hairbrush
    All words found in hairbrush:
    [bar, briar, brush, bus, hub, huh, hush, rub, shrub, sir, sub]
    Anagrams for hairbrush:
    [bar, huh, sir]
    [bar, sir, huh]
    [briar, hush]
    [huh, bar, sir]
    [huh, sir, bar]
    [hush, briar]
    [sir, bar, huh]
    [sir, huh, bar]
  
The anagrams are printed as lists of words; each list could be used to form an anagram phrase.
The example above has eight unique lists of words, or eight possible anagram phrases.
