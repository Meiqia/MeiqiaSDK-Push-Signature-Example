// need Apache Commons Codec
// https://commons.apache.org/proper/commons-codec/download_codec.cgi

import java.security.SignatureException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

class MeiqiaToolsSigner {
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    private String raw_body;
    private String key;

    public MeiqiaToolsSigner(String raw_body, String key){
        this.raw_body = raw_body;
        this.key = key;
    }

    public String sign() throws java.security.SignatureException{
        String result;
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF-8"), HMAC_SHA1_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(raw_body.getBytes("UTF-8"));
            byte[] hexBytes = new org.apache.commons.codec.binary.Hex().encode(rawHmac);
            result = org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(hexBytes).trim();
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
        return "meiqia_sign:" + result;
    }
}
