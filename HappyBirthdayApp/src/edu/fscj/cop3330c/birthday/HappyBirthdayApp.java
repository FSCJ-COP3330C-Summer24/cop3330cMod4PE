// HappyBirthdayApp.java
// D. Singletary
// 1/29/23
// wish multiple users a happy birthday

package edu.fscj.cop3330c.birthday;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;

// main mpplication class
public class HappyBirthdayApp implements BirthdayCardSender {
    //private User user;
    private ArrayList<User> birthdays = new ArrayList<>();

    public HappyBirthdayApp() { }

    public void sendCard(BirthdayCard bc) {
        System.out.println(bc);
        System.out.println("sending email to " + bc.getUser().getEmail() + "\n");
    }

    // compare current month and day to user's data
    // to see if it is their birthday
    public boolean isBirthday(User u) {
        boolean result = false;

        LocalDate today = LocalDate.now();
        if (today.getMonth() == u.getBirthday().getMonth() &&
                today.getDayOfMonth() == u.getBirthday().getDayOfMonth())
            result = true;

        return result;
    }

    // add multiple birthdays
    public void addBirthdays(User... users) {
        for (User u : users) {
            birthdays.add(u);
        }
    }

    // main program
    public static void main(String[] args) {

        HappyBirthdayApp hba = new HappyBirthdayApp();

        BirthdayCardFactory cardFactory = new BirthdayCardFactory();

        // test  varargs method by creating multiple birthdays and adding them.
        // names  generated using http://random-name-generator.info/
        // be sure to test positive case (today is someone's birthday)
        // and negative (today is not their birthday)

        // use current date for testing, adjust where necessary
        ZonedDateTime currentDate = ZonedDateTime.now();

        // negative test
        User u1 = new User("Dianne", "Romero", "Dianne.Romero@email.test",
                currentDate.minusDays(1));

        // positive tests
        // test with odd length full name
        User u2 = new User("Sally", "Roberts", "Sally.Roberts@email.test",
                currentDate);
        // test with even length full name
        User u3 = new User("Edwin", "Peterson", "Edwin.Peterson@email.test",
                currentDate);

        User u4 = new User("Nicolas", "Saunders", "Nicolas.Saunders@email.test",
                currentDate);

        User u5 = new User("Tammy", "Mcguire", "Tammy.Mcguire@email.test",
                currentDate);

        hba.addBirthdays(u1, u2, u3, u4, u5);

        // show the birthdays
        if (!hba.birthdays.isEmpty()) {
            System.out.println("Here are the birthdays:");
            int count = 0;
            for (User u : hba.birthdays) {
                System.out.println(u.getName() + ":");
                // see if today is their birthday
                if (!hba.isBirthday(u))
                   System.out.println("Sorry, today is not their birthday.\n");
                else {
                    BirthdayCard bc = null;
                    count++;
                    switch (count) {
                        case 1:
                            bc = cardFactory.createCard(u, AgeCategory.AGE_CHILD);
                            break;
                        case 2:
                            bc = cardFactory.createCard(u, AgeCategory.AGE_ADOLESCENT);
                            break;
                        case 3:
                            bc = cardFactory.createCard(u, AgeCategory.AGE_ADULT);
                            break;
                        case 4:
                            bc = cardFactory.createCard(u, AgeCategory.AGE_SENIOR);
                            break;
                    }
                    hba.sendCard(bc);
                }
            }
        }
    }
}

// user class
class User {
    private StringBuilder name;
    private String email;
    private ZonedDateTime birthday;

    public User(String fName, String lName, String email, 
                ZonedDateTime birthday) {
        this.name = new StringBuilder();
        this.name.append(fName).append(" ").append(lName);
        this.email = email;
        this.birthday = birthday;
    }

    public StringBuilder getName() {
        return name;
    }

    public String getEmail() { return email; }

    public ZonedDateTime getBirthday() {
        return birthday;
    }

    //public void setBirthday(ZonedDateTime birthday) {
    //    this.birthday = birthday;
    //}

    @Override
    public String toString() {
        return this.name + "," + this.birthday;
    }
}
