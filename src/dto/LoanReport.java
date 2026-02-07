package dto;

import java.time.LocalDate;

public class LoanReport {

    private final int loanId;
    private final String bookTitle;
    private final String memberName;
    private final LocalDate loanDate;
    private final LocalDate dueDate;
    private final double fine;

    private LoanReport(Builder builder) {
        this.loanId = builder.loanId;
        this.bookTitle = builder.bookTitle;
        this.memberName = builder.memberName;
        this.loanDate = builder.loanDate;
        this.dueDate = builder.dueDate;
        this.fine = builder.fine;
    }

    public static class Builder {
        private int loanId;
        private String bookTitle;
        private String memberName;
        private LocalDate loanDate;
        private LocalDate dueDate;
        private double fine;

        public Builder loanId(int loanId) {
            this.loanId = loanId;
            return this;
        }

        public Builder bookTitle(String bookTitle) {
            this.bookTitle = bookTitle;
            return this;
        }

        public Builder memberName(String memberName) {
            this.memberName = memberName;
            return this;
        }

        public Builder loanDate(LocalDate loanDate) {
            this.loanDate = loanDate;
            return this;
        }

        public Builder dueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder fine(double fine) {
            this.fine = fine;
            return this;
        }

        public LoanReport build() {
            return new LoanReport(this);
        }
    }

    @Override
    public String toString() {
        return """
                Loan Report:
                Loan ID: %d
                Book: %s
                Member: %s
                Loan Date: %s
                Due Date: %s
                Fine: %.2f
                """.formatted(loanId, bookTitle, memberName, loanDate, dueDate, fine);
    }
}