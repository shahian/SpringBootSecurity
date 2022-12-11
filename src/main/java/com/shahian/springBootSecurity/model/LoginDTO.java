package com.shahian.springBootSecurity.model;


/*
A record class declares a sequence of fields,
 and then the appropriate accessors, constructors, equals, hashCode,
  and toString methods are created automatically.
   The fields are final because the class is intended to serve as a simple "data carrier".
 */
public record LoginDTO(String userName,String password) {
}
