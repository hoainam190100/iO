package tuDien2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller_TD {

    public HashMap readFile() {
        try {
            URL url = new URL("file:/Users/admin/MOUDEL2/anhviet.txt");
            HashMap<String, String> hashMapEnglist = new HashMap<>();
            Scanner scanner = new Scanner(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            scanner.useDelimiter("\\Z");
            String content = scanner.next();
            scanner.close();

            content = content.replaceAll("\\n", ";");

            Pattern p = Pattern.compile("@(.*?) /(.*?);;");
            Matcher m = p.matcher(content);
            while (m.find()) {
                hashMapEnglist.put(m.group(1), m.group(2));
            }
            return hashMapEnglist;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void writeFile_GhiDe(HashMap<String, String> hashMap) throws IOException {
        File file = new File("anhviet.txt");
        FileWriter fileWriter = new FileWriter(file);
        for (HashMap.Entry<String, String> entry : hashMap.entrySet()) {
            fileWriter.write("@"+entry.getKey()+" /" + entry.getValue()+"\n\n");
        }

    }

    public String TimKiemTuGanD(HashMap<String, String> hashMap, String english) {
        Pattern pattern = Pattern.compile(english);
        Set<String> setHasdM = hashMap.keySet();
        for (String x : setHasdM) {
            Matcher matcher = pattern.matcher(x);
            if (matcher.find()) {
                return hashMap.get(x);
            }
        }
        return null;
    }

    public String TimKiemChinhXac(HashMap<String, String> hashMap, String english) {
        Set<String> setHasdM = hashMap.keySet();
        for (String x : setHasdM) {
            if (x.equals(english)){
                return hashMap.get(english);
            }
        }
        return null;
    }

    public void ThemTu(HashMap<String, String> hashMap, String key, String value) {
        hashMap.put(key, value);
    }

    public boolean isCheck_Key(HashMap<String, String> hashMap, String key) {
        for (HashMap.Entry<String, String> entry : hashMap.entrySet()) {
            if (entry.getKey().equals(key)) {
                return false;
            }
        }
        return true;
    }

    public void delete(HashMap<String, String> hashMap, String key) {
        hashMap.remove(key);
    }

    public void edit(HashMap<String, String> hashMap, String valuenew,String key){
        hashMap.replace(key,valuenew);
    }

}



