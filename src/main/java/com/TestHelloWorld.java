package com;

import java.io.IOException;
import java.util.Date;

import com.angaza.nexus.keycode.KeycodeFactory;
import com.angaza.nexus.keycode.KeycodeMetadata;
import com.angaza.nexus.keycode.KeycodeProtocol;
import com.angaza.nexus.keycode.exceptions.*;
import com.angaza.nexus.keycode.util.HexToByteArray;


class TestHelloWorld {
    static String deviceSecretKey = "deadbeefdeadbeefdeadbeefdeadbeef";

    public TestHelloWorld() {
    }

    public static void main(String[] args) throws UnsupportedKeyMappingException, UnsupportedMessageDaysException, UnsupportedProtocolException, UnsupportedMessageIdException, IOException, UnsupportedMessageTypeException {
        generateFixedDaysToken();
        generateUnlockCode();
    }

    public static void generateFixedDaysToken() throws UnsupportedKeyMappingException, UnsupportedMessageDaysException, UnsupportedProtocolException, UnsupportedMessageIdException, IOException, UnsupportedMessageTypeException {
        byte[] secretKey = new HexToByteArray().convert(deviceSecretKey);
        int messageId = 42;
        long seconds = 8 /* days */ * 24 /* hours */ * 60 /* minutes */ * 60 /* seconds */;
        Date clampedTime = new Date();
        KeycodeMetadata output = KeycodeFactory.addCredit(
                clampedTime,
                messageId,
                secretKey,
                KeycodeProtocol.FULL,
                seconds
        );
        String keycode = output.getKeycodeData().getKeycode();
       System.out.println(keycode);
    }

    public static void generateUnlockCode() throws UnsupportedKeyMappingException, UnsupportedMessageDaysException, UnsupportedProtocolException, UnsupportedMessageIdException, IOException, UnsupportedMessageTypeException {
        byte[] secretKey = new HexToByteArray().convert(deviceSecretKey);
        int messageId = 44;
        KeycodeMetadata output = KeycodeFactory.unlock(messageId, secretKey, KeycodeProtocol.FULL);
        String keycode = output.getKeycodeData().getKeycode();
        System.out.println(keycode);

    }
}
