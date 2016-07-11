package com.commonSocket.net.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class FileUtil {

    public static byte[] readByteDataFromFile(String filename)
            throws IOException {
        byte[] buffer = new byte[512000];
        FileInputStream in = new FileInputStream(filename);
        int totallen = 0;
        int len;
        for (int offset = 0; (len = in.read(buffer, offset, 1024)) != -1; offset += 1024) {
            totallen += len;
        }

        byte[] data = new byte[totallen];
        for (int i = 0; i < totallen; i++) {
            data[i] = buffer[i];
        }

        in.close();
        return data;
    }

    public static void writeByteDataToFile(String filename, byte[] imageData) throws IOException {
        BufferedOutputStream out = null;
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(filename);
            out = new BufferedOutputStream(fout);

            if (imageData != null) {
                safeClose(out);
            }
            System.out.println("ERROR: empty Feature data");
        } catch (IOException localIOException) {
        } finally {
            safeClose(out);
            safeClose(fout);
        }
    }

    public static void writeStringToFile(String filename, String str) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
        } finally {
            if (writer != null) {
                writer.write(str);
                writer.flush();
                writer.close();
            } else {
                System.out.println("ERROR: empty Feature data");
            }
        }
    }

    public static void appendStringToFile(String filename, String str) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        writer.write(str);
        writer.flush();
        writer.close();
    }

    public static String readStringFromFile(String filename)
            throws IOException {
        return readStringFromFile(filename, null);
    }

    public static String readStringFromFile(String filename, String encoding) throws IOException {
        BufferedReader reader = null;
        if ((encoding == null) || ("".equals(encoding))) {
            reader = new BufferedReader(new FileReader(filename));
        } else {
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), encoding));
            } catch (UnsupportedEncodingException e) {
                reader = new BufferedReader(new FileReader(filename));
            }
        }
        StringBuffer sb = new StringBuffer();
        String line = "";
        boolean first = true;
        while (true) {
            line = reader.readLine();
            if (line == null) {
                break;
            }
            if (first) {
                first = false;
            } else {
                sb.append("\n");
            }
            sb.append(line);
        }
        reader.close();
        return sb.toString();
    }

    public static void copyFile(String srcFile, String dstFile)
            throws IOException {
        byte[] buffer = new byte[4096];
        if (new File(dstFile).isDirectory()) {
            String dstPath = dstFile;
            dstFile = dstPath + "/" + new File(srcFile).getName();
        }
        FileInputStream in = new FileInputStream(srcFile);
        FileOutputStream out = new FileOutputStream(dstFile);
        int len;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.close();
        in.close();
    }

    public static void safeClose(OutputStream os) {
        if (os == null) {
            return;
        }
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void safeClose(InputStream is) {
        if (is == null) {
            return;
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean createDir(String path, boolean makeParents) {
        if (makeParents) {
            return new File(path).mkdirs();
        }
        return new File(path).mkdir();
    }

    public static String refineFilename(Object filename) {
        return refineFilename((String) filename);
    }

    public static String refineFilename(String filename) {
        if (filename == null) {
            return "";
        }
        return filename.replaceAll(" ", "_").toLowerCase();
    }

    public static String toDirName(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        char[] ac = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                ac[i] = '_';
            } else {
                ac[i] = c;
            }
        }

        return new String(ac);
    }

    public static ArrayList getDirFiles(String path, String prefix, String ext) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files == null) {
            return new ArrayList();
        }
        ArrayList list = new ArrayList();
        for (int i = 0; i < files.length; i++) {
            String filename = files[i].getName().toLowerCase();
            if (((prefix == null) || (filename.startsWith(prefix))) && ((ext == null) || (filename.endsWith("." + ext)))) {
                list.add(files[i]);
            }
        }

        return list;
    }

    public static ArrayList getDirs(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files == null) {
            return new ArrayList();
        }
        ArrayList list = new ArrayList();
        for (int i = 0; i < files.length; i++) {
            list.add(files[i]);
        }

        return list;
    }

    public static byte[] packZeroByte(byte[] input, int size) {
        if (input == null) {
            return new byte[size];
        }
        byte[] bytedata = new byte[size];
        int i = 0;
        i = 0;
        do {
            bytedata[i] = input[i];
            i++;
            if (i >= size) {
                break;
            }
        } while (i < input.length);
        for (; i < size; i++) {
            bytedata[i] = 0;
        }

        return bytedata;
    }

    public static ArrayList readArrayFromFile(String filename, String encoding, boolean skipFirstRow) {
        FileInputStream fin = null;
        BufferedReader in = null;
        ArrayList datalist = new ArrayList();
        try {
            fin = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader(fin, encoding);
            in = new BufferedReader(isr);
            int lineIdx = 0;
            while (true) {
                String line = in.readLine();
                if (line != null) {
                    String[] data = line.split("\t");
                    if ((skipFirstRow) && (lineIdx == 0)) {
                        lineIdx++;
                    }
                    datalist.add(data);
                    lineIdx++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datalist;
    }

    public static boolean writeStringToFile(String filename, String str, String encoding) throws IOException {
        FileOutputStream fout = null;
        OutputStreamWriter osw = null;
        try {
            fout = new FileOutputStream(filename);
            osw = new OutputStreamWriter(fout, encoding);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(str);

            bw.close();
            osw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            safeClose(fout);
        }
    }

    public static byte[] loadResource(String filename) {
        try {
            DataInputStream dout = null;
            FileInputStream is = new FileInputStream(filename);
            dout = new DataInputStream(is);

            byte[] data = new byte[dout.available()];

            dout.readFully(data);

            dout.close();
            dout = null;

            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteAllFile(String filename) {
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println(filename + "not exist!");
            return;
        }
        if (file.isFile()) {
            file.delete();
            System.out.println(filename + " is file and delete!");
            return;
        }
        String[] subfilename = file.list();
        for (int i = 0; i < subfilename.length; i++) {
            deleteAllFile(filename + "/" + subfilename[i]);
        }
        file.delete();
        System.out.println(filename + " is dir and delete!");
    }
}
