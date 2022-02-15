import java.io.*;

import java.util.ArrayList;

public class ReadExcel {
    private ArrayList<String> dataList() throws IOException {
        String filePath = "/Users/wangmengkai/Desktop/CSAIR/MENG信息/sourceFile.csv";
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String cvsSplitBy = ",";
        String line = "";
        ArrayList<String> list = new ArrayList<String>();
        while ((line = bufferedReader.readLine()) != null) {
            String data = line.split(cvsSplitBy)[0];
            list.add(data);
        }
        return list;
    }

    public void copyFile() throws IOException {
        String filePath = "/Users/wangmengkai/Desktop/CSAIR/MENG信息";
        String sourceFilePath = "/Users/wangmengkai/CSAIR/MENG需求文档/06系统上线/工程数据移植/EO原文件";
        File file = new File(sourceFilePath);
        File[] files = file.listFiles();
        ArrayList<String> folderPath = new ArrayList<String>();
        File endFileDir = new File(filePath + "/copyFiles/");
        ArrayList<String> dataList = dataList();
        ArrayList<String> fileNameList = new ArrayList<String>();
        if (!endFileDir.exists()) {
            endFileDir.mkdirs();
        }

        for (File f : files) {
            String path = sourceFilePath + "/" + f.getName();
            folderPath.add(path);
        }
        for (String s : folderPath) {
            File eoFiles = new File(s);
            if(eoFiles.listFiles() == null){
                System.out.println(s+" 不存在文件");
                continue;
            }
            for (File f : eoFiles.listFiles()) {
                for (String name : dataList) {
                    if (f.getName().equals(name)) {
                        copy(s+"/"+f.getName(),filePath + "/copyFiles/"+f.getName());
                        fileNameList.add(f.getName());
                    }
                }

            }
        }
        for(String s:fileNameList){
            dataList.remove(s);
        }
        System.out.println(dataList);
    }

    private void copy(String source, String target) throws IOException {
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(target);
        byte data[] = new byte[1024 * 8];
        int len = 0;
        while ((len = fis.read(data)) != -1) {
            fos.write(data, 0, len);
        }
        fis.close();
        fis.close();
    }
}

