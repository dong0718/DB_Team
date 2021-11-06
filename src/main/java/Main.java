import persistence.DAO.RegistDAO;
import persistence.DTO.CreatedsubjectDTO;
import persistence.MybatisConnectionFactory;
import service.RegistService;
import view.RegistView;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RegistDAO registDAO=new RegistDAO(MybatisConnectionFactory.getSqlSessionFactory());
        RegistService registService=new RegistService(registDAO);
        RegistView registView= new RegistView();
        System.out.println("원하는 대분류 기능 선택");
        System.out.println("1.학생 crd 2.교과목 crd 3.개설 교과목 crd,수강신청기간변경  4.수강신청관련");
        Scanner sc= new Scanner(System.in);

        int startmenu=sc.nextInt();

        switch (startmenu)
        {
            case 1:
                System.out.println("원하는 소분류 기능 선택");
                int firstmenu=sc.nextInt();
                switch (firstmenu)
                {

                }
                break;
            case 2:

                System.out.println("원하는 소분류 기능 선택");
                System.out.println("1.전체조회  2.학년별조회  3.교과목추가  4.교과목명변경");
                SubjectDAO subjectDAO = new SubjectDAO(MybatisConnectionFactory.getSqlSessionFactory());
                int secondmenu=sc.nextInt();
                switch (secondmenu)
                {
                    case 1:
                        List<SubjectDTO> subjectDTOS1 = subjectDAO.select_all();
                        System.out.println("< 전체 교과목 조회 >");
                        for(SubjectDTO dto:subjectDTOS1){
                            System.out.println(dto.toString());
                        }
                        System.out.println();
                        break;
                    case 2:
                        int grade = 2; //조회할 학년 입력
                        List<SubjectDTO> subjectDTOS2 = subjectDAO.select_by_grade(grade);
                        System.out.println("< 해당 학년 교과목 조회 (선택된 학년 : " + grade + "학년) >");
                        for(SubjectDTO dto:subjectDTOS2){
                            System.out.println(dto.toString());
                        }
                        System.out.println();
                        break;

                    case 3:
                        SubjectDTO subjectDTOS3 = new SubjectDTO();
                        subjectDTOS3.setSubcode("qwer20");  //추가할 과목의 과목코드
                        subjectDTOS3.setSubname("testsub"); //추가할 과목의 과목명
                        subjectDTOS3.setSubinfo("testinfo"); //추가할 과목의 과목정보
                        subjectDTOS3.setSubgrade(2); //추가할 과목의 학년
                        System.out.println("추가됨 : " + subjectDTOS3.toString());
                        subjectDAO.insert_subject(subjectDTOS3);
                        break;

                    case 4:
                        SubjectDTO subjectDTOS4 = new SubjectDTO();
                        subjectDTOS4.setSubcode("qwer2");  //과목명을 변경할 과목의 과목코드
                        subjectDTOS4.setSubname("changed1");  //변경할 과목명
                        System.out.println("과목명 변경됨 : " + subjectDTOS4.toString());
                        subjectDAO.update_subname(subjectDTOS4);
                        break;
                }
                break;
            case 3:
                System.out.println("원하는 소분류 기능 선택");
                int thirdmenu=sc.nextInt();
                switch (thirdmenu)
                {

                }
                break;
            case 4:
                System.out.println("원하는 소분류 기능 선택");
                System.out.println("1.수강신청2.수강조회3.수강삭제");
                System.out.println("교과목 수강신청학생목록조회");
                int fourthmenu=sc.nextInt();
                String id="kumid"; String password="kumpass";
                switch (fourthmenu)
                {
                    case 1:
                        //학생아이디,비밀번호 입력가정
                        System.out.println("아이디로"+id+"입력됨");
                        System.out.println("비밀번호로"+password+"입력됨");
                        registService.regist(id,password);
                        System.out.println("신청서비스종료");
                        break;
                    case 2:
                        //학생아이디,비밀번호 입력가정
                        System.out.println("아이디로"+id+"입력됨");
                        System.out.println("비밀번호로"+password+"입력됨");
                        List<CreatedsubjectDTO> r=registService.getmysubject(id,password);
                        registView.print_stu_subject(r);
                        System.out.println("조회서비스 종료");
                }
                break;
        }
    }

}
