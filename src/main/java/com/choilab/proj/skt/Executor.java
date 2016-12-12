package com.choilab.proj.skt;

import javax.swing.SwingUtilities;

import com.choilab.proj.skt.Configure.ConfigureManager;
import com.choilab.proj.skt.Job.JobManager;

public class Executor {
	public static void main(String[] args) {
		// 1. configure xml 파싱을 통해 load
		ConfigureManager cm = new ConfigureManager();

		// 2. UI를 통해 configure 수정
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ConfigUI();
			}
		});

		// 2-1. 수정된 configure를 xml 파일로 업데이트
		cm.writeXML(true, 1, 4); // @@@

		// 3. Server side init
		// 3-1. Container run 한 후, Server conatainer ip 얻어오기
		// 3-2. mysql table

		// 4. Client side init
		// 4-1. Thread pool 생성
		// 4-2. Configure에 정의된 만큼 Container 생성
		// 4-3. Thread당 하나의 Container 매칭

		// 5. JobScheduler
		JobManager jm = new JobManager(); // input @@@
	}
}
