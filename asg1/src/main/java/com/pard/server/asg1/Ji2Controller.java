package com.pard.server.asg1;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class Ji2Controller {
    // 값을 입력 안해서 get에서 정보가 없음을 알려주고 post에서는 변수를 넣으라고 안내
    @GetMapping("/")
    public String getUsers1(){
        return "아직 아무것도 입력되어 있지 않습니다. 예시 : /add?name=이름&num=학번&major=전공";
    }
    //변수를 넣어서 get,patch,delete하는 모습 //http://localhost:8080/2?name1=binjiseong&num1=22100349&age1=24&major=ComputerScience
    @PostMapping("/post")
    public String PostUser2(@RequestParam(required = false, defaultValue ="파디") String name,@RequestParam(required = false, defaultValue ="99999999") int num,@RequestParam(required = false, defaultValue ="##전공") String major){
        return "userName : "+ name +"\n"+ "schoolNum : "+ num +"\n"+ "major : "+ major + "\n추가했습니다!";
    }
    @GetMapping("/get")
    public String GetUser2(){
        return "userName : "+ "binjiseong" +" "+ "schoolNum : "+ "22100349" +" "+ "major : "+ "ComputerScience";
    }
    //전과하는 경우 많아서 major가 많이 바뀜
    @PatchMapping("/patch")
    public String PatchUser2(@RequestParam(required = false, defaultValue ="99999999") int num,@RequestParam(required = false, defaultValue ="##전공") String major){
        return num + "님 전공이 " + major + "로 변경되었습니다";
    }
    @DeleteMapping("/delete")
    public String DeleteUser2(@RequestParam(required = false, defaultValue ="파디") String name,@RequestParam(required = false, defaultValue ="99999999") int num){
        return "userName : "+ name +" "+ "schoolNum : "+ num +" " + "삭제완료했습니다!";
    }

}