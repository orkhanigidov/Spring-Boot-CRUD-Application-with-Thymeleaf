package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE userid=#{userid}")
    ArrayList<Credential> getCredentials(int userid);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid=#{credentialid} AND userid=#{userid}")
    Credential getCredential(int credentialid, int userid);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid)" +
            "VALUES(#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int insert(Credential credentialid);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{username}, key=#{key}, password=#{password}" +
            "WHERE credentialid=#{credentialid} AND userid=#{userid}")
    void update(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid=#{credentialid}")
    void delete(int credentialid);
}
