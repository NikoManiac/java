package com.cultivation.javaBasic.showYourIntelligence;

import com.cultivation.javaBasic.util.Person;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import sun.security.krb5.internal.crypto.NullEType;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

@SuppressWarnings("unused")
public class PersonForEquals implements Comparable<PersonForEquals>{
    private final String name;
    private final short yearOfBirth;

    public PersonForEquals(String name, short yearOfBirth) {
        if (name == null) {
            throw new IllegalArgumentException("name is mandatory.");
        }

        if (yearOfBirth <= 1900 || yearOfBirth >= 2019) {
            throw new IllegalArgumentException("year of birth is out of range.");
        }

        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }


    public String getName() {
        return name;
    }

    public short getYearOfBirth() {
        return yearOfBirth;
    }

    @SuppressWarnings("Contract")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        PersonForEquals personForEquals = (PersonForEquals) obj;
        return this.name.equals(personForEquals.name)
                && this.yearOfBirth == personForEquals.yearOfBirth;
    }


    @Override
    public int hashCode() {
        // TODO: please modify the following code to pass the test
        // <--start
        return Objects.hash(name, yearOfBirth);
        // --end-->
    }

    @Override
    public int compareTo(PersonForEquals onePerson) {
        if (onePerson == null) throw new NullPointerException();

        boolean nameBoolean = name.compareTo(onePerson.name) == 0;
        boolean ageBoolean = yearOfBirth != onePerson.yearOfBirth;

        if (nameBoolean && ageBoolean) return 0;
        if (nameBoolean) return name.compareTo(onePerson.name);
        if (ageBoolean) return yearOfBirth > onePerson.yearOfBirth ? 1 : -1;
        return 0;
    }
}
