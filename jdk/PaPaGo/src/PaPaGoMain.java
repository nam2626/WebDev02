import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class PaPaGoMain {
	/**
	 * 
	 * @param target 번역할 언어 코드
	 * @param text 번역할 내용
	 * @return 번역 결과
	 */
	public static String papagoTranslate(String target, String text) {
		String clientId = "9IEElpSYKwxvuax9FsIh";
		String clientSecret = "bBrxBHQPu0";
		String apiUrl = "https://openapi.naver.com/v1/papago/n2mt";
		String msg = null;//번역결과
		HttpURLConnection con = null;
		DataOutputStream dos = null;
		BufferedReader br = null;
		
		try {
			text = URLEncoder.encode(text,"UTF-8");
			//url 연결
			URL url = new URL(apiUrl);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			con.setDoOutput(true);
			
			//데이터 전송
			dos = new DataOutputStream(con.getOutputStream());
			String postParams = "source=ko&target="+target+"&text="+text;
			dos.writeBytes(postParams);
			dos.flush();
			dos.close();
			//응답
			int responseCode = con.getResponseCode(); //응답 코드 받음
			if(responseCode == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			msg = new String();
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		return msg;
	}
	
	
	public static void main(String[] args) {
		
	}

}




