package br.uam.criptografialocalrsa;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class RSA {
    private BigInteger p, q, n, e, d;

    public RSA(BigInteger p, BigInteger q) {
        this.p = p;
        this.q = q;
        this.n = p.multiply(q);
        this.e = new BigInteger("3"); // Um valor comum para 'e'
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        this.d = e.modInverse(phi);
    }

    public String encrypt(String message) {
        byte[] msgBytes = message.getBytes(StandardCharsets.US_ASCII);
        StringBuilder cifradaStringBuilder = new StringBuilder();
        for (byte b : msgBytes) {
            BigInteger msgBigInt = new BigInteger(new byte[]{b});
            BigInteger cifrada = msgBigInt.modPow(e, n);
            cifradaStringBuilder.append(cifrada).append(" ");
        }
        return cifradaStringBuilder.toString().trim();
    }

    public String decrypt(String cifrada) {
        String[] cifradaParts = cifrada.split(" ");
        StringBuilder decifradaStringBuilder = new StringBuilder();
        for (String part : cifradaParts) {
            BigInteger cifradaBigInt = new BigInteger(part);
            BigInteger decifrada = cifradaBigInt.modPow(d, n);
            decifradaStringBuilder.append((char) decifrada.byteValueExact());
        }
        return decifradaStringBuilder.toString();
    }
}
