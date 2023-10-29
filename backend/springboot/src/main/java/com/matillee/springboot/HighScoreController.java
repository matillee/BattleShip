package com.matillee.springboot;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HighScoreController {

	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/highscore")
	public HighScore highScore(@RequestParam(value = "name", defaultValue = "Player Name") String name) {
		return new HighScore(counter.incrementAndGet(), 32, name);
	}
}
