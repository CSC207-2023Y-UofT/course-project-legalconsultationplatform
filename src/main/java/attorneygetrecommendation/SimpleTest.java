package attorneygetrecommendation;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.URLUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.*;
import java.util.regex.Pattern;


public class SimpleTest {


    private Map add(List<Integer> list) throws JsonProcessingException{

        //参数转json字符串
        ObjectMapper objectMapper = new ObjectMapper();

        String s = objectMapper.writeValueAsString(list);

        Process proc;
        try {
            //执行python脚本
            String command = "python lib/test.py --aa=" + s;
            proc = Runtime.getRuntime().exec(command);
            //等待程序执行完毕再统一打印
            int exitCode = proc.waitFor();
            //获取python脚本的输出
            String s1 = IOUtils.toString(proc.getInputStream());
            System.out.println(s1);
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            String errorLine;
            StringBuilder errorOutput = new StringBuilder();
            while ((errorLine = errorReader.readLine()) != null) {
                errorOutput.append(errorLine).append("\n");
            }
            if (errorOutput.length() > 0) {
                System.err.println("Errors from Python script: " + errorOutput.toString());
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = reader.readLine();
            System.out.println("Captured output: " + line);

            Map map = objectMapper.readValue(s1, Map.class);
            proc.destroy();
            return map;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("error");
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        try {
            System.out.println(new SimpleTest().add(list));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
