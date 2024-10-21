package io.vikunja.utils;

import com.github.javafaker.Faker;
import io.vikunja.api.models.labels.addToTask.validData.AddLabelToTaskBodyModel;
import io.vikunja.api.models.labels.createLabel.CreateLabelBodyModel;
import io.vikunja.api.models.login.LoginBodyModel;
import io.vikunja.api.models.project.CreateProjectBodyModel;
import io.vikunja.api.models.registration.RegistrationBodyModel;
import io.vikunja.api.models.task.CreateTaskBodyModel;
import io.vikunja.api.models.team.CreateTeamBodyModel;
import io.vikunja.api.models.token.createToken.invalidData.CreateTokenWithInvalidExpDateBodyModel;
import io.vikunja.api.models.token.createToken.validData.CreateTokenBodyModel;
import io.vikunja.drivers.ConfigDriver;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static io.vikunja.data.web.TaskPriority.*;

public class TestData {
    private final Faker faker = new Faker();

    public final String validUsername = getRandomValidUsername(),
            validEmail = getRandomValidEmail(),
            validPassword = getRandomValidPassword(),
            unregisteredUsername = faker.name().username() + "_UNREGISTERED",
            unregisteredPassword = faker.internet().password(8, 72) + "_UNREGISTERED",
            invalidShortPassword = faker.internet().password(1, 7),
            invalidLongPassword = faker.internet().password(73, 1000),
            projectName = faker.harryPotter().character().replaceAll(" ", ""),
            taskName = faker.funnyName().name() + " Task",
            teamName = faker.harryPotter().house(),
            labelName = faker.app().name(),
            tokenTitle = faker.backToTheFuture().character(),
            validTokenExpTime = getRandomDateTimeInFuture(),
            existUsername = ConfigDriver.getAuthConfig().vikunjaUsername(),
            existPassword = ConfigDriver.getAuthConfig().vikunjaPassword(),
            existServerAddress = ConfigDriver.getAuthConfig().vikunjaServerAddress(),
            currentDay = getCurrentDate(),
            taskTitleAdditional = " " + getRandomNumberBetween(5, 10),
            taskPriority = getTaskPriority();

    public final int invalidTokenExpTime = getRandomNumber(),
            nonExistingTokenId = getRandomNumberBetween(100, 1000),
            nonExistingLabelId = getRandomNumberBetween(60, 10000),
            numberOfLabels = getRandomNumberBetween(2, 5);

    public final RegistrationBodyModel regData = RegistrationBodyModel.builder()
            .email(getRandomValidEmail())
            .password(getRandomValidPassword())
            .username(getRandomValidUsername())
            .build();

    public final LoginBodyModel credentials = LoginBodyModel.builder()
            .password(existPassword)
            .username(existUsername)
            .build();

    public final CreateProjectBodyModel projectData = CreateProjectBodyModel.builder()
            .title(projectName)
            .build();

    public final CreateTaskBodyModel taskData = CreateTaskBodyModel.builder()
            .title(taskName)
            .build();

    public final CreateTeamBodyModel teamData = CreateTeamBodyModel.builder()
            .name(teamName)
            .build();

    public final CreateLabelBodyModel labelData = CreateLabelBodyModel.builder()
            .title(labelName)
            .build();

    public final AddLabelToTaskBodyModel labelNonExistingId = AddLabelToTaskBodyModel.builder()
            .labelId(nonExistingLabelId)
            .build();

    private String getRandomValidUsername() {
        return faker.name().firstName().toLowerCase() +
                faker.number().numberBetween(1, 1000);
    }

    private String getRandomValidEmail() {
        return faker.internet().emailAddress();
    }

    private String getRandomValidPassword() {
        return faker.internet().password(8, 72);
    }

    private String getRandomDateTimeInFuture() {
        Date randomDate = faker.date().future(365, java.util.concurrent.TimeUnit.DAYS);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(randomDate);
    }

    private int getRandomNumber() {
        return faker.number().randomDigit();
    }

    private int getRandomNumberBetween(int firstNumber, int secondNumber) {
        return faker.number().numberBetween(firstNumber, secondNumber);
    }

    private final Map<String, List<String>> propertiesMap = Map.of(
            "filters", List.of("create", "delete", "read_one", "update"),
            "labels", List.of("create", "delete", "read_all", "read_one", "update"),
            "teams", List.of("create", "delete", "read_all", "read_one", "update")
    );

    private String getRandomProperty() {
        Set<String> keysSet = propertiesMap.keySet(); // Получаем Set ключей
        String[] keys = keysSet.toArray(new String[0]);
        return faker.options().option(keys);
    }

    private List<String> getRandomValues(String tokenProperty) {
        List<String> propertyValues = new ArrayList<>(propertiesMap.get(tokenProperty));
        int numberOfValues = getRandomNumberBetween(1, propertyValues.size());
        return propertyValues.subList(0, numberOfValues);
    }

    public final String tokenProperty = getRandomProperty();
    public final List<String> tokenPropertyValueList = getRandomValues(tokenProperty);

    public final CreateTokenBodyModel validTokenData = CreateTokenBodyModel.builder()
            .title(tokenTitle)
            .permissions(Map.of(tokenProperty, tokenPropertyValueList))
            .expiresAt(validTokenExpTime)
            .build();

    public final CreateTokenWithInvalidExpDateBodyModel invalidExpTimeTokenData = CreateTokenWithInvalidExpDateBodyModel
            .builder()
            .title(tokenTitle)
            .permissions(Map.of(tokenProperty, tokenPropertyValueList))
            .expiresAt(invalidTokenExpTime)
            .build();

    private String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        return dtf.format(localDate);
    }

    private String getTaskPriority() {
        String[] priorities = {
                LOW_PRIORITY.priority,
                MEDIUM_PRIORITY.priority,
                HIGH_PRIORITY.priority,
                URGENT_PRIORITY.priority,
                DO_NOW_PRIORITY.priority
        };
        return faker.options().option(priorities);
    }
}
