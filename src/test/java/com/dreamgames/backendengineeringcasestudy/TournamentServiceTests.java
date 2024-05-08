package com.dreamgames.backendengineeringcasestudy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.mock.mockito.MockBean;

import com.dreamgames.Country;

@SpringBootTest
class BackendEngineeringCaseStudyApplicationTests {

    @Autowired
    private TournamentService tournamentService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TournamentRepository tournamentRepository;

    @MockBean
    private TournamentGroupRepository groupRepository;

    @MockBean
    private TournamentParticipantRepository participantRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testEnterTournament_UserCanEnter() {
        // Setup
        User user = new User(1L, 25, 2000, Country.UNITED_STATES);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        TournamentGroup group = new TournamentGroup();
        when(groupRepository.findAvailableGroup()).thenReturn(Optional.of(group));

        // Execution
        TournamentGroup result = tournamentService.enterTournament(1L);

        // Verification
        assertNotNull(result);
        verify(participantRepository).save(any(TournamentParticipant.class));
    }
}
