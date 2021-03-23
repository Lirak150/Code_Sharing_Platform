package platform;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import platform.models.Code;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CodeSharingHelper {

    private final CodeRepository codeRepository;

    @Autowired
    public CodeSharingHelper(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;

        setConfiguration();
    }

    public Optional<Code> getCode(UUID id) {
        Optional<Code> opCode = codeRepository.findById(id);
        if (opCode.isEmpty()) {
            return Optional.empty();
        }
        opCode.ifPresent(code -> {
            code.setViewsRestricted();
            code.setTimeRestricted();
        });
        Code curCode = opCode.get();
        opCode = processCode(curCode);
        return opCode;
    }

    private Optional<Code> processCode(Code code) {
        boolean shouldBeDeleted = false;

        if (code.isViewsRestricted()) {
            code.setViews(code.getViews() - 1);
            if (code.getViews() == 0) {
                shouldBeDeleted = true;
            }
        }
        if (!shouldBeDeleted) {
            codeRepository.save(code);
        }

        if (code.isTimeRestricted()) {
            long differenceInSeconds = ChronoUnit.SECONDS.between(code.getDateTime(), LocalDateTime.now());
            if (differenceInSeconds > code.getTime()) {
                codeRepository.deleteById(code.getId());
                return Optional.empty();
            }
            if (differenceInSeconds == code.getTime()) {
                shouldBeDeleted = true;
            }
            code.setTime(code.getTime() - differenceInSeconds);
        }
        if (shouldBeDeleted) {
            codeRepository.deleteById(code.getId());
        }
        return Optional.of(code);
    }

    public String getJSONWithNewId(UUID id) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("id", id.toString());
        return node.toString();
    }

    public String addCode(Code code) {
        Code c = codeRepository.save(code);
        return getJSONWithNewId(c.getId());
    }

    public List<Code> getLatestCodes() {
        return codeRepository.findFirst10ByTimeEqualsAndViewsEqualsOrderByDateTimeDesc(0, 0);
    }

    private void setConfiguration() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        try {
            cfg.setDirectoryForTemplateLoading(new File("./Code Sharing Platform/task/out/production/resources/templates"));
        } catch (IOException e) {
            try {
                cfg.setDirectoryForTemplateLoading(new File("./out/production/resources/templates"));
            } catch (IOException ex) {
                System.out.println("No templates");
                ex.printStackTrace();
                e.printStackTrace();

            }
        }

        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
    }
}
