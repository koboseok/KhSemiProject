
package com.kh.semi.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override public File rename(File originalFile) {
 
 // 업로드된 시간을 파일명에 작성 + 5자리 랜덤 숫자 추가 // 20200107101311_12345.jpg
  
  long currentTime = System.currentTimeMillis();
  
  SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
  
  int random = (int)(Math.random() * 100000); // 5자리 난수 // 0 부터 99999 까지의 난수 발생
  
  String str = "_" + String.format("%05d", random); // %d : 정수 // %5d : 5칸  오른쪽정렬된 정수 
  // %05d : 5칸 오른쪽 정렬된 정수 , 빈칸에는 0을 채워넣어라
 
  // 파일명을 변경해도 확장자는 유지 되어야 하므로 // 업로드된 원본 파일의 확장자 부분만 얻어오기
  
  int dot = originalFile.getName().lastIndexOf("."); // 원본 파일명에서 제일 마지막 "."의위치를 얻어온다. , 없으면 -1이 반환된다.
 
 String ext = ""; // 확장자를 저장할 변수
  
 if(dot != -1) { ext = originalFile.getName().substring(dot); // hello.jpg - >. 은 5번 인덱스 dot에는 5가 대입 hello는 버리고 .jpg는 ext에 저장된다.
  
  } String fileName = ft.format(new Date(currentTime)) + str + ext; //20210107102655_random5.png
  
  
  
  
  return new File(originalFile.getParent(), fileName); }

}
