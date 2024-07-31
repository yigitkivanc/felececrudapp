package com.felececrud.felececrudapp.projections;

import com.felececrud.felececrudapp.entity.OtherInformation;
import com.felececrud.felececrudapp.entity.PersonalInformation;

public interface EmployeeProjection {
    Long getId();
    String getFullName();
    OtherInformation getOtherInformation();
    PersonalInformation getPersonalInformation();
}
