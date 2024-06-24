package util;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

// BCrypt Example Wrapper found on dzone.com
public class PasswordHashing {
    private static final Logger log = LoggerFactory.getLogger(PasswordHashing.class);
    private final static int logRounds = 10; 

    // this hashes the password using the BCrypt algorithm 
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
    }

    // this verifies a plaintext password against a stored hashed password
    public static boolean verifyPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

    // this verifies the password and updates its hash if the number of hashing rounds has changed.
    public boolean verifyAndUpdateHash(String password, String hash, Function<String, Boolean> updateFunc) {
        if (BCrypt.checkpw(password, hash)) {
            int rounds = getRounds(hash);
            if (rounds != logRounds) {
                log.debug("Updating password from {} rounds to {}", rounds, logRounds);
                String newHash = hashPassword(password);
                return updateFunc.apply(newHash);
            }
            return true;
        }
        return false;
    }

    private int getRounds(String salt) {
        char minor = (char)0;
        int off = 0;

        if (salt.charAt(0) != '$' || salt.charAt(1) != '2')
            throw new IllegalArgumentException ("Invalid salt version");
        if (salt.charAt(2) == '$')
            off = 3;
        else {
            minor = salt.charAt(2);
            if (minor != 'a' || salt.charAt(3) != '$')
                throw new IllegalArgumentException ("Invalid salt revision");
            off = 4;
        }

        // Extract the number of rounds from the salt 
        if (salt.charAt(off + 2) > '$')
            throw new IllegalArgumentException ("Missing salt rounds");
        return Integer.parseInt(salt.substring(off, off + 2));
    }
}

