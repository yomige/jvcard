package com.aitlp.jvcard;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.parameter.EmailType;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuickStartEzVcard {

    public static void main(String[] args) {
        List<String> whiteListEmails = new QuickStartEzVcard().readEmails("F:\\jvcard\\src\\main\\java\\com\\aitlp\\jvcard\\whitelist.txt");
        List<String> emails = new QuickStartEzVcard().readEmails("F:\\飞飞影视群.txt");
        List<VCard> vcards = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(emails)) {
            for (String email : emails) {
                if(!whiteListEmails.contains(email)){
                    VCard vCard = new VCard();
                    vCard.addEmail(email, EmailType.INTERNET);
                    vcards.add(vCard);
                }
            }
            new QuickStartEzVcard().writeVcardFile(vcards,"F:\\ALLEMAILS.vcf");
        }
    }

    /**
     * 读取单个.vcf文件
     *
     * @param path
     * @return
     */
    public List<VCard> readVcardFile(String path) {
        List<VCard> vcards = new ArrayList<>();
        try {
            File file = new File(path);
            vcards = Ezvcard.parse(file).all();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vcards;
    }

    /**
     * 将多个Vcard对象写入到vcf文件
     *
     * @param vCards
     * @param path
     * @return
     */
    public boolean writeVcardFile(List<VCard> vCards, String path) {
        try {
            File file = new File(path);
            Ezvcard.write(vCards).go(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 读取邮件(一行一个)
     *
     * @return
     */
    public List<String> readEmails(String fileFullPath) {
        List<String> emails = new ArrayList<>();
        FileInputStream fileInputStream;
        try {
            InputStreamReader inputStreamReader;
            BufferedReader bufferedReader;

            File filePath = new File(fileFullPath);
            // 将文件读入输入流
            fileInputStream = new FileInputStream(filePath);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            // 读取原有的内容
            String line;
            while (bufferedReader.ready()) {
                line = bufferedReader.readLine();
                if (StringUtils.isNotBlank(StringUtils.trimToEmpty(line))) {
                    emails.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emails;
    }
}