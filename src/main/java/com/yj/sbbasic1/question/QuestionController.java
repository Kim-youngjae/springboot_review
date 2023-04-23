package com.yj.sbbasic1.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/list")
    //    @ResponseBody
    public String showList(Model model) {
        List<Question> questionList = this.questionService.getList(); // 가져오면 안되냐고 서비스에 요청

        model.addAttribute("questionList", questionList);

        return "question_list";
    }

    @GetMapping(value = "/detail/{id}")
        public String showDetail(Model model, @PathVariable("id") Integer id) {

        Question question = this.questionService.getQuestion(id);

        model.addAttribute("question", question);

        return "question_detail";
    }
}
