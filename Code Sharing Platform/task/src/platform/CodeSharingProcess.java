package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.models.Code;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CodeSharingProcess {
    private final CodeSharingHelper helper;

    @Autowired
    public CodeSharingProcess(CodeSharingHelper helper) {
        this.helper = helper;
    }

    @GetMapping(path = "/code/{id}")
    public ModelAndView getCodeWithHtml(@PathVariable UUID id, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("codeReadHtml");
        Optional<Code> code = helper.getCode(id);
        if(code.isPresent()){
            System.out.println("getHtml, Id " + id.toString() + " views " + code.get().getViews() + " time " + code.get().getTime() );
            response.setStatus(HttpServletResponse.SC_OK);
            modelAndView.addObject("code", code.get());
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            modelAndView.addObject("code", Code.DEFAULT_CODE);
        }
        return modelAndView;
    }

    @GetMapping(path = "/api/code/{id}")
    public Code getCodeWithJSON(@PathVariable UUID id, HttpServletResponse response) {
        Optional<Code> code = helper.getCode(id);
        if(code.isPresent()){
            System.out.println("getJson, Id " + id.toString() + " views " + code.get().getViews() + " time " + code.get().getTime() );
            response.setStatus(HttpServletResponse.SC_OK);
            return code.get();
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return Code.DEFAULT_CODE;
        }
    }

    @PostMapping(value = "/api/code/new", consumes = "application/json")
    public String postCodeWithJSON(@RequestBody Code code) {
        System.out.println("postJson, views " + code.getViews() + " time " + code.getTime());
        code.setTimeRestricted();
        code.setViewsRestricted();
        return helper.addCode(code);
    }

    @GetMapping(path = "/code/new")
    public ModelAndView getHtmlCodeForm() {
        return new ModelAndView("codeWriteHtml");
    }

    @GetMapping(path = "/code/latest")
    public ModelAndView getCodeLatestWithHtml() {
        ModelAndView modelAndView = new ModelAndView("latestCodes");
        modelAndView.addObject("codes", helper.getLatestCodes());
        return modelAndView;
    }

    @GetMapping(path = "api/code/latest")
    public List<Code> getCodeLatestWithJSON() {
        return helper.getLatestCodes();
    }
}
