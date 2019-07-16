package com.fanhehe.user.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public final class Crypto {

    private static final String ENCODE = "utf-8";

    public static String makePassword(
            @NotNull String password,
            @NotNull String saltFromDB,
            @NotNull String saltFromRedis
    ) {
        return sha256(sha256(password + saltFromDB) + saltFromRedis);
    }

    enum Algorithm {
        MD5("MD5", "%032x"),
        SHA256("SHA-256", "%064x"),
        SHA512("SHA-512", "%0128x");

        private String name;
        private String format;

        Algorithm(String name, String format) {
            this.name = name;
            this.format = format;
        }

        public String getName() {
            return this.name;
        }

        public String getFormat() {
            return this.format;
        }
    }

    public static String md5(String origin) {
        return hash(origin, Algorithm.MD5);
    }

    public static String sha256(String origin) {
        return hash(origin, Algorithm.SHA256);
    }

    public static String sha512(String origin) {
        return hash(origin, Algorithm.SHA512);
    }

    private static String hash(String origin, Crypto.Algorithm algorithm) {

        try {

            MessageDigest digest = MessageDigest.getInstance(algorithm.getName());

            digest.reset();
            digest.update(origin.getBytes(ENCODE));

            return String.format(algorithm.getFormat(), new BigInteger(1, digest.digest()));

        } catch (NoSuchAlgorithmException|UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "";
    }
}
