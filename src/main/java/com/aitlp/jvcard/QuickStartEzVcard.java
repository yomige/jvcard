package com.aitlp.jvcard;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.parameter.EmailType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuickStartEzVcard {

    public static void main(String[] args) {
        VCard vCard = new VCard();
        vCard.addEmail("4spaces.com@gmail.com", EmailType.INTERNET);
        List<VCard> vcards = new QuickStartEzVcard().readVcardFile("C:\\Users\\michael\\Downloads\\contacts.vcf");
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

}
