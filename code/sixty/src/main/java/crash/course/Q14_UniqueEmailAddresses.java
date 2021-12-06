package crash.course;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/unique-email-addresses/
 * Question
 * Every valid email consists of a local name and a domain name, separated by the '@' sign. 
 * Besides lowercase letters, the email may contain one or more '.' or '+'.
 * 
 * For example, in "alice@leetcode.com", "alice" is the local name, and "leetcode.com" is the domain name.
 * If you add periods '.' between some characters in the local name part of an email address, 
 * mail sent there will be forwarded to the same address without dots in the local name. Note that this rule does not apply to domain names.
 * For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.
 * If you add a plus '+' in the local name, everything after the first plus sign will be ignored. 
 * This allows certain emails to be filtered. Note that this rule does not apply to domain names.
 * For example, "m.y+name@email.com" will be forwarded to "my@email.com".
 * It is possible to use both of these rules at the same time.
 * Given an array of strings emails where we send one email to each email[i], 
 * return the number of different addresses that actually receive mails.
 */
/**
 * - convert each email by removing . and everything else after + and add domain name.
 * - store it in map
 * - return the map size
 * Time: O(n*m) m is the the max length of the email address
 * Space: O(n*m)    
 */
/**
 * Improvements
 * use hashset instead of hashmap
 * use split and replace functions. using stringBuilder
 */
public class Q14_UniqueEmailAddresses {
    public int numUniqueEmails1(String[] emails) {
        // hash set to store all the unique emails
        Set<String> uniqueEmails = new HashSet<>();

        for (String email : emails) {
            StringBuilder cleanMail = new StringBuilder();

            // iterate over each character in email
            for (int i = 0; i < email.length(); ++i) {
                char currChar = email.charAt(i);

                // stop adding characters to localName
                if (currChar == '+' || currChar == '@') break;

                // add this character if not '.'
                if (currChar != '.') cleanMail.append(currChar);
            }

            // compute domain name (substring from end to '@')
            StringBuilder domainName = new StringBuilder();

            for (int i = email.length() - 1; i >= 0; --i) {
                char currChar = email.charAt(i);
                domainName.append(currChar);
                if (currChar == '@') break;
            }

            domainName = domainName.reverse();
            cleanMail.append(domainName);
            uniqueEmails.add(cleanMail.toString());
        }

        return uniqueEmails.size();
    }

    public int numUniqueEmails2(String[] emails) {
        // hash set to store all the unique emails
        Set<String> uniqueEmails = new HashSet<>();

        for (String email : emails) {
            // split into two parts local and domain
            String[] parts = email.split("@");

            // split local by '+'
            String[] local = parts[0].split("\\+");

            // remove all '.', and concatenate '@' and append domain
            uniqueEmails.add(local[0].replace(".", "") + "@" + parts[1]);
        }

        return uniqueEmails.size();
    }
}
