package service;

import dto.LoanReport;
import entities.Book;
import entities.Member;

import java.time.LocalDate;

public class LoanReportService {

    public LoanReport generateReport(
            int loanId,
            Book book,
            Member member,
            LocalDate loanDate,
            LocalDate dueDate,
            double fine
    ) {
        return new LoanReport.Builder()
                .loanId(loanId)
                .bookTitle(book.getTitle())
                .memberName(member.getName())
                .loanDate(loanDate)
                .dueDate(dueDate)
                .fine(fine)
                .build();
    }
}

