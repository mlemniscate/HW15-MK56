package ir.maktab.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonInfo {

    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String ADDRESS = "address";
    public static final String NATIONAL_CODE = "national_code";

    @Column(name = FIRST_NAME)
    private String firstName;
    @Column(name = LAST_NAME)
    private String lastName;
    @Column(name = EMAIL)
    private String email;
    @Column(name = PHONE_NUMBER)
    private String phoneNumber;
    @Column(name = ADDRESS)
    private String address;
    @Column(name = NATIONAL_CODE)
    private String nationalCode;

}
