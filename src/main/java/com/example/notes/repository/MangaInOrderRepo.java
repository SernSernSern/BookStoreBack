package com.example.notes.repository;

import com.example.notes.model.MangaInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MangaInOrderRepo extends JpaRepository<MangaInOrder, Long> {
}
