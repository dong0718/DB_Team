package service;

import persistence.DAO.RegistDAO;
import persistence.DTO.AppliedregistDTO;
import persistence.DTO.CreatedsubjectDTO;
import persistence.DTO.StudentDTO;

import java.time.LocalTime;
import java.util.List;

public class RegistService {
    private final RegistDAO registDAO;
    public RegistService(RegistDAO registDAO){this.registDAO=registDAO;}

    public String check_student(String id,String password){
        String count=registDAO.check_student_dao(id,password);
        return count;
    }
    public void regist(String id,String password){
        String check=check_student(id,password);
        if(check.equals("0")){
            System.out.println("id또는 비밀번호가 틀렸습니다");
            return;
        }
        else
        {
            System.out.println("--------------");
            System.out.println("학생 로그인 성공");
            System.out.println("--------------");
            //개설 과목 정보 출력 dao
            List<CreatedsubjectDTO> list=registDAO.get_all_created_subject();
            System.out.println("-----개설 과목 목록--------");
            for(CreatedsubjectDTO csubject:list) {
                System.out.print("과목이름:"+csubject.getCreatedsubname()+" ");
                System.out.print("과목코드:"+csubject.getCreatedsubcode()+" ");
                System.out.print("현재수강신청인원:"+csubject.getStumax()+" ");
                System.out.print("강의시작시간:"+csubject.getClassstarttime()+" ");
                System.out.println();
            }
            System.out.println("---------------------------");
            //신청 과목 선택(선택했다고 가정 하드코딩)
            System.out.println("학생이 CS0016 선택했다고 가정");
            String selectsubject="CS0016";//현재 선택한 교과목 코드
            System.out.println("---------------------------");
            //선택한 교과목 시작시간이 현재 시간표시작시간과 겹치는게 있는지 확인
            boolean flag=true;
            CreatedsubjectDTO getsubject=registDAO.get_one_by_created_code(selectsubject);//현재선택과목
            StudentDTO getstudent=registDAO.get_student_by_id_password(id, password);//현재학생

            int stunum=getstudent.getStunum();//현재학생 학번
            List<AppliedregistDTO> studentapplylist= registDAO.get_same_with_stunum(stunum);//현재학생 수강 리스트
            LocalTime cursubstarttime=getsubject.getClassstarttime();//현재선택과목 시간
            for (AppliedregistDTO one:studentapplylist) {
                String onesubname=one.getFcreatedsubcode();
                CreatedsubjectDTO onesubject= registDAO.get_one_by_created_code(onesubname);
                if(cursubstarttime==onesubject.getClassstarttime()){
                    flag=false;
                    break;
                }
            }
            if(flag==false){
                System.out.println("현재 시간표와 겹치는 시간이므로 수강신청이 불가합니다");
                return;
            }
            //개설교과목 update 트랜젝션 수행 table 락걸음 update 실패시 인원초과출력
            String result=registDAO.update_created_subject_max_stu(selectsubject);
            if(result.equals("실패"))
            {
                System.out.println("수강최대인원초과되어 수강신청이 불가합니다");
                return;
            }
            //수강신청 table insert수행
            AppliedregistDTO na=new AppliedregistDTO();
            na.setFcreatedsubcode(selectsubject);
            na.setFstunum(stunum);
            String inresult=registDAO.insert_current_select_subject(na);
            //신청완료출력
            System.out.println(inresult);
            return;
        }
    }
}
