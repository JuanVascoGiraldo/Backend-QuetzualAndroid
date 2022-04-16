package com.example.quetzualandroid.Models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.security.*;
import 	java.util.Base64;
import java.util.Base64.Decoder;
import 	java.util.Base64.Encoder;


import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;


public class Cifrado {

    private static final String clave = "S~J?xm,:c7WU8HFz)K$a$N&[V:ez*EN#";
    private static final byte[] keyvalue = clave.getBytes() ;

    private static final String instancia = "AES";


    public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher cifrado = Cipher.getInstance(instancia);
        cifrado.init(Cipher.ENCRYPT_MODE, key);
        Encoder encoder = Base64.getEncoder();
        byte[] encValores = cifrado.doFinal(Data.getBytes());
        String valoresencriptados = new String(encoder.encode(encValores));
        return valoresencriptados;
    }

    public static String decrypt(String valoresencriptados) throws Exception {
        Key key = generateKey();
        Cipher cifrado = Cipher.getInstance(instancia);
        cifrado.init(Cipher.DECRYPT_MODE, key);
        Decoder decoder = Base64.getDecoder();
        byte [] decodificadorvalores = decoder.decode(valoresencriptados);
        byte[] decValores = cifrado.doFinal(decodificadorvalores);
        String valoresdescifrados = new String(decValores);
        return valoresdescifrados;
    }
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyvalue, instancia);
        return key;
    }
}
