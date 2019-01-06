package com.tribesbackend.tribes.tribesuser.okstatusservice;

import java.security.KeyPairGenerator;
import java.security.interfaces.{RSAPrivateKey, RSAPublicKey};

import com.google.gson.{GsonBuilder, JsonElement, JsonParser};
import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.jwk.{JWKSet, KeyUse, RSAKey};

public class JWKGenerator {
    object JWKGenerator {

        def make(keySize: Integer, keyUse: KeyUse, keyAlg: Algorithm, keyId: String) = {
            val generator = KeyPairGenerator.getInstance("RSA")
            generator.initialize(keySize)
            val kp = generator.generateKeyPair()
            val publicKey = kp.getPublic().asInstanceOf[RSAPublicKey]
            val privateKey = kp.getPrivate().asInstanceOf[RSAPrivateKey]
            new RSAKey.Builder(publicKey)
                    .privateKey(privateKey)
                    .keyUse(keyUse)
                    .algorithm(keyAlg)
                    .keyID(keyId)
                    .build()
        }

        def generateJWKKeypair(rsaKey: RSAKey): JsonElement = {
                val jwkSet = new JWKSet(rsaKey)
                new JsonParser().parse(jwkSet.toJSONObject(false).toJSONString)
        }

        def generateJWKJson(rsaKey: RSAKey): String = {
                val jsonElement  = generateJWKKeypair(rsaKey)
                val gson = new GsonBuilder().setPrettyPrinting().create()
                gson.toJson(jsonElement)
        }


    }

}
