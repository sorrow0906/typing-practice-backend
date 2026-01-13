package com.example.typing.controller;

import com.example.typing.dto.WordRequest;
import com.example.typing.dto.WordResponse;
import com.example.typing.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/words")
@RequiredArgsConstructor
public class WordController {

    private final WordService wordService;

    @GetMapping
    public ResponseEntity<List<WordResponse>> getMyWords() {
        return ResponseEntity.ok(wordService.getMyWords());
    }

    @PostMapping
    public ResponseEntity<WordResponse> addWord(@RequestBody WordRequest request) {
        return ResponseEntity.ok(wordService.addWord(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWord(@PathVariable Long id) {
        wordService.deleteWord(id);
        return ResponseEntity.ok().build();
    }
}
