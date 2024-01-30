package com.example.fitnesstrackerapp.scheduler;

import com.example.fitnesstrackerapp.entity.User;
import com.example.fitnesstrackerapp.repository.UserRepository;
import com.example.fitnesstrackerapp.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReminderService {

    private final UserRepository userRepository;
    private final MailService mailService;

    @Scheduled(cron = "*/25 * * * * *")
    public void sendReminderEmails() {
        log.info("Cron job started to work");
        List<User> usersWithoutWorkouts = userRepository.findUsersWithoutWorkoutsForLast7Days();

        List<String> mailAddresses = usersWithoutWorkouts.stream()
                .map(User::getEmail)
                .filter(e -> StringUtils.isNoneEmpty(e))
                .distinct()
                .toList();
        mailService.sendBulkmail(mailAddresses);
        log.info("Cron job to work finish");

    }
}
