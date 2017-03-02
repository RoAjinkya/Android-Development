package com.example.studentattendencesystem.NetworkClass;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

public class RegistrationNetwork {
	public void setadmininsert(String adminid, String fname, String lname,
			String address, String contno, String emailid, String loginid,
			String passwd) {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("Adminid", adminid));
		pList.add(new BasicNameValuePair("Fname", fname));
		pList.add(new BasicNameValuePair("Lname", lname));
		pList.add(new BasicNameValuePair("Address", address));
		pList.add(new BasicNameValuePair("Contno", contno));
		pList.add(new BasicNameValuePair("Emailid", emailid));
		pList.add(new BasicNameValuePair("Loginid", loginid));
		pList.add(new BasicNameValuePair("Passwd", passwd));

		try {

			HttpPost httpPost;
			HttpClient client = new DefaultHttpClient();
			httpPost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Register/setadminregister.php");
			System.out.println("on netcheck :" + pList);

			httpPost.setEntity(new UrlEncodedFormEntity(pList));
			HttpResponse entity = client.execute(httpPost);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	public String getadminidregister(String pass) {
	  	  ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		studLId.add(new BasicNameValuePair("id", pass));
			try {
				System.out.println("1");

				HttpPost httppost;
				System.out.println("2");

				HttpClient httpclient = new DefaultHttpClient();
				System.out.println("3");

				httppost = new HttpPost(
						"http://gopajibaba.com/StudentAttendence/Authenticate/getadminidregister.php");
				System.out.println("4");

				HttpResponse httprespnc = httpclient.execute(httppost);
				System.out.println("5");

				httppost.setEntity(new UrlEncodedFormEntity(studLId));
				System.out.println("6");

				ResponseHandler<String> resHandler = new BasicResponseHandler();
				System.out.println("7");

				final String respnc = httpclient.execute(httppost, resHandler);
				System.out.println("resp " + respnc);

				return respnc.trim();

			} catch (Exception e) {
				// TODO: handle exception
				Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
				System.out.println("Error :" + e.getMessage());

				return "error";
			}
}
	
	public String getlectid(String pass) {
	  	  ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		studLId.add(new BasicNameValuePair("id", pass));
			try {
				System.out.println("1");

				HttpPost httppost;
				System.out.println("2");

				HttpClient httpclient = new DefaultHttpClient();
				System.out.println("3");

				httppost = new HttpPost(
						"http://gopajibaba.com/StudentAttendence/Authenticate/getlectidregister.php");
				System.out.println("4");

				HttpResponse httprespnc = httpclient.execute(httppost);
				System.out.println("5");

				httppost.setEntity(new UrlEncodedFormEntity(studLId));
				System.out.println("6");

				ResponseHandler<String> resHandler = new BasicResponseHandler();
				System.out.println("7");

				final String respnc = httpclient.execute(httppost, resHandler);
				System.out.println("resp " + respnc);

				return respnc.trim();

			} catch (Exception e) {
				// TODO: handle exception
				Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
				System.out.println("Error :" + e.getMessage());

				return "error";
			}

	}
	public String getstudid(String pass) {
	  	  ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		studLId.add(new BasicNameValuePair("id", pass));
			try {
				System.out.println("1");

				HttpPost httppost;
				System.out.println("2");

				HttpClient httpclient = new DefaultHttpClient();
				System.out.println("3");

				httppost = new HttpPost(
						"http://gopajibaba.com/StudentAttendence/Authenticate/getstudregister.php");
				System.out.println("4");

				HttpResponse httprespnc = httpclient.execute(httppost);
				System.out.println("5");

				httppost.setEntity(new UrlEncodedFormEntity(studLId));
				System.out.println("6");

				ResponseHandler<String> resHandler = new BasicResponseHandler();
				System.out.println("7");

				final String respnc = httpclient.execute(httppost, resHandler);
				System.out.println("resp " + respnc);

				return respnc.trim();

			} catch (Exception e) {
				// TODO: handle exception
				Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
				System.out.println("Error :" + e.getMessage());

				return "error";
			}
}
			
	public String getallsubjectnm(String pass) {
	  	  ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		studLId.add(new BasicNameValuePair("id", pass));
			try {
				System.out.println("1");

				HttpPost httppost;
				System.out.println("2");

				HttpClient httpclient = new DefaultHttpClient();
				System.out.println("3");

				httppost = new HttpPost(
						"http://gopajibaba.com/StudentAttendence/Authenticate/getallsubjectname.php");
				System.out.println("4");

				HttpResponse httprespnc = httpclient.execute(httppost);
				System.out.println("5");

				httppost.setEntity(new UrlEncodedFormEntity(studLId));
				System.out.println("6");

				ResponseHandler<String> resHandler = new BasicResponseHandler();
				System.out.println("7");

				final String respnc = httpclient.execute(httppost, resHandler);
				System.out.println("resp " + respnc);

				return respnc.trim();

			} catch (Exception e) {
				// TODO: handle exception
				Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
				System.out.println("Error :" + e.getMessage());

				return "error";
			}
}


	public void setlectInsert(String lectid, String fname, String lname,
			String address, String contno, String emailid, String loginid,
			String passwd) {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("Lectid", lectid));
		pList.add(new BasicNameValuePair("Fname", fname));
		pList.add(new BasicNameValuePair("Lname", lname));
		pList.add(new BasicNameValuePair("Address", address));
		pList.add(new BasicNameValuePair("Contno", contno));
		// pList.add(new BasicNameValuePair("branch",branch));
		// pList.add(new BasicNameValuePair("year",year));
		pList.add(new BasicNameValuePair("Emailid", emailid));
		pList.add(new BasicNameValuePair("Loginid", loginid));
		pList.add(new BasicNameValuePair("Passwd", passwd));

		try {

			HttpPost httpPost;
			HttpClient client = new DefaultHttpClient();
			httpPost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Register/setlectinsert.php");
			System.out.println("on netcheck :" + pList);

			httpPost.setEntity(new UrlEncodedFormEntity(pList));
			HttpResponse entity = client.execute(httpPost);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void setstudInsert(String studid, String rollno, String sfname,
			String slname, String address, String contno, String branch,
			String year, String emailid, String loginid, String passwd) {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("Studid", studid));
		pList.add(new BasicNameValuePair("rolln", rollno));
		pList.add(new BasicNameValuePair("Fname", sfname));
		pList.add(new BasicNameValuePair("Lname", slname));
		pList.add(new BasicNameValuePair("Address", address));
		pList.add(new BasicNameValuePair("Contno", contno));
		pList.add(new BasicNameValuePair("branch", branch));
		pList.add(new BasicNameValuePair("year", year));
		pList.add(new BasicNameValuePair("Emailid", emailid));
		pList.add(new BasicNameValuePair("Loginid", loginid));
		pList.add(new BasicNameValuePair("Passwd", passwd));

		try {

			HttpPost httpPost;
			HttpClient client = new DefaultHttpClient();
			httpPost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Register/setstudinsert.php");
			System.out.println("on netcheck :" + pList);

			httpPost.setEntity(new UrlEncodedFormEntity(pList));
			HttpResponse entity = client.execute(httpPost);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String getlectbranchids(String branch) {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("branch", branch));

		try {
			System.out.println("1");
			HttpPost hpost;

			HttpClient hclient = new DefaultHttpClient();
			hpost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Register/getbranchid.php");
			System.out.println(hpost);

			hpost.setEntity(new UrlEncodedFormEntity(pList));

			ResponseHandler<String> handler = new BasicResponseHandler();
			final String respose = hclient.execute(hpost, handler);
			System.out.println("2");
			return respose.trim();

		} catch (Exception e) {
			System.out.println("ecxception" + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}

	public String getlectyearids(String year) {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("year", year));

		try {
			System.out.println("1");
			HttpPost hpost;

			HttpClient hclient = new DefaultHttpClient();
			hpost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Register/getyearid.php");
			System.out.println(hpost);

			hpost.setEntity(new UrlEncodedFormEntity(pList));

			ResponseHandler<String> handler = new BasicResponseHandler();
			final String respose = hclient.execute(hpost, handler);
			System.out.println("2");
			return respose.trim();

		} catch (Exception e) {
			System.out.println("ecxception" + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}

	public void setstudBranchIdInsert(String studid,String branch,String year)
	{
		ArrayList<NameValuePair> pList=new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("studid",studid));
		pList.add(new BasicNameValuePair("branch",branch));
		pList.add(new BasicNameValuePair("yearid",year));
		
		try
		{
			
			HttpPost httpPost;
			HttpClient client=new DefaultHttpClient();
			httpPost=new HttpPost("http://gopajibaba.com/StudentAttendence/Register/setstudinsertbranchyearids.php");
			System.out.println("on netcheck :" + pList);

			httpPost.setEntity(new UrlEncodedFormEntity(pList));
			HttpResponse entity = client.execute(httpPost);
		}catch (Exception e) {
			// TODO: handle exception
		}

	}	
	
	public void setlectBranchIdInsert(String lectid, String branch, String year) {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("lectid", lectid));

		pList.add(new BasicNameValuePair("branch", branch));

		pList.add(new BasicNameValuePair("yearid", year));

		try {

			HttpPost httpPost;
			HttpClient client = new DefaultHttpClient();
			httpPost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Register/setinsertlectbranchids.php");
			System.out.println("on netcheck :" + pList);

			httpPost.setEntity(new UrlEncodedFormEntity(pList));
			HttpResponse entity = client.execute(httpPost);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void setlectYearIdInsert(String year) {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("id", year));

		try {

			HttpPost httpPost;
			HttpClient client = new DefaultHttpClient();
			httpPost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Register/setyearid.php");
			System.out.println("on netcheck :" + pList);

			httpPost.setEntity(new UrlEncodedFormEntity(pList));
			HttpResponse entity = client.execute(httpPost);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void setstudYearIdInsert(String year) {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("id", year));

		try {

			HttpPost httpPost;
			HttpClient client = new DefaultHttpClient();
			httpPost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Register/setstudyearid.php");
			System.out.println("on netcheck :" + pList);

			httpPost.setEntity(new UrlEncodedFormEntity(pList));
			HttpResponse entity = client.execute(httpPost);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public String getAdminids() {

		try {
			System.out.println("1");
			HttpPost hpost;

			HttpClient hclient = new DefaultHttpClient();
			hpost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Register/getmaxid.php");
			System.out.println(hpost);

			ResponseHandler<String> handler = new BasicResponseHandler();
			final String respose = hclient.execute(hpost, handler);
			System.out.println("2");
			return respose.trim();

		} catch (Exception e) {
			System.out.println("ecxception" + e.getMessage());
			e.printStackTrace();
			return "error";
		}

	}

	public String getlectids() {

		try {
			System.out.println("1");
			HttpPost hpost;

			HttpClient hclient = new DefaultHttpClient();
			hpost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Register/getlectids.php");
			System.out.println(hpost);

			ResponseHandler<String> handler = new BasicResponseHandler();
			final String respose = hclient.execute(hpost, handler);
			System.out.println("2");
			return respose.trim();

		} catch (Exception e) {
			System.out.println("ecxception" + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}

	public String getstudids() {

		try {
			System.out.println("1");
			HttpPost hpost;

			HttpClient hclient = new DefaultHttpClient();
			hpost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Register/getstudids.php");
			System.out.println(hpost);

			ResponseHandler<String> handler = new BasicResponseHandler();
			final String respose = hclient.execute(hpost, handler);
			System.out.println("2");
			return respose.trim();

		} catch (Exception e) {
			System.out.println("ecxception" + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}

	public String getstudrollno() {
		try {
			System.out.println("1");
			HttpPost hpost;

			HttpClient hclient = new DefaultHttpClient();
			hpost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Register/getrollno.php");
			System.out.println(hpost);
			ResponseHandler<String> handler = new BasicResponseHandler();
			final String respose = hclient.execute(hpost, handler);
			System.out.println("2");
			return respose.trim();
		} catch (Exception e) {
			System.out.println("ecxception" + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}

	public String getadminlogin() {
		try {
			System.out.println("1");
			HttpPost hpost;

			HttpClient hclient = new DefaultHttpClient();
			hpost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Register/getadminloginid.php");
			System.out.println(hpost);
			ResponseHandler<String> handler = new BasicResponseHandler();
			final String respose = hclient.execute(hpost, handler);
			System.out.println("2");
			return respose.trim();
		} catch (Exception e) {
			System.out.println("ecxception" + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}

	public String getadminpasswd() {
		try {
			System.out.println("1");
			HttpPost hpost;

			HttpClient hclient = new DefaultHttpClient();
			hpost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Register/getadminloginpasswd.php");
			System.out.println(hpost);
			ResponseHandler<String> handler = new BasicResponseHandler();
			final String respose = hclient.execute(hpost, handler);
			System.out.println("2");
			return respose.trim();
		} catch (Exception e) {
			System.out.println("ecxception" + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}

	public String getuserpasswd(String pass) {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("login", pass));

		try {

			HttpPost httpPost;
			System.out.println("1");
			HttpClient client = new DefaultHttpClient();
			System.out.println("2");

			httpPost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Register/getadminloginpasswd.php");
			System.out.println("3");

			System.out.println("on netcheck :" + pList);

			HttpResponse entity = client.execute(httpPost);
			System.out.println("4");

			httpPost.setEntity(new UrlEncodedFormEntity(pList));
			System.out.println("5");

			ResponseHandler<String> handler = new BasicResponseHandler();
			System.out.println("6");

			final String respose = client.execute(httpPost, handler);
			System.out.println("7");
			return respose.trim();

		} catch (Exception e) {
			// TODO: handle exception
			return "error";
		}

	}

	public String getstudpasswd(String pass) {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("login", pass));

		try {

			HttpPost httpPost;
			System.out.println("1");
			HttpClient client = new DefaultHttpClient();
			System.out.println("2");

			httpPost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Register/getstudpasswd.php");
			System.out.println("3");

			System.out.println("on netcheck :" + pList);

			HttpResponse entity = client.execute(httpPost);
			System.out.println("4");

			httpPost.setEntity(new UrlEncodedFormEntity(pList));
			System.out.println("5");

			ResponseHandler<String> handler = new BasicResponseHandler();
			System.out.println("6");

			final String respose = client.execute(httpPost, handler);
			System.out.println("7");
			return respose.trim();

		} catch (Exception e) {
			// TODO: handle exception
			return "error";
		}

	}

	public String getlectpasswd(String pass) {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("login", pass));

		try {

			HttpPost httpPost;
			System.out.println("1");
			HttpClient client = new DefaultHttpClient();
			System.out.println("2");

			httpPost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Register/getlecturerpasswd.php");
			System.out.println("3");

			System.out.println("on netcheck :" + pList);

			HttpResponse entity = client.execute(httpPost);
			System.out.println("4");

			httpPost.setEntity(new UrlEncodedFormEntity(pList));
			System.out.println("5");

			ResponseHandler<String> handler = new BasicResponseHandler();
			System.out.println("6");
			final String respose = client.execute(httpPost, handler);
			System.out.println("7");
			return respose.trim();

		} catch (Exception e) {
			// TODO: handle exception
			return "error";
		}

	}

	public String getBranchName() {
		try {
			System.out.println("1");
			HttpPost hpost;
			HttpClient hclient = new DefaultHttpClient();
			hpost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Register/getbranchname.php");

			ResponseHandler<String> handler = new BasicResponseHandler();
			final String respose = hclient.execute(hpost, handler);
			System.out.println("2");
			return respose.trim();
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String getyearName() {
		try {
			System.out.println("1");
			HttpPost hpost;
			HttpClient hclient = new DefaultHttpClient();
			hpost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Register/getyearname.php");

			ResponseHandler<String> handler = new BasicResponseHandler();
			final String respose = hclient.execute(hpost, handler);
			System.out.println("2");
			return respose.trim();
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}
	public String getLogindata(String pass) {
	  	  ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
			studLId.add(new BasicNameValuePair("loginid", pass));
			try {

				HttpPost httppost;
				HttpClient httpclient = new DefaultHttpClient();
				httppost = new HttpPost(
						"http://gopajibaba.com/StudentAttendence/Register/getadminloginid.php");

				HttpResponse httprespnc = httpclient.execute(httppost);

				httppost.setEntity(new UrlEncodedFormEntity(studLId));
				ResponseHandler<String> resHandler = new BasicResponseHandler();
				final String respnc = httpclient.execute(httppost, resHandler);
				System.out.println("resp " + respnc);

				return respnc.trim();

			} catch (Exception e) {
				// TODO: handle exception
				Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
				System.out.println("Error :" + e.getMessage());

				return "error";
			}
}

	 public String getLogindatalect(String pass) {
      	  ArrayList<NameValuePair> lectid = new ArrayList<NameValuePair>();
    		lectid.add(new BasicNameValuePair("loginid", pass));
    		try {

    			HttpPost httppost;
    			HttpClient httpclient = new DefaultHttpClient();
    			httppost = new HttpPost(
    					"http://gopajibaba.com/StudentAttendence/Register/getlectloginid.php");

    			HttpResponse httprespnc = httpclient.execute(httppost);

    			httppost.setEntity(new UrlEncodedFormEntity(lectid));
    			ResponseHandler<String> resHandler = new BasicResponseHandler();
    			final String respnc = httpclient.execute(httppost, resHandler);
    			System.out.println("resp " + respnc);

    			return respnc.trim();

    		} catch (Exception e) {
    			// TODO: handle exception
    			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
    			System.out.println("Error :" + e.getMessage());

    			return "error";
    		}
}

	     public String getLogindatastud(String pass) {
      	  ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
    		studLId.add(new BasicNameValuePair("loginid", pass));
    		try {

    			HttpPost httppost;
    			HttpClient httpclient = new DefaultHttpClient();
    			httppost = new HttpPost(
    					"http://gopajibaba.com/StudentAttendence/Register/getloginidstud.php");

    			HttpResponse httprespnc = httpclient.execute(httppost);

    			httppost.setEntity(new UrlEncodedFormEntity(studLId));
    			ResponseHandler<String> resHandler = new BasicResponseHandler();
    			final String respnc = httpclient.execute(httppost, resHandler);
    			System.out.println("resp " + respnc);

    			return respnc.trim();

    		} catch (Exception e) {
    			// TODO: handle exception
    			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
    			System.out.println("Error :" + e.getMessage());

    			return "error";
    		}

}

}
