package org.zjn.myplant.test;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ModelTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		String exe = "python";
        String command = "C:\\Users\\s3\\Desktop\\new.py";
        String num1 = "hello";
        String num2 = "hi";
        String[] cmdArr = new String[4];
        cmdArr[0] = exe;
        cmdArr[1] = command;
        cmdArr[2] = num1;
        cmdArr[3] = num2;
        Process process;
        String result;
        try {
			process = Runtime.getRuntime().exec(cmdArr);
			InputStream is = process.getInputStream();
	        DataInputStream dis = new DataInputStream(is);
	        while ((result = dis.readLine()) != null) {  
	              System.out.println(result);  
	          }  
	          dis.close();  
	          int re = process.waitFor();  
	          System.out.println(re);
	          
		} catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		String exe = "python";
        String command = "src/main/resources/model/predict_evaporation.py";
		//String command = "C:\\Users\\s3\\Desktop\\predict_evaporation.py";
        String num1 = "12";
        String num2 = "22.23";
        String num3 = "31.75";
        String num4 = "0.03";
        String num5 = "1.67";
        String num6 = "511.3";
        String num7 = "729.92";
        String num8 = "2";
        String num9 = "3";
        String num10 = "3";
        String num11 = "1";
        String num12 = "66";
        String[] cmdArr = new String[14];
        cmdArr[0] = exe;
        cmdArr[1] = command;
        cmdArr[2] = num1;
        cmdArr[3] = num2;
        cmdArr[4] = num3;
        cmdArr[5] = num4;
        cmdArr[6] = num5;
        cmdArr[7] = num6;
        cmdArr[8] = num7;
        cmdArr[9] = num8;
        cmdArr[10] = num9;
        cmdArr[11] = num10;
        cmdArr[12] = num11;
        cmdArr[13] = num12;
        Process process;
        String result;
        try {
			process = Runtime.getRuntime().exec(cmdArr);
			
			FileInputStream errorStream = (FileInputStream)process.getErrorStream();
            InputStreamReader isr = new InputStreamReader(errorStream,"gbk");//∂¡»°
            System.out.println(isr.getEncoding());
            BufferedReader bufr = new BufferedReader(isr);//ª∫≥Â
            String line = null;
            while((line =bufr.readLine())!=null) {
                System.out.println(line);
            }
            isr.close();
			
			InputStream is = process.getInputStream();
	        DataInputStream dis = new DataInputStream(is);
	        while ((result = dis.readLine()) != null) {  
	              System.out.println(result);  
	          }  
	          dis.close();  
	          int re = process.waitFor();  
	          System.out.println(re);
	          
		} catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
