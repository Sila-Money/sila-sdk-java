package com.silamoney.clientrefactored.endpoints.entities.register;

import com.silamoney.clientrefactored.domain.Address;
import com.silamoney.clientrefactored.domain.Contact;
import com.silamoney.clientrefactored.domain.CryptoEntry;
import com.silamoney.clientrefactored.domain.Device;
import com.silamoney.clientrefactored.domain.Entity;
import com.silamoney.clientrefactored.domain.Identity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegisterRequest {
    
    private String userHandle;
    private Address address;
    private Identity identity;
    private Contact contact;
    private CryptoEntry cryptoEntry;
    private Entity entity;
    private Device device;
    private String reference;

}
