package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

@Service
public class CredentialService {
    private final EncryptionService encryptionService;
    private final CredentialMapper credentialMapper;

    public CredentialService(EncryptionService encryptionService, CredentialMapper credentialMapper) {
        this.encryptionService = encryptionService;
        this.credentialMapper = credentialMapper;
    }

    public String decryptCredential(int credentialid, int userid) {
        Credential cred = credentialMapper.getCredential(credentialid, userid);
        return encryptionService.decryptValue(cred.getPassword(), cred.getKey());
    }

    public String encodedKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        return encodedKey;
    }

    public String encryptCredential(String key, String password) {
        String encodedPassword = encryptionService.encryptValue(password, key);
        return encodedPassword;
    }

    public ArrayList<Credential> getCredentials(int userid) {
        return credentialMapper.getCredentials(userid);
    }

    public int insert(Credential credential) {
        return credentialMapper.insert(credential);
    }

    public void update(Credential credential) {
        credentialMapper.update(credential);
    }

    public void delete(int credentialid) {
        credentialMapper.delete(credentialid);
    }
}
