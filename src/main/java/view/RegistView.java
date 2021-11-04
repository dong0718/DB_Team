package view;

import persistence.DTO.CreatedsubjectDTO;

import java.util.List;

public class RegistView {

    public void print_stu_subject(List<CreatedsubjectDTO> r){
        if(r.size()==0){
            System.out.println("수강신청한 과목이 없습니다");
        }
        else {
            System.out.println("현재 학생의 수강신청 정보 목록");
            r.stream().forEach(v -> System.out.println("toString()=" + v.toString()));
        }
    }
}
