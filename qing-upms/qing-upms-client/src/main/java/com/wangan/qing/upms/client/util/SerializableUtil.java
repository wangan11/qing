package com.wangan.qing.upms.client.util;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;

import java.io.*;

public class SerializableUtil {

    public static String serialize(Session session){
        if (null == session) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(session);
            return  Base64.encodeToString(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("serialize session error", e);
        }
    }

    public static Session deserialize(String sessionStr){
        if(sessionStr==null){
            return null;
        }
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decode(sessionStr));
            ObjectInputStream objectInputStream=new ObjectInputStream(bis);
            Session session =(Session) objectInputStream.readObject();
            return session;
        } catch (Exception e) {
            throw new RuntimeException("deserialize session error", e);
        }
    }

}
