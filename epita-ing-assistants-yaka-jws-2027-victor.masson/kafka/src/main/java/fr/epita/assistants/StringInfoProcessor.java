package fr.epita.assistants;

import fr.epita.assistants.utils.StringInfo;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@ApplicationScoped
public class StringInfoProcessor {

    @Broadcast
    @Incoming("string-info-command")
    @Outgoing("string-info-aggregate")
    public StringInfo process(String input) {
        int vowels = countVowels(input);
        int consonants = countConsonants(input);
        boolean isPalindrome = checkPalindrome(input);

        return new StringInfo(input, vowels, consonants, isPalindrome);
    }

    private int countVowels(String s) {
        return (int) s.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> "aeiouyAEIOUY".indexOf(c) != -1)
                .count();
    }

    private int countConsonants(String s) {
        return (int) s.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> Character.isLetter(c) && "aeiouyAEIOUY".indexOf(c) == -1)
                .count();
    }

    private boolean checkPalindrome(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }
}