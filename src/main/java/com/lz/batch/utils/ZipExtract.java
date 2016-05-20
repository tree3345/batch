package com.lz.batch.utils;

/**
 * Created by Administrator on 2015/5/11.
 */


import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by ldh.
 */
public class ZipExtract {
    private static final int DEFAULT_BUFSIZE=1024*16;

    /**
     * 解压到指定路径
     * @param fileName
     * @param unZipDir
     * @return
     * @throws IOException
     */
    public List<Map<String,Object>> unZip(String fileName,String unZipDir) throws IOException {
            List<Map<String,Object>> strsList =new ArrayList<Map<String, Object>>();

            File f = new File(unZipDir);
            if(!f.exists())
                f.mkdirs();

            BufferedOutputStream dest = null;
            BufferedInputStream is =null;
            ZipEntry entry;
            ZipFile zipFile = new ZipFile(fileName,Charset.forName("GBK"));
            Enumeration<? extends ZipEntry> e = zipFile.entries();
            while (e.hasMoreElements()){
                Map<String,Object> map = new HashMap<String, Object>();
                entry = e.nextElement();
                if(entry.isDirectory()){
                    File f0 =new File(unZipDir + "/" + entry);
                    f0.mkdir();
                }else {
                    map.put("fileDir", unZipDir + "/" + entry);
                    map.put("fileName", entry.getName());
                    strsList.add(map);
                    is = new BufferedInputStream(zipFile.getInputStream(entry));
                    int count;
                    byte[] data = new byte[DEFAULT_BUFSIZE];
                    FileOutputStream fos = new FileOutputStream(unZipDir + "/" + entry.getName());
                    dest = new BufferedOutputStream(fos, DEFAULT_BUFSIZE);
                    while ((count = is.read(data, 0, DEFAULT_BUFSIZE)) != -1) {
                        dest.write(data, 0, count);
                    }
                    dest.flush();
                    dest.close();
                    is.close();
                }
            }
            return strsList;
    }

    /**
     * 解压路径名为当前zip名
     * @param filedir
     * @return
     * @throws IOException
     */
    public List<Map<String,Object>> unZip(String filedir) throws IOException {
        String dir=filedir.substring(0,filedir.indexOf(".zip"));
        return unZip(filedir,dir);
    }

    /**
     * 返回的文件名及路径list
     */
    private List<Map<String,Object>> fileList = new ArrayList<Map<String,Object>>();

    /**
     * 解压当前路径为当前zip名下的所有zip
     * @param fileName
     * @return
     * @throws IOException
     */
    public List<Map<String,Object>> unZips(String fileName) throws IOException {

        for(Map<String,Object> fmap:unZip(fileName)){
            String fName = (String)fmap.get("fileDir");

            if(fName.substring(fName.lastIndexOf(".") + 1).equals("zip")){
                unZips(fName);
                System.gc();
                File f = new File(fName);
                f.delete();
            }
            else
                fileList.add(fmap);
        }
        return fileList;
    }

    /**
     * 根据zip中是否有一个文件夹进行解压 1.是，则解压到当前文件夹 2.否，解压到以zip名的路径
     * @param fileName
     * @return
     * @throws IOException
     */
    public List<Map<String,Object>> unZipToDir(String fileName) throws IOException {
        ZipFile zipFile = new ZipFile(fileName,Charset.forName("GBK"));
        Set<String>  tempSet = new HashSet<>();
        Enumeration<? extends ZipEntry> e = zipFile.entries();
        while (e.hasMoreElements()){
            ZipEntry zipEntry = e.nextElement();
                tempSet.add(zipEntry.toString().split("/")[0]);
        }
        File f = new File(fileName);
        if(tempSet.size()==1)
            fileList=unZip(fileName,f.getParent());
        else
            fileList = unZip(fileName);
        return fileList;
    }
    public static void main(String[] args) throws IOException {
        String filedir="F:/ddd2.zip";
        ZipExtract zipExtract = new ZipExtract();
//        path=path+"\\"+"test.zip";
        List<Map<String,Object>> list=zipExtract.unZipToDir(filedir);
        for(Map<String,Object> fmap:list) {
            String filePath = (String)fmap.get("fileDir");
                System.out.println("fileName:  "+filePath);
        }
    }
}

