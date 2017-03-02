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

public class ExamInfoNetwork {
	public void setStudentAttendance(String examId, String studId,
			String branch, String year, String date, String marks,
			String outoff, String percentage) {
		ArrayList<NameValuePair> studMarks = new ArrayList<NameValuePair>();
		studMarks.add(new BasicNameValuePair("ExamId", examId));
		studMarks.add(new BasicNameValuePair("StudID", studId));
		studMarks.add(new BasicNameValuePair("EBranch", branch));
		studMarks.add(new BasicNameValuePair("EYear", year));
		studMarks.add(new BasicNameValuePair("Date", date));
		studMarks.add(new BasicNameValuePair("Marks", marks));
		studMarks.add(new BasicNameValuePair("Outoff", outoff));
		studMarks.add(new BasicNameValuePair("Percentage", percentage));
		System.out.println(studMarks);
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Examinfo/setStudentMarks.php");
			httppost.setEntity(new UrlEncodedFormEntity(studMarks));
			HttpResponse respnc = httpclient.execute(httppost);

		} catch (Exception e) {
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());
		}

	}

	public String getExamName() {
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Examinfo/getExamName.php");
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

	public String getStudentBranchYearId(String sId) {

		ArrayList<NameValuePair> studId = new ArrayList<NameValuePair>();
		studId.add(new BasicNameValuePair("Studentid", sId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Examinfo/getStudentBranchYear.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(studId));
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

	public String getExamInfo(String eId, String sId) {

		ArrayList<NameValuePair> esId = new ArrayList<NameValuePair>();
		esId.add(new BasicNameValuePair("StudId", sId));
		esId.add(new BasicNameValuePair("ExamID", eId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Examinfo/getSxamINFO.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(esId));
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
	
	
	
	
	
	public String checkStudentExam(String sId, String eId) {

		ArrayList<NameValuePair> esId = new ArrayList<NameValuePair>();
		esId.add(new BasicNameValuePair("StudentID", sId));
		esId.add(new BasicNameValuePair("ExamID", eId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Examinfo/checkStudnetMarksExist.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(esId));
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

	public String getStudentName(String sId) {

		ArrayList<NameValuePair> esId = new ArrayList<NameValuePair>();
		esId.add(new BasicNameValuePair("SID", sId));

		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Examinfo/getStudentName.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(esId));
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
	public String getStudBranchName(String bId) {

		ArrayList<NameValuePair> esId = new ArrayList<NameValuePair>();
		esId.add(new BasicNameValuePair("BID", bId));

		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Examinfo/getStudBranchName.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(esId));
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
	
	
	public String getStudYearName(String yId) {

		ArrayList<NameValuePair> esId = new ArrayList<NameValuePair>();
		esId.add(new BasicNameValuePair("YID", yId));

		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Examinfo/getStudYearName.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(esId));
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
