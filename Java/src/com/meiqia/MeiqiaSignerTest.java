package com.meiqia;

import junit.framework.TestCase;

import javax.script.ScriptEngineManager;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SignatureException;
import java.util.List;
import java.util.Map;

/**
 * Created by hu on 16/9/13.
 */
public class MeiqiaSignerTest extends TestCase {
    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {

    }

    public void testSign() throws Exception {
        String json = new String(Files.readAllBytes(Paths.get("../sample.txt")));
        String script = "Java.asJSONCompatible(" + json + ")";
        Object result = new ScriptEngineManager().getEngineByName("javascript").eval(script);
        Map sample = (Map) result;

        MeiqiaSigner signer =  new MeiqiaSigner((String) sample.get("key"));
        List maps = (List) sample.get("maps");
        maps.forEach((map) -> {
            try {
                assertEquals(signer.sign((String) (((Map) map).get("text"))), ((Map)map).get("sign"));
            } catch (SignatureException e) {
                e.printStackTrace();
            }
        });
    }

}