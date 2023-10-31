package at.fh.js.ims.quizappapp

class QuestionCounter {
    private var correctCount = 0
    private var falseCount = 0
    private var totalCount = 0
    

    fun incrementCorrectCount() {
        correctCount++
    }

    fun incrementFalseCount() {
        falseCount++
    }

    fun incrementTotalCount() {
        totalCount++
    }

    fun getCorrectCount(): Int {
        return correctCount
    }

    fun getFalseCount(): Int {
        return falseCount
    }

    fun returnTotalCount(): Int {
        return totalCount
    }

    fun increaseTotalCount() {
        totalCount++
    }

}
