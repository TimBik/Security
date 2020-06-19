package ru.spring.files.service;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.Map;

public class MessageCreateImpl implements MessageCreate {
    @Autowired
    FreeMarkerConfigurer cfg;
    @Override
    public String createMessageFtl(String name, Map<String, Object> root) {
        try {

            Template t = cfg.getConfiguration().getTemplate(name);
            return FreeMarkerTemplateUtils.processTemplateIntoString(t, root);
        } catch (IOException | TemplateException e) {
            throw new IllegalStateException(e);
        }
    }
}
