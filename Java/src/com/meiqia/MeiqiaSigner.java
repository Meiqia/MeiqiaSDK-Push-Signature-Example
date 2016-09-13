package com.meiqia;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.SignatureException;


class MeiqiaSigner {
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    private String key;

    public MeiqiaSigner(String key){
        this.key = key;
    }

    public String sign(String raw_body) throws java.security.SignatureException{
        String result;
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF-8"), HMAC_SHA1_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(raw_body.getBytes("UTF-8"));
            byte[] hexBytes = new org.apache.commons.codec.binary.Hex().encode(rawHmac);
            result = org.apache.commons.codec.binary.Base64.encodeBase64String(hexBytes).trim();
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
        return "meiqia_sign:" + result;
    }
}
