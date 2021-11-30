package com.chaoxuzhong.study.serialization;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class HessianSerializerTests {

    static class User implements Serializable {
        private static final long serialVersionUID = -3724134093296313813L;

        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setUsername("vulgar.cd");
        user.setPassword("123456");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Hessian2Output hessian2Output = new Hessian2Output(bos);
        hessian2Output.writeObject(user);
        hessian2Output.flush();
        hessian2Output.completeMessage();
        byte[] data = bos.toByteArray();
        System.out.println(Arrays.toString(data));
        hessian2Output.close();

        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        Hessian2Input hessian2Input = new Hessian2Input(bis);
        User readObject = (User) hessian2Input.readObject();
        System.out.println(readObject);
        hessian2Input.close();
    }
}
