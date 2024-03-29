package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    @Autowired
    AdminConfig adminConfig;
    @Autowired
    private CompanyConfig companyConfig;
    @Autowired
    private DbService dbService;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your task");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Applications allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit_website");
        context.setVariable("admin_name",adminConfig.getAdminName());
        context.setVariable("goodbye_message","Best regards");
        context.setVariable("company_name",companyConfig.getCompanyName());
        context.setVariable("company_goal",companyConfig.getCompanyGoal());
        context.setVariable("company_mail",companyConfig.getCompanyMail());
        context.setVariable("company_phone",companyConfig.getCompanyPhone());
        context.setVariable("show_button",false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
    public String countTasksEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your task");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Applications allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit_website");
        context.setVariable("admin_name",adminConfig.getAdminName());
        context.setVariable("goodbye_message","Best regards");
        context.setVariable("company_name",companyConfig.getCompanyName());
        context.setVariable("company_goal",companyConfig.getCompanyGoal());
        context.setVariable("company_mail",companyConfig.getCompanyMail());
        context.setVariable("company_phone",companyConfig.getCompanyPhone());
        context.setVariable("show_button",false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        context.setVariable("quantity_tasks",dbService.getCountTasks());
        return templateEngine.process("mail/info-quantity-tasks-mail", context);
    }


}
