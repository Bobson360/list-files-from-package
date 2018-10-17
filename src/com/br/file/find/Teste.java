/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.file.find;

/**
 *
 * @author bobso
 *
 * ClassesDoPacote.java
 *
 * Created on July 24, 2007, 10:04 AM
 *
 */

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

import com.br.file.*;
/**
 *
 * @author diego
 */
public class Teste {
    public Teste() {
        try {
            Class[] classes = getClasses("com.br.file");
            for (int i = 0; i < classes.length; i++) {
                //System.out.println(classes[i].getName());
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public static Class[] getClasses(String pckgname) throws ClassNotFoundException {
        ArrayList classes=new ArrayList();
        // Get a File object for the package
        File directory=null;
        try {
            directory=new File(Thread.currentThread().getContextClassLoader().getResource(pckgname.replace('.', '/')).getFile());
        } catch(NullPointerException x) {
            throw new ClassNotFoundException(pckgname+" does not appear to be a valid package1");
        }
        if(directory.exists()) {
        // Get the list of the files contained in the package
            String[] files=directory.list();
            for(int i=0; i<files.length; i++) {
        // we are only interested in .class files
                if(files[i].endsWith(".class")) {
        // removes the .class extension
                    //classes.add(Class.forName(pckgname+'.'+files[i].substring(0, files[i].length()-6)));
                    Field[] campos = null;
                    System.out.println("File " + i + ": " +files[i].substring(0, files[i].length()-6));
                    campos = Class.forName(pckgname+'.'+files[i].substring(0, files[i].length()-6)).getFields(); // getDeclaredFields();
                    for (int j =0; j < campos.length; j++) {
                        System.out.println(campos[j].getName());
                    }
                    classes.add(Class.forName(pckgname+'.'+files[i].substring(0, files[i].length()-6)));
                }
            }
        } else {
            throw new ClassNotFoundException(pckgname+" does not appear to be a valid package2");
        }
        Class[] classesA=new Class[classes.size()];
        classes.toArray(classesA);
        return classesA;
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Teste();
            }
        });
    }
}