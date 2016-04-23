package com.htc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileSystem {
    HashMap<String, List<String>> filesInPath = new HashMap<String,List<String>>();
    HashMap<String, String> contentMap = new HashMap<String, String>();

    public FileSystem(){
        filesInPath.put("/", new ArrayList<String>());
    }

    public List<String> ls(String path) {
        if(path == null || path.isEmpty() || filesInPath.get(path) == null)
            return new ArrayList<String>();

       return filesInPath.get(path);
    }

    public void mkdirP(String dirPath) {
        // IMPLEMENT ME
        while(  dirPath.lastIndexOf("/") != -1){

            int idx =  dirPath.lastIndexOf("/");

            String path = dirPath.substring(0, idx);
            if( path.isEmpty() ||  path == null){
                path = "/";
            }
            List<String> files = filesInPath.get(path);
            if(files ==null)
                files = new ArrayList<String>();

            files.add(dirPath.substring(idx+1, dirPath.length()));

            filesInPath.put(path, files);

            dirPath = dirPath.substring(0, idx);
        }
    }

    public void addFileWithContent(String filePath, String content) {
        // IMPLEMENT ME
        int idx =  filePath.lastIndexOf("/");

        String path = filePath.substring(0, idx);
        if( path.isEmpty() ||  path == null){
            path = "/";
        }
        List<String> files = filesInPath.get(path);
        if(files ==null)
            files = new ArrayList<String>();

        files.add(filePath.substring(idx+1, filePath.length()));
        filesInPath.put(path, files);

        filesInPath.put(filePath, files);

        contentMap.put(filePath, content);
    }

    public String getFileContent(String filePath) {
        if(filePath == null || filePath.isEmpty() || contentMap.get(filePath) ==null){
            return "No such file in the file system";
        }

        return contentMap.get(filePath);
    }

    public static void main(String[] args) {
        // assumption: all path starts with / and do not end with /
        FileSystem fs = new FileSystem();

        // should print []
        System.out.println(fs.ls("/"));

        fs.mkdirP("/a/b/c");
        fs.addFileWithContent("/a/b/c/d", "hello world");

        // should print [a]
        System.out.println(fs.ls("/"));

        // should print [d]
        System.out.println(fs.ls("/a/b/c"));

        // should print [d]
        System.out.println(fs.ls("/a/b/c/d"));

        // should print hello world
        System.out.println(fs.getFileContent("/a/b/c/d"));

        // My test cases
        System.out.println(fs.ls("/a"));
        System.out.println(fs.ls("/a/b"));
        System.out.println(fs.ls("/a/b/c"));
        System.out.println(fs.ls("/a/b/c/d"));

        System.out.println(fs.ls("/a/b/c/d/e"));
        System.out.println(fs.getFileContent("/a/b/c/d/e"));
    }
}
