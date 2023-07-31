// AgeCategory.java
// D. Singletary
// 7/16/23
// enum representing age categories

package edu.fscj.cop3330c.birthday;

// https://www.nih.gov/nih-style-guide/age
// The following are the American Medical Associations’ age designations:
//    Neonates or newborns (birth to 1 month)
//    Infants (1 month to 1 year)
//    Children (1 year through 12 years)
//    Adolescents (13 years through 17 years)
//    Adults (18 years or older)
//    Older adults (65 and older)

public enum AgeCategory {
    AGE_UNKNOWN, AGE_INFANT, AGE_CHILD, AGE_ADOLESCENT, AGE_ADULT, AGE_SENIOR;
}