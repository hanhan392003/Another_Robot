package org.usfirst.frc.team6520.robot.subsystems;

import java.util.ArrayList;
import java.util.List;


import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team6520.robot.RobotMap;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.*;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_Vision extends Subsystem {

	public Thread m_visionThread;
	double centerX = 0, centerY = 0;
	public double averageSize;
	boolean centered;
	public boolean paused;
	Preferences prefs = Preferences.getInstance();
	public double centerX_final = 0;
	public double centerY_final = 0;
	public double coeff = 1;
	public double olddistance = 0;
	public double height = 0;
	public double width = 0;
	public double distance_final = 0;
	public double distance2 = 0;
	public double distance3 = 0;

	public void vision() {
		m_visionThread = new Thread(() -> {
		// khởi tạo camera và đặt resolution
		// resolution khá là quan trọng vì hệ thống điều khiển sân đấu có
		// giới hạn về bandwidth nên cần để dữ liệu thấp
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(640, 360);
		camera.setExposureManual(10);
		camera.setFPS(30);

		// khởi tạo CvSink là một vật thể host video từ cam
		CvSink cvSink = CameraServer.getInstance().getVideo();
		// khởi tạo vật thể để output processed feed đến smartdashboard
		CvSource outputStream = CameraServer.getInstance().putVideo("Vision", 320, 240);

		Mat mat = new Mat();
		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));

		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		List<RotatedRect> boundRect = new ArrayList<RotatedRect>();
		List<Rect> boundingrect = new ArrayList<Rect>();
		
		Mat hierarchy = new Mat();

		int R = prefs.getInt("R", 0); // màu đỏ
		int G = prefs.getInt("G", 0); // màu lục
		int B = prefs.getInt("B", 0); // màu xanh lam
		int RE = prefs.getInt("RE", 20); // khoảng của đỏ
		int GE = prefs.getInt("GE", 20); // khoảng của lục
		int BE = prefs.getInt("BE", 20); // khoảng của xanh

		SmartDashboard.putNumber("R", R);
		SmartDashboard.putNumber("G", G);
		SmartDashboard.putNumber("B", B);
		SmartDashboard.putNumber("RE", RE);
		SmartDashboard.putNumber("GE", GE);
		SmartDashboard.putNumber("BE", BE);

		while (!Thread.interrupted()) {
				centerX_final = 0;
				centerY_final = 0;
				height = 0;
				width = 0;
				distance_final = 0;
				distance2 = 0;
				distance3 = 0;

				contours.removeAll(contours);
				boundRect.removeAll(boundRect);
				boundingrect.removeAll(boundingrect);

				prefs.putInt("R", R);
				prefs.putInt("G", G);
				prefs.putInt("B", B);
				prefs.putInt("RE", RE);
				prefs.putInt("GE", GE);
				prefs.putInt("BE", BE);

				if (cvSink.grabFrame(mat) == 0) {
					outputStream.notifyError(cvSink.getError());
					continue;
				}

				R = (int) SmartDashboard.getNumber("R", 255);
				G = (int) SmartDashboard.getNumber("G", 255);
				B = (int) SmartDashboard.getNumber("B", 255);
				RE = (int) SmartDashboard.getNumber("RE", 20);
				GE = (int) SmartDashboard.getNumber("GE", 20);
				BE = (int) SmartDashboard.getNumber("BE", 20);

				Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2HSV);
				Core.inRange(mat, new Scalar(R - RE, G - GE, B - BE), new Scalar(R + RE, G + GE, B + BE), mat);
				Imgproc.dilate(mat, mat, kernel);
				Imgproc.findContours(mat, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);

				if (!paused){
				//area qualification
				for (int i = 0; i < contours.size(); i++) {

					Rect newrect = Imgproc.boundingRect(contours.get(i));
					if (newrect.size().area() > 100){
						boundingrect.add(newrect);
					}

					MatOfPoint2f dst = new MatOfPoint2f();  
					contours.get(i).convertTo(dst, CvType.CV_32F);

					RotatedRect rect = Imgproc.minAreaRect(dst);
					if (rect.size.area() > 100){
							boundRect.add(rect);
						// if (boundRect.get(i).size.area() > 300 ) {
							Imgproc.putText(mat, Math.round(rect.angle)+"", new Point(rect.center.x, rect.center.y-30), 0 , 0.5 , new Scalar(255,255,255));
							Imgproc.putText(mat, Math.round(rect.size.area())+"", new Point(rect.center.x, rect.center.y+50), 0 , 0.5 , new Scalar(255,255,255));
							Imgproc.putText(mat, Math.round(rect.center.x)+"", new Point(rect.center.x, rect.center.y+80), 0 , 0.5 , new Scalar(255,255,255));							
							// Imgproc.putText(mat, i+"", new Point(Imgproc.minAreaRect(dst).center.x,Imgproc.minAreaRect(dst).center.y-80), 0 , 0.5 , new Scalar(255,255,255));
						}
					}

				//sort by x
				for (int i = boundRect.size(); i > 0; i--){
					// Imgproc.putText(mat, i - 1 + "", new Point(boundRect.get(i - 1).center.x,boundRect.get(i - 1).center.y-50), 0 , 0.5 , new Scalar(255,255,255));
					
					for (int j = 0; j < i - 1; j++){
						if (boundRect.get(j).center.x > boundRect.get(j + 1).center.x){
							RotatedRect mid = boundRect.get(j);
							boundRect.set(j, boundRect.get(j + 1));
							boundRect.set(j + 1, mid);	
						}
						if (boundingrect.get(j).x > boundingrect.get(j+1).x){
							Rect middle = boundingrect.get(j);
							boundingrect.set(j, boundingrect.get(j + 1));
							boundingrect.set(j + 1, middle);
						}
					}
				}	

				for (int i = 0; i < boundRect.size() - 1; i++) {
						if (boundRect.get(i).angle < boundRect.get(i+1).angle && Math.round(boundRect.get(i).angle)!=-90.00) {
							centerX = Math.round((boundRect.get(i).center.x+boundRect.get(i+1).center.x)/2);
							centerY = Math.round((boundRect.get(i).center.y+boundRect.get(i+1).center.y)/2);
							if (Math.abs(320-centerX) < Math.abs(320-centerX_final)){
							centerX_final = centerX;
							centerY_final = centerY;
							height = boundingrect.get(i).height;
							width = boundingrect.get(i).width;
							// distance1 = 2.3/Math.tan(Math.toRadians(width*640/70.42));
							// distance2 = 15*70.42/(2*height*Math.tan(Math.toRadians(70.42)));
							distance3 = (6.5 * 640) / (2 * height * Math.tan(70.42/2)) * 2.54;
							distance2 = 7000/height;
							distance_final = distance2 + (distance2-distance3)*2.45;
							}

							Imgproc.putText(mat, i+"", new Point(boundingrect.get(i).x, boundingrect.get(i).y-100), 0, 0.5, new Scalar(255,255,255));
							Imgproc.putText(mat, "*", new Point(centerX_final,centerY_final), 0 , 0.5 , new Scalar(255,255,255));
							centered = true;
							averageSize = (boundRect.get(i).size.area() + boundRect.get(i + 1).size.area())/2;
						}
						if (!centered){
							averageSize = -1;
						}
				}

				
				SmartDashboard.putNumber("height", height);
				SmartDashboard.putNumber("distance1", distance_final);
				SmartDashboard.putNumber("distance2", distance2);
				SmartDashboard.putNumber("distance3", distance3);
				SmartDashboard.putNumber("distance_final", Math.round(distance_final));
				SmartDashboard.putNumber("centerX_final", centerX);
				SmartDashboard.putNumber("centerY_final", centerY);
				SmartDashboard.putBoolean("Paused?", paused);
				SmartDashboard.putString("Coordinates", centerX + ", " + centerY);
				SmartDashboard.putBoolean("Centered?", centered);
				SmartDashboard.putNumber("Average Size", averageSize);
				}
				else {
					averageSize = -1;
					// RobotMap.m_left.set(0);
					// RobotMap.m_right.set(0);
				}
				outputStream.putFrame(mat);

				boolean turn = false;
				double distanceP = 0.0095;
				double centerError = 320 - centerX_final;
				double centerP = 0.00115;
				if (distance_final>300){
					RobotMap.m_left.set(-0.9 + centerError*centerP);
					RobotMap.m_right.set(-0.9 - centerError*centerP);
				}
				else if (distance_final > 109.5 && distance_final <300){
					double distanceError = distance_final - 109.5;	
					RobotMap.m_left.set(-0.4 * distanceError * distanceP + centerError*centerP);
					RobotMap.m_right.set(-0.4 * distanceError * distanceP - centerError*centerP);
					SmartDashboard.putNumber("left",-0.5 * distanceError * distanceP + centerError*centerP);
					SmartDashboard.putNumber("right",-0.5 * distanceError * distanceP - centerError*centerP);
				}
				else {
					RobotMap.m_left.set(0);
					RobotMap.m_right.set(0);
					turn = true;
				}
			}
		});
		m_visionThread.setDaemon(true);
		m_visionThread.start();
		// R: 70, RE: 50
		// G: 200, GE: 150
		// B: 200, BE: 75
	}
	
	public void TurnToCenter(int tolerance){
		double LOWPower = -0.5;
		double HIGHPower = -0.7;
		double distanceP = 0.00175;
		if (!paused){
			if (averageSize < 1000 && averageSize != -1){
				double distanceError = 950- averageSize;
				if (centerX_final < 320 - tolerance){
					RobotMap.m_left.set(LOWPower * distanceError * distanceP);
					RobotMap.m_right.set(HIGHPower * distanceError * distanceP);
				}
				else if (centerX_final > 320 + tolerance) {
					RobotMap.m_left.set(HIGHPower * distanceError * distanceP);
					RobotMap.m_right.set(LOWPower * distanceError * distanceP);
				}
				else {
					RobotMap.m_left.set(LOWPower * distanceError * distanceP);
					RobotMap.m_right.set(LOWPower * distanceError * distanceP);
				}	
			}
			else {
				RobotMap.m_left.stopMotor();
				RobotMap.m_right.stopMotor();
			}
		}	
	}

	@Override
	protected void initDefaultCommand() {

	}
	
}