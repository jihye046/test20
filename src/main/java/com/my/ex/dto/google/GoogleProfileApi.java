package com.my.ex.dto.google;

import lombok.Data;

@Data
public class GoogleProfileApi {
	private String iss;
    private String azp;
    private String aud;
    private String sub;
    private String email;
    private String email_verified;
    private String at_hash;
    private String name;
    private String picture;
    private String given_name;
    private String family_name;
    private String locale;
    private String iat;
    private String exp;
    private String alg;
    private String kid;
    private String typ;
    private String error;
    private String error_description;
    private String scope;
    private int expires_in;
    private String access_type;
}
