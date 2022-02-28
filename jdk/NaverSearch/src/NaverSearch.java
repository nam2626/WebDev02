import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

public class NaverSearch {
	/**
	 * 네이버 블로그 검색 결과를 받음 
	 * @param text 검색어
	 * @return 검색결과 title link description
	 */
	public static String search(String text) {
		String result = null;
		String clientId = "9IEElpSYKwxvuax9FsIh";
		String clientSecret = "bBrxBHQPu0";
		String apiUrl = "https://openapi.naver.com/v1/search/blog.json";
		HttpURLConnection con = null;
		BufferedReader br = null;
		
		try {
			text = URLEncoder.encode(text, "UTF-8");
			URL url = new URL(apiUrl+"?query="+text);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			con.setDoOutput(true);
			
			int responseCode = con.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			result = new String();
			
			while (true) {
				String str = br.readLine();
				if(str == null) break;
				result += str;
			}
			JSONObject json = new JSONObject(result);
			JSONArray arr = json.getJSONArray("items");
			result = "";
			for(int i=0;i<arr.length();i++) {
				JSONObject obj = (JSONObject) arr.get(i);
				result += obj.getString("title") + "\t" + obj.getString("bloggername") + "\t"
						+ obj.getString("link") + "\n";
			}
			System.out.println(result);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("검색어 입력 : ");
		//검색어 입력
		String text = sc.nextLine();
		search(text);
	}

}
