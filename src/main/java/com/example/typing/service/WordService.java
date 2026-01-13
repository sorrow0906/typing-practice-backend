package com.example.typing.service;

import com.example.typing.dto.WordRequest;
import com.example.typing.dto.WordResponse;
import com.example.typing.entity.User;
import com.example.typing.entity.Word;
import com.example.typing.repository.UserRepository;
import com.example.typing.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Transactional
    public WordResponse addWord(WordRequest request) {
        User user = getCurrentUser();
        Word word = Word.builder()
                .english(request.getEnglish())
                .meaning(request.getMeaning())
                .user(user)
                .build();

        Word savedWord = wordRepository.save(word);
        return mapToResponse(savedWord);
    }

    @Transactional(readOnly = true)
    public List<WordResponse> getMyWords() {
        User user = getCurrentUser();
        List<Word> words = wordRepository.findByUserId(user.getId());
        return words.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteWord(Long id) {
        User user = getCurrentUser();
        Word word = wordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Word not found"));

        if (!word.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Not authorized to delete this word");
        }

        wordRepository.delete(word);
    }

    private WordResponse mapToResponse(Word word) {
        return WordResponse.builder()
                .id(word.getId())
                .english(word.getEnglish())
                .meaning(word.getMeaning())
                .build();
    }
}
