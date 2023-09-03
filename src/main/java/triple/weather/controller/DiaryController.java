package triple.weather.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import triple.weather.domain.Diary;
import triple.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @Operation(summary = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장", description = "DB에 저장할 수 있습니다.")
    @PostMapping("/create/diary")
    void createDiary(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) @Parameter(name = "date", description = "일기를 저장할 날짜 ex) 2020-02-02") LocalDate date, @RequestBody String text) {
        diaryService.createDiary(date, text);
    }

    @Operation(summary = "특정 날짜를 이용하여 해당 날짜의 DB를 조회", description = "특정 날짜의 DB를 조회할 수 있습니다.")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) @Parameter(name = "date", description = "조회할 날짜 ex) 2020-02-02") LocalDate date) {
        diaryService.readDiary(date);
        return diaryService.readDiary(date);
    }

    @Operation(summary = "특정 날짜를 이용하여 해당 날짜 사이 기간의 DB를 조회", description = "특정 기간의 DB를 조회할 수 있습니다.")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) @Parameter(name = "startDate", description = "조회할 기간의 시작 날 ex) 2020-02-02") LocalDate startDate,
                            @RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) @Parameter(name = "endDate", description = "조회할 기간의 마지막 날 ex) 2020-02-02") LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @Operation(summary = "특정 날짜의 일기 DB를 수정", description = "특정 날짜의 DB를 수정할 수 있습니다.")
    @PutMapping("/update/diary")
    void updateDiary(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) @Parameter(name = "date", description = "일기를 수정할 날짜 ex) 2020-02-02") LocalDate date, @RequestBody String text) {
        diaryService.updateDiary(date, text);
    }

    @Operation(summary = "특정 날짜의 일기 DB를 삭제", description = "특정 날짜의 DB를 삭제할 수 있습니다.")
    @DeleteMapping("/delete/diary")
    void deleteDiary(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) @Parameter(name = "date", description = "일기를 삭제할 날짜 ex) 2020-02-02") LocalDate date) {
        diaryService.deleteDiary(date);
    }
}
