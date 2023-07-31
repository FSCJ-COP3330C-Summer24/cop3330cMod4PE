// BirthdayCardFactory.java
// D. Singletary
// 7/2/23
// Class which represents a birthday card

package edu.fscj.cop3330c.birthday;

import java.util.Arrays;

public class BirthdayCardFactory {
    public BirthdayCard createCard(User u, AgeCategory ageCat) {
        BirthdayCard retCard;  // return variable
        if (ageCat.equals(AgeCategory.AGE_CHILD)){
            retCard = new BirthdayCard_Child(u);
        }
        else if (ageCat.equals(AgeCategory.AGE_ADOLESCENT)) {
            retCard = new BirthdayCard_Adolescent(u);
        }
        else if (ageCat.equals(AgeCategory.AGE_ADULT)) {
            retCard = new BirthdayCard_Adult(u);
        }
        else if (ageCat.equals(AgeCategory.AGE_SENIOR)) {
            retCard = new BirthdayCard_Senior(u);
        }
        else {
            System.out.println("no card available for that age category");
            throw new IllegalArgumentException(
                    "no card available for that age category");
        }
        return retCard;
    }
}

class BirthdayCard implements BirthdayCardBuilder {

    // test with odd length (comment to test with even length, below)
    private final String WISHES = "Hope all of your birthday wishes come true!";
    // uncomment to test with even length
    //final String WISHES = "Hope all of your birthday wishes come true!x";

    User user;
    private String message;

    public BirthdayCard() { }

    public BirthdayCard(User user) {
        this.user = user;
        System.out.println("building card for " + this.getClass().getSimpleName());
        this.buildCard(user);
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    // given a String containing a (possibly) multi-line message,
    // split the lines, find the longest line, and return its length
    public int getLongest(String s) {
        final String NEWLINE = "\n";
        int maxLength = 0;
        String[] splitStr = s.split(NEWLINE);
        for (String line : splitStr)
            if (line.length() > maxLength)
                maxLength = line.length();
        return maxLength;
    }

    public void buildCard(User u) {
        String msg = "Happy Birthay, " + u.getName() + "\n" + WISHES;

        final String NEWLINE = "\n";

        // get the widest line and number of lines in the message
        int longest = getLongest(msg);

        // need to center lines
        // dashes on top (header) and bottom (footer)
        // vertical bars on the sides
        // |-----------------------|
        // | longest line in group |
        // |      other lines      |
        // |-----------------------|
        //
        // pad with an extra space if the length is odd

        int numDashes = (longest + 2) + (longest % 2);  // pad if odd length
        char[] dashes = new char[numDashes];  // header and footer
        char[] spaces = new char[numDashes];  // body lines
        Arrays.fill(dashes, '-');
        Arrays.fill(spaces, ' ');
        String headerFooter = "|" + new String(dashes) + "|\n";
        String spacesStr = "|" + new String(spaces) + "|\n";

        // start the card with the header
        this.message  = headerFooter;

        // split the message into separate strings
        String[] splitStr = msg.split(NEWLINE);
        for (String s : splitStr) {
            String line = spacesStr;  // start with all spaces

            // create a StringBuilder with all spaces,
            // then replace some spaces with the centered string
            StringBuilder buildLine = new StringBuilder(spacesStr);

            // start at  middle minus half the length of the string (0-based)
            int start = (spacesStr.length() / 2) - (s.length() / 2);
            // end at the starting index plus the length of the string
            int end = start + s.length();
            /// replace the spaces and create a String, then append
            buildLine.replace(start, end, s);
            line = new String(buildLine);
            this.message += line;
        }
        // append the footer
        this.message += headerFooter;
    }

    @Override
    public String toString() {
        String s = "Birthday card for " + this.getUser().getName() + "\n";
        s += getMessage();
        return s;
    }
}

//AGE_UNKNOWN, AGE_INFANT, AGE_CHILD, AGE_ADOLESCENT, AGE_ADULT, AGE_SENIOR;

class BirthdayCard_Child extends BirthdayCard {
    public BirthdayCard_Child(User u) {
        super(u);
    }
}

class BirthdayCard_Adolescent extends BirthdayCard {
    public BirthdayCard_Adolescent(User u) { super(u); }
}

class BirthdayCard_Adult extends BirthdayCard {
    public BirthdayCard_Adult(User u) {
        super(u);
    }
}

class BirthdayCard_Senior extends BirthdayCard {
    public BirthdayCard_Senior(User u) {
        super(u);
    }
}