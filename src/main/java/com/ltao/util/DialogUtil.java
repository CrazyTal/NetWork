package com.ltao.util;

import javax.swing.*;

public class DialogUtil {

    static JPasswordField passwd = new JPasswordField(10);

    public static String passwordDialog(String des) {
        JOptionPane localJOptionPane = new JOptionPane(
                des,
                JOptionPane.QUESTION_MESSAGE);
        localJOptionPane.add(passwd, 1);
        passwd.setEchoChar('*');
        JDialog localJDialog = localJOptionPane.createDialog(localJOptionPane,
                "Input");
        localJDialog.setVisible(true);
        String localObject = String.valueOf(passwd.getPassword());
        localJDialog.dispose();
        return localObject;
    }

    public static String showInputDialog(String des) {

        return JOptionPane.showInputDialog(des);

    }
}
